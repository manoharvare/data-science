package com.softcell.datascience.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.softcell.datascience.model.request.client.Aggregation;
import com.softcell.datascience.model.request.client.ClientRequest;

import java.io.IOException;
import java.util.List;

public interface AnalyticsManager {
    Object doChaidAnalysis(Object query) throws IOException;

    Object doDynamicChaidAnalysis(List<Aggregation> query) throws IOException;

    Object doDynamicChaidAnalysisWithFilter(ClientRequest clientRequest) throws IOException;
}
