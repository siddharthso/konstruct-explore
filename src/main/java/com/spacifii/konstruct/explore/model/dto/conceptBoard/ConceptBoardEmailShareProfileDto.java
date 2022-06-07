package com.spacifii.konstruct.explore.model.dto.conceptBoard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.spacifii.konstruct.explore.model.dto.explore.UserProfileMiniDto;
import com.spacifii.konstruct.explore.model.dto.explore.UserProfileMiniDto2;

@JsonPropertyOrder(alphabetic = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConceptBoardEmailShareProfileDto {

    private String email;

    private UserProfileMiniDto2 profileMiniDto;

    public ConceptBoardEmailShareProfileDto() {
    }

    public ConceptBoardEmailShareProfileDto(String email, UserProfileMiniDto2 profileMiniDto) {
        this.email = email;
        this.profileMiniDto = profileMiniDto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserProfileMiniDto2 getProfileMiniDto() {
        return profileMiniDto;
    }

    public void setProfileMiniDto(UserProfileMiniDto2 profileMiniDto) {
        this.profileMiniDto = profileMiniDto;
    }
}
