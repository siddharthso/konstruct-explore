package com.spacifii.konstruct.explore.entities.explore;

import com.spacifii.konstruct.explore.annotation.Attribute;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "MEDIA_CLOUDVISION_RESPONSE_MAPPING")
public class MediaCloudVIsionResponseMapping {

    @Id
    @Attribute(keyword = "MEDIACLOUDVISIONRESPONSEMAPPING_ID", resolvedKeyword = "mediaId", enableFindBy = true)
    @Field(value = "MEDIA_ID")
    private String mediaId;

    @Attribute(keyword = "EMDIACLOUDVISIONRESPONSEMAPPING_ID", resolvedKeyword = "cloudVisionResponses")
    @Field(value = "CLOUDVISIONRESPONSES")
    private List<CloudVisionResponse> cloudVisionResponses;


    public MediaCloudVIsionResponseMapping(String mediaId, List<CloudVisionResponse> cloudVisionResponses) {
        this.mediaId = mediaId;
        this.cloudVisionResponses = cloudVisionResponses;
    }

    public MediaCloudVIsionResponseMapping() {
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public List<CloudVisionResponse> getCloudVisionResponses() {
        return cloudVisionResponses;
    }

    public void setCloudVisionResponses(List<CloudVisionResponse> cloudVisionResponses) {
        this.cloudVisionResponses = cloudVisionResponses;
    }
}
