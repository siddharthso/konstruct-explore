package com.spacifii.konstruct.explore.model.dto.query;

import java.io.Serializable;

public class QueryData implements Serializable {

    private static final long serialVersionUID = 6174437605496010461L;
    private String key;
    private Object value;

    public QueryData(String key, Object value) {
        super();
        this.key = key;
        this.value = value;
    }

    public QueryData() {
        super();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

}
