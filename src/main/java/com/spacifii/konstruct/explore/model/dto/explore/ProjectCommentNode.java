package com.spacifii.konstruct.explore.model.dto.explore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder(alphabetic = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectCommentNode {

    ProjectComment projectComment;

    List<ProjectCommentNode> childrenNodes;

    public ProjectComment getProjectComment() {
        return projectComment;
    }

    public void setProjectComment(ProjectComment projectComment) {
        this.projectComment = projectComment;
    }

    public List<ProjectCommentNode> getChildrenNodes() {
        return childrenNodes;
    }

    public void setChildrenNodes(List<ProjectCommentNode> childrenNodes) {
        this.childrenNodes = childrenNodes;
    }
}
