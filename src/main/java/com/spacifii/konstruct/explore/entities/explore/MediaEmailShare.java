package com.spacifii.konstruct.explore.entities.explore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.spacifii.konstruct.explore.annotation.Attribute;
import com.spacifii.konstruct.explore.annotation.NotToUseDuringMerge;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
@Document(collection = "MEDIA_EMAIL_SHARE")
public class MediaEmailShare {

    @Id
    @Field("ID")
    @Attribute(keyword = "MEDIAEMAILSHARE_ID", resolvedKeyword = "id")
    private String id;

    @Field("MEDIA_ID")
    @Attribute(keyword = "MEDIAEMAILSHARE_CREATIONDATE", resolvedKeyword = "mediaId")
    private String mediaId;

    @Field("NAME")
    @Attribute(keyword = "MEDIAEMAILSHARE_NAME", resolvedKeyword = "name")
    private String name;

    @Field("EMAIL_IDS_TO")
    @Attribute(keyword = "MEDIAEMAILSHARE_EMAILIDSTO", resolvedKeyword = "emailIdsTo")
    private Set<String> emailIdsTo;

    @Field("EMAIL_IDS_FROM")
    @Attribute(keyword = "MEDIAEMAILSHARE_EMAILIDFROM", resolvedKeyword = "emailIdFrom")
    private String emailIdFrom;

    @Field("MESSAGE")
    @Attribute(keyword = "MEDIAEMAILSHARE_MESSAGE", resolvedKeyword = "message")
    private String message;

    @Field("TNC_ACCEPTED")
    @Attribute(keyword = "MEDIAEMAILSHARE_MESSAGE", resolvedKeyword = "tncAccepted")
    private Boolean tncAccepted = Boolean.TRUE;

    @Attribute(keyword = "MEDIAEMAILSHARE_CREATIONDATE", resolvedKeyword = "creationDate")
    @Field(value  = "CREATION_DATE")
    @NotToUseDuringMerge
    private Long creationDate;

    @Attribute(keyword = "MEDIAEMAILSHARE_LASTMODIFIEDDATE", resolvedKeyword = "lastModifiedDate")
    @Field(value  = "LAST_MODIFIED_DATE")
    @NotToUseDuringMerge
    private Long lastModifiedDate;

    @Attribute(keyword = "MEDIAEMAILSHARE_CREATEDBY", resolvedKeyword = "createdBy")
    @Field(value  = "CREATED_BY")
    @NotToUseDuringMerge
    private Long createdBy;

    @Attribute(keyword = "MEDIAEMAILSHARE_LASTMODIFIEDBY", resolvedKeyword = "lastModifiedBy")
    @Field(value  = "LAST_MODIFIED_BY")
    @NotToUseDuringMerge
    private Long lastModifiedBy;

    @Version
    @Attribute(keyword = "MEDIAEMAILSHARE_VERSION", resolvedKeyword = "version")
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getTncAccepted() {
        return tncAccepted;
    }

    public void setTncAccepted(Boolean tncAccepted) {
        this.tncAccepted = tncAccepted;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public Set<String> getEmailIdsTo() {
        return emailIdsTo;
    }

    public void setEmailIdsTo(Set<String> emailIdsTo) {
        this.emailIdsTo = emailIdsTo;
    }

    public String getEmailIdFrom() {
        return emailIdFrom;
    }

    public void setEmailIdFrom(String emailIdFrom) {
        this.emailIdFrom = emailIdFrom;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
