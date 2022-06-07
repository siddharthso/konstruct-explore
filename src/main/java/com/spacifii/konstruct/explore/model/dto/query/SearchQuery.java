package com.spacifii.konstruct.explore.model.dto.query;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchQuery implements Serializable {
    private static final long serialVersionUID = 1124747040876592257L;

    private String globalSearch;

    private Map<String, List<Object>> where;

    private List<String> requiredKeys;

    public Map<String, List<Object>> getWhere() {
        return where;
    }

    public void setWhere(Map<String, List<Object>> where) {
        this.where = where;
    }

    public List<String> getRequiredKeys() {
        return requiredKeys;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getGlobalSearch() {
        return globalSearch;
    }

    public void setGlobalSearch(String globalSearch) {
        this.globalSearch = globalSearch;
    }

    public void setRequiredKeys(List<String> requiredKeys) {
        this.requiredKeys = requiredKeys;
    }
}
