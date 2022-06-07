package com.spacifii.konstruct.explore.model.dto.explore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spacifii.konstruct.explore.entities.explore.MediaUserActionType;
import com.spacifii.konstruct.explore.entities.explore.ShareType;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MediaUserActionDTO {

    private String id;

    private String mediaId;

    private String projectId;

    private String conceptBoardId;

    private MediaUserActionType mediaUserActionType;

    private ShareType shareType;

    private String details;

    private BigDecimal rating;

    private String parentCommentId;

    private String question;

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public String getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(String parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public MediaUserActionType getMediaUserActionType() {
        return mediaUserActionType;
    }

    public void setMediaUserActionType(MediaUserActionType mediaUserActionType) {
        this.mediaUserActionType = mediaUserActionType;
    }

    public ShareType getShareType() {
        return shareType;
    }

    public void setShareType(ShareType shareType) {
        this.shareType = shareType;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getConceptBoardId() {
        return conceptBoardId;
    }

    public void setConceptBoardId(String conceptBoardId) {
        this.conceptBoardId = conceptBoardId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
