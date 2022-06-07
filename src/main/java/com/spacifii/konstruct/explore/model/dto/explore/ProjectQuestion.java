package com.spacifii.konstruct.explore.model.dto.explore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.spacifii.konstruct.explore.entities.explore.ProjectUserAction;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
public class ProjectQuestion {

    private String projectId;

    private String questionId;

    private String answer;

    private String question;

    private Boolean isAskedByMe = false;

    private UserProfileMiniDto questionProfile;

    private UserProfileMiniDto answerProfile;

    private Long creationDate;

    private Long lastModifiedDate;

    public void convertProjectUserACtionToProjectQuestion(ProjectUserAction projectUserAction){
        this.setProjectId(projectUserAction.getProjectId());
        this.setQuestionId(projectUserAction.getId());
        this.setAnswer(projectUserAction.getAnswer());
        this.setQuestion(projectUserAction.getDetails());
        this.setCreationDate(projectUserAction.getCreationDate());
        this.setLastModifiedDate(projectUserAction.getLastModifiedDate());

    }

    public ProjectQuestion(Boolean isAskedByMe, UserProfileMiniDto questionProfile, UserProfileMiniDto answerProfile) {
        this.isAskedByMe = isAskedByMe;
        this.questionProfile = questionProfile;
        this.answerProfile = answerProfile;
    }

    public ProjectQuestion(String projectId, String questionId, String answer, String question, Boolean isAskedByMe, UserProfileMiniDto questionProfile, UserProfileMiniDto answerProfile, Long creationDate, Long lastModifiedDate) {
        this.projectId = projectId;
        this.questionId = questionId;
        this.answer = answer;
        this.question = question;
        this.isAskedByMe = isAskedByMe;
        this.questionProfile = questionProfile;
        this.answerProfile = answerProfile;
        this.creationDate = creationDate;
        this.lastModifiedDate = lastModifiedDate;
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

    public ProjectQuestion() {
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Boolean getAskedByMe() {
        return isAskedByMe;
    }

    public void setAskedByMe(Boolean askedByMe) {
        isAskedByMe = askedByMe;
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
