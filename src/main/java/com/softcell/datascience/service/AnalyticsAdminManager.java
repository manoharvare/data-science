package com.softcell.datascience.service;

import com.softcell.datascience.model.request.client.ClientRequest;

public interface AnalyticsAdminManager {
    Boolean saveQuery(ClientRequest clientRequest);
}
