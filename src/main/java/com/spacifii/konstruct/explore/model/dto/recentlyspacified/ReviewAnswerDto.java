package com.spacifii.konstruct.explore.model.dto.recentlyspacified;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Objects;

@JsonPropertyOrder(alphabetic = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReviewAnswerDto {

    private String question;

    private LinkedHashSet<String> customerAnswerString = new LinkedHashSet<>();

    private LinkedHashSet<BigDecimal> customerAnswerDecimal = new LinkedHashSet<>();

    private Boolean customerAnswerBoolean;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewAnswerDto that = (ReviewAnswerDto) o;
        return Objects.equals(question, that.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(question);
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
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
