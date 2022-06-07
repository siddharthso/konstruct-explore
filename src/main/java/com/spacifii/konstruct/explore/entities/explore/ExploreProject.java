 package com.spacifii.konstruct.explore.entities.explore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spacifii.konstruct.explore.annotation.Attribute;
import com.spacifii.konstruct.explore.annotation.NotToUseDuringMerge;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

 /**
  * This is entity cass for ExploreProject
  */
 @Document(collection = "EXPLORE_PROJECT")
 @JsonIgnoreProperties(ignoreUnknown = true)
public class ExploreProject {


    @Id
    @Attribute(keyword = "PROJECT_ID", resolvedKeyword = "id", enableFindBy = true)
    @Field(value = "ID")
    @NotToUseDuringMerge
    private String projectId;


    @Attribute(keyword = "PROJECT_SUBJECTID", resolvedKeyword = "subjectId")
    @Field(value = "SUBJECT_ID")
    @NotToUseDuringMerge
    private Long subjectId;

    @Attribute(keyword = "PROJECT_PROJECTNAME", resolvedKeyword = "projectName")
    @Field(value = "PROJECT_NAME")
    private String projectName;

    @Attribute(keyword = "PROJECT_PROJECTTYPE", resolvedKeyword = "projectType")
    @Field(value = "PROJECT_TYPE")
    private String projectType;

    @Attribute(keyword = "PROJECT_PROJECTDESCRIPTION", resolvedKeyword = "projectDescription")
    @Field(value = "PROJECT_DESCRIPTION")
    private String projectDescription;

    @Attribute(keyword = "PROJECT_PROPERTYTYPE", resolvedKeyword = "propertyType")
    @Field(value = "PROPERTY_TYPE")
    private String propertyType;

    @Attribute(keyword = "PROJECT_PROJECTADDRESS", resolvedKeyword = "projectAddress")
    @Field(value = "PROJECT_ADDRESS")
    private String projectAddress;

    @Attribute(keyword = "PROJECT_DEVELOPERORPROJECTNAME", resolvedKeyword = "developerOrProjectName")
    @Field(value = "DEVELOPER_OR_PROJECTNAME")
    private String developerOrProjectName;

    @Attribute(keyword = "PROJECT_PROJECTYEAR", resolvedKeyword = "projectYear")
    @Field(value = "PROJECT_YEAR")
    private Integer projectYear;

    @Attribute(keyword = "PROJECT_PROJECTBUDGETACTUAL", resolvedKeyword = "projectBudgetActual")
    @Field(value = "PROJECT_BUDGET_ACTUAL")
    private BigDecimal projectBudgetActual;

    @Attribute(keyword = "PROJECT_PROJECTBUDGETRANGE", resolvedKeyword = "projectBudgetRange")
    @Field(value = "PROJECT_BUDGET_RANGE")
    private String projectBudgetRange;

    @Attribute(keyword = "PROJECT_PROJECTBUDGETSTARTRANGE", resolvedKeyword = "projectBudgetStartRange")
    @Field(value = "PROJECT_BUDGET_START_RANGE")
    private BigDecimal projectBudgetStartRange;

    @Attribute(keyword = "PROJECT_PROJECTBUDGETENDRANGE", resolvedKeyword = "projectBudgetEndRange")
    @Field(value = "PROJECT_BUDGET_END_RANGE")
    private BigDecimal projectBudgetEndRange;


    @DBRef
     @Attribute(keyword = "PROJECT_FEATUREDPROJECTMEDIA", resolvedKeyword = "featuredProjectMedia")
     @Field(value = "FEATURED_PROJECT_MEDIA")
    @NotToUseDuringMerge
     private Media featuredProjectMedia;


    @Field(value = "PROJECT_STYLES")
    private Set<String> projectStyles = new HashSet<>();

    @Field(value = "SCOPE_TYPES")
    private Set<String> scopeTypes = new HashSet<>();

    @Field(value = "SPACES")
    private Set<String> spaces = new HashSet<>();

    @Attribute(keyword = "PROJECT_DURATION", resolvedKeyword = "duration")
    @Field(value = "DURATION")
    private BigDecimal duration;

    @Attribute(keyword = "PROIJECT_DURATIONTYPE", resolvedKeyword = "durationType")
    @Field(value = "DURATION_TYPE")
    private String durationType;

     @Attribute(keyword = "PROIJECT_ACTIVE", resolvedKeyword = "active")
     @Field(value = "ACTIVE")
     private Boolean active = true;

     @Attribute(keyword = "PROIJECT_ADDEDTORECENTLYSPACIFIEDANDACTIVE", resolvedKeyword = "addedToRecentlySpacifiedAndActive")
     @Field(value = "ADDED_TO_RECENTLY_SPACIFIED_AND_ACTIVE")
     private Boolean addedToRecentlySpacifiedAndActive = false;

    @Attribute(keyword = "PROJECT_CREATIONDATE", resolvedKeyword = "creationDate")
    @Field(value  = "CREATION_DATE")
    @NotToUseDuringMerge
    private Long creationDate;

    @Attribute(keyword = "PROJECT_LASTMODIFIEDDATE", resolvedKeyword = "lastModifiedDate")
    @Field(value  = "LAST_MODIFIED_DATE")
    @NotToUseDuringMerge
    private Long lastModifiedDate;

    @Attribute(keyword = "PROJECT_CREATEDBY", resolvedKeyword = "createdBy")
    @Field(value  = "CREATED_BY")
    @NotToUseDuringMerge
    private Long createdBy;

    @Attribute(keyword = "PROJECT_LASTMODIFIEDBY", resolvedKeyword = "lastModifiedBy")
    @Field(value  = "LAST_MODIFIED_BY")
    @NotToUseDuringMerge
    private Long lastModifiedBy;

    @Version
    @Attribute(keyword = "PROJECT_VERSION", resolvedKeyword = "version")
    @Field(value  = "VERSION")
    @NotToUseDuringMerge
    private Integer version;

     @Attribute(keyword = "PROJECT_PROJECTUSERACTIONSTATISTICS", resolvedKeyword = "projectUserActionStatistics")
     @Field(value  = "PROJECT_USER_ACTION_STATISTICS")
     @NotToUseDuringMerge
    private ProjectUserActionStatistics projectUserActionStatistics = new ProjectUserActionStatistics();

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

     public Set<String> getMyKeywords(){
         Set<String> strings = new HashSet<>();
         strings.addAll(this.getProjectStyles());
         strings.addAll(this.getScopeTypes());
         if(this.getPropertyType() != null) {
             strings.add(this.getProjectType());
         }
         return strings;
     }

     public String getProjectDescription() {
         return projectDescription;
     }

     public void setProjectDescription(String projectDescription) {
         this.projectDescription = projectDescription;
     }

     public String getProjectId() {
         return projectId;
     }

     public void setProjectId(String projectId) {
         this.projectId = projectId;
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

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getProjectAddress() {
        return projectAddress;
    }

    public void setProjectAddress(String projectAddress) {
        this.projectAddress = projectAddress;
    }

    public String getDeveloperOrProjectName() {
        return developerOrProjectName;
    }

    public void setDeveloperOrProjectName(String developerOrProjectName) {
        this.developerOrProjectName = developerOrProjectName;
    }

    public Integer getProjectYear() {
        return projectYear;
    }

    public void setProjectYear(Integer projectYear) {
        this.projectYear = projectYear;
    }

    public BigDecimal getProjectBudgetActual() {
        return projectBudgetActual;
    }

    public void setProjectBudgetActual(BigDecimal projectBudgetActual) {
        this.projectBudgetActual = projectBudgetActual;
    }

    public String getProjectBudgetRange() {
        return projectBudgetRange;
    }

    public void setProjectBudgetRange(String projectBudgetRange) {
        this.projectBudgetRange = projectBudgetRange;
    }

    public BigDecimal getProjectBudgetStartRange() {
        return projectBudgetStartRange;
    }

    public void setProjectBudgetStartRange(BigDecimal projectBudgetStartRange) {
        this.projectBudgetStartRange = projectBudgetStartRange;
    }

    public BigDecimal getProjectBudgetEndRange() {
        return projectBudgetEndRange;
    }

    public void setProjectBudgetEndRange(BigDecimal projectBudgetEndRange) {
        this.projectBudgetEndRange = projectBudgetEndRange;
    }

    public Set<String> getProjectStyles() {
        return projectStyles;
    }

    public void setProjectStyles(Set<String> projectStyles) {
        this.projectStyles = projectStyles;
    }

    public Set<String> getScopeTypes() {
        return scopeTypes;
    }

    public void setScopeTypes(Set<String> scopeTypes) {
        this.scopeTypes = scopeTypes;
    }

    public BigDecimal getDuration() {
        return duration;
    }

    public void setDuration(BigDecimal duration) {
        this.duration = duration;
    }

    public String getDurationType() {
        return durationType;
    }

    public void setDurationType(String durationType) {
        this.durationType = durationType;
    }

     public ProjectUserActionStatistics getProjectUserActionStatistics() {
         return projectUserActionStatistics;
     }

     public void setProjectUserActionStatistics(ProjectUserActionStatistics projectUserActionStatistics) {
         this.projectUserActionStatistics = projectUserActionStatistics;
     }

     public Media getFeaturedProjectMedia() {
         return featuredProjectMedia;
     }

     public void setFeaturedProjectMedia(Media featuredProjectMedia) {
         this.featuredProjectMedia = featuredProjectMedia;
     }

     public Boolean getActive() {
         return active;
     }

     public void setActive(Boolean active) {
         this.active = active;
     }

     public Boolean getAddedToRecentlySpacifiedAndActive() {
         return addedToRecentlySpacifiedAndActive;
     }

     public void setAddedToRecentlySpacifiedAndActive(Boolean addedToRecentlySpacifiedAndActive) {
         this.addedToRecentlySpacifiedAndActive = addedToRecentlySpacifiedAndActive;
     }

     public Set<String> getSpaces() {
         return spaces;
     }

     public void setSpaces(Set<String> spaces) {
         this.spaces = spaces;
     }
 }
