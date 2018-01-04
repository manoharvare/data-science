package com.softcell.datascience.service;

import com.softcell.datascience.model.request.client.Aggregation;

import java.io.IOException;
import java.util.List;

public interface AnalyticsManager {
    Object doChaidAnalysis(Object query) throws IOException;

    Object doDynamicChaidAnalysis(List<Aggregation> query) throws IOException;
}
