package com.spacifii.konstruct.explore.entities.conceptBoard;

import com.spacifii.konstruct.explore.annotation.Attribute;
import com.spacifii.konstruct.explore.annotation.NotToUseDuringMerge;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "CONCEPT_BOARD_SHORTLIST")
public class ConceptBoardShortList {

    @Id
    @Attribute(keyword = "CONCEPTBOARDSHORTLIST_ID", resolvedKeyword = "id")
    @Field(value  = "ID")
    private String id;

    @Attribute(keyword = "CONCEPTBOARDSHORTLIST_CONCEPTBOARDID", resolvedKeyword = "conceptBoardId")
    @Field(value  = "CONCEPT_BOARD_ID")
    @Indexed
    private String conceptBoardId;

    @Attribute(keyword = "CONCEPTBOARDSHORTLIST_CONCEPTBOARDMEDIAID", resolvedKeyword = "conceptBoardMediaId")
    @Field(value  = "CONCEPT_BOARDMEDIA_ID")
    @Indexed
    private String conceptBoardMediaId;

    @Attribute(keyword = "CONCEPTBOARDSHORTLIST_DESCRIPTION", resolvedKeyword = "description")
    @Field(value  = "DESCRIPTION")
    private String description;

    @Attribute(keyword = "CONCEPTBOARDSHORTLIST_ISSHORTLISTED", resolvedKeyword = "isShortListed")
    @Field(value  = "IS_SHORTLISTED")
    @Indexed
    private Boolean isShortListed = true;

    @Attribute(keyword = "CONCEPTBOARDSHORTLIST_SHORTLISTEDSUBJECTID", resolvedKeyword = "shortListedBySubjectId")
    @Field(value  = "SHORTLISTED_BY_SUBJECT_ID")
    private Long shortListedBySubjectId;

    @Attribute(keyword = "CONCEPTBOARDSHORTLIST_CREATIONDATE", resolvedKeyword = "creationDate")
    @Field(value  = "CREATION_DATE")
    @NotToUseDuringMerge
    private Long creationDate;

    @Attribute(keyword = "CONCEPTBOARDSHORTLIST_LASTMODIFIEDDATE", resolvedKeyword = "lastModifiedDate")
    @Field(value  = "LAST_MODIFIED_DATE")
    @NotToUseDuringMerge
    private Long lastModifiedDate;

    @Attribute(keyword = "CONCEPTBOARDSHORTLIST_CREATEDBY", resolvedKeyword = "createdBy")
    @Field(value  = "CREATED_BY")
    @NotToUseDuringMerge
    private Long createdBy;

    @Attribute(keyword = "CONCEPTBOARDSHORTLIST_LASTMODIFIEDBY", resolvedKeyword = "lastModifiedBy")
    @Field(value  = "LAST_MODIFIED_BY")
    @NotToUseDuringMerge
    private Long lastModifiedBy;

    @Version
    @Attribute(keyword = "CONCEPTBOARDSHORTLIST_VERSION", resolvedKeyword = "version")
    @Field(value  = "VERSION")
    @NotToUseDuringMerge
    private Integer version;


    public ConceptBoardShortList() {
    }

    public ConceptBoardShortList(String conceptBoardId, String conceptBoardMediaId, String description, Long shortListedBySubjectId) {
        this.conceptBoardId = conceptBoardId;
        this.conceptBoardMediaId = conceptBoardMediaId;
        this.description = description;
        this.shortListedBySubjectId = shortListedBySubjectId;
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
        this.setLastModifiedDate(this.getCreationDate());
        // this.setVersion(this.getVersion() + 1);
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

    public String getConceptBoardMediaId() {
        return conceptBoardMediaId;
    }

    public void setConceptBoardMediaId(String conceptBoardMediaId) {
        this.conceptBoardMediaId = conceptBoardMediaId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getShortListed() {
        return isShortListed;
    }

    public void setShortListed(Boolean shortListed) {
        isShortListed = shortListed;
    }

    public Long getShortListedBySubjectId() {
        return shortListedBySubjectId;
    }

    public void setShortListedBySubjectId(Long shortListedBySubjectId) {
        this.shortListedBySubjectId = shortListedBySubjectId;
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
