package com.spacifii.konstruct.explore.model.dto.explore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.spacifii.konstruct.explore.entities.explore.KEYWORD_CATEGORY;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
public class ExploreKeywordRequestDto {

    private String keyword;

    private String keywordDisplay;

    private String description;

    private KEYWORD_CATEGORY keywordCategory;

    private String logoUrl;

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public KEYWORD_CATEGORY getKeywordCategory() {
        return keywordCategory;
    }

    public void setKeywordCategory(KEYWORD_CATEGORY keywordCategory) {
        this.keywordCategory = keywordCategory;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getKeywordDisplay() {
        return keywordDisplay;
    }

    public void setKeywordDisplay(String keywordDisplay) {
        this.keywordDisplay = keywordDisplay;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
