package com.spacifii.konstruct.explore.model.dto.recentlyspacified;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.spacifii.konstruct.explore.entities.recentlyspacified.SearchProfileBy;


/**
 * This Dto class is used to handle createNewRecentlySpacifiedCustomerReview
 */
@JsonPropertyOrder(alphabetic = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateNewRecentlySpacifiedCustomerReviewDto {
    private SearchProfileBy searchProfileBy;
    private String searchProfileValue;
    private String projectId;
    private String userType;

    public SearchProfileBy getSearchProfileBy() {
        return searchProfileBy;
    }

    public void setSearchProfileBy(SearchProfileBy searchProfileBy) {
        this.searchProfileBy = searchProfileBy;
    }

    public String getSearchProfileValue() {
        return searchProfileValue;
    }

    public void setSearchProfileValue(String searchProfileValue) {
        this.searchProfileValue = searchProfileValue;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
