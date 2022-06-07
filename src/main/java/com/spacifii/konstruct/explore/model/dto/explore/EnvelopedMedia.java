package com.spacifii.konstruct.explore.model.dto.explore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.spacifii.konstruct.explore.entities.explore.Media;
import com.spacifii.konstruct.explore.entities.explore.MediaUserActionStatistics;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
public class EnvelopedMedia {

    private Media media;

    private MediaUserActionStatistics mediaUserActionStatistics;


    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public MediaUserActionStatistics getMediaUserActionStatistics() {
        return mediaUserActionStatistics;
    }

    public void setMediaUserActionStatistics(MediaUserActionStatistics mediaUserActionStatistics) {
        this.mediaUserActionStatistics = mediaUserActionStatistics;
    }
}
