package com.spacifii.konstruct.explore.entities.recentlyspacified;


import com.spacifii.konstruct.explore.annotation.Attribute;
import com.spacifii.konstruct.explore.annotation.NotToUseDuringMerge;
import com.spacifii.konstruct.explore.entities.explore.ExploreProject;
import com.spacifii.konstruct.explore.entities.explore.MediaType;
import com.spacifii.konstruct.explore.model.dto.recentlyspacified.RecentlySpacifiedRequestFeaturedDto;
import com.spacifii.konstruct.explore.model.dto.recentlyspacified.RecentlySpacifiedRequestKeywordTagsCagtegoriesDto;
import com.spacifii.konstruct.explore.model.dto.recentlyspacified.RecentlySpacifiedRequestMainSectionDto;
import com.spacifii.konstruct.explore.model.dto.recentlyspacified.RecentlySpacifiedRequestPermissionDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.*;

@Document(collection = "RECENTLY_SPACIFIED_PROJECT")
public class RecentlySpacifiedProject {

    @Id
    @Field(value = "PROJECT_ID")
    private String projectId;

    @Field(value = "BEFORE_AFTER_MEDIAS")
    private LinkedHashSet<BeforeAfterMedia> beforeAfterMedias = new LinkedHashSet<>();

    @Field(value = "RECENTLY_SPACIFIED_MEDIAS")
    private LinkedHashSet<RecentlySpacifiedMedia> recentlySpacifiedMedias = new LinkedHashSet<>();

    @Field(value = "TITLE")
    @Attribute(keyword = "RECENTLYSPACIFIED_TITLE", resolvedKeyword = "title")
    private String title;

    @Indexed
    @Field(value = "ROUTE_URL")
    @Attribute(keyword = "RECENTLYSPACIFIED_ROUTEURL", resolvedKeyword = "routeUrl")
    private String routeUrl;

    @Field(value = "DESCRIPTION")
    @Attribute(keyword = "RECENTLYSPACIFIED_DESCRIPTION", resolvedKeyword = "description")
    private String description;

    @Field(value = "FEATURED_TITLE")
    @Attribute(keyword = "RECENTLYSPACIFIED_FEATUREDTITLE", resolvedKeyword = "featuredTitle")
    private String featuredTitle;

    @Field(value = "FEATURED_DESCRIPTION")
    @Attribute(keyword = "RECENTLYSPACIFIED_FEATUREDDESCRIPTION", resolvedKeyword = "featuredDescription")
    private String featuredDescription;


    @Indexed
    @Field(value = "FEATURED")
    @Attribute(keyword = "RECENTLYSPACIFIED_FEATURED", resolvedKeyword = "featured")
    private Boolean featured = Boolean.FALSE;

    @Indexed
    @Field(value = "FEATURED_HOME")
    @Attribute(keyword = "RECENTLYSPACIFIED_FEATUREHOME", resolvedKeyword = "featuredHome")
    private Boolean featuredHome = Boolean.FALSE;

    @Field(value = "FEATURED_TYPE")
    @Attribute(keyword = "RECENTLYSPACIFIED_FEATUREDTITLE", resolvedKeyword = "featuredType")
    private String featuredType;

    @Field(value = "PROPERTY_TYPE")
    @Attribute(keyword = "RECENTLYSPACIFIED_PROPTERTYTYPE", resolvedKeyword = "featuredType")
    private String propertyType;

    @Field(value = "PROPERTY_STYLES")
    @Attribute(keyword = "RECENTLYSPACIFIED_PROJECTSTYLES", resolvedKeyword = "featuredType")
    private Set<String> projectStyles = new HashSet<>();

    @Field(value = "PROPERTY_SPACES")
    @Attribute(keyword = "RECENTLYSPACIFIED_SPACES", resolvedKeyword = "featuredType")
    private Set<String> spaces = new HashSet<>();

    @Field(value = "FEATURED_MEDIA_URL")
    @Attribute(keyword = "RECENTLYSPACIFIED_FEATUREDMEDIAURL", resolvedKeyword = "featuredMediaUrl")
    private String featuredMediaUrl;

    @Field(value = "FEATURED_MEDIA_TYPE")
    @Attribute(keyword = "RECENTLYSPACIFIED_FEATUREDMEDIATYPE", resolvedKeyword = "featuredMediaType")
    private MediaType featuredMediaType;

    @Field(value = "FEATURED_KEYWORDS")
    @Attribute(keyword = "RECENTLYSPACIFIED_KEYWORDS", resolvedKeyword = "keywords")
    private Set<String> keywords;

    @Field(value = "FEATURED_CATEGORIES")
    @Attribute(keyword = "RECENTLYSPACIFIED_CATEGORIES", resolvedKeyword = "categories")
    private Set<String> categories;

    @Field(value = "FEATURED_TAGS")
    @Attribute(keyword = "RECENTLYSPACIFIED_TAGS", resolvedKeyword = "tags")
    private Set<String> tags;


    @Field(value = "REVIEW_STATUS")
    @Attribute(keyword = "RECENTLYSPACIFIED_RECENTLYSPACIFIED", resolvedKeyword = "recentlySpacifiedStatus")
    private RecentlySpacifiedStatus recentlySpacifiedStatus = RecentlySpacifiedStatus.DRAFT;

    @Field(value = "BLOG_VISIBILITY")
    @Attribute(keyword = "RECENTLYSPACIFIED_RECENTLYSPACIFIEDVISIBILITY", resolvedKeyword = "recentlySpacifiedVisibility")
    private RecentlySpacifiedVisibility recentlySpacifiedVisibility = RecentlySpacifiedVisibility.PRIVATE;

    @Field(value = "FEATURED_SHOW_AUTHOR_INFO")
    @Attribute(keyword = "RECENTLYSPACIFIED_SHOWAUTHORINFO", resolvedKeyword = "showAuthorInfo")
    private Boolean showAuthorInfo = Boolean.TRUE;

    @Field(value = "FEATURED_ALLOW_COMMENTS")
    @Attribute(keyword = "RECENTLYSPACIFIED_ALLOWCOMMENTS", resolvedKeyword = "allowComments")
    private Boolean allowComments= Boolean.TRUE;

    @Field(value = "FEATURED_SHOW_RELATED_POSTS")
    @Attribute(keyword = "RECENTLYSPACIFIED_SHOWRELATEDPOSTS", resolvedKeyword = "showRelatedPosts")
    private Boolean showRelatedPosts = Boolean.TRUE;

    @Field(value = "FEATURED_SHOE_SOCIAL_SHARE_BOX")
    @Attribute(keyword = "RECENTLYSPACIFIED_SHOWSOCIALSHAREBOX", resolvedKeyword = "showSocialShareBox")
    private Boolean showSocialShareBox = Boolean.TRUE;

    @Field(value = "FEATURED_OPEN_IN_NEW_WINDOW")
    @Attribute(keyword = "RECENTLYSPACIFIED_OPENINNEWWINDOW", resolvedKeyword = "openInNewWindow")
    private Boolean openInNewWindow = Boolean.TRUE;

    @Field(value = "TEMPLATE_ID")
    @Attribute(keyword = "RECENTLYSPACIFIED_TEMPLATEID", resolvedKeyword = "templateId")
    private String templateId;

    @Field(value = "KEYWORDS_INPUT")
    @Attribute(keyword = "RECENTLYSPACIFIED_KEYWORDSINPUT", resolvedKeyword = "keywordsInput")
    private Map<String,Object> keywordsInput = new HashMap<>();

    @Attribute(keyword = "RECENTLYSPACIFIED_CREATIONDATE", resolvedKeyword = "creationDate")
    @Field(value  = "CREATION_DATE")
    @NotToUseDuringMerge
    private Long creationDate;

    @Attribute(keyword = "RECENTLYSPACIFIED_LASTMODIFIEDDATE", resolvedKeyword = "lastModifiedDate")
    @Field(value  = "LAST_MODIFIED_DATE")
    @NotToUseDuringMerge
    private Long lastModifiedDate;

    @Attribute(keyword = "RECENTLYSPACIFIED_CREATEDBY", resolvedKeyword = "createdBy")
    @Field(value  = "CREATED_BY")
    @NotToUseDuringMerge
    private Long createdBy;

    @Attribute(keyword = "RECENTLYSPACIFIED_LASTMODIFIEDBY", resolvedKeyword = "lastModifiedBy")
    @Field(value  = "LAST_MODIFIED_BY")
    @NotToUseDuringMerge
    private Long lastModifiedBy;


    @Version
    @Attribute(keyword = "RECENTLYSPACIFIED_VERSION", resolvedKeyword = "version")
    @Field(value  = "VERSION")
    @NotToUseDuringMerge
    private Integer version;




    public void preSave(Long subjectId){
        this.setCreatedBy(subjectId);
        this.setCreationDate(System.currentTimeMillis());
        this.setLastModifiedBy(subjectId);
        this.setLastModifiedDate(this.getCreationDate());

        // this.setVersion(0);
    }

    public void preUpdate(Long subjectId) {
        this.setLastModifiedBy(subjectId);
        this.setLastModifiedDate(System.currentTimeMillis());
    }


    public void mergeProjectInMe(ExploreProject exploreProject){
        this.setProjectId(exploreProject.getProjectId());
        this.setProjectStyles(exploreProject.getProjectStyles());
        this.setPropertyType(exploreProject.getPropertyType());
        this.setSpaces(exploreProject.getSpaces());
        this.setRouteUrl(exploreProject.getProjectName().replaceAll(" ","-").toUpperCase()+System.currentTimeMillis());
    }

    public void mergeInMe(RecentlySpacifiedRequestFeaturedDto recentlySpacifiedRequestFeaturedDto){
        this.setFeatured(recentlySpacifiedRequestFeaturedDto.getFeatured());
        this.setFeaturedHome(recentlySpacifiedRequestFeaturedDto.getFeaturedHome());
        this.setFeaturedMediaType(recentlySpacifiedRequestFeaturedDto.getFeaturedMediaType());
        this.setFeaturedMediaUrl(recentlySpacifiedRequestFeaturedDto.getFeaturedMediaUrl());
        this.setFeaturedType(recentlySpacifiedRequestFeaturedDto.getFeaturedType());
    }


    public void mergeInMe(RecentlySpacifiedRequestKeywordTagsCagtegoriesDto recentlySpacifiedRequestKeywordTagsCagtegoriesDto){
        this.setKeywords(recentlySpacifiedRequestKeywordTagsCagtegoriesDto.getKeywords());
        this.setTags(recentlySpacifiedRequestKeywordTagsCagtegoriesDto.getTags());
        this.setCategories(recentlySpacifiedRequestKeywordTagsCagtegoriesDto.getCategories());
    }

    public void mergeInMe(RecentlySpacifiedRequestMainSectionDto recentlySpacifiedRequestMainSectionDto){
        this.setTitle(recentlySpacifiedRequestMainSectionDto.getTitle());
        this.setDescription(recentlySpacifiedRequestMainSectionDto.getDescription());
        this.setFeaturedTitle(recentlySpacifiedRequestMainSectionDto.getFeaturedTitle());
        this.setFeaturedDescription(recentlySpacifiedRequestMainSectionDto.getFeaturedDescription());
    }

    public void mergeInMe(RecentlySpacifiedRequestPermissionDto recentlySpacifiedRequestPermissionDto){
        this.setRecentlySpacifiedStatus(recentlySpacifiedRequestPermissionDto.getRecentlySpacifiedStatus());
        this.setRecentlySpacifiedVisibility(recentlySpacifiedRequestPermissionDto.getRecentlySpacifiedVisibility());
        this.setShowAuthorInfo(recentlySpacifiedRequestPermissionDto.getShowAuthorInfo());
        this.setAllowComments(recentlySpacifiedRequestPermissionDto.getAllowComments());
        this.setOpenInNewWindow(recentlySpacifiedRequestPermissionDto.getOpenInNewWindow());
        this.setShowSocialShareBox(recentlySpacifiedRequestPermissionDto.getShowSocialShareBox());
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public Set<String> getProjectStyles() {
        return projectStyles;
    }

    public void setProjectStyles(Set<String> projectStyles) {
        this.projectStyles = projectStyles;
    }

    public Set<String> getSpaces() {
        return spaces;
    }

    public void setSpaces(Set<String> spaces) {
        this.spaces = spaces;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public LinkedHashSet<BeforeAfterMedia> getBeforeAfterMedias() {
        return beforeAfterMedias;
    }

    public void setBeforeAfterMedias(LinkedHashSet<BeforeAfterMedia> beforeAfterMedias) {
        this.beforeAfterMedias = beforeAfterMedias;
    }

    public LinkedHashSet<RecentlySpacifiedMedia> getRecentlySpacifiedMedias() {
        return recentlySpacifiedMedias;
    }

    public void setRecentlySpacifiedMedias(LinkedHashSet<RecentlySpacifiedMedia> recentlySpacifiedMedias) {
        this.recentlySpacifiedMedias = recentlySpacifiedMedias;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRouteUrl() {
        return routeUrl;
    }

    public void setRouteUrl(String routeUrl) {
        this.routeUrl = routeUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFeaturedTitle() {
        return featuredTitle;
    }

    public void setFeaturedTitle(String featuredTitle) {
        this.featuredTitle = featuredTitle;
    }

    public String getFeaturedDescription() {
        return featuredDescription;
    }

    public void setFeaturedDescription(String featuredDescription) {
        this.featuredDescription = featuredDescription;
    }

    public Boolean getFeatured() {
        return featured;
    }

    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }

    public Boolean getFeaturedHome() {
        return featuredHome;
    }

    public void setFeaturedHome(Boolean featuredHome) {
        this.featuredHome = featuredHome;
    }

    public String getFeaturedType() {
        return featuredType;
    }

    public void setFeaturedType(String featuredType) {
        this.featuredType = featuredType;
    }

    public String getFeaturedMediaUrl() {
        return featuredMediaUrl;
    }

    public void setFeaturedMediaUrl(String featuredMediaUrl) {
        this.featuredMediaUrl = featuredMediaUrl;
    }

    public MediaType getFeaturedMediaType() {
        return featuredMediaType;
    }

    public void setFeaturedMediaType(MediaType featuredMediaType) {
        this.featuredMediaType = featuredMediaType;
    }

    public Set<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(Set<String> keywords) {
        this.keywords = keywords;
    }

    public Set<String> getCategories() {
        return categories;
    }

    public void setCategories(Set<String> categories) {
        this.categories = categories;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
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

    public Boolean getShowRelatedPosts() {
        return showRelatedPosts;
    }

    public void setShowRelatedPosts(Boolean showRelatedPosts) {
        this.showRelatedPosts = showRelatedPosts;
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

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public Map<String, Object> getKeywordsInput() {
        return keywordsInput;
    }

    public void setKeywordsInput(Map<String, Object> keywordsInput) {
        this.keywordsInput = keywordsInput;
    }

    public Long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Long creationDate) {
        this.creationDate = creationDate;
    }

    public Long getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Long lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(Long lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }
}
