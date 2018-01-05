package com.softcell.datascience.service;

import com.softcell.datascience.dao.mongo.QueryStoreRepository;
import com.softcell.datascience.model.datamodel.QueryStore;
import com.softcell.datascience.model.request.client.ClientRequest;
import com.softcell.datascience.util.DataScienceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnalyticsAdminManagerImpl implements AnalyticsAdminManager {

    @Autowired
    private QueryStoreRepository queryStoreRepository;

    @Autowired
    private DataScienceUtil util;

    @Override
    public Boolean saveQuery(ClientRequest clientRequest) {
        validateQuery(clientRequest);
        QueryStore queryStore = buildQueryModel(clientRequest);
        queryStoreRepository.save(queryStore);
        return true;
    }

    private QueryStore buildQueryModel(ClientRequest clientRequest) {
        return QueryStore.builder()
                .institutionId(util.getValue("institutionId", clientRequest.getFilter()))
                .product(util.getValue("product", clientRequest.getFilter()))
                .query(clientRequest)
                .build();
    }

    private boolean validateQuery(ClientRequest clientRequest) {
        return true;
    }
}
