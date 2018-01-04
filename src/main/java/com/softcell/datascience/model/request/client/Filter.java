package com.softcell.datascience.model.request.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Filter {
    private String fieldName;
    private String fieldType;
    private String value;
    private Bucket range;
}
