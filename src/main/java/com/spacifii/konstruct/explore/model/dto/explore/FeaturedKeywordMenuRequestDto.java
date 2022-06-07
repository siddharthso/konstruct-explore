package com.spacifii.konstruct.explore.model.dto.explore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.spacifii.konstruct.explore.entities.explore.KEYWORD_CATEGORY;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonPropertyOrder(alphabetic = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FeaturedKeywordMenuRequestDto {

    KEYWORD_CATEGORY keyword_category;
    Map<String, List<String>> exploreKeywords = new LinkedHashMap<>();

    public KEYWORD_CATEGORY getKeyword_category() {
        return keyword_category;
    }

    public void setKeyword_category(KEYWORD_CATEGORY keyword_category) {
        this.keyword_category = keyword_category;
    }

    public Map<String, List<String>> getExploreKeywords() {
        return exploreKeywords;
    }

    public void setExploreKeywords(Map<String, List<String>> exploreKeywords) {
        this.exploreKeywords = exploreKeywords;
    }
}
