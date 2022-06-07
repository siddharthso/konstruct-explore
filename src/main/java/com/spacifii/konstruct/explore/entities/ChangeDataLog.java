package com.spacifii.konstruct.explore.entities;

import com.spacifii.konstruct.explore.annotation.Attribute;
import com.spacifii.konstruct.explore.annotation.NotToUseDuringMerge;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * This entity is used to track changes
 */
@Document(collection = "CHANGE_DATA_LOG")
public class ChangeDataLog {

    @Id
    @Attribute(keyword = "CHANGEDATALOG_ID", resolvedKeyword = "id")
    @Field("ID")
    private String id;

    @Attribute(keyword = "CHANGEDATALOG_SERVICENAME", resolvedKeyword = "serviceName")
    @Field("SERVICE_NAME")
    private String serviceName;

    @Attribute(keyword = "CHANGEDATALOG_CLASSNAME", resolvedKeyword = "className")
    @Field("CLASS_NAME")
    private String className;


    @Attribute(keyword = "CHANGEDATALOG_CHANGETYPE", resolvedKeyword = "className")
    @Field("CHANGE_TYPE")
    private ChangeType changeType;

    @Attribute(keyword = "CHANGEDATALOG_JSONBEFORE", resolvedKeyword = "json")
    @Field("JSON_BEFORE")
    private String jsonBefore;

    @Attribute(keyword = "CHANGEDATALOG_JSONAFTER", resolvedKeyword = "json")
    @Field("JSON_AFTER")
    private String jsonAfter;

    @Attribute(keyword = "CHANGEDATALOG_CREATIONDATE", resolvedKeyword = "creationDate")
    @Field(value  = "CREATION_DATE")
    @NotToUseDuringMerge
    private Long creationDate;

    @Attribute(keyword = "CHANGEDATALOG_LASTMODIFIEDDATE", resolvedKeyword = "lastModifiedDate")
    @Field(value  = "LAST_MODIFIED_DATE")
    @NotToUseDuringMerge
    private Long lastModifiedDate;

    @Attribute(keyword = "CHANGEDATALOG_CREATEDBY", resolvedKeyword = "createdBy")
    @Field(value  = "CREATED_BY")
    @NotToUseDuringMerge
    private Long createdBy;

    @Attribute(keyword = "CHANGEDATALOG_LASTMODIFIEDBY", resolvedKeyword = "lastModifiedBy")
    @Field(value  = "LAST_MODIFIED_BY")
    @NotToUseDuringMerge
    private Long lastModifiedBy;

    @Version
    @Attribute(keyword = "CHANGEDATALOG_VERSION", resolvedKeyword = "version")
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

    public ChangeType getChangeType() {
        return changeType;
    }

    public void setChangeType(ChangeType changeType) {
        this.changeType = changeType;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getJsonBefore() {
        return jsonBefore;
    }

    public void setJsonBefore(String jsonBefore) {
        this.jsonBefore = jsonBefore;
    }

    public String getJsonAfter() {
        return jsonAfter;
    }

    public void setJsonAfter(String jsonAfter) {
        this.jsonAfter = jsonAfter;
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
