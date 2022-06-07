package com.spacifii.konstruct.explore.entities.explore;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * This is Entity Class for ExploreMediaElastic
 */
@Document(indexName = "explore_media", type = "exploreMedia")
public class ExploreMediaElastic {


    @Id
    private String id;

    //this is Kewords separated by something
    @Field(type = FieldType.Text, analyzer = "autocomplete_media", searchAnalyzer = "standard")
    private String keywords;


    //this is tags separated by something
    @Field(type = FieldType.Text, analyzer = "autocomplete_media", searchAnalyzer = "standard")
    private String tags;


    //this is description as is
    @Field(type = FieldType.Text, analyzer = "autocomplete_media", searchAnalyzer = "standard")
    private String description;

    //this is project metadata for Search. would be projectName + projectAddress + developerOrProjectName + projectStyles
    @Field(type = FieldType.Text, analyzer = "autocomplete_media", searchAnalyzer = "standard")
    private String project;

    public void mergeMeWithMedia(Media media){
        this.setId(media.getId());
        this.setKeywords(media.getExtendedKeywords().toString());
        this.setTags(media.getTags().toString());
        this.setDescription(media.getDescription());
    }

    public void mergeMeWithProject(ExploreProject exploreProject){
       this.setProject( exploreProject.getProjectName()
                + " | " +
                exploreProject.getDeveloperOrProjectName()
                + " | " +
                exploreProject.getProjectAddress()
                + " | " +
                exploreProject.getProjectStyles());
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }
}
