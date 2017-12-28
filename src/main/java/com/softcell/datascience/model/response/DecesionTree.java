package com.softcell.datascience.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DecesionTree {
    @JsonProperty("name")
    private String name;
    @JsonProperty("parent")
    private String parent;
    @JsonProperty("children")
    private List<TreeView> placeHolder;
}
