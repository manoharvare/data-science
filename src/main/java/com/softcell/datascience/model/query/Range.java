package com.softcell.datascience.model.query;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Data
@JsonPropertyOrder({
        "key",
        "from",
        "to"
})
public class Range {
    @JsonProperty("key")
    private String key;
    @JsonProperty("from")
    private Long from;
    @JsonProperty("to")
    private Long to;
}

