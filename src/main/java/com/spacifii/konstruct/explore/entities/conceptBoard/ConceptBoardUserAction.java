package com.spacifii.konstruct.explore.entities.conceptBoard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.spacifii.konstruct.explore.annotation.Attribute;
import com.spacifii.konstruct.explore.entities.explore.MediaUserActionType;
import com.spacifii.konstruct.explore.entities.explore.ShareType;
import com.spacifii.konstruct.explore.model.dto.explore.MediaUserActionDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

/**
 * This Entity Class is for ConceptBoardMediaUserAction which can be Share, Like, Comment, Reviews
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
@Document(collection = "MEDIA_USER_ACTION")
public class ConceptBoardUserAction {

    @Id
    @Attribute(keyword = "MEDIAUSERACTION_ID", resolvedKeyword = "id", enableFindBy = true)
    @Field(value  = "ID")
    private String id;

    @Indexed
    @Attribute(keyword = "MEDIAUSERACTION_SUBJECTID", resolvedKeyword = "subjectId")
    @Field(value  = "SUBJECTID")
    private String subjectId;

    @Indexed
    @Attribute(keyword = "MEDIAUSERACTION_CONCEPTBOARDID", resolvedKeyword = "conceptBoardId")
    @Field(value  = "CONCEPT_BOARD_ID")
    private String conceptBoardId;

    @Indexed
    @Attribute(keyword = "MEDIAUSERACTION_MEDIAUSERACTIONTYPE", resolvedKeyword = "mediaUserActionType")
    @Field(value  = "MEDIA_USER_ACTION_TYPE")
    private MediaUserActionType mediaUserActionType;

    @Attribute(keyword = "MEDIAUSERACTION_QUESTION", resolvedKeyword = "question")
    @Field(value  = "QUESTION")
    private String question;

    @Attribute(keyword = "MEDIAUSERACTION_ANSWER", resolvedKeyword = "answer")
    @Field(value  = "ANSWER")
    private String answer;

    @Attribute(keyword = "MEDIAUSERACTION_ANSWEREDBY", resolvedKeyword = "answeredby")
    @Field(value  = "ANSWERED_BY")
    private Long answeredBy;


    @Attribute(keyword = "MEDIAUSERACTION_RATINGS", resolvedKeyword = "ratings")
    @Field(value  = "RATINGS")
    private BigDecimal ratings;

    @Attribute(keyword = "MEDIAUSERACTION_SHARETYPE", resolvedKeyword = "shareType")
    @Field(value  = "SHARE_TYPE")
    private ShareType shareType;

    @Attribute(keyword = "MEDIAUSERACTION_DETAILS", resolvedKeyword = "details")
    @Field(value  = "DETAILS")
    private String details;

    @Attribute(keyword = "MEDIAUSERACTION_PARENTCOMMENTID", resolvedKeyword = "parentCommentId")
    @Field(value  = "PARENT_COMMENT_ID")
    private String parentCommentId;


    @Attribute(keyword = "MEDIAUSERACTION_CREATIONDATE", resolvedKeyword = "creationDate")
    @Field(value  = "CREATION_DATE")
    private Long creationDate;

    @Attribute(keyword = "MEDIAUSERACTION_LASTMODIFIEDDATE", resolvedKeyword = "lastModifiedDate")
    @Field(value  = "LAST_MODIFIED_DATE")
    private Long lastModifiedDate;

    @Attribute(keyword = "MEDIAUSERACTION_CREATEDBY", resolvedKeyword = "createdBy")
    @Field(value  = "CREATED_BY")
    private Long createdBy;

    @Attribute(keyword = "MEDIAUSERACTION_LASTMODIFIEDBY", resolvedKeyword = "lastModifiedBy")
    @Field(value  = "LAST_MODIFIED_BY")
    private Long lastModifiedBy;

    @Version
    @Attribute(keyword = "MEDIAUSERACTION_VERSION", resolvedKeyword = "version")
    @Field(value  = "VERSION")
    private Integer version;


    public void convertCOnceUserActionDTOToMe(MediaUserActionDTO mediaUserActionDTO){
        this.setMediaUserActionType(mediaUserActionDTO.getMediaUserActionType());
        this.setConceptBoardId(mediaUserActionDTO.getConceptBoardId());
        this.setShareType(mediaUserActionDTO.getShareType());
        this.setDetails(mediaUserActionDTO.getDetails());
        this.setQuestion(mediaUserActionDTO.getQuestion());
        this.setParentCommentId(mediaUserActionDTO.getParentCommentId());
        this.setRatings(mediaUserActionDTO.getRating());

    }


    public void preSave(Long subjectId){
        this.setCreatedBy(subjectId);
        this.setCreationDate(System.currentTimeMillis());
        this.setLastModifiedBy(subjectId);
        this.setLastModifiedDate(this.getCreationDate());
      //  this.setVersion(0);
    }

    public void preUpdate(Long subjectId){
        this.setLastModifiedBy(subjectId);
        this.setLastModifiedDate(System.currentTimeMillis());
       // this.setVersion(this.getVersion() + 1);
    }

    public BigDecimal getRatings() {
        return ratings;
    }

    public void setRatings(BigDecimal ratings) {
        this.ratings = ratings;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }


    public Long getAnsweredBy() {
        return answeredBy;
    }

    public void setAnsweredBy(Long answeredBy) {
        this.answeredBy = answeredBy;
    }

    public String getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(String parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public String getConceptBoardId() {
        return conceptBoardId;
    }

    public void setConceptBoardId(String conceptBoardId) {
        this.conceptBoardId = conceptBoardId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public MediaUserActionType getMediaUserActionType() {
        return mediaUserActionType;
    }

    public void setMediaUserActionType(MediaUserActionType mediaUserActionType) {
        this.mediaUserActionType = mediaUserActionType;
    }

    public ShareType getShareType() {
        return shareType;
    }

    public void setShareType(ShareType shareType) {
        this.shareType = shareType;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
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
