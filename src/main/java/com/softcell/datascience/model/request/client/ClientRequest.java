package com.softcell.datascience.model.request.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequest {
    private Integer size;
    private List<Filter> filter;
    private List<Aggregation> aggregations;
}
