package com.spacifii.konstruct.explore.model.dto.explore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.spacifii.konstruct.explore.entities.explore.MediaUserAction;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
public class MediaComment {
    private String mediaId;
    private String commentId;
    private String comment;
    private String parentCommentId;
    private String name;
    private String email;
    private Boolean isAskedByMe = false;
    private UserProfileMiniDto userProfile;
    private Long creationDate;
    private Long lastModifiedDate;


    public MediaComment(String mediaId, String commentId, String comment, String parentCommentId) {
        this.mediaId = mediaId;
        this.commentId = commentId;
        this.comment = comment;
        this.parentCommentId = parentCommentId;
    }

    public MediaComment(String mediaId, String commentId, String comment, String parentCommentId, String name, String email) {
        this.mediaId = mediaId;
        this.commentId = commentId;
        this.comment = comment;
        this.parentCommentId = parentCommentId;
        this.name = name;
        this.email = email;
    }

    public void convertProjectUserActionToProjectComment(MediaUserAction mediaUserAction) {
        this.setMediaId(mediaUserAction.getMediaId());
        this.setCommentId(mediaUserAction.getId());
        this.setComment(mediaUserAction.getDetails());
        this.setParentCommentId(mediaUserAction.getParentCommentId());
        this.setLastModifiedDate(mediaUserAction.getLastModifiedDate());
        this.setCreationDate(mediaUserAction.getCreationDate());

    }

    public MediaComment(Boolean isAskedByMe, UserProfileMiniDto userProfile) {
        this.isAskedByMe = isAskedByMe;
        this.userProfile = userProfile;
    }

    public UserProfileMiniDto getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfileMiniDto userProfile) {
        this.userProfile = userProfile;
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

    public MediaComment() {
    }

    public Boolean getAskedByMe() {
        return isAskedByMe;
    }

    public void setAskedByMe(Boolean askedByMe) {
        isAskedByMe = askedByMe;
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

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
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
}
