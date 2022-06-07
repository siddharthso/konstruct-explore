package com.spacifii.konstruct.explore.entities.showcases;

import com.spacifii.konstruct.explore.annotation.NotToUseDuringMerge;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.*;

@Document(collection = "SHOWCASE")
public class Showcase {

    @Id
    @Field(value = "ID")
    @NotToUseDuringMerge
    private String id;

    @Field(value = "SPACE_ID")
    private String spaceId;

    @Field(value = "PROPERTY_ID")
    private String propertyId;

    @Field(value = "BUILDER_ID")
    private String builderId;

    @Field(value = "NAME")
    private String name;

    @Field(value = "DESCRIPTION")
    private String description;

    @DBRef
    @NotToUseDuringMerge
    @Field(value = "CONTAINER_MEDIA")
    private ContainerMedia containerMedia;

    @NotToUseDuringMerge
    @Field(value = "GROUP_COORDINATE_MAP")
    private Map<String,Coordinate> groupCoordinateMap = new LinkedHashMap<>();

    @DBRef
    @NotToUseDuringMerge
    @Field(value = "GROUP_PRODUCTS")
    private Map<String,Set<OfferingProduct>> groupProducts = new LinkedHashMap<>();

    @DBRef
    @NotToUseDuringMerge
    @Field(value = "MATERIALS")
    private Set<Material> materials = new LinkedHashSet<>();

    @DBRef
    @NotToUseDuringMerge
    @Field(value = "GROUP_DEFAULTS")
    private Map<String,OfferingProduct> groupDefaults = new LinkedHashMap<>();


    public Map<String, Set<OfferingProduct>> getGroupProducts() {
        return groupProducts;
    }

    public void setGroupProducts(Map<String, Set<OfferingProduct>> groupProducts) {
        this.groupProducts = groupProducts;
    }

    public Set<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(Set<Material> materials) {
        this.materials = materials;
    }

    public Map<String, OfferingProduct> getGroupDefaults() {
        return groupDefaults;
    }

    public void setGroupDefaults(Map<String, OfferingProduct> groupDefaults) {
        this.groupDefaults = groupDefaults;
    }

    public Map<String, Coordinate> getGroupCoordinateMap() {
        return groupCoordinateMap;
    }

    public void setGroupCoordinateMap(Map<String, Coordinate> groupCoordinateMap) {
        this.groupCoordinateMap = groupCoordinateMap;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(String spaceId) {
        this.spaceId = spaceId;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public String getBuilderId() {
        return builderId;
    }

    public void setBuilderId(String builderId) {
        this.builderId = builderId;
    }

    public ContainerMedia getContainerMedia() {
        return containerMedia;
    }

    public void setContainerMedia(ContainerMedia containerMedia) {
        this.containerMedia = containerMedia;
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
