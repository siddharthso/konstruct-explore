package com.spacifii.konstruct.explore.model.dto.explore;

import com.spacifii.konstruct.explore.entities.explore.ProjectUserAction;

import java.math.BigDecimal;

public class ProjectReview {

    private String reviewId;

    private String projectId;

    private BigDecimal ratings;

    private BigDecimal outOf;

    private String review;

    private Boolean isAskedByMe = false;

    private UserProfileMiniDto userProfile;

    private Long creationDate;

    private Long lastModifiedDate;

    public void convertProjectUserActionToProjectReview(ProjectUserAction projectUserAction){
        this.setProjectId(projectUserAction.getProjectId());
        this.setReviewId(projectUserAction.getId());
        this.setReview(projectUserAction.getDetails());
        this.setRatings(projectUserAction.getRating());
        this.setCreationDate(projectUserAction.getCreationDate());
        this.setLastModifiedDate(projectUserAction.getLastModifiedDate());
    }


    public ProjectReview() {
    }

    public ProjectReview(Boolean isAskedByMe, UserProfileMiniDto userProfile) {
        this.isAskedByMe = isAskedByMe;
        this.userProfile = userProfile;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
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

    public BigDecimal getRatings() {
        return ratings;
    }

    public void setRatings(BigDecimal ratings) {
        this.ratings = ratings;
    }

    public BigDecimal getOutOf() {
        return outOf;
    }

    public void setOutOf(BigDecimal outOf) {
        this.outOf = outOf;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
