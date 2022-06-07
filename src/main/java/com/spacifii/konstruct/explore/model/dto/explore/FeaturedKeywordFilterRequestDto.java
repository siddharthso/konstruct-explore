package com.spacifii.konstruct.explore.model.dto.explore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.spacifii.konstruct.explore.entities.explore.KEYWORD_CATEGORY;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
public class FeaturedKeywordFilterRequestDto {

    private KEYWORD_CATEGORY keyword_category;
    private List<String> exploreKeywords = new ArrayList<>();

    public KEYWORD_CATEGORY getKeyword_category() {
        return keyword_category;
    }

    public void setKeyword_category(KEYWORD_CATEGORY keyword_category) {
        this.keyword_category = keyword_category;
    }

    public List<String> getExploreKeywords() {
        return exploreKeywords;
    }

    public void setExploreKeywords(List<String> exploreKeywords) {
        this.exploreKeywords = exploreKeywords;
    }
}
