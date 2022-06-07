package com.spacifii.konstruct.explore.model.dto.explore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.spacifii.konstruct.explore.entities.explore.ProjectUserAction;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
public class ProjectComment {
    private String projectId;
    private String commentId;
    private String comment;
    private String parentCommentId;
    private String name;
    private String email;
    private UserProfileMiniDto userProfile;
    private Boolean isAskedByMe = false;
    private Long creationDate;
    private Long lastModifiedDate;

    public ProjectComment(String projectId, String commentId, String comment, String parentCommentId) {
        this.projectId = projectId;
        this.commentId = commentId;
        this.comment = comment;
        this.parentCommentId = parentCommentId;
    }

    public ProjectComment(String projectId, String commentId, String comment, String parentCommentId, String name, String email) {
        this.projectId = projectId;
        this.commentId = commentId;
        this.comment = comment;
        this.parentCommentId = parentCommentId;
        this.name = name;
        this.email = email;
    }

    public void convertProjectUserActionToProjectComment(ProjectUserAction projectUserAction){
        this.setProjectId(projectUserAction.getProjectId());
        this.setCommentId(projectUserAction.getId());
        this.setComment(projectUserAction.getDetails());
        this.setParentCommentId(projectUserAction.getParentCommentId());
        this.setLastModifiedDate(projectUserAction.getLastModifiedDate());
        this.setCreationDate(projectUserAction.getCreationDate());
    }
    public ProjectComment(UserProfileMiniDto userProfile, Boolean isAskedByMe) {
        this.userProfile = userProfile;
        this.isAskedByMe = isAskedByMe;
    }

    public UserProfileMiniDto getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfileMiniDto userProfile) {
        this.userProfile = userProfile;
    }

    public ProjectComment() {
    }

    public Boolean getAskedByMe() {
        return isAskedByMe;
    }

    public void setAskedByMe(Boolean askedByMe) {
        isAskedByMe = askedByMe;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(String parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public Long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Long creationDate) {
        this.creationDate = creationDate;
    }

    public Long getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Long lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
