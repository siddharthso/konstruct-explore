package com.spacifii.konstruct.explore.model.dto.explore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.spacifii.konstruct.explore.entities.explore.ExploreProject;
import com.spacifii.konstruct.explore.entities.explore.Media;
import com.spacifii.konstruct.explore.entities.explore.MediaUserActionStatistics;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
public class EnvelopedB2CMedia {

    private Media media;

    private MediaUserActionStatistics mediaUserActionStatistics;

    private ExploreProject project;

    private UserProfileMiniDto profile;

    private List<Media> projectMedias;

    private List<Media> relatedMedias;

    public EnvelopedB2CMedia(Media media, MediaUserActionStatistics mediaUserActionStatistics, ExploreProject project, UserProfileMiniDto profile) {
        this.media = media;
        this.mediaUserActionStatistics = mediaUserActionStatistics;
        this.project = project;
        this.profile = profile;
    }

    public EnvelopedB2CMedia(Media media, MediaUserActionStatistics mediaUserActionStatistics, ExploreProject project, UserProfileMiniDto profile, List<Media> projectMedias, List<Media> relatedMedias) {
        this.media = media;
        this.mediaUserActionStatistics = mediaUserActionStatistics;
        this.project = project;
        this.profile = profile;
        this.projectMedias = projectMedias;
        this.relatedMedias = relatedMedias;
    }

    public EnvelopedB2CMedia() {
    }

    public UserProfileMiniDto getProfile() {
        return profile;
    }

    public void setProfile(UserProfileMiniDto profile) {
        this.profile = profile;
    }

    public List<Media> getProjectMedias() {
        return projectMedias;
    }

    public void setProjectMedias(List<Media> projectMedias) {
        this.projectMedias = projectMedias;
    }

    public List<Media> getRelatedMedias() {
        return relatedMedias;
    }

    public void setRelatedMedias(List<Media> relatedMedias) {
        this.relatedMedias = relatedMedias;
    }

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

    public ExploreProject getProject() {
        return project;
    }

    public void setProject(ExploreProject project) {
        this.project = project;
    }
}
