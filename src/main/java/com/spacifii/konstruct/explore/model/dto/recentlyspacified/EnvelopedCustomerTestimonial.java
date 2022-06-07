package com.spacifii.konstruct.explore.model.dto.recentlyspacified;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spacifii.konstruct.explore.entities.recentlyspacified.CustomerTestimonial;
import com.spacifii.konstruct.explore.model.dto.explore.UserProfileMiniDto;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EnvelopedCustomerTestimonial {

    private UserProfileMiniDto userProfile;

    private UserProfileMiniDto userProfileInitiator;

    private CustomerTestimonial customerTestimonial;

    public EnvelopedCustomerTestimonial() {
    }

    public EnvelopedCustomerTestimonial(UserProfileMiniDto userProfile, UserProfileMiniDto userProfileInitiator, CustomerTestimonial customerTestimonial) {
        this.userProfile = userProfile;
        this.userProfileInitiator = userProfileInitiator;
        this.customerTestimonial = customerTestimonial;
    }

    public UserProfileMiniDto getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfileMiniDto userProfile) {
        this.userProfile = userProfile;
    }

    public CustomerTestimonial getCustomerTestimonial() {
        return customerTestimonial;
    }

    public void setCustomerTestimonial(CustomerTestimonial customerTestimonial) {
        this.customerTestimonial = customerTestimonial;
    }

    public UserProfileMiniDto getUserProfileInitiator() {
        return userProfileInitiator;
    }

    public void setUserProfileInitiator(UserProfileMiniDto userProfileInitiator) {
        this.userProfileInitiator = userProfileInitiator;
    }
}
