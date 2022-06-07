package com.spacifii.konstruct.explore.model.dto.recentlyspacified;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.spacifii.konstruct.explore.entities.recentlyspacified.CustomerTestimonial;
import com.spacifii.konstruct.explore.entities.recentlyspacified.RecentlySpacifiedCustomerReview;
import com.spacifii.konstruct.explore.entities.recentlyspacified.RecentlySpacifiedProject;
import com.spacifii.konstruct.explore.model.dto.explore.EnvelopedProject;
import com.spacifii.konstruct.explore.model.dto.explore.UserProfileMiniDto;


import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
public class EnvelopedRecentlySpacifiedProjectDto {

    private RecentlySpacifiedProject recentlySpacifiedProject;

    private EnvelopedProject envelopedExploreProject;

    private List<CustomerTestimonial> customerTestimonials = new ArrayList<>();

    private List<RecentlySpacifiedCustomerReview> recentlySpacifiedCustomerReviews = new ArrayList<>();

    private UserProfileMiniDto projectCreatorUserProfile;

    public UserProfileMiniDto getProjectCreatorUserProfile() {
        return projectCreatorUserProfile;
    }

    public void setProjectCreatorUserProfile(UserProfileMiniDto projectCreatorUserProfile) {
        this.projectCreatorUserProfile = projectCreatorUserProfile;
    }

    public EnvelopedRecentlySpacifiedProjectDto() {
    }


    public RecentlySpacifiedProject getRecentlySpacifiedProject() {
        return recentlySpacifiedProject;
    }

    public void setRecentlySpacifiedProject(RecentlySpacifiedProject recentlySpacifiedProject) {
        this.recentlySpacifiedProject = recentlySpacifiedProject;
    }

    public EnvelopedProject getEnvelopedExploreProject() {
        return envelopedExploreProject;
    }

    public void setEnvelopedExploreProject(EnvelopedProject envelopedExploreProject) {
        this.envelopedExploreProject = envelopedExploreProject;
    }

    public List<CustomerTestimonial> getCustomerTestimonials() {
        return customerTestimonials;
    }

    public void setCustomerTestimonials(List<CustomerTestimonial> customerTestimonials) {
        this.customerTestimonials = customerTestimonials;
    }

    public List<RecentlySpacifiedCustomerReview> getRecentlySpacifiedCustomerReviews() {
        return recentlySpacifiedCustomerReviews;
    }

    public void setRecentlySpacifiedCustomerReviews(List<RecentlySpacifiedCustomerReview> recentlySpacifiedCustomerReviews) {
        this.recentlySpacifiedCustomerReviews = recentlySpacifiedCustomerReviews;
    }
}
