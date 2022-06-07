package com.spacifii.konstruct.explore.model.dto.conceptBoard;

import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardUserAction;
import com.spacifii.konstruct.explore.model.dto.explore.UserProfileMiniDto;

import java.math.BigDecimal;

public class ConceptBoardReview {

    private String reviewId;

    private String conceptBoardId;

    private BigDecimal ratings;

    private BigDecimal outOf;

    private String review;

    private Boolean isAskedByMe = false;

    private UserProfileMiniDto userProfile;

    private Long creationDate;

    private Long lastModifiedDate;


    public void convertConceptBoardUserActionToConceptBoardReview(ConceptBoardUserAction conceptBoardUserAction){
        this.setConceptBoardId(conceptBoardUserAction.getConceptBoardId());
        this.setReviewId(conceptBoardUserAction.getId());
        this.setReview(conceptBoardUserAction.getDetails());
        this.setRatings(conceptBoardUserAction.getRatings());
        this.setCreationDate(conceptBoardUserAction.getCreationDate());
        this.setLastModifiedDate(conceptBoardUserAction.getLastModifiedDate());
    }
    public ConceptBoardReview() {
    }

    public ConceptBoardReview(Boolean isAskedByMe, UserProfileMiniDto userProfile) {
        this.isAskedByMe = isAskedByMe;
        this.userProfile = userProfile;
    }

    public ConceptBoardReview(String reviewId, String conceptBoardId, BigDecimal ratings, BigDecimal outOf, String review, Boolean isAskedByMe, UserProfileMiniDto userProfile, Long creationDate, Long lastModifiedDate) {
        this.reviewId = reviewId;
        this.conceptBoardId = conceptBoardId;
        this.ratings = ratings;
        this.outOf = outOf;
        this.review = review;
        this.isAskedByMe = isAskedByMe;
        this.userProfile = userProfile;
        this.creationDate = creationDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getConceptBoardId() {
        return conceptBoardId;
    }

    public void setConceptBoardId(String conceptBoardId) {
        this.conceptBoardId = conceptBoardId;
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
