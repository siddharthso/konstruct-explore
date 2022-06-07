package com.spacifii.konstruct.explore.entities.explore;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.*;

@Document(collection = "FEATURED_KEYWORD_MENU")
public class FeaturedKeywordMenu {


    @Id
    @Field(value = "ID")
    private String id;

    @Field(value = "KEYWORD_CATEGORY")
    KEYWORD_CATEGORY keyword_category;

    @Field(value = "GROUP")
    List<FeaturedKeywordMenuGroup> groups = new ArrayList<>();


    public List<FeaturedKeywordMenuGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<FeaturedKeywordMenuGroup> groups) {
        this.groups = groups;
    }

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



}
