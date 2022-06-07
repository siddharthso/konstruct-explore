package com.spacifii.konstruct.explore.entities.explore;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "FEATURED_KEYWORD_FILTER")
public class FeaturedKeywordFilter {


    @Id
    @Field(value = "ID")
    private String id;

    @Field(value = "KEYWORD_CATEGORY")
    private KEYWORD_CATEGORY keyword_category;

    @DBRef
    @Field(value = "EXPLORE_KEYWORDS")
    private List<ExploreKeyword> exploreKeywords = new ArrayList<>();


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public KEYWORD_CATEGORY getKeyword_category() {
        return keyword_category;
    }

    public void setKeyword_category(KEYWORD_CATEGORY keyword_category) {
        this.keyword_category = keyword_category;
    }

    public List<ExploreKeyword> getExploreKeywords() {
        return exploreKeywords;
    }

    public void setExploreKeywords(List<ExploreKeyword> exploreKeywords) {
        this.exploreKeywords = exploreKeywords;
    }
}
