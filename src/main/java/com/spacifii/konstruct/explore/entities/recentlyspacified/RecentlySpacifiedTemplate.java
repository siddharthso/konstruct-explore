package com.spacifii.konstruct.explore.entities.recentlyspacified;


import com.spacifii.konstruct.explore.annotation.Attribute;
import com.spacifii.konstruct.explore.annotation.NotToUseDuringMerge;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.lang.annotation.ElementType;
import java.util.HashMap;
import java.util.Map;

@Document(collection = "RECENTLY_SPACIFIED_TEMPLATE")
public class RecentlySpacifiedTemplate {

    @Id
    @NotToUseDuringMerge
    @Attribute(keyword = "RECENTLYSPACIFIEDTEMPLATE_ID", resolvedKeyword = "id",enableFindBy = true)
    @Field(value  = "ID")
    private String id;

    @Attribute(keyword = "RECENTLYSPACIFIEDTEMPLATE_ACTIVE", resolvedKeyword = "active")
    @Field(value  = "ACTIVE")
    private Boolean active = Boolean.TRUE;

    @Attribute(keyword = "RECENTLYSPACIFIEDTEMPLATE_TEMPLATENAME", resolvedKeyword = "templateName")
    @Field("TEMPLATE_NAME")
    private String templateName;

    @Attribute(keyword = "RECENTLYSPACIFIEDTEMPLATE_DATA", resolvedKeyword = "templateData")
    @Field(value  = "TEMPLATE_DATA")
    private String templateData;

    @Attribute(keyword = "RECENTLYSPACIFIEDTEMPLATE_KEYWORDELEMENTTYPEMAPPING", resolvedKeyword = "elementsKeywordsMapping")
    @Field(value = "KEYWORD_ELEMENTTYPE_MAPPING")
    private Map<String, ElementType> keywordElementTypeMapping = new HashMap<>();

    @Attribute(keyword = "RECENTLYSPACIFIEDTEMPLATE_CREATIONDATE", resolvedKeyword = "creationDate")
    @Field(value  = "KEYWORDS_INPUT")
    private Map<String,Object> keywordsInput = new HashMap<>();

    @Attribute(keyword = "RECENTLYSPACIFIEDTEMPLATE_KEYWORDSDEFAULTVALUES", resolvedKeyword = "keywordsDefaultValues")
    @Field(value  = "KEYWORDS_DEFAULT_VALUES")
    private Map<String,Object> keywordsDefaultValues = new HashMap<>();

    @Attribute(keyword = "RECENTLYSPACIFIEDTEMPLATE_CREATIONDATE", resolvedKeyword = "creationDate")
    @Field(value  = "CREATION_DATE")
    @NotToUseDuringMerge
    private Long creationDate;

    @Attribute(keyword = "RECENTLYSPACIFIEDTEMPLATE_LASTMODIFIEDDATE", resolvedKeyword = "lastModifiedDate")
    @Field(value  = "LAST_MODIFIED_DATE")
    @NotToUseDuringMerge
    private Long lastModifiedDate;

    @Attribute(keyword = "RECENTLYSPACIFIEDTEMPLATE_CREATEDBY", resolvedKeyword = "createdBy")
    @Field(value  = "CREATED_BY")
    @NotToUseDuringMerge
    private Long createdBy;

    @Attribute(keyword = "RECENTLYSPACIFIEDTEMPLATE_LASTMODIFIEDBY", resolvedKeyword = "lastModifiedBy")
    @Field(value  = "LAST_MODIFIED_BY")
    @NotToUseDuringMerge
    private Long lastModifiedBy;

    @Version
    @Attribute(keyword = "RECENTLYSPACIFIEDTEMPLATE_VERSION", resolvedKeyword = "version")
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateData() {
        return templateData;
    }

    public void setTemplateData(String templateData) {
        this.templateData = templateData;
    }

    public Map<String, ElementType> getKeywordElementTypeMapping() {
        return keywordElementTypeMapping;
    }

    public void setKeywordElementTypeMapping(Map<String, ElementType> keywordElementTypeMapping) {
        this.keywordElementTypeMapping = keywordElementTypeMapping;
    }

    public Map<String, Object> getKeywordsInput() {
        return keywordsInput;
    }

    public void setKeywordsInput(Map<String, Object> keywordsInput) {
        this.keywordsInput = keywordsInput;
    }

    public Map<String, Object> getKeywordsDefaultValues() {
        return keywordsDefaultValues;
    }

    public void setKeywordsDefaultValues(Map<String, Object> keywordsDefaultValues) {
        this.keywordsDefaultValues = keywordsDefaultValues;
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
