package com.spacifii.konstruct.explore.entities.recentlyspacified;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Objects;

public class UserTypeQuestionContainer {

    @DBRef
    @Field(value = "CUSTOMER_REVIEW_QUESTION")
    private CustomerReviewQuestion customerReviewQuestion;

    @Field(value = "REQUIRED")
    private Boolean required;

    @Field(value = "REGEX")
    private String regex;

    @Field(value = "PLACE_HOLDER")
    private String placeHolder;

    @Field(value = "TOOL_TIP")
    private String toolTip;

    @Field(value = "HINT")
    private String hint;

    @Field(value = "ERROR_MESSAGE")
    private String errorMessage;

    @Field(value = "GROUP_NAME")
    private String groupName;

    public UserTypeQuestionContainer(CustomerReviewQuestion customerReviewQuestion, Boolean required, String regex, String placeHolder, String toolTip, String hint, String errorMessage, String groupName) {
        this.customerReviewQuestion = customerReviewQuestion;
        this.required = required;
        this.regex = regex;
        this.placeHolder = placeHolder;
        this.toolTip = toolTip;
        this.hint = hint;
        this.errorMessage = errorMessage;
        this.groupName = groupName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTypeQuestionContainer that = (UserTypeQuestionContainer) o;
        return Objects.equals(customerReviewQuestion, that.customerReviewQuestion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerReviewQuestion);
    }

    public UserTypeQuestionContainer() {
    }

    public CustomerReviewQuestion getCustomerReviewQuestion() {
        return customerReviewQuestion;
    }

    public void setCustomerReviewQuestion(CustomerReviewQuestion customerReviewQuestion) {
        this.customerReviewQuestion = customerReviewQuestion;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public String getPlaceHolder() {
        return placeHolder;
    }

    public void setPlaceHolder(String placeHolder) {
        this.placeHolder = placeHolder;
    }

    public String getToolTip() {
        return toolTip;
    }

    public void setToolTip(String toolTip) {
        this.toolTip = toolTip;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
