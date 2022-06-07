package com.spacifii.konstruct.explore.model.dto.conceptBoard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardShortList;
import com.spacifii.konstruct.explore.model.dto.explore.UserProfileMiniDto;
import com.spacifii.konstruct.explore.model.dto.explore.UserProfileMiniDto2;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
public class EnvelopedConceptBoardShortList {
    private ConceptBoardEnveloped conceptBoardEnveloped;
    private List<ConceptBoardShortList> conceptBoardShortLists;
    private List<MediaShortListMapping> mediaShortListMappings;

    private UserProfileMiniDto2 profileMiniDto;
    private String emailid;


    public EnvelopedConceptBoardShortList() {
    }

    public EnvelopedConceptBoardShortList(List<ConceptBoardShortList> conceptBoardShortLists, UserProfileMiniDto2 profileMiniDto) {
        this.conceptBoardShortLists = conceptBoardShortLists;
        this.profileMiniDto = profileMiniDto;
    }

    public EnvelopedConceptBoardShortList(List<ConceptBoardShortList> conceptBoardShortLists, UserProfileMiniDto2 profileMiniDto, String emailid) {
        this.conceptBoardShortLists = conceptBoardShortLists;
        this.profileMiniDto = profileMiniDto;
        this.emailid = emailid;
    }

    public EnvelopedConceptBoardShortList(List<ConceptBoardShortList> conceptBoardShortLists, List<MediaShortListMapping> mediaShortListMappings, UserProfileMiniDto2 profileMiniDto, String emailid) {
        this.conceptBoardShortLists = conceptBoardShortLists;
        this.mediaShortListMappings = mediaShortListMappings;
        this.profileMiniDto = profileMiniDto;
        this.emailid = emailid;
    }

    public ConceptBoardEnveloped getConceptBoardEnveloped() {
        return conceptBoardEnveloped;
    }

    public void setConceptBoardEnveloped(ConceptBoardEnveloped conceptBoardEnveloped) {
        this.conceptBoardEnveloped = conceptBoardEnveloped;
    }

    public List<MediaShortListMapping> getMediaShortListMappings() {
        return mediaShortListMappings;
    }

    public void setMediaShortListMappings(List<MediaShortListMapping> mediaShortListMappings) {
        this.mediaShortListMappings = mediaShortListMappings;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public List<ConceptBoardShortList> getConceptBoardShortLists() {
        return conceptBoardShortLists;
    }

    public void setConceptBoardShortLists(List<ConceptBoardShortList> conceptBoardShortLists) {
        this.conceptBoardShortLists = conceptBoardShortLists;
    }

    public UserProfileMiniDto2 getProfileMiniDto() {
        return profileMiniDto;
    }

    public void setProfileMiniDto(UserProfileMiniDto2 profileMiniDto) {
        this.profileMiniDto = profileMiniDto;
    }
}
