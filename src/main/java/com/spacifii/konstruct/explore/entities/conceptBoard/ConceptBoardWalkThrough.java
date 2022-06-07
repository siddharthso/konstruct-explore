package com.spacifii.konstruct.explore.entities.conceptBoard;

import com.spacifii.konstruct.explore.annotation.Attribute;
import com.spacifii.konstruct.explore.annotation.NotToUseDuringMerge;
import com.spacifii.konstruct.explore.entities.explore.InfoSpot;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Document(collection = "CONCEPT_BOARD_WALK_THROUGH")
public class ConceptBoardWalkThrough {

    @Id
    @Attribute(keyword = "CONCEPTBOARDWALKTHROUGH_ID", resolvedKeyword = "id")
    @Field("ID")
    private String id;

    @Indexed
    @Attribute(keyword = "CONCEPTBOARDWALKTHROUGH_CONCEPTBOARDID", resolvedKeyword = "conceptBoardId")
    @Field("CONCEPT_BOARD_ID")
    private String conceptBoardId;

    @Attribute(keyword = "CONCEPTBOARDWALKTHROUGH_TITLE", resolvedKeyword = "title")
    @Field("TITLE")
    private String title;

    @Attribute(keyword = "CONCEPTBOARDWALKTHROUGH_DESCRIPTION", resolvedKeyword = "description")
    @Field("DESCRIPTION")
    private String description;

    @Attribute(keyword = "CONCEPTBOARDWALKTHROUGH_ACTIVE", resolvedKeyword = "active")
    @Field("ACTIVE")
    private Boolean active;

    @Attribute(keyword = "CONCEPTBOARDWALKTHROUGH_SPACES", resolvedKeyword = "spaces")
    @Field("SPACES")
    private Set<String> spaces = new HashSet<>();

    @Attribute(keyword = "CONCEPTBOARDWALKTHROUGH_PRODUCTS", resolvedKeyword = "products")
    @Field("PRODUCTS")
    private Set<String> products = new HashSet<>();

    @Attribute(keyword = "CONCEPTBOARDWALKTHROUGH_SERVICES", resolvedKeyword = "services")
    @Field("SERVICES")
    private Set<String> services = new HashSet<>();

    @Attribute(keyword = "CONCEPTBOARDWALKTHROUGH_CONCEPTBOARDWALKTHROUGHMEDIAMAPPING", resolvedKeyword = "conceptBoardWalkThroughMediaMappings")
    @Field("CONCEPT_BOARD_WALK_THROUGH_MEDIA_MAPPING")
    private LinkedHashSet<ConceptBoardWalkThroughMediaMapping> conceptBoardWalkThroughMediaMappings = new LinkedHashSet<>();

    @Attribute(keyword = "CONCEPTBOARDWALKTHROUGH_CREATIONDATE", resolvedKeyword = "creationDate")
    @Field(value  = "CREATION_DATE")
    @NotToUseDuringMerge
    private Long creationDate;

    @Attribute(keyword = "CONCEPTBOARDWALKTHROUGH_LASTMODIFIEDDATE", resolvedKeyword = "lastModifiedDate")
    @Field(value  = "LAST_MODIFIED_DATE")
    @NotToUseDuringMerge
    private Long lastModifiedDate;

    @Attribute(keyword = "CONCEPTBOARDWALKTHROUGH_CREATEDBY", resolvedKeyword = "createdBy")
    @Field(value  = "CREATED_BY")
    @NotToUseDuringMerge
    private Long createdBy;

    @Attribute(keyword = "CONCEPTBOARDWALKTHROUGH_LASTMODIFIEDBY", resolvedKeyword = "lastModifiedBy")
    @Field(value  = "LAST_MODIFIED_BY")
    @NotToUseDuringMerge
    private Long lastModifiedBy;

    @Version
    @Attribute(keyword = "CONCEPTBOARDWALKTHROUGH_VERSION", resolvedKeyword = "version")
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
        if(this.conceptBoardWalkThroughMediaMappings.size() > 0) {
            LinkedHashSet<ConceptBoardWalkThroughMediaMapping> conceptBoardWalkThroughMediaMappings = new LinkedHashSet<>();
            for (ConceptBoardWalkThroughMediaMapping  conceptBoardWalkThroughMediaMapping: this.conceptBoardWalkThroughMediaMappings) {
                if (conceptBoardWalkThroughMediaMapping.getOverriddenInfoSpots().size() > 0) {
                    Set<InfoSpot> infoSpots = new LinkedHashSet<>();
                    for (InfoSpot infoSpot : conceptBoardWalkThroughMediaMapping.getOverriddenInfoSpots()) {
                        if (infoSpot.getId() == null) {
                            infoSpot.setId(UUID.randomUUID().toString());
                        }
                        infoSpots.add(infoSpot);
                    }
                    conceptBoardWalkThroughMediaMapping.setOverriddenInfoSpots(infoSpots);
                }
                conceptBoardWalkThroughMediaMappings.add(conceptBoardWalkThroughMediaMapping);
            }
            this.setConceptBoardWalkThroughMediaMappings(conceptBoardWalkThroughMediaMappings);
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

    public String getConceptBoardId() {
        return conceptBoardId;
    }

    public void setConceptBoardId(String conceptBoardId) {
        this.conceptBoardId = conceptBoardId;
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

    public LinkedHashSet<ConceptBoardWalkThroughMediaMapping> getConceptBoardWalkThroughMediaMappings() {
        return conceptBoardWalkThroughMediaMappings;
    }

    public void setConceptBoardWalkThroughMediaMappings(LinkedHashSet<ConceptBoardWalkThroughMediaMapping> conceptBoardWalkThroughMediaMappings) {
        this.conceptBoardWalkThroughMediaMappings = conceptBoardWalkThroughMediaMappings;
    }
}
