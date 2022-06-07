package com.spacifii.konstruct.explore.entities.recentlyspacified;

import com.spacifii.konstruct.explore.entities.explore.MediaType;
import org.springframework.data.mongodb.core.mapping.Field;

public class RecentlySpacifiedMedia {

    @Field(value = "URL")
    private String url;

    @Field(value = "MEDIA_TYPE")
    private MediaType mediaType;

    @Field(value = "NAME")
    private String name;

    @Field(value = "DESCRIPTION")
    private String description;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }
}
