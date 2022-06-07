package com.spacifii.konstruct.explore.entities.generalMedia;

import com.spacifii.konstruct.explore.annotation.Attribute;
import com.spacifii.konstruct.explore.annotation.NotToUseDuringMerge;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Set;

@Document(collection = "GENRAL_MEDIA")
public class GeneralMedia {


    @Id
    @Field(value = "ID")
    @Attribute(keyword = "GENERALMEDIA_ID", resolvedKeyword = "id", enableFindBy = true)
    private String id;

    @Indexed
    @Attribute(keyword = "GENERALMEDIA_FILENAME", resolvedKeyword = "fileName")
    @Field(value = "FILE_NAME")
    private String fileName;

    @Attribute(keyword = "GENERALMEDIA_EXTENSION", resolvedKeyword = "extension")
    @Field(value = "EXTENSION")
    private String extension;

    @Attribute(keyword = "GENERALMEDIA_ORIGINALFILENAME", resolvedKeyword = "originalFilename")
    @Field(value = "ORIGINAL_FILENAME")
    private String originalFilename;

    @Attribute(keyword = "GENERALMEDIA_KEYWORDS", resolvedKeyword = "keywords")
    @Field(value = "KEYWORDS")
    private Set<String> keywords;

    @Attribute(keyword = "GENERALMEDIA_URL", resolvedKeyword = "url")
    @Field(value = "URL")
    private String url;

    @Attribute(keyword = "GENERALMEDIA_ALTURL", resolvedKeyword = "altUrl")
    @Field(value = "ALT_URL")
    private String altUrl;

    @Attribute(keyword = "GENERALMEDIA_CHECKSUM", resolvedKeyword = "checksum")
    @Field(value = "CHECKSUM")
    private String checksum;

    @Attribute(keyword = "GENERALMEDIA_CREATIONDATE", resolvedKeyword = "creationDate")
    @Field(value  = "CREATION_DATE")
    @NotToUseDuringMerge
    private Long creationDate;

    @Attribute(keyword = "GENERALMEDIA_LASTMODIFIEDDATE", resolvedKeyword = "lastModifiedDate")
    @Field(value  = "LAST_MODIFIED_DATE")
    @NotToUseDuringMerge
    private Long lastModifiedDate;

    @Attribute(keyword = "GENERALMEDIA_CREATEDBY", resolvedKeyword = "createdBy")
    @Field(value  = "CREATED_BY")
    @NotToUseDuringMerge
    private Long createdBy;

    @Attribute(keyword = "GENERALMEDIA_LASTMODIFIEDBY", resolvedKeyword = "lastModifiedBy")
    @Field(value  = "LAST_MODIFIED_BY")
    @NotToUseDuringMerge
    private Long lastModifiedBy;

    @Version
    @Attribute(keyword = "GENERALMEDIA_VERSION", resolvedKeyword = "version")
    @Field(value  = "VERSION")
    @NotToUseDuringMerge
    private Integer version;

    public String getAltUrl() {
        return altUrl;
    }

    public void setAltUrl(String altUrl) {
        this.altUrl = altUrl;
    }

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

    public String getChecksum() {
        return checksum;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public Set<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(Set<String> keywords) {
        this.keywords = keywords;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
