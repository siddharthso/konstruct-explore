package com.spacifii.konstruct.explore.model.dto.recentlyspacified;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import com.spacifii.konstruct.explore.entities.recentlyspacified.RecentlySpacifiedStatus;
import com.spacifii.konstruct.explore.entities.recentlyspacified.RecentlySpacifiedVisibility;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
public class RecentlySpacifiedRequestPermissionDto
{
    private String id;

    private RecentlySpacifiedStatus recentlySpacifiedStatus = RecentlySpacifiedStatus.DRAFT;

    private RecentlySpacifiedVisibility  recentlySpacifiedVisibility = RecentlySpacifiedVisibility.PRIVATE;

    private Boolean showAuthorInfo = Boolean.TRUE;

    private Boolean allowComments= Boolean.TRUE;

    private Boolean showRelatedProjects = Boolean.TRUE;

    private Boolean showSocialShareBox = Boolean.TRUE;

    private Boolean openInNewWindow = Boolean.TRUE;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getShowSocialShareBox() {
        return showSocialShareBox;
    }

    public void setShowSocialShareBox(Boolean showSocialShareBox) {
        this.showSocialShareBox = showSocialShareBox;
    }

    public Boolean getOpenInNewWindow() {
        return openInNewWindow;
    }

    public void setOpenInNewWindow(Boolean openInNewWindow) {
        this.openInNewWindow = openInNewWindow;
    }

    public RecentlySpacifiedStatus getRecentlySpacifiedStatus() {
        return recentlySpacifiedStatus;
    }

    public void setRecentlySpacifiedStatus(RecentlySpacifiedStatus recentlySpacifiedStatus) {
        this.recentlySpacifiedStatus = recentlySpacifiedStatus;
    }

    public RecentlySpacifiedVisibility getRecentlySpacifiedVisibility() {
        return recentlySpacifiedVisibility;
    }

    public void setRecentlySpacifiedVisibility(RecentlySpacifiedVisibility recentlySpacifiedVisibility) {
        this.recentlySpacifiedVisibility = recentlySpacifiedVisibility;
    }

    public Boolean getShowAuthorInfo() {
        return showAuthorInfo;
    }

    public void setShowAuthorInfo(Boolean showAuthorInfo) {
        this.showAuthorInfo = showAuthorInfo;
    }

    public Boolean getAllowComments() {
        return allowComments;
    }

    public void setAllowComments(Boolean allowComments) {
        this.allowComments = allowComments;
    }

    public Boolean getShowRelatedProjects() {
        return showRelatedProjects;
    }

    public void setShowRelatedProjects(Boolean showRelatedProjects) {
        this.showRelatedProjects = showRelatedProjects;
    }
}
