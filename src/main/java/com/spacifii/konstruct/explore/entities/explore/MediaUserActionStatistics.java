package com.spacifii.konstruct.explore.entities.explore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.spacifii.konstruct.explore.annotation.Attribute;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.math.BigInteger;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
@Document(collection = "MEDIA_USER_ACTION_STATISTICS")
public class MediaUserActionStatistics {

    @Id
    @Attribute(keyword = "MEDIAUSERACTIONSTATISTICS_MEDIAID", resolvedKeyword = "mediaId", enableFindBy = true)
    @Field(value  = "MEDIA_ID")
    private String mediaId;

    @Attribute(keyword = "MEDIAUSERACTIONSTATISTICS_VIEWCOUNT", resolvedKeyword = "viewCount")
    @Field(value  = "VIEW_COUNT")
    private Long viewCount = 0L;

    @Attribute(keyword = "MEDIAUSERACTIONSTATISTICS_LASTVIEWDATE", resolvedKeyword = "lastViewDate")
    @Field(value  = "LAST_VIEW_DATE")
    private Long lastViewDate = 0L;

    @Attribute(keyword = "MEDIAUSERACTIONSTATISTICS_SHARECOUNT", resolvedKeyword = "shareCount")
    @Field(value  = "SHARE_COUNT")
    private Long shareCount = 0L;


    @Attribute(keyword = "MEDIAUSERACTIONSTATISTICS_LASTSHAREDATE", resolvedKeyword = "lastShareDate")
    @Field(value  = "LAST_SHARE_DATE")
    private Long lastShareDate = 0L;


    @Attribute(keyword = "MEDIAUSERACTIONSTATISTICS_LIKECOUNT", resolvedKeyword = "likeCount")
    @Field(value  = "LIKE_COUNT")
    private Long likeCount = 0L;


    @Attribute(keyword = "MEDIAUSERACTIONSTATISTICS_LASTLIKEDATE", resolvedKeyword = "lastLikeDate")
    @Field(value  = "LAST_LIKE_DATE")
    private Long lastLikeDate = 0L;

    @Attribute(keyword = "MEDIAUSERACTIONSTATISTICS_QUESTIONCOUNT", resolvedKeyword = "questionCount")
    @Field(value  = "QUESTION_COUNT")
    private Long questionCount = 0L;


    @Attribute(keyword = "MEDIAUSERACTIONSTATISTICS_LASTQUESTIONDATE", resolvedKeyword = "lastQuestionDate")
    @Field(value  = "LAST_QUESTION_DATE")
    private Long lastQuestionDate = 0L;



    @Attribute(keyword = "MEDIAUSERACTIONSTATISTICS_AVERAGERATINGS", resolvedKeyword = "averageRatings")
    @Field(value  = "AVERAGE_RATINGS")
    private BigDecimal averageRatings = new BigDecimal(BigInteger.ZERO);

    @Attribute(keyword = "MEDIAUSERACTIONSTATISTICS_RATINGSCOUNT", resolvedKeyword = "ratingsCount")
    @Field(value  = "RATINGS_COUNG")
    private Long ratingsCount = 0L;

    @Attribute(keyword = "MEDIAUSERACTIONSTATISTICS_TOTALRATINGS", resolvedKeyword = "averageRatings")
    @Field(value  = "TOTAL_RATINGS")
    private BigDecimal totalRatings = new BigDecimal(BigInteger.ZERO);


    @Attribute(keyword = "MEDIAUSERACTIONSTATISTICS_REVIEWSCOUNT", resolvedKeyword = "reviewsCount")
    @Field(value  = "REVIEWS_COUNT")
    private Long reviewsCount = 0L;


    @Attribute(keyword = "MEDIAUSERACTIONSTATISTICS_LASTREVIEWDATE", resolvedKeyword = "lastReviewDate")
    @Field(value  = "LAST_REVIEW_DATE")
    private Long lastReviewDate = 0L;


    @Attribute(keyword = "MEDIAUSERACTIONSTATISTICS_COMMENTSCOUNT", resolvedKeyword = "commentsCount")
    @Field(value  = "COMMENTS_COUNT")
    private Long commentsCount = 0L;


    @Attribute(keyword = "MEDIAUSERACTIONSTATISTICS_LASTCOMMENTDATE", resolvedKeyword = "lastCommentDate")
    @Field(value  = "LAST_COMMENT_DATE")
    private Long lastCommentDate = 0L;



    @Attribute(keyword = "MEDIAUSERACTIONSTATISTICS_CREATIONDATE", resolvedKeyword = "creationDate")
    @Field(value  = "CREATION_DATE")
    private Long creationDate;

    @Attribute(keyword = "MEDIAUSERACTIONSTATISTICS_LASTMODIFIEDDATE", resolvedKeyword = "lastModifiedDate")
    @Field(value  = "LAST_MODIFIED_DATE")
    private Long lastModifiedDate;

    @Attribute(keyword = "MEDIAUSERACTIONSTATISTICS_CREATEDBY", resolvedKeyword = "createdBy")
    @Field(value  = "CREATED_BY")
    private Long createdBy;

    @Attribute(keyword = "MEDIAUSERACTIONSTATISTICS_LASTMODIFIEDBY", resolvedKeyword = "lastModifiedBy")
    @Field(value  = "LAST_MODIFIED_BY")
    private Long lastModifiedBy;

    @Version
    @Attribute(keyword = "MEDIAUSERACTIONSTATISTICS_VERSION", resolvedKeyword = "version")
    @Field(value  = "VERSION")
    private Integer version;



    public void preSave(Long subjectId){
        this.setCreatedBy(subjectId);
        this.setCreationDate(System.currentTimeMillis());
        this.setLastModifiedBy(subjectId);
        this.setLastModifiedDate(this.getCreationDate());
        //this.setVersion(0);
    }

    public void preUpdate(Long subjectId){
        this.setLastModifiedBy(subjectId);
        this.setLastModifiedDate(System.currentTimeMillis());
        //this.setVersion(this.getVersion() + 1);
    }


    public void addInMe(MediaUserActionStatistics mediaUserActionStatistics){
        this.setTotalRatings(this.getTotalRatings().add(mediaUserActionStatistics.getTotalRatings()));
        this.setRatingsCount(this.getRatingsCount()+mediaUserActionStatistics.getRatingsCount());
        this.setAverageRatings(this.getAverageRatings().add(mediaUserActionStatistics.getAverageRatings()));
        this.setViewCount(this.getViewCount()+mediaUserActionStatistics.getViewCount());
        this.setShareCount(this.getShareCount()+mediaUserActionStatistics.getShareCount());
        this.setLikeCount(this.getLikeCount()+mediaUserActionStatistics.getLikeCount());
        this.setReviewsCount(this.getReviewsCount()+mediaUserActionStatistics.getReviewsCount());
        this.setQuestionCount(this.getQuestionCount()+mediaUserActionStatistics.getQuestionCount());
    }


    public Long getViewCount() {
        return viewCount;
    }

    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }

    public Long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }

    public Long getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(Long questionCount) {
        this.questionCount = questionCount;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public Long getShareCount() {
        return shareCount;
    }

    public void setShareCount(Long shareCount) {
        this.shareCount = shareCount;
    }

    public BigDecimal getAverageRatings() {
        return averageRatings;
    }

    public void setAverageRatings(BigDecimal averageRatings) {
        this.averageRatings = averageRatings;
    }

    public Long getRatingsCount() {
        return ratingsCount;
    }

    public void setRatingsCount(Long ratingsCount) {
        this.ratingsCount = ratingsCount;
    }

    public Long getReviewsCount() {
        return reviewsCount;
    }

    public void setReviewsCount(Long reviewsCount) {
        this.reviewsCount = reviewsCount;
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

    public Long getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(Long commentsCount) {
        this.commentsCount = commentsCount;
    }

    public BigDecimal getTotalRatings() {
        return totalRatings;
    }

    public void setTotalRatings(BigDecimal totalRatings) {
        this.totalRatings = totalRatings;
    }

    public Long getLastViewDate() {
        return lastViewDate;
    }

    public void setLastViewDate(Long lastViewDate) {
        this.lastViewDate = lastViewDate;
    }

    public Long getLastShareDate() {
        return lastShareDate;
    }

    public void setLastShareDate(Long lastShareDate) {
        this.lastShareDate = lastShareDate;
    }

    public Long getLastLikeDate() {
        return lastLikeDate;
    }

    public void setLastLikeDate(Long lastLikeDate) {
        this.lastLikeDate = lastLikeDate;
    }

    public Long getLastQuestionDate() {
        return lastQuestionDate;
    }

    public void setLastQuestionDate(Long lastQuestionDate) {
        this.lastQuestionDate = lastQuestionDate;
    }

    public Long getLastReviewDate() {
        return lastReviewDate;
    }

    public void setLastReviewDate(Long lastReviewDate) {
        this.lastReviewDate = lastReviewDate;
    }

    public Long getLastCommentDate() {
        return lastCommentDate;
    }

    public void setLastCommentDate(Long lastCommentDate) {
        this.lastCommentDate = lastCommentDate;
    }
}
