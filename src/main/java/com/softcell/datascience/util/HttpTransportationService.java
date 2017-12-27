package com.softcell.datascience.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by yogeshb on 20/3/17.
 */
@Component
public class HttpTransportationService {

    private static final Logger logger = LoggerFactory.getLogger(HttpTransportationService.class);
    private static final String NOT_AVAILABLE = "N/A";
    private final Charset charset = StandardCharsets.UTF_8;
    @Value("${http.connectionTimeout:30000}")
    private int connectionTimeout;
    /**
     * Set the maximum number of concurrent connections per route, which is 2 by default.
     */
    private int MAX_PER_ROUTE = 5;
    /**
     * Set the maximum number of total open connections.
     */
    private int MAX_HTTP_CONNECTION = 10;
    /**
     * validity after inactivity to check httpclients if they are ideal before leasing them out to initiate new
     * request , so this is a hook to validate HttpClient object
     */
    private int validityAfterInactivity = 1000;
    /**
     * Port of the proxy server which the HTTP clients will use, it's optional, goes hand in hand with proxy.
     */
    @Value("${http.proxyPort:-1}")
    private int proxyPort;

    /**
     * IP of the proxy server which the HTTP clients will use, it's optional, goes hand in hand with proxyPort.
     */
    @Value("${http.proxyHost:'N/A'}")
    private String proxyHostAddr;

    /**
     * Proxy host object which is used when a proxyHostAddr and proxyPort are configured.
     */
    private HttpHost proxy;

    @PostConstruct
    public void initIt() throws Exception {
        this.proxy = (NOT_AVAILABLE.equalsIgnoreCase(this.proxyHostAddr) || this.proxyPort <= 0) ? null : new HttpHost(this.proxyHostAddr, this.proxyPort);
    }

    /**
     * Please use this method with caution as in production environment bypassing SSL certificate check
     * is disgraced
     *
     * @param url      uri to be hit in POST request
     * @param postData a map containing key-value representing data to be send in POST request
     * @return string response in case if server send HttpStatus.Ok {@see HttpStatus.Ok}
     * @throws IOException exception if something went wrong while sending post data to server or server
     *                     send any other http status code instead of HttpStatus.Ok
     */
    public String postHttpSSLRequest(final String url, Map<String, String> postData) throws Exception {

        CloseableHttpClient client = getSSLBypassedHttpClosableClient();

        return post(client, url, postData);

    }

    /**
     * this method return default closeable http client provided by apache Http 4.x library
     *
     * @return {@see CloseableHttpClient} client given back this method
     */
    private CloseableHttpClient getDefaultClient() {

        RequestConfig build = RequestConfig.custom()
                .setConnectTimeout(this.connectionTimeout)
                .build();

        return HttpClientBuilder.create()
                .setKeepAliveStrategy(getConnectionKeepAliveStrategy())
                .setDefaultRequestConfig(build)
                .setConnectionManager(getPoolingHttpClientConnectionManager())
                .setProxy(this.proxy)
                .build();

    }


    private CloseableHttpClient getDefaultClientWithCustomHeader(List<Header> headers) {

        RequestConfig build = RequestConfig.custom()
                .setConnectTimeout(this.connectionTimeout)
                .build();

        return HttpClientBuilder.create()
                .setDefaultHeaders(headers)
                .setKeepAliveStrategy(getConnectionKeepAliveStrategy())
                .setDefaultRequestConfig(build)
                .setConnectionManager(getPoolingHttpClientConnectionManager())
                .setProxy(this.proxy)
                .build();

    }


    private DefaultHttpClient getHttpSSLTrustAllCertClient() throws NoSuchAlgorithmException, KeyManagementException {

        //TODO: Remove all deprecated library usages from this method.
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    public void checkClientTrusted(
                            X509Certificate[] certs,
                            String authType) {
                    }

                    public void checkServerTrusted(
                            X509Certificate[] certs,
                            String authType) {
                    }
                }
        };

        SSLContext sc = SSLContext.getInstance("SSL");

        sc.init(null, trustAllCerts, null);

        SSLSocketFactory ssf = new SSLSocketFactory(sc);
        ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        SchemeRegistry registry = new SchemeRegistry();
        registry.register(new Scheme("https", ssf, 80)); //TODO port and schema should be coming from the strings file

        HttpParams params = new BasicHttpParams();
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);
        HttpProtocolParams.setUseExpectContinue(params, false);
        HttpConnectionParams.setConnectionTimeout(params, 30000);
        HttpConnectionParams.setSoTimeout(params, 30000);
        ConnManagerParams.setMaxTotalConnections(params, 100);
        ConnManagerParams.setTimeout(params, 30000);
        ThreadSafeClientConnManager manager = new ThreadSafeClientConnManager(params, registry);

		 /*SSLContextBuilder builder = new SSLContextBuilder();
            builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());*/
            /*SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sc);
            CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(
		            sslsf).build();*/

        DefaultHttpClient httpclient = new DefaultHttpClient(manager, params);
        httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, this.proxy);
        return httpclient;
    }

    /**
     * this method takes no argument and return a ClosableHttpClient instance
     * that will pass self signed certificates , short-circuit the SSL validation
     *
     * @return {@see CloseableHttpClient}
     * @throws Exception generic exception thrown by this method in case something went wrong
     *                   with closable http client
     */
    private CloseableHttpClient getSSLBypassedHttpClosableClient() throws Exception {

        CloseableHttpClient httpClient;

        try {

            httpClient = HttpClients.custom().setConnectionManager(new PoolingHttpClientConnectionManager()).
                    setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE).
                    setSSLContext(new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                        public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                            return true;
                        }
                    }).build())
                    .setProxy(this.proxy)
                    .build();

        } catch (KeyManagementException e) {
            logger.error("KeyManagementException in creating http client instance", e);
            throw new Exception(e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            logger.error("NoSuchAlgorithmException in creating http client instance", e);
            throw new Exception(e.getMessage());
        } catch (KeyStoreException e) {
            logger.error("KeyStoreException in creating http client instance", e);
            throw new Exception(e.getMessage());
        }

        return httpClient;
    }

    /**
     * @param connectionTimeout connection timeout parameter
     *                          i.e how much time your http connection should wait in case of any delay while communicating with
     *                          http server where we are sensing request .
     * @return CloseableHttpClient
     */
    private CloseableHttpClient getHttpClientWithTimeout(int connectionTimeout) {

        RequestConfig build = RequestConfig.custom().setConnectTimeout(connectionTimeout).build();

        return HttpClientBuilder.create()
                .setKeepAliveStrategy(getConnectionKeepAliveStrategy())
                .setDefaultRequestConfig(build)
                .setConnectionManager(getPoolingHttpClientConnectionManager())
                .setProxy(this.proxy)
                .build();
    }

    /**
     * @param url      this represent url to be hit by api
     * @param postData request data used to be send with post request
     * @param timeout  timeout for http connection default {@see connectionTimeout}
     * @return response in string format
     */
    public String postRequest(String url, Map<String, String> postData, int timeout) throws IOException {

        CloseableHttpClient client = getHttpClientWithTimeout(timeout);

        return post(client, url, postData);

    }


    /**
     * @param url      uri to be hit in POST request
     * @param postData a map containing key-value representing data to be send in POST request
     * @return string response in case if server send HttpStatus.Ok {@see HttpStatus.Ok}
     * @throws IOException exception if something went wrong while sending post data to server or server
     *                     send any other http status code instead of HttpStatus.Ok
     */
    public String postRequest(final String url, Map<String, String> postData) throws IOException {

        CloseableHttpClient client = getDefaultClient();

        return post(client, url, postData);

    }

    public String postRequest(final String url, List<NameValuePair> postData, String contentType) throws IOException {

        CloseableHttpClient client = getDefaultClient();

        return post(client, url, postData, contentType);

    }

    private String post(CloseableHttpClient client, final String url, final List<NameValuePair> postData, final String contentType) throws IOException {

        HttpPost httpPost = new HttpPost(url);

        StringEntity entity = new UrlEncodedFormEntity(postData, "UTF-8");

        entity.setContentType(contentType);

        httpPost.setEntity(entity);

        return getResponse(client, url, httpPost, postData);

    }

    private String getResponse(CloseableHttpClient client, String url, HttpPost httpPost, Object postData) throws IOException {
        CloseableHttpResponse closeableHttpResponse = null;

        String response = null;

        try {

            closeableHttpResponse = client.execute(httpPost);

            if (closeableHttpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                response = EntityUtils.toString(closeableHttpResponse.getEntity());
            } else {
                logger.debug("We get following HttpStatusCode {} while sending request with post data {}",
                        closeableHttpResponse.getStatusLine().getStatusCode(), postData);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception caught while sending post request to url {} with data {} ", url, postData);
        } finally {

            if (null != closeableHttpResponse) {
                closeableHttpResponse.close();
            }

            if (null != client) {
                try {
                    client.close();
                } catch (Exception e) {
                    // ignore exception while closing client
                }

            }
        }
        return response;
    }


    /**
     * @param url         uri to be hit in POST request
     * @param postData    consist a json string of post request
     * @param contentType content type to be sent based on third party implementation
     * @return string response in case if server send HttpStatus.Ok {@see HttpStatus.Ok}
     * @throws IOException exception if something went wrong while sending post data to server or server
     *                     send any other http status code instead of HttpStatus.Ok
     */
    public String postRequest(final String url, String postData, String contentType) throws IOException {

        CloseableHttpClient client = getDefaultClient();

        return post(client, url, postData, contentType);

    }


    public Map<Integer, String> postRequest(String url, String requestJson, HashMap<String, String> headerParams, String mediaType) throws IOException {

        List<Header> headers = new ArrayList<>();
        if (null != headerParams) {
            headerParams.forEach((key, value) -> headers.add(new BasicHeader(key, value)));
        }

        CloseableHttpClient client = getDefaultClientWithCustomHeader(headers);

        return post(client, url, requestJson, headerParams, mediaType);
    }

    private Map<Integer, String> post(CloseableHttpClient client, final String url, String requestJson, Map<String, String> headerParams, String mediaType) throws UnsupportedEncodingException {
        if (null == requestJson || (null != headerParams && headerParams.isEmpty()) || StringUtils.isBlank(url)) {
            logger.error("We can't process your post request  url {} with payload as {}  this should not be null", url, requestJson);
        }

        Map<Integer, String> stringMap = new HashMap<>();

        HttpPost httpPost = new HttpPost(StringUtils.trim(url));

        StringEntity se = new StringEntity(requestJson);

        se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, mediaType));

        httpPost.setEntity(se);

        CloseableHttpResponse closeableHttpResponse = null;

        try {

            closeableHttpResponse = client.execute(httpPost);

            String response = EntityUtils.toString(closeableHttpResponse.getEntity());

            stringMap.put(closeableHttpResponse.getStatusLine().getStatusCode(), response);


        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception caught while sending post request to url {} with data {} ", url, requestJson);
        } finally {

            if (null != closeableHttpResponse) {
                try {
                    closeableHttpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (null != client) {
                try {
                    client.close();
                } catch (Exception e) {
                    // ignore exception while closing client
                }

            }
        }
        return stringMap;


    }

    /**
     * @param client   {@see org.apache.http.impl.client.CloseableHttpClient} httpclient
     * @param url      server url at which post request hit
     * @param postData data to be send to http POST request
     * @return string response from this service method
     * @throws IOException In case http post request fails it must throw IOException
     */
    private String post(CloseableHttpClient client, final String url, Map<String, String> postData) throws IOException {

        if (null == postData) {
            logger.error("We can't process your post request  url {} with payload as {}  this should not be null", url, postData);
        }


        List<BasicNameValuePair> postParameters = postData.keySet().parallelStream()
                .map(key -> new BasicNameValuePair(key, postData.get(key)))
                .collect(Collectors.toList());

        HttpPost httpPost = new HttpPost(url);

        httpPost.setEntity(new UrlEncodedFormEntity(postParameters, charset));

        CloseableHttpResponse closeableHttpResponse = null;

        String response = null;

        try {

            closeableHttpResponse = client.execute(httpPost);

            if (closeableHttpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                response = EntityUtils.toString(closeableHttpResponse.getEntity());
            } else {
                logger.debug("We get following HttpStatusCode {} while sending request with post data {} to url [{}]",
                        closeableHttpResponse.getStatusLine().getStatusCode(), postData, url);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception caught while sending post request to url {} with data {} with probable cause {}", url, postData, e.getMessage());
        } finally {

            if (null != closeableHttpResponse) {
                closeableHttpResponse.close();
            }

            if (null != client) {
                try {
                    client.close();
                } catch (Exception e) {
                    // ignore exception while closing client
                }

            }
        }
        return response;
    }


    /**
     * @param client      {@see org.apache.http.impl.client.CloseableHttpClient} httpclient
     * @param url         server url at which post request hit
     * @param postData    data to be send to http POST request
     * @param contentType contentType content type to be sent based on third party implementation
     * @return string response from this service method
     * @throws IOException In case http post request fails it must throw IOException
     */
    private String post(CloseableHttpClient client, final String url, final String postData, final String contentType) throws IOException {

        HttpPost httpPost = new HttpPost(url);

        StringEntity se = new StringEntity(postData);

        se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, contentType));

        httpPost.setEntity(se);

        return getResponse(client, url, httpPost, postData);

    }

    /**
     * a method to fetch PoolingHttpClientConnectionManager  for making a Http client connection reusable
     * with customized manager having validityAfterInactivity flag on with default
     *
     * @return {@see PoolingHttpClientConnectionManager}
     */
    private PoolingHttpClientConnectionManager getPoolingHttpClientConnectionManager() {
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();

        poolingHttpClientConnectionManager.setValidateAfterInactivity(validityAfterInactivity);

        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(MAX_PER_ROUTE);

        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(MAX_HTTP_CONNECTION);


        return poolingHttpClientConnectionManager;
    }

    /**
     * If the Keep-Alive header is not present in the response, HttpClient assumes the connection can be kept alive indefinitely
     * To get around this, and be able to manage dead connections we need a customized strategy implementation
     * and build it into the HttpClient
     *
     * @return {@see ConnectionKeepAliveStrategy}
     */
    private ConnectionKeepAliveStrategy getConnectionKeepAliveStrategy() {
        return (response, context) -> {
            HeaderElementIterator it = new BasicHeaderElementIterator
                    (response.headerIterator(HTTP.CONN_KEEP_ALIVE));
            while (it.hasNext()) {
                HeaderElement he = it.nextElement();
                String param = he.getName();
                String value = he.getValue();
                if (value != null && param.equalsIgnoreCase
                        ("timeout")) {
                    return Long.parseLong(value) * 1000;
                }
            }
            return 5 * 1000;
        };
    }

    /**
     * @param url
     * @param postData
     * @param attachmentFile
     * @return
     * @throws IOException
     */
    public Boolean postMultipartRequest(final String url, Map<String, String> postData, final File attachmentFile) throws IOException {

        HttpPost httpPost = new HttpPost(url);

        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();

        for (String key : postData.keySet()) {

            multipartEntityBuilder.addTextBody(key, postData.get(key));
        }

        multipartEntityBuilder.addBinaryBody("file", attachmentFile);

        HttpEntity httpEntity = multipartEntityBuilder.build();

        httpPost.setEntity(httpEntity);

        CloseableHttpClient defaultClient = getDefaultClient();

        CloseableHttpResponse httpResponse;

        try {

            httpResponse = defaultClient.execute(httpPost);

        } catch (IOException e) {
            logger.error("We encounter problem while uploading file to url {} ", url);
            e.printStackTrace();
            throw new IOException("Exception while uploading file to url " + url);
        }


        if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
            return true;


        return false;

    }

    public Boolean checkServiceHeartBeat(final String url) {

        CloseableHttpClient defaultClient = getDefaultClient();

        HttpGet httpGet = new HttpGet(url);

        try {

            CloseableHttpResponse response = defaultClient.execute(httpGet);

            if (null != response && null != response.getStatusLine()) {
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
                    return true;
            }


        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occurred while checking ping  service with base url [{}] ", url);
        } finally {

            try {

                defaultClient.close();

            } catch (IOException e) {
            }
        }

        return false;


    }

    public String postHttpSSLRequest(final String url, final String postData, final String contentType) throws Exception {

        CloseableHttpClient client = getHttpSSLTrustAllCertClient();

        return post(client, url, postData, contentType);
    }


}
