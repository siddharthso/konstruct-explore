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
public class EnvelopedRecentlySpacifiedProjectDtoExtended {

    private RecentlySpacifiedProject recentlySpacifiedProject;

    private EnvelopedProject envelopedExploreProject;

    private List<EnvelopedCustomerTestimonial> customerTestimonials = new ArrayList<>();

    private List<EnvelopedCustomerReview> recentlySpacifiedCustomerReviews = new ArrayList<>();

    private UserProfileMiniDto projectCreatorUserProfile;


    public EnvelopedRecentlySpacifiedProjectDtoExtended(RecentlySpacifiedProject recentlySpacifiedProject, EnvelopedProject envelopedExploreProject, List<EnvelopedCustomerTestimonial> customerTestimonials, List<EnvelopedCustomerReview> recentlySpacifiedCustomerReviews, UserProfileMiniDto projectCreatorUserProfile) {
        this.recentlySpacifiedProject = recentlySpacifiedProject;
        this.envelopedExploreProject = envelopedExploreProject;
        this.customerTestimonials = customerTestimonials;
        this.recentlySpacifiedCustomerReviews = recentlySpacifiedCustomerReviews;
        this.projectCreatorUserProfile = projectCreatorUserProfile;
    }

    public UserProfileMiniDto getProjectCreatorUserProfile() {
        return projectCreatorUserProfile;
    }

    public void setProjectCreatorUserProfile(UserProfileMiniDto projectCreatorUserProfile) {
        this.projectCreatorUserProfile = projectCreatorUserProfile;
    }

    public EnvelopedRecentlySpacifiedProjectDtoExtended() {
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

    public List<EnvelopedCustomerTestimonial> getCustomerTestimonials() {
        return customerTestimonials;
    }

    public void setCustomerTestimonials(List<EnvelopedCustomerTestimonial> customerTestimonials) {
        this.customerTestimonials = customerTestimonials;
    }

    public List<EnvelopedCustomerReview> getRecentlySpacifiedCustomerReviews() {
        return recentlySpacifiedCustomerReviews;
    }

    public void setRecentlySpacifiedCustomerReviews(List<EnvelopedCustomerReview> recentlySpacifiedCustomerReviews) {
        this.recentlySpacifiedCustomerReviews = recentlySpacifiedCustomerReviews;
    }
}
