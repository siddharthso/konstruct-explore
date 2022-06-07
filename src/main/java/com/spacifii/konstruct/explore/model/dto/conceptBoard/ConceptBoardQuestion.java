package com.spacifii.konstruct.explore.model.dto.conceptBoard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardUserAction;
import com.spacifii.konstruct.explore.model.dto.explore.UserProfileMiniDto;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
public class ConceptBoardQuestion {

    private String conceptBoardId;

    private String questionId;

    private String answer;

    private String question;

    private Boolean isAskedByMe;

    private UserProfileMiniDto questionProfile;

    private UserProfileMiniDto answerProfile;

    private Long creationDate;

    private Long lastModifiedDate;

    public String getQuestion() {
        return question;
    }

    public void convertConceptBoardUserACtionToConceptBoardQuestion(ConceptBoardUserAction conceptBoardUserAction){
        this.setConceptBoardId(conceptBoardUserAction.getConceptBoardId());
        this.setQuestionId(conceptBoardUserAction.getId());
        this.setAnswer(conceptBoardUserAction.getAnswer());
        this.setQuestion(conceptBoardUserAction.getDetails());
        this.setCreationDate(conceptBoardUserAction.getCreationDate());
        this.setLastModifiedDate(conceptBoardUserAction.getLastModifiedDate());

    }

    public ConceptBoardQuestion(Boolean isAskedByMe, UserProfileMiniDto questionProfile, UserProfileMiniDto answerProfile) {
        this.isAskedByMe = isAskedByMe;
        this.questionProfile = questionProfile;
        this.answerProfile = answerProfile;
    }

    public ConceptBoardQuestion(String conceptBoardId, String questionId, String answer, String question, Boolean isAskedByMe, UserProfileMiniDto questionProfile, UserProfileMiniDto answerProfile, Long creationDate, Long lastModifiedDate) {
        this.conceptBoardId = conceptBoardId;
        this.questionId = questionId;
        this.answer = answer;
        this.question = question;
        this.isAskedByMe = isAskedByMe;
        this.questionProfile = questionProfile;
        this.answerProfile = answerProfile;
        this.creationDate = creationDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public ConceptBoardQuestion() {
    }

    public Boolean getAskedByMe() {
        return isAskedByMe;
    }

    public void setAskedByMe(Boolean askedByMe) {
        isAskedByMe = askedByMe;
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

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getConceptBoardId() {
        return conceptBoardId;
    }

    public void setConceptBoardId(String conceptBoardId) {
        this.conceptBoardId = conceptBoardId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public UserProfileMiniDto getQuestionProfile() {
        return questionProfile;
    }

    public void setQuestionProfile(UserProfileMiniDto questionProfile) {
        this.questionProfile = questionProfile;
    }

    public UserProfileMiniDto getAnswerProfile() {
        return answerProfile;
    }

    public void setAnswerProfile(UserProfileMiniDto answerProfile) {
        this.answerProfile = answerProfile;
    }
}
