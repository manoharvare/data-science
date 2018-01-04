package com.softcell.datascience.model.request.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/*{
       "data-point-name": "cibilScore",
           "data-point-type": "numeric",
           "level": 2,
           "buckets": [
       {
           "key": "500-600",
               "from": 500,
               "to": 600
       },
       {
           "key": "600-700",
               "from": 600,
               "to": 700
       },
       {
           "key": "700-800",
               "from": 700,
               "to": 800
       }
   ]
   }*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aggregation {
    private String fieldName;
    private String fieldType;
    private Integer level;
    private Integer size;
    private List<Bucket> buckets;
}
