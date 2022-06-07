package com.spacifii.konstruct.explore.model.dto.conceptBoard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spacifii.konstruct.explore.model.dto.explore.UserProfileMiniDto;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailIdUserProfileDto {
    private String emailId;
    private UserProfileMiniDto profileMiniDto;

    public EmailIdUserProfileDto() {
    }

    public EmailIdUserProfileDto(String emailId, UserProfileMiniDto profileMiniDto) {
        this.emailId = emailId;
        this.profileMiniDto = profileMiniDto;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public UserProfileMiniDto getProfileMiniDto() {
        return profileMiniDto;
    }

    public void setProfileMiniDto(UserProfileMiniDto profileMiniDto) {
        this.profileMiniDto = profileMiniDto;
    }
}
