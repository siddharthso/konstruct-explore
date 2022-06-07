package com.spacifii.konstruct.explore.entities.recentlyspacified;

import org.springframework.data.mongodb.core.mapping.Field;

public class BeforeAfterMedia {

    @Field(value = "NAME")
    private String name;

    @Field(value = "DESCRIPTION")
    private String description;

    @Field(value = "FEATURED_TITLE")
    private String featuredTitle;

    @Field(value = "BEFORE_URL")
    private String beforeUrl;

    @Field(value = "AFTER_URL")
    private String afterUrl;

    @Field(value = "BEFORE_MEDIA_TYPE")
    private String beforeMediaType;

    @Field(value = "AFTER_MEDIA_TYPE")
    private String afterMediaType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFeaturedTitle() {
        return featuredTitle;
    }

    public void setFeaturedTitle(String featuredTitle) {
        this.featuredTitle = featuredTitle;
    }

    public String getBeforeUrl() {
        return beforeUrl;
    }

    public void setBeforeUrl(String beforeUrl) {
        this.beforeUrl = beforeUrl;
    }

    public String getAfterUrl() {
        return afterUrl;
    }

    public void setAfterUrl(String afterUrl) {
        this.afterUrl = afterUrl;
    }

    public String getBeforeMediaType() {
        return beforeMediaType;
    }

    public void setBeforeMediaType(String beforeMediaType) {
        this.beforeMediaType = beforeMediaType;
    }

    public String getAfterMediaType() {
        return afterMediaType;
    }

    public void setAfterMediaType(String afterMediaType) {
        this.afterMediaType = afterMediaType;
    }
}
