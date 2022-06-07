package com.spacifii.konstruct.explore.entities.conceptBoard;

import com.spacifii.konstruct.explore.annotation.Attribute;
import com.spacifii.konstruct.explore.annotation.NotToUseDuringMerge;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Document(collection = "CONCEPT_BOARD")
public class ConceptBoard {

    @Id
    @Field(value = "ID")
    @Attribute(keyword = "CONCEPTBOARD_ID", resolvedKeyword = "id")
    private String id;

    @Indexed
    @Attribute(keyword = "CONCEPTBOARD_SUBJECTID", resolvedKeyword = "subjectId")
    @Field(value = "SUBJECT_ID")
    private Long subjectId;

    @Indexed
    @Attribute(keyword = "CONCEPTBOARD_FOLDERNAME", resolvedKeyword = "folderName")
    @Field(value = "FOLDER_NAME")
    private String folderName;

    @Field(value = "DESCRIPTION")
    @Attribute(keyword = "CONCEPTBOARD_DESCRIPTION", resolvedKeyword = "description")
    private String description;

    @Field(value = "PARENT_CONCEPT_BARD")
    @Attribute(keyword = "CONCEPTBOARD_PARENTCONCEPTBOARD", resolvedKeyword = "parentConceptBoard")
    private String parentConceptBoard;

    @Field(value = "IS_PARENT")
    @Attribute(keyword = "CONCEPTBOARD_ISPARENT", resolvedKeyword = "isParent")
    private Boolean isParent;

    @Attribute(keyword = "CONCEPTBOARD_CONCEPTBOARDVISIBILITY", resolvedKeyword = "active")
    @Field(value = "CONCEPT_BOARD_VISIBILITY")
    private ConceptBoardVisibility conceptBoardVisibility = ConceptBoardVisibility.PRIVATE;

    @Attribute(keyword = "CONCEPTBOARD_EMAILWITHVIEWRIGHTS", resolvedKeyword = "active")
    @Field(value = "EMAIL_WITH_RIGHTS")
    private Set<String> emailWithViewRights = new TreeSet<>();

    @DBRef
    @Field("MEDIAS")
    @Attribute(keyword = "CONCEPTBOARD_ACTIVE", resolvedKeyword = "active")
    private Set<ConceptBoardMedia> medias = new HashSet<>();


    @DBRef
    @Field("FEATURED_IMAGE")
    @Attribute(keyword = "CONCEPTBOARD_FEATUREDMEDIA", resolvedKeyword = "featuredMedia")
    private ConceptBoardMedia featuredMedia;


    @Attribute(keyword = "CONCEPTBOARD_ACTIVE", resolvedKeyword = "active")
    @Field(value = "ACTIVE")
    @NotToUseDuringMerge
    private Boolean active = Boolean.TRUE;

    @Field(value = "ALLOW_COMMENTS")
    @Attribute(keyword = "CONCEPTBOARD_ALLOWCOMMENTS", resolvedKeyword = "allowComments")
    private Boolean allowComments= Boolean.TRUE;

    @Field(value = "ALLOW_QUESTIONS")
    @Attribute(keyword = "CONCEPTBOARD_ALLOWQUESTIONS", resolvedKeyword = "allowQuestions")
    private Boolean allowQuestions= Boolean.TRUE;

    @Attribute(keyword = "CONCEPTBOARD_CREATIONDATE", resolvedKeyword = "creationDate")
    @Field(value  = "CREATION_DATE")
    @NotToUseDuringMerge
    private Long creationDate;

    @Attribute(keyword = "CONCEPTBOARD_LASTMODIFIEDDATE", resolvedKeyword = "lastModifiedDate")
    @Field(value  = "LAST_MODIFIED_DATE")
    @NotToUseDuringMerge
    private Long lastModifiedDate;

    @Attribute(keyword = "CONCEPTBOARD_CREATEDBY", resolvedKeyword = "createdBy")
    @Field(value  = "CREATED_BY")
    @NotToUseDuringMerge
    private Long createdBy;

    @Attribute(keyword = "CONCEPTBOARD_LASTMODIFIEDBY", resolvedKeyword = "lastModifiedBy")
    @Field(value  = "LAST_MODIFIED_BY")
    @NotToUseDuringMerge
    private Long lastModifiedBy;

    @Version
    @Attribute(keyword = "CONCEPTBOARD_VERSION", resolvedKeyword = "version")
    @Field(value  = "VERSION")
    @NotToUseDuringMerge
    private Integer version;

    @Attribute(keyword = "CONCEPTBOARD_CONCEPTBOARDUSERACTIONSTATISTICS", resolvedKeyword = "conceptBoardUserActionStatistics")
    @Field(value  = "CONCEPTBOARD_USER_ACTION_STATISTICS")
    @NotToUseDuringMerge
    private ConceptBoardUserActionStatistics conceptBoardUserActionStatistics = new ConceptBoardUserActionStatistics();

    public void preSave(Long subjectId){
        this.setCreatedBy(subjectId);
        this.setSubjectId(subjectId);
        this.setCreationDate(System.currentTimeMillis());
        this.setLastModifiedBy(subjectId);
        this.setLastModifiedDate(this.getCreationDate());
        if(this.getParentConceptBoard() == null){
            this.setParent(true);
        }else {
            this.setParent(false);
        }
        // this.setVersion(0);
    }

    public void preUpdate(Long subjectId){
        this.setLastModifiedBy(subjectId);
        this.setLastModifiedDate(System.currentTimeMillis());
        this.setLastModifiedDate(this.getCreationDate());
        if(this.getParentConceptBoard() == null){
            this.setParent(true);
        }else {
            this.setParent(false);
        }
        // this.setVersion(this.getVersion() + 1);
    }


    public Boolean getAllowComments() {
        return allowComments;
    }

    public void setAllowComments(Boolean allowComments) {
        this.allowComments = allowComments;
    }

    public Boolean getAllowQuestions() {
        return allowQuestions;
    }

    public void setAllowQuestions(Boolean allowQuestions) {
        this.allowQuestions = allowQuestions;
    }

    public Boolean getParent() {
        return isParent;
    }

    public void setParent(Boolean parent) {
        isParent = parent;
    }

    public ConceptBoardMedia getFeaturedMedia() {
        return featuredMedia;
    }


    public String getParentConceptBoard() {
        return parentConceptBoard;
    }

    public void setParentConceptBoard(String parentConceptBoard) {
        this.parentConceptBoard = parentConceptBoard;
    }

    public void setFeaturedMedia(ConceptBoardMedia featuredMedia) {
        this.featuredMedia = featuredMedia;
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

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public ConceptBoardVisibility getConceptBoardVisibility() {
        return conceptBoardVisibility;
    }

    public void setConceptBoardVisibility(ConceptBoardVisibility conceptBoardVisibility) {
        this.conceptBoardVisibility = conceptBoardVisibility;
    }

    public Set<String> getEmailWithViewRights() {
        return emailWithViewRights;
    }

    public void setEmailWithViewRights(Set<String> emailWithViewRights) {
        this.emailWithViewRights = emailWithViewRights;
    }

    public Set<ConceptBoardMedia> getMedias() {
        return medias;
    }

    public void setMedias(Set<ConceptBoardMedia> medias) {
        this.medias = medias;
    }

    public ConceptBoardUserActionStatistics getConceptBoardUserActionStatistics() {
        return conceptBoardUserActionStatistics;
    }

    public void setConceptBoardUserActionStatistics(ConceptBoardUserActionStatistics conceptBoardUserActionStatistics) {
        this.conceptBoardUserActionStatistics = conceptBoardUserActionStatistics;
    }
}
