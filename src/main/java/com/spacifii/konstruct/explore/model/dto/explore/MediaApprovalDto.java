package com.spacifii.konstruct.explore.model.dto.explore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Set;

@JsonPropertyOrder(alphabetic = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MediaApprovalDto {

    private WorkflowDecision workflowDecision;

    private Set<String> mediaIds;

    public WorkflowDecision getWorkflowDecision() {
        return workflowDecision;
    }

    public void setWorkflowDecision(WorkflowDecision workflowDecision) {
        this.workflowDecision = workflowDecision;
    }

    public Set<String> getMediaIds() {
        return mediaIds;
    }

    public void setMediaIds(Set<String> mediaIds) {
        this.mediaIds = mediaIds;
    }
}
