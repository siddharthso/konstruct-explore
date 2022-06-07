package com.spacifii.konstruct.explore.entities.explore;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * This is Entity Class for ExploreKeywordElastic
 */
@Document(indexName = "explore", type = "exploreKeyword")
public class ExploreKeywordElastic {

    @Id
    @Field(type = FieldType.Text, analyzer = "autocomplete", searchAnalyzer = "standard")
    private String keyword;


    @Field(type = FieldType.Text, analyzer = "autocomplete", searchAnalyzer = "standard")
    private String keywordDisplay;

    public void updateMe(ExploreKeyword exploreKeyword){
        this.setKeyword(exploreKeyword.getKeyword());
        this.setKeywordDisplay(exploreKeyword.getKeywordDisplay());
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

    @Override
    public String toString() {
        return "ExploreKeywordElastic{" +
                "keyword='" + keyword + '\'' +
                ", keywordDisplay='" + keywordDisplay + '\'' +
                '}';
    }
}
