package com.spacifii.konstruct.explore.entities.explore;

import com.spacifii.konstruct.explore.annotation.Attribute;
import com.spacifii.konstruct.explore.annotation.NotToUseDuringMerge;
import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardWalkThroughMediaMapping;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Document(collection = "EXPLORE_WALK_THROUGH")
public class ExploreWalkThrough {

    @Id
    @Attribute(keyword = "EXPLOREWALKTHROUGH_CREATIONDATE", resolvedKeyword = "creationDate")
    @Field("ID")
    private String id;

    @Attribute(keyword = "EXPLOREWALKTHROUGH_CREATIONDATE", resolvedKeyword = "creationDate")
    @Field("PROJECT_ID")
    private String projectId;

    @Attribute(keyword = "EXPLOREWALKTHROUGH_CREATIONDATE", resolvedKeyword = "creationDate")
    @Field("TITLE")
    private String title;

    @Attribute(keyword = "EXPLOREWALKTHROUGH_CREATIONDATE", resolvedKeyword = "creationDate")
    @Field("DESCRIPTION")
    private String description;

    @Attribute(keyword = "EXPLOREWALKTHROUGH_CREATIONDATE", resolvedKeyword = "creationDate")
    @Field("ACTIVE")
    private Boolean active;

    @Attribute(keyword = "EXPLOREWALKTHROUGH_CREATIONDATE", resolvedKeyword = "creationDate")
    @Field("SPACES")
    private Set<String> spaces = new HashSet<>();

    @Attribute(keyword = "EXPLOREWALKTHROUGH_CREATIONDATE", resolvedKeyword = "creationDate")
    @Field("PRODUCTS")
    private Set<String> products = new HashSet<>();

    @Attribute(keyword = "EXPLOREWALKTHROUGH_CREATIONDATE", resolvedKeyword = "creationDate")
    @Field("SERVICES")
    private Set<String> services = new HashSet<>();

    @Attribute(keyword = "EXPLOREWALKTHROUGH_CREATIONDATE", resolvedKeyword = "creationDate")
    @Field("EXPLORE_WALK_THROUGH_MEDIA_MAPPING")
    private LinkedHashSet<ExploreWalkThroughMediaMapping> exploreWalkThroughMediaMappings = new LinkedHashSet<>();

    @Attribute(keyword = "EXPLOREWALKTHROUGH_CREATIONDATE", resolvedKeyword = "creationDate")
    @Field(value  = "CREATION_DATE")
    @NotToUseDuringMerge
    private Long creationDate;

    @Attribute(keyword = "EXPLOREWALKTHROUGH_LASTMODIFIEDDATE", resolvedKeyword = "lastModifiedDate")
    @Field(value  = "LAST_MODIFIED_DATE")
    @NotToUseDuringMerge
    private Long lastModifiedDate;

    @Attribute(keyword = "EXPLOREWALKTHROUGH_CREATEDBY", resolvedKeyword = "createdBy")
    @Field(value  = "CREATED_BY")
    @NotToUseDuringMerge
    private Long createdBy;

    @Attribute(keyword = "EXPLOREWALKTHROUGH_LASTMODIFIEDBY", resolvedKeyword = "lastModifiedBy")
    @Field(value  = "LAST_MODIFIED_BY")
    @NotToUseDuringMerge
    private Long lastModifiedBy;

    @Version
    @Attribute(keyword = "EXPLOREWALKTHROUGH_VERSION", resolvedKeyword = "version")
    @Field(value  = "VERSION")
    @NotToUseDuringMerge
    private Integer version;

    public void preSave(Long subjectId){
        this.setCreatedBy(subjectId);
        this.setCreationDate(System.currentTimeMillis());
        this.setLastModifiedBy(subjectId);
        this.setLastModifiedDate(this.getCreationDate());
        this.mergeInfospots();
        // this.setVersion(0);
    }

    public void preUpdate(Long subjectId){
        this.setLastModifiedBy(subjectId);
        this.setLastModifiedDate(System.currentTimeMillis());
        this.setLastModifiedDate(this.getCreationDate());
        this.mergeInfospots();
        // this.setVersion(this.getVersion() + 1);
    }

    private void mergeInfospots() {
        if(this.exploreWalkThroughMediaMappings.size() > 0) {
            LinkedHashSet<ExploreWalkThroughMediaMapping> exploreWalkThroughMediaMappings = new LinkedHashSet<>();
            for (ExploreWalkThroughMediaMapping  exploreWalkThroughMediaMapping: this.getExploreWalkThroughMediaMappings()) {
                if (exploreWalkThroughMediaMapping.getOverriddenInfoSpots().size() > 0) {
                    Set<InfoSpot> infoSpots = new LinkedHashSet<>();
                    for (InfoSpot infoSpot : exploreWalkThroughMediaMapping.getOverriddenInfoSpots()) {
                        if (infoSpot.getId() == null) {
                            infoSpot.setId(UUID.randomUUID().toString());
                        }
                        infoSpots.add(infoSpot);
                    }
                    exploreWalkThroughMediaMapping.setOverriddenInfoSpots(infoSpots);
                }
                exploreWalkThroughMediaMappings.add(exploreWalkThroughMediaMapping);
            }
            this.setExploreWalkThroughMediaMappings(exploreWalkThroughMediaMappings);
        }
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Set<String> getSpaces() {
        return spaces;
    }

    public void setSpaces(Set<String> spaces) {
        this.spaces = spaces;
    }

    public Set<String> getProducts() {
        return products;
    }

    public void setProducts(Set<String> products) {
        this.products = products;
    }

    public Set<String> getServices() {
        return services;
    }

    public void setServices(Set<String> services) {
        this.services = services;
    }

    public LinkedHashSet<ExploreWalkThroughMediaMapping> getExploreWalkThroughMediaMappings() {
        return exploreWalkThroughMediaMappings;
    }

    public void setExploreWalkThroughMediaMappings(LinkedHashSet<ExploreWalkThroughMediaMapping> exploreWalkThroughMediaMappings) {
        this.exploreWalkThroughMediaMappings = exploreWalkThroughMediaMappings;
    }
}
