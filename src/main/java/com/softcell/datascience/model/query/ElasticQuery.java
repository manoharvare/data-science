package com.softcell.datascience.model.query;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ElasticQuery {
    @JsonProperty("size")
    private Integer size;
    @JsonProperty("query")
    private Query query;
    @JsonProperty("aggs")
    private Aggregation aggregation;
}
