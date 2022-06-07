package com.spacifii.konstruct.explore.model.dto.query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QueryResult implements Serializable

{
    private static final long serialVersionUID = -3852641380005011201L;

    private List<QueryData> queryDatas = new ArrayList<>();

    public QueryResult() {
        super();
    }

    public QueryResult(List<QueryData> queryDatas) {
        super();
        this.queryDatas = queryDatas;
    }

    public List<QueryData> getQueryDatas() {
        return queryDatas;
    }

    public void setQueryDatas(List<QueryData> queryDatas) {
        this.queryDatas = queryDatas;
    }

}
