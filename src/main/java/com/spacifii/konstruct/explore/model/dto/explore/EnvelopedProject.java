package com.spacifii.konstruct.explore.model.dto.explore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.spacifii.konstruct.explore.entities.explore.ExploreProject;
import com.spacifii.konstruct.explore.entities.explore.ExploreWalkThrough;
import com.spacifii.konstruct.explore.entities.explore.ProjectUserActionStatistics;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
public class
EnvelopedProject {

    private ExploreProject exploreProject;

    private List<EnvelopedMedia> envelopedMedia = new ArrayList<>();

    private ProjectUserActionStatistics  projectUserActionStatistics;

    public ExploreProject getExploreProject() {
        return exploreProject;
    }

    private List<ExploreWalkThrough> walkThroughs = new ArrayList<>();

    public List<ExploreWalkThrough> getWalkThroughs() {
        return walkThroughs;
    }

    public void setWalkThroughs(List<ExploreWalkThrough> walkThroughs) {
        this.walkThroughs = walkThroughs;
    }

    public void setExploreProject(ExploreProject exploreProject) {
        this.exploreProject = exploreProject;
    }

    public List<EnvelopedMedia> getEnvelopedMedia() {
        return envelopedMedia;
    }

    public void setEnvelopedMedia(List<EnvelopedMedia> envelopedMedia) {
        this.envelopedMedia = envelopedMedia;
    }

    public ProjectUserActionStatistics getProjectUserActionStatistics() {
        return projectUserActionStatistics;
    }

    public void setProjectUserActionStatistics(ProjectUserActionStatistics projectUserActionStatistics) {
        this.projectUserActionStatistics = projectUserActionStatistics;
    }
}
