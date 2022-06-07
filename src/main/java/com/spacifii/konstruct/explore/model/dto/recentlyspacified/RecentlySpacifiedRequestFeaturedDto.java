package com.spacifii.konstruct.explore.model.dto.recentlyspacified;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.spacifii.konstruct.explore.entities.explore.MediaType;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
public class RecentlySpacifiedRequestFeaturedDto {
    private String id;
    private Boolean featured = Boolean.FALSE;
    private Boolean featuredHome =  Boolean.FALSE;
    private String featuredType;
    private String featuredMediaUrl;
    private MediaType featuredMediaType;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getFeatured() {
        return featured;
    }

    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }

    public Boolean getFeaturedHome() {
        return featuredHome;
    }

    public void setFeaturedHome(Boolean featuredHome) {
        this.featuredHome = featuredHome;
    }

    public String getFeaturedType() {
        return featuredType;
    }

    public void setFeaturedType(String featuredType) {
        this.featuredType = featuredType;
    }

    public String getFeaturedMediaUrl() {
        return featuredMediaUrl;
    }

    public void setFeaturedMediaUrl(String featuredMediaUrl) {
        this.featuredMediaUrl = featuredMediaUrl;
    }

    public MediaType getFeaturedMediaType() {
        return featuredMediaType;
    }

    public void setFeaturedMediaType(MediaType featuredMediaType) {
        this.featuredMediaType = featuredMediaType;
    }
}
