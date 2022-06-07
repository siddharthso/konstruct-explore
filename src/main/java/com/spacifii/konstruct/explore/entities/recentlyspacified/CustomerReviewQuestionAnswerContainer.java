package com.spacifii.konstruct.explore.entities.recentlyspacified;


import com.spacifii.konstruct.explore.model.dto.recentlyspacified.ReviewAnswerDto;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class CustomerReviewQuestionAnswerContainer {

    @DBRef
    @Field(value = "CUSTOMER_REVIEW_QUESTION")
    private CustomerReviewQuestion customerReviewQuestion;

    @Field(value = "CUSTOMER_ANSWER_STRING")
    private LinkedHashSet<String> customerAnswerString = new LinkedHashSet<>();

    @Field(value = "CUSTOMER_ANSWER_DECIMAL")
    private LinkedHashSet<BigDecimal> customerAnswerDecimal = new LinkedHashSet<>();

    @Field(value = "CUSTOMER_ANSWER_BOOLEAN")
    private Boolean customerAnswerBoolean;

    public CustomerReviewQuestionAnswerContainer() {
    }

    public CustomerReviewQuestionAnswerContainer(CustomerReviewQuestion customerReviewQuestion) {
        this.customerReviewQuestion = customerReviewQuestion;
    }

    public CustomerReviewQuestionAnswerContainer(CustomerReviewQuestion customerReviewQuestion, LinkedHashSet<String> customerAnswerString, LinkedHashSet<BigDecimal> customerAnswerDecimal, Boolean customerAnswerBoolean) {
        this.customerReviewQuestion = customerReviewQuestion;
        this.customerAnswerString = customerAnswerString;
        this.customerAnswerDecimal = customerAnswerDecimal;
        this.customerAnswerBoolean = customerAnswerBoolean;
    }

    public void MergeInMe(ReviewAnswerDto reviewAnswerDto){
        this.setCustomerAnswerString(reviewAnswerDto.getCustomerAnswerString());
        this.setCustomerAnswerDecimal(reviewAnswerDto.getCustomerAnswerDecimal());
        this.setCustomerAnswerBoolean(reviewAnswerDto.getCustomerAnswerBoolean());
    }

    public CustomerReviewQuestion getCustomerReviewQuestion() {
        return customerReviewQuestion;
    }

    public void setCustomerReviewQuestion(CustomerReviewQuestion customerReviewQuestion) {
        this.customerReviewQuestion = customerReviewQuestion;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerReviewQuestionAnswerContainer that = (CustomerReviewQuestionAnswerContainer) o;
        return Objects.equals(customerReviewQuestion, that.customerReviewQuestion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerReviewQuestion);
    }

    public LinkedHashSet<String> getCustomerAnswerString() {
        return customerAnswerString;
    }

    public void setCustomerAnswerString(LinkedHashSet<String> customerAnswerString) {
        this.customerAnswerString = customerAnswerString;
    }

    public LinkedHashSet<BigDecimal> getCustomerAnswerDecimal() {
        return customerAnswerDecimal;
    }

    public void setCustomerAnswerDecimal(LinkedHashSet<BigDecimal> customerAnswerDecimal) {
        this.customerAnswerDecimal = customerAnswerDecimal;
    }

    public Boolean getCustomerAnswerBoolean() {
        return customerAnswerBoolean;
    }

    public void setCustomerAnswerBoolean(Boolean customerAnswerBoolean) {
        this.customerAnswerBoolean = customerAnswerBoolean;
    }
}
