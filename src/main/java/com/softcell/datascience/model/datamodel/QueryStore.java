package com.softcell.datascience.model.datamodel;

import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class QueryStore {
    @Id
    private String reportId;
    private String institutionId;
    private String product;
    private String reportName;
    private String reportType;
    private Object query;
}
