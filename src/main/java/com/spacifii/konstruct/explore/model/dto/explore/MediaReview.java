package com.spacifii.konstruct.explore.model.dto.explore;

import com.spacifii.konstruct.explore.entities.explore.MediaUserAction;

import java.math.BigDecimal;

public class MediaReview {

    private String reviewId;

    private String mediaId;

    private BigDecimal ratings;

    private BigDecimal outOf;

    private String review;

    private Boolean isAskedByMe = false;

    private UserProfileMiniDto userProfile;

    private Long creationDate;

    private Long lastModifiedDate;


    public void convertMediaUserActionToMediaReview(MediaUserAction mediaUserAction){
        this.setMediaId(mediaUserAction.getMediaId());
        this.setReviewId(mediaUserAction.getId());
        this.setReview(mediaUserAction.getDetails());
        this.setRatings(mediaUserAction.getRatings());
        this.setCreationDate(mediaUserAction.getCreationDate());
        this.setLastModifiedDate(mediaUserAction.getLastModifiedDate());
    }
    public MediaReview() {
    }

    public MediaReview(Boolean isAskedByMe, UserProfileMiniDto userProfile) {
        this.isAskedByMe = isAskedByMe;
        this.userProfile = userProfile;
    }

    public MediaReview(String reviewId, String mediaId, BigDecimal ratings, BigDecimal outOf, String review, Boolean isAskedByMe, UserProfileMiniDto userProfile, Long creationDate, Long lastModifiedDate) {
        this.reviewId = reviewId;
        this.mediaId = mediaId;
        this.ratings = ratings;
        this.outOf = outOf;
        this.review = review;
        this.isAskedByMe = isAskedByMe;
        this.userProfile = userProfile;
        this.creationDate = creationDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
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
