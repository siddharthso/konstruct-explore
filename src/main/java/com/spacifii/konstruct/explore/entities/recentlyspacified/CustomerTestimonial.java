package com.spacifii.konstruct.explore.entities.recentlyspacified;


import com.spacifii.konstruct.explore.model.dto.recentlyspacified.TestimonialDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.LinkedHashSet;
import java.util.TreeSet;

@Document(collection = "CUSTOMER_TESTIMONIAL")
public class CustomerTestimonial {

    @Id
    @Field(value = "ID")
    private String id;

    @Indexed
    @Field(value = "PROJECT_ID")
    private String projectId;

    @Field(value = "QUOTE_TEXT")
    private String quoteText;

    @Field(value = "DESCRIPTION_TEXT")
    private String descriptionText;

    @Field(value = "PEOPLE_TO_BE_THANKED")
    private LinkedHashSet<String> peopleToBeThanked;

    @Indexed
    @Field(value = "CUSTOMER_SUBJECT_ID")
    private Long customerSubjectId;

    @Indexed
    @Field(value = "INITIATED_BY_SUBJECT_ID")
    private Long initiatedBySubjectId;

    @Field(value = "CREATION_DATE")
    private Long creationDate;

    @Field(value = "TESTIMONIAL_CUSTOMER_RESPONSE_DATE")
    private Long testimonialCustomerResponseDate;

    @Field(value = "CAN_EDIT")
    private boolean canEdit = true;

    @Field(value = "FEATURED_HOME")
    private Boolean featuredHome = false;

    @Field(value = "ACTIVE")
    private boolean active = true;

    @Field(value = "MEDIAS")
    private TreeSet<RecentlySpacifiedMedia> medias = new TreeSet<>();

    public void preSave(Long subjectId){
        this.setInitiatedBySubjectId(subjectId);
        this.setCreationDate(System.currentTimeMillis());
    }

    public void mergeInMe(CustomerTestimonialInitiateRequest customerTestimonialInitiateRequest){
        this.setCustomerSubjectId(customerTestimonialInitiateRequest.getCustomerSubjectId());
        this.setProjectId(customerTestimonialInitiateRequest.getProjectId());
    }

    public void mergeInMe(TestimonialDto testimonialDto){
        this.setQuoteText(testimonialDto.getQuoteText());
        this.setDescriptionText(testimonialDto.getDescriptionText());
        this.setPeopleToBeThanked(testimonialDto.getPeopleToBeThanked());
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

    public String getDescriptionText() {
        return descriptionText;
    }

    public void setDescriptionText(String descriptionText) {
        this.descriptionText = descriptionText;
    }

    public Long getCustomerSubjectId() {
        return customerSubjectId;
    }

    public void setCustomerSubjectId(Long customerSubjectId) {
        this.customerSubjectId = customerSubjectId;
    }

    public TreeSet<RecentlySpacifiedMedia> getMedias() {
        return medias;
    }

    public void setMedias(TreeSet<RecentlySpacifiedMedia> medias) {
        this.medias = medias;
    }

    public String getQuoteText() {
        return quoteText;
    }

    public void setQuoteText(String quoteText) {
        this.quoteText = quoteText;
    }

    public LinkedHashSet<String> getPeopleToBeThanked() {
        return peopleToBeThanked;
    }

    public void setPeopleToBeThanked(LinkedHashSet<String> peopleToBeThanked) {
        this.peopleToBeThanked = peopleToBeThanked;
    }

    public Long getInitiatedBySubjectId() {
        return initiatedBySubjectId;
    }

    public void setInitiatedBySubjectId(Long initiatedBySubjectId) {
        this.initiatedBySubjectId = initiatedBySubjectId;
    }

    public Long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Long creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isCanEdit() {
        return canEdit;
    }

    public void setCanEdit(boolean canEdit) {
        this.canEdit = canEdit;
    }

    public Long getTestimonialCustomerResponseDate() {
        return testimonialCustomerResponseDate;
    }

    public void setTestimonialCustomerResponseDate(Long testimonialCustomerResponseDate) {
        this.testimonialCustomerResponseDate = testimonialCustomerResponseDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Boolean getFeaturedHome() {
        return featuredHome;
    }

    public void setFeaturedHome(Boolean featuredHome) {
        this.featuredHome = featuredHome;
    }
}
