package com.softcell.datascience.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ElasticSearchConfig {
    private String clusterName;
    private String[] nodes;
    private String indexDoc;
}
