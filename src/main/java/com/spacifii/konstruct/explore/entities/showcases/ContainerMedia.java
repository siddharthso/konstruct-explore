package com.spacifii.konstruct.explore.entities.showcases;

import com.spacifii.konstruct.explore.annotation.NotToUseDuringMerge;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.LinkedHashSet;

@Document(collection = "CONTAINER_MEDIA")
public class ContainerMedia {

    @Id
    @NotToUseDuringMerge
    @Field(value = "ID")
    private String id;

    @Field(value = "GENERAL_MEDIA")
    private String generalMediaId;

    @Field(value = "URL")
    private String url;

    @Field(value = "ALT_URL")
    private String altUrl;

    @Field(value = "NAME")
    private String name;

    @Field(value = "DESCRIPTON")
    private String description;

    @NotToUseDuringMerge
    @Field(value = "COORDINATE_CONTAINERS")
    private LinkedHashSet<CoordinateContainer> coordinateContainers = new LinkedHashSet<>();


    @Field(value  = "CREATION_DATE")
    @NotToUseDuringMerge
    private Long creationDate;


    @Field(value  = "LAST_MODIFIED_DATE")
    @NotToUseDuringMerge
    private Long lastModifiedDate;

    @Field(value  = "CREATED_BY")
    @NotToUseDuringMerge
    private Long createdBy;


    @Field(value  = "LAST_MODIFIED_BY")
    @NotToUseDuringMerge
    private Long lastModifiedBy;

    @Version
    @Field(value  = "VERSION")
    @NotToUseDuringMerge
    private Integer version;

    public void preSave(Long subjectId){
        this.setCreatedBy(subjectId);
        this.setCreationDate(System.currentTimeMillis());
        this.setLastModifiedBy(subjectId);
        this.setLastModifiedDate(this.getCreationDate());
    }

    public void preUpdate(Long subjectId){
        this.setLastModifiedBy(subjectId);
        this.setLastModifiedDate(System.currentTimeMillis());
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

    public LinkedHashSet<CoordinateContainer> getCoordinateContainers() {
        return coordinateContainers;
    }

    public void setCoordinateContainers(LinkedHashSet<CoordinateContainer> coordinateContainers) {
        this.coordinateContainers = coordinateContainers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGeneralMediaId() {
        return generalMediaId;
    }

    public void setGeneralMediaId(String generalMediaId) {
        this.generalMediaId = generalMediaId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAltUrl() {
        return altUrl;
    }

    public void setAltUrl(String altUrl) {
        this.altUrl = altUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
