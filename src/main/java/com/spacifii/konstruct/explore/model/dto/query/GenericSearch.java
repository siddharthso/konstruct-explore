package com.spacifii.konstruct.explore.model.dto.query;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GenericSearch {
    private Map<String, Where> where;

    private List<String> requiredKeys;

    public Map<String, Where> getWhere() {
        if (where == null) {
            where = new LinkedHashMap<>();
        }
        return where;
    }

    public void setWhere(Map<String, Where> where) {
        this.where = where;
    }

    public List<String> getRequiredKeys() {
        return requiredKeys;
    }

    public void setRequiredKeys(List<String> requiredKeys) {
        this.requiredKeys = requiredKeys;
    }
}
