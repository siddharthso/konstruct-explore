package com.spacifii.konstruct.explore.entities.showcases;

import com.spacifii.konstruct.explore.annotation.NotToUseDuringMerge;
import com.spacifii.konstruct.explore.entities.explore.SpacifiiColor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Set;

/**
 * This collection for Material
 */
@Document(collection = "MATERIAL")
public class Material {

    @Id
    @NotToUseDuringMerge
    @Field(value = "ID")
    private String id;

    @Field(value = "NAME")
    private String name;

    @Field(value = "DESCRIPTION")
    private String description;

    // Either Texture or Pattern
    @Field(value = "TYPE")
    private String type;

    @Field(value = "OFFERING_ID")
    private String offeringId;

    @Field(value = "MEDIA_URL")
    private String mediaUrl;

    @Field(value = "MEDIA_ALT_URL")
    private String mediaAltUrl;

    @Field(value = "ACTIVE")
    private Boolean active;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getOfferingId() {
        return offeringId;
    }

    public void setOfferingId(String offeringId) {
        this.offeringId = offeringId;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public String getMediaAltUrl() {
        return mediaAltUrl;
    }

    public void setMediaAltUrl(String mediaAltUrl) {
        this.mediaAltUrl = mediaAltUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
