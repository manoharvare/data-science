package com.softcell.datascience.model.request.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bucket {
    private String key;
    private long from;
    private long to;
}
