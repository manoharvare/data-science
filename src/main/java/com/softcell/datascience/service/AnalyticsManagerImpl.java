package com.softcell.datascience.service;

import com.softcell.datascience.model.request.client.ChaidAnalysisRequest;
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
    public Object doDynamicChaidAnalysis(List<ChaidAnalysisRequest> query) throws IOException {
        List<ChaidAnalysisRequest> sortedRequestObject = builder.doSorting(query);
        String chaidGraphJson = httpTransportationService.postRequest(util.getUrl(), util.buildJsonString(builder.buildChaidQuery(sortedRequestObject)), MediaType.APPLICATION_JSON_UTF8_VALUE.toString());
        Object finalResponse = new Object();
        if(StringUtils.isNotBlank(chaidGraphJson)){
            finalResponse = builder.getParseObject(chaidGraphJson, sortedRequestObject);
        }
        return finalResponse;
    }


}
