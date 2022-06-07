package com.spacifii.konstruct.explore.entities.explore;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

public class FeaturedKeywordMenuGroup {

    @Field(value = "GROUP_NAME")
    private String groupName;

    @DBRef
    @Field(value = "EXPLORE_KEYWORDS")
    private List<ExploreKeyword> exploreKeywords;

    public FeaturedKeywordMenuGroup() {
    }

    public FeaturedKeywordMenuGroup(String groupName, List<ExploreKeyword> exploreKeywords) {
        this.groupName = groupName;
        this.exploreKeywords = exploreKeywords;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<ExploreKeyword> getExploreKeywords() {
        return exploreKeywords;
    }

    public void setExploreKeywords(List<ExploreKeyword> exploreKeywords) {
        this.exploreKeywords = exploreKeywords;
    }
}
