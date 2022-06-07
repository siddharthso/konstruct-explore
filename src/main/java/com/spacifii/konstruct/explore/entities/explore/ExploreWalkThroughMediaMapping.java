package com.spacifii.konstruct.explore.entities.explore;

import org.springframework.data.mongodb.core.mapping.Field;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

public class ExploreWalkThroughMediaMapping {

    @Field("MEDIA_ID")
    private String mediaId;

    @Field("URL")
    private String url;

    @Field("ALT_URL")
    private String altUrl;

    @Field("NAME")
    private String name;

    @Field("SPACENAME")
    private String spaceName;

    @Field("OVERRIDDEN_INFOSPOTS")
    private Set<InfoSpot> overriddenInfoSpots;

    private void mergeInfospots() {
        if(this.getOverriddenInfoSpots().size() > 0){
            Set<InfoSpot> infoSpots = new LinkedHashSet<>();
            for (InfoSpot infoSpot: this.getOverriddenInfoSpots()) {
                if(infoSpot.getId() == null){
                    infoSpot.setId(UUID.randomUUID().toString());
                }
                infoSpots.add(infoSpot);
            }
            this.setOverriddenInfoSpots(infoSpots);
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAltUrl() {
        return altUrl;
    }

    public void setAltUrl(String altUrl) {
        this.altUrl = altUrl;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public Set<InfoSpot> getOverriddenInfoSpots() {
        return overriddenInfoSpots;
    }

    public void setOverriddenInfoSpots(Set<InfoSpot> overriddenInfoSpots) {
        this.overriddenInfoSpots = overriddenInfoSpots;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpaceName() {
        return spaceName;
    }

    public void setSpaceName(String spaceName) {
        this.spaceName = spaceName;
    }
}
