package com.softcell.datascience.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class Config {

    @Value("${elastic-cluster-name}")
    private String clusterName;

    @Value("${elastic-nodes}")
    private String[] nodes;

    @Value("${elastic-index-type}")
    private String indexDoc;

    public  ElasticSearchConfig elasticSearchConfig(){
        return ElasticSearchConfig.builder()
                .clusterName(clusterName)
                .nodes(nodes)
                .indexDoc(indexDoc)
                .build();
    }
}
