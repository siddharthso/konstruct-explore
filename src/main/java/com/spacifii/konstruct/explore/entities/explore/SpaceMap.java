package com.spacifii.konstruct.explore.entities.explore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
public class SpaceMap {
    @Field("IMAGE_URL")
    private String imageUrl;

    @Field("SPACE_MAP_COORDINATES")
    private Set<SpaceMapCoordinates> spaceMapCoordinates = new HashSet<>();

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Set<SpaceMapCoordinates> getSpaceMapCoordinates() {
        return spaceMapCoordinates;
    }

    public void setSpaceMapCoordinates(Set<SpaceMapCoordinates> spaceMapCoordinates) {
        this.spaceMapCoordinates = spaceMapCoordinates;
    }
}
