package com.spacifii.konstruct.explore.entities.explore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spacifii.konstruct.explore.annotation.Attribute;
import com.spacifii.konstruct.explore.annotation.NotToUseDuringMerge;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * This is Entity Class for ExploreKeyword
 */
@Document(collection = "EXPLORE_KEYWORD")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExploreKeyword{

    @Id
    @Attribute(keyword = "EXPLOREKEYWORD_ID", resolvedKeyword = "id", enableFindBy = true)
    @Field(value = "ID")
    private String id;


    @Attribute(keyword = "EXPLOREKEYWORD_KEYWORDCATEGORY", resolvedKeyword = "keyword")
    @Field(value = "KEYWORD")
    private String keyword;


    @Attribute(keyword = "EXPLOREKEYWORD_KEYWORDCATEGORY", resolvedKeyword = "keywordDisplay")
    @Field(value = "KEYWORD_DISPLAY")
    private String keywordDisplay;


    @Attribute(keyword = "EXPLOREKEYWORD_DESCRIPTION", resolvedKeyword = "description")
    @Field(value = "KEYWORD_DESCRIPTION")
    private String description;


    @Attribute(keyword = "EXPLOREKEYWORD_LOGOURL", resolvedKeyword = "logoUrl")
    @Field(value = "KEYWORD_LOGOURL")
    private String logoUrl;

    @Attribute(keyword = "EXPLOREKEYWORD_KEYWORDCATEGORY", resolvedKeyword = "keywordCategory")
    @Field(value = "KEYWORD_CATEGORY")
    private KEYWORD_CATEGORY keywordCategory;


    @Attribute(keyword = "EXPLOREKEYWORD_KEYWORDSOURCE", resolvedKeyword = "keywordSource")
    @Field(value = "KEYWORD_SOURCE")
    private KEYWORD_SOURCE keywordSource = KEYWORD_SOURCE.SELF;

    @Attribute(keyword = "EXPLOREKEYWORD_CREATIONDATE", resolvedKeyword = "creationDate")
    @Field(value  = "CREATION_DATE")
    @NotToUseDuringMerge
    private Long creationDate;

    @Attribute(keyword = "EXPLOREKEYWORD_LASTMODIFIEDDATE", resolvedKeyword = "lastModifiedDate")
    @Field(value  = "LAST_MODIFIED_DATE")
    @NotToUseDuringMerge
    private Long lastModifiedDate;

    @Attribute(keyword = "EXPLOREKEYWORD_CREATEDBY", resolvedKeyword = "createdBy")
    @Field(value  = "CREATED_BY")
    @NotToUseDuringMerge
    private Long createdBy;

    @Attribute(keyword = "EXPLOREKEYWORD_LASTMODIFIEDBY", resolvedKeyword = "lastModifiedBy")
    @Field(value  = "LAST_MODIFIED_BY")
    @NotToUseDuringMerge
    private Long lastModifiedBy;

    @Version
    @Attribute(keyword = "EXPLOREKEYWORD_VERSION", resolvedKeyword = "version")
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

    public void preUpdate(Long subjectId){
        this.setLastModifiedBy(subjectId);
        this.setLastModifiedDate(System.currentTimeMillis());
        // this.setVersion(this.getVersion() + 1);
    }

    public KEYWORD_SOURCE getKeywordSource() {
        return keywordSource;
    }

    public void setKeywordSource(KEYWORD_SOURCE keywordSource) {
        this.keywordSource = keywordSource;
    }

    public String getKeywordDisplay() {
        return keywordDisplay;
    }

    public void setKeywordDisplay(String keywordDisplay) {
        this.keywordDisplay = keywordDisplay;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public KEYWORD_CATEGORY getKeywordCategory() {
        return keywordCategory;
    }

    public void setKeywordCategory(KEYWORD_CATEGORY keywordCategory) {
        this.keywordCategory = keywordCategory;
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
