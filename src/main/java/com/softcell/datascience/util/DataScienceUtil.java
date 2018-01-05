package com.softcell.datascience.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softcell.datascience.config.Config;
import com.softcell.datascience.config.ElasticSearchConfig;
import com.softcell.datascience.model.request.client.Filter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataScienceUtil {

    @Autowired
    private Config config;

    public String getUrl() {
        ElasticSearchConfig elasticSearchConfig = config.elasticSearchConfig();
        return StringUtils.join("http://", elasticSearchConfig.getNodes()[0], "/", elasticSearchConfig.getIndexDoc(), "/_search");
    }

    public String buildJsonString(Object o) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(o);
    }

    public String getValue(String key, List<Filter> filters) {
        String value = null;
        for (Filter filter : filters) {
            if (key.equals(filter.getFieldName())) {
                value = filter.getValue();
                break;
            }
        }
        return value;
    }

}
