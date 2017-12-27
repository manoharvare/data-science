package com.softcell.datascience.model.response;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TreeView {
    @JsonProperty("name")
    private String name;
    @JsonProperty("parent")
    private String parent;
    @JsonProperty("count")
    private String count;
    private String bucketName;
    @JsonProperty("children")
    private List<TreeView> children;
}
