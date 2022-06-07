package com.spacifii.konstruct.explore.model.dto.conceptBoard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardUserAction;
import com.spacifii.konstruct.explore.model.dto.explore.UserProfileMiniDto;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
public class ConceptBoardComment {
    private String conceptBoardId;
    private String commentId;
    private String comment;
    private String parentCommentId;
    private String name;
    private String email;
    private Boolean isAskedByMe = false;
    private UserProfileMiniDto userProfile;
    private Long creationDate;
    private Long lastModifiedDate;


    public ConceptBoardComment(String conceptBoardId, String commentId, String comment, String parentCommentId) {
        this.conceptBoardId = conceptBoardId;
        this.commentId = commentId;
        this.comment = comment;
        this.parentCommentId = parentCommentId;
    }

    public ConceptBoardComment(String conceptBoardId, String commentId, String comment, String parentCommentId, String name, String email) {
        this.conceptBoardId = conceptBoardId;
        this.commentId = commentId;
        this.comment = comment;
        this.parentCommentId = parentCommentId;
        this.name = name;
        this.email = email;
    }

    public void convertConceptBoardUserActionToConceptBoardComment(ConceptBoardUserAction conceptBoardUserAction) {
        this.setConceptBoardId(conceptBoardUserAction.getConceptBoardId());
        this.setCommentId(conceptBoardUserAction.getId());
        this.setComment(conceptBoardUserAction.getDetails());
        this.setParentCommentId(conceptBoardUserAction.getParentCommentId());
        this.setLastModifiedDate(conceptBoardUserAction.getLastModifiedDate());
        this.setCreationDate(conceptBoardUserAction.getCreationDate());

    }


    public ConceptBoardComment(Boolean isAskedByMe, UserProfileMiniDto userProfile) {
        this.isAskedByMe = isAskedByMe;
        this.userProfile = userProfile;
    }

    public String getConceptBoardId() {
        return conceptBoardId;
    }

    public void setConceptBoardId(String conceptBoardId) {
        this.conceptBoardId = conceptBoardId;
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

    public ConceptBoardComment() {
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
