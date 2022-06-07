package com.spacifii.konstruct.explore.entities.recentlyspacified;


import com.spacifii.konstruct.explore.annotation.Attribute;
import com.spacifii.konstruct.explore.annotation.NotToUseDuringMerge;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.LinkedHashMap;
import java.util.Set;

/**
 * According to Google As nouns the difference between reviewer and reviewee.
 * is that reviewer is a person who writes critical reviews for a newspaper
 * or other publication; a critic while reviewee is one who undergoes a review.
 */
@Document(collection = "RECENTLY_SPACIFIED_REVIEW")
public class RecentlySpacifiedCustomerReview {

    @Id
    @Field(value = "ID")
    @NotToUseDuringMerge
    private String id;

    @Field(value = "PROJECT")
    @NotToUseDuringMerge
    private String projectId;

    @Field(value = "REVIEWER_SUBJECTED")
    @NotToUseDuringMerge
    private Long reviewerSubjectId;

    @Field(value = "SEARCH_BY")
    private SearchProfileBy searchProfileBy;

    @Field(value = "SEARCH_BY_VALUE")
    private String searchProfileByValue;

    @Field(value = "REVIEWEE_SUBJECTID")
    private Long revieweeSubjectId;

    @Field(value = "REVIEWEE_USER_TYPE")
    private String revieweeUserType;

    @Field(value = "canReEdit")
    private Boolean canReEdit;

    @Field(value = "REVIEW_STATUS")
    private ReviewStatus reviewStatus;


    @Field(value = "CUSTOMER_QUESTION_ANSWE_CONTAINER_SET")
    private LinkedHashMap<String,Set<CustomerReviewQuestionAnswerContainer>> groupQuestionSet = new LinkedHashMap<>();


    @Attribute(keyword = "RECENTLYSPACIFIEDREVIEW_CREATIONDATE", resolvedKeyword = "creationDate")
    @Field(value  = "CREATION_DATE")
    @NotToUseDuringMerge
    private Long creationDate;

    @Attribute(keyword = "RECENTLYSPACIFIEDREVIEW_LASTMODIFIEDDATE", resolvedKeyword = "lastModifiedDate")
    @Field(value  = "LAST_MODIFIED_DATE")
    @NotToUseDuringMerge
    private Long lastModifiedDate;

    @Attribute(keyword = "RECENTLYSPACIFIEDREVIEW_CREATEDBY", resolvedKeyword = "createdBy")
    @Field(value  = "CREATED_BY")
    @NotToUseDuringMerge
    private Long createdBy;

    @Attribute(keyword = "RECENTLYSPACIFIEDREVIEW_LASTMODIFIEDBY", resolvedKeyword = "lastModifiedBy")
    @Field(value  = "LAST_MODIFIED_BY")
    @NotToUseDuringMerge
    private Long lastModifiedBy;

    @Version
    @NotToUseDuringMerge
    @Attribute(keyword = "RECENTLYSPACIFIEDREVIEW_VERSION", resolvedKeyword = "version")
    @Field(value  = "VERSION")
    private Integer version;



    public void preSave(Long subjectId){
        this.setCreatedBy(subjectId);
        this.setCreationDate(System.currentTimeMillis());
        this.setLastModifiedBy(subjectId);
        this.setLastModifiedDate(this.getCreationDate());
        //this.setVersion(0);
    }

    public void preUpdate(Long subjectId){
        this.setLastModifiedBy(subjectId);
        this.setLastModifiedDate(System.currentTimeMillis());
        //this.setVersion(this.getVersion() + 1);
    }


    public ReviewStatus getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(ReviewStatus reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public Boolean getCanReEdit() {
        return canReEdit;
    }

    public void setCanReEdit(Boolean canReEdit) {
        this.canReEdit = canReEdit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Long getReviewerSubjectId() {
        return reviewerSubjectId;
    }

    public void setReviewerSubjectId(Long reviewerSubjectId) {
        this.reviewerSubjectId = reviewerSubjectId;
    }

    public SearchProfileBy getSearchProfileBy() {
        return searchProfileBy;
    }

    public void setSearchProfileBy(SearchProfileBy searchProfileBy) {
        this.searchProfileBy = searchProfileBy;
    }

    public String getSearchProfileByValue() {
        return searchProfileByValue;
    }

    public void setSearchProfileByValue(String searchProfileByValue) {
        this.searchProfileByValue = searchProfileByValue;
    }

    public Long getRevieweeSubjectId() {
        return revieweeSubjectId;
    }

    public void setRevieweeSubjectId(Long revieweeSubjectId) {
        this.revieweeSubjectId = revieweeSubjectId;
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

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(Long lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getRevieweeUserType() {
        return revieweeUserType;
    }

    public void setRevieweeUserType(String revieweeUserType) {
        this.revieweeUserType = revieweeUserType;
    }

    public LinkedHashMap<String, Set<CustomerReviewQuestionAnswerContainer>> getGroupQuestionSet() {
        return groupQuestionSet;
    }

    public void setGroupQuestionSet(LinkedHashMap<String, Set<CustomerReviewQuestionAnswerContainer>> groupQuestionSet) {
        this.groupQuestionSet = groupQuestionSet;
    }
}
