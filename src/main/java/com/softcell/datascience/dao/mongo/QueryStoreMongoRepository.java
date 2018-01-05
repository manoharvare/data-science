package com.softcell.datascience.dao.mongo;

import com.softcell.datascience.model.datamodel.QueryStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class QueryStoreMongoRepository implements QueryStoreRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void save(QueryStore queryStore) {
        mongoTemplate.insert(queryStore);
    }
}
