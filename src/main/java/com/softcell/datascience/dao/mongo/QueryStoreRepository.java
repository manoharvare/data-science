package com.softcell.datascience.dao.mongo;

import com.softcell.datascience.model.datamodel.QueryStore;
import com.softcell.datascience.model.request.client.ClientRequest;

public interface QueryStoreRepository {
    void save(QueryStore queryStore);
}
