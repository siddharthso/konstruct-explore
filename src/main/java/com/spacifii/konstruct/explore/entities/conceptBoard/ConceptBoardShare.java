package com.spacifii.konstruct.explore.entities.conceptBoard;

import com.spacifii.konstruct.explore.annotation.Attribute;
import com.spacifii.konstruct.explore.annotation.NotToUseDuringMerge;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "CONCEPT_BOARD_SHARE")
public class ConceptBoardShare {

    @Id
    @Attribute(keyword = "CONCEPTBOARDSHARE_ID", resolvedKeyword = "id")
    @Field(value  = "ID")
    private String id;

    @Attribute(keyword = "CONCEPTBOARDSHARE_CONCEPTBOARDID", resolvedKeyword = "conceptBoardId")
    @Field(value  = "CONCEPT_BOARD_ID")
    @Indexed
    private String conceptBoardId;

    @Attribute(keyword = "CONCEPTBOARDSHARE_SHAREMESSAGE", resolvedKeyword = "shareMessage")
    @Field(value  = "SHAREMESSAGE")
    private String shareMessage;

    @Attribute(keyword = "CONCEPTBOARDSHARE_EMAIL", resolvedKeyword = "email")
    @Field(value  = "EMAIL")
    @Indexed
    private String email;

    @Attribute(keyword = "CONCEPTBOARDSHARE_INVITERSUBJECTID", resolvedKeyword = "version")
    @Field(value  = "INVITER_SUBJECT_ID")
    @Indexed
    private Long inviterSubjectId;

    @Attribute(keyword = "CONCEPTBOARDSHARE_INVITEESUBJECTID", resolvedKeyword = "version")
    @Field(value  = "INVITEE_SUBJECT_ID")
    @Indexed
    private Long inviteeSubjectId;

    @Attribute(keyword = "CONCEPTBOARDSHARE_ISREVOKED", resolvedKeyword = "isRevoked")
    @Field(value  = "IS_REVOKED")
    @Indexed
    private Boolean isRevoked= false;

    @Attribute(keyword = "CONCEPTBOARDSHARE_REVOKECOMMENTS", resolvedKeyword = "revokeComments")
    @Field(value  = "REVOKE_COMMENTS")
    private String revokeComments;

    @Attribute(keyword = "CONCEPTBOARDSHARE_CREATIONDATE", resolvedKeyword = "creationDate")
    @Field(value  = "CREATION_DATE")
    @NotToUseDuringMerge
    private Long creationDate;

    @Attribute(keyword = "CONCEPTBOARDSHARE_LASTMODIFIEDDATE", resolvedKeyword = "lastModifiedDate")
    @Field(value  = "LAST_MODIFIED_DATE")
    @NotToUseDuringMerge
    private Long lastModifiedDate;

    @Attribute(keyword = "CONCEPTBOARDSHARE_CREATEDBY", resolvedKeyword = "createdBy")
    @Field(value  = "CREATED_BY")
    @NotToUseDuringMerge
    private Long createdBy;

    @Attribute(keyword = "CONCEPTBOARDSHARE_LASTMODIFIEDBY", resolvedKeyword = "lastModifiedBy")
    @Field(value  = "LAST_MODIFIED_BY")
    @NotToUseDuringMerge
    private Long lastModifiedBy;

    @Version
    @Attribute(keyword = "CONCEPTBOARDSHARE_VERSION", resolvedKeyword = "version")
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
        this.setLastModifiedDate(this.getCreationDate());
        // this.setVersion(this.getVersion() + 1);
    }


    public String getRevokeComments() {
        return revokeComments;
    }

    public void setRevokeComments(String revokeComments) {
        this.revokeComments = revokeComments;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getShareMessage() {
        return shareMessage;
    }

    public void setShareMessage(String shareMessage) {
        this.shareMessage = shareMessage;
    }



    public Long getInviterSubjectId() {
        return inviterSubjectId;
    }

    public void setInviterSubjectId(Long inviterSubjectId) {
        this.inviterSubjectId = inviterSubjectId;
    }

    public Long getInviteeSubjectId() {
        return inviteeSubjectId;
    }

    public void setInviteeSubjectId(Long inviteeSubjectId) {
        this.inviteeSubjectId = inviteeSubjectId;
    }

    public Boolean getRevoked() {
        return isRevoked;
    }

    public void setRevoked(Boolean revoked) {
        isRevoked = revoked;
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
