package com.spacifii.konstruct.explore.model.dto.explore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.spacifii.konstruct.explore.model.dto.query.SearchQuery;
import org.springframework.data.domain.Sort;

import java.util.LinkedHashSet;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
public class FilterRequestDto {
    private int page;
    private int size;
    private Sort.Direction direction = Sort.Direction.DESC;
    private Set<String> sortKeys = new LinkedHashSet<>();
    private SearchQuery searchQuery;


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Sort.Direction getDirection() {
        return direction;
    }

    public void setDirection(Sort.Direction direction) {
        this.direction = direction;
    }

    public Set<String> getSortKeys() {
        return sortKeys;
    }

    public void setSortKeys(Set<String> sortKeys) {
        this.sortKeys = sortKeys;
    }

    public SearchQuery getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(SearchQuery searchQuery) {
        this.searchQuery = searchQuery;
    }
}
