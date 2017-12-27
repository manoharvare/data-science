package com.softcell.datascience.model.query;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RangeBucket {
    private String field;
    private Boolean keyed;
    private List<Range> ranges;
}
