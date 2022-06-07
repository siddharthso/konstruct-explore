package com.spacifii.konstruct.explore.model.dto.recentlyspacified;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spacifii.konstruct.explore.entities.recentlyspacified.RecentlySpacifiedCustomerReview;
import com.spacifii.konstruct.explore.model.dto.explore.UserProfileMiniDto;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EnvelopedCustomerReview {

    private  UserProfileMiniDto userProfile;

    private UserProfileMiniDto userProfileMiniInitiator;

    private RecentlySpacifiedCustomerReview recentlySpacifiedCustomerReview;

    public EnvelopedCustomerReview(UserProfileMiniDto userProfile, UserProfileMiniDto userProfileMiniInitiator, RecentlySpacifiedCustomerReview recentlySpacifiedCustomerReview) {
        this.userProfile = userProfile;
        this.userProfileMiniInitiator = userProfileMiniInitiator;
        this.recentlySpacifiedCustomerReview = recentlySpacifiedCustomerReview;
    }

    public EnvelopedCustomerReview() {
    }

    public UserProfileMiniDto getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfileMiniDto userProfile) {
        this.userProfile = userProfile;
    }

    public RecentlySpacifiedCustomerReview getRecentlySpacifiedCustomerReview() {
        return recentlySpacifiedCustomerReview;
    }

    public void setRecentlySpacifiedCustomerReview(RecentlySpacifiedCustomerReview recentlySpacifiedCustomerReview) {
        this.recentlySpacifiedCustomerReview = recentlySpacifiedCustomerReview;
    }

    public UserProfileMiniDto getUserProfileMiniInitiator() {
        return userProfileMiniInitiator;
    }

    public void setUserProfileMiniInitiator(UserProfileMiniDto userProfileMiniInitiator) {
        this.userProfileMiniInitiator = userProfileMiniInitiator;
    }
}
