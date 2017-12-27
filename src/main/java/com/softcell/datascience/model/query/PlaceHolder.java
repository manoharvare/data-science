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
public class PlaceHolder {
    @JsonProperty("terms")
    private Term term;
    @JsonProperty("range")
    private RangeBucket range;
    @JsonProperty("aggs")
    private Aggregation aggregations;
}
