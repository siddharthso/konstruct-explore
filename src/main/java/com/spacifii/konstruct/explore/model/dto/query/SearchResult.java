package com.spacifii.konstruct.explore.model.dto.query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SearchResult implements Serializable {
    private static final long serialVersionUID = 4155825226626885635L;

    private List<QueryResult> queryResults = new ArrayList<>();

    public SearchResult() {
        super();
    }

    public SearchResult(List<QueryResult> queryResults) {
        super();
        this.queryResults = queryResults;
    }

    public List<QueryResult> getQueryResults() {
        return queryResults;
    }

    public void setQueryResults(List<QueryResult> queryResults) {
        this.queryResults = queryResults;
    }


}
