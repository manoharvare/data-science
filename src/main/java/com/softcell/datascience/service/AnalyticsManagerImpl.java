package com.softcell.datascience.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.softcell.datascience.model.query.ElasticQuery;
import com.softcell.datascience.model.request.client.Aggregation;
import com.softcell.datascience.model.request.client.ClientRequest;
import com.softcell.datascience.util.DataScienceUtil;
import com.softcell.datascience.util.HttpTransportationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class AnalyticsManagerImpl implements AnalyticsManager {

    @Autowired
    private HttpTransportationService httpTransportationService;

    @Autowired
    private DataScienceUtil util;

    @Autowired
    private QueryBuilder builder;

    @Override
    public Object doChaidAnalysis(Object query) throws IOException {
        return httpTransportationService.postRequest(util.getUrl(), util.buildJsonString(query), MediaType.APPLICATION_JSON_UTF8_VALUE.toString());
    }

    @Override
    public Object doDynamicChaidAnalysis(List<Aggregation> query) throws IOException {
        List<Aggregation> sortedRequestObject = builder.doSorting(query);
        String chaidGraphJson = httpTransportationService.postRequest(util.getUrl(), util.buildJsonString(builder.buildChaidQuery(sortedRequestObject)), MediaType.APPLICATION_JSON_UTF8_VALUE.toString());
        Object finalResponse = new Object();
        if(StringUtils.isNotBlank(chaidGraphJson)){
            finalResponse = builder.getParseObject(chaidGraphJson, sortedRequestObject);
        }
        return finalResponse;
    }

    @Override
    public Object doDynamicChaidAnalysisWithFilter(ClientRequest clientRequest) throws IOException {
        List<Aggregation> sortedRequestObject = builder.doSorting(clientRequest.getAggregations());
        ElasticQuery elasticQuery = builder.buildChaidQuery(clientRequest.getFilter(),sortedRequestObject);
        String response = httpTransportationService.postRequest(util.getUrl(), util.buildJsonString(elasticQuery), MediaType.APPLICATION_JSON_UTF8_VALUE.toString());
        Object finalResponse = new Object();
        if(StringUtils.isNotBlank(response)){
            finalResponse = builder.getParseObject(response, sortedRequestObject);
        }
        return finalResponse;
    }


}
