package com.spacifii.konstruct.explore.entities.recentlyspacified;

import org.elasticsearch.common.inject.Inject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "CUSTOMER_TESTIMONIAL_INITIATE_REQUEST")
public class CustomerTestimonialInitiateRequest {

    @Id
    @Field(value = "ID")
    private String id;

    @Inject
    @Field(value = "PROJECT_ID")
    private String projectId;

    @Field(value = "SEARCH_VALUE")
    private String searchValue;

    @Field(value = "SEARCH_PROFILE_BY")
    private SearchProfileBy searchProfileBy;

    @Field(value = "CUSTOMER_TESTIMONIAL_ID")
    private String customerTestimonialId;

    @Field(value = "CUSTOMER_SUBJECT_ID")
    private Long customerSubjectId;

    @Field(value = "CREATED_BY")
    private Long createdBy;

    @Field(value = "CREATION_DATE")
    private Long creationDate;


    public void preSave(Long subjectId){
        this.setCreatedBy(subjectId);
        this.setCreationDate(System.currentTimeMillis());
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public SearchProfileBy getSearchProfileBy() {
        return searchProfileBy;
    }

    public void setSearchProfileBy(SearchProfileBy searchProfileBy) {
        this.searchProfileBy = searchProfileBy;
    }

    public String getCustomerTestimonialId() {
        return customerTestimonialId;
    }

    public void setCustomerTestimonialId(String customerTestimonialId) {
        this.customerTestimonialId = customerTestimonialId;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Long creationDate) {
        this.creationDate = creationDate;
    }

    public Long getCustomerSubjectId() {
        return customerSubjectId;
    }

    public void setCustomerSubjectId(Long customerSubjectId) {
        this.customerSubjectId = customerSubjectId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
