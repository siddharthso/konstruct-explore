package com.spacifii.konstruct.explore.entities.recentlyspacified;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Objects;
import java.util.Set;

@Document(collection = "CUSTOMER_REVIEW_QUESTION")
public class CustomerReviewQuestion {

    @Id
    @Field(value = "ID")
    private String id;

    @Indexed
    @Field(value = "QUESTION")
    private String question;

    @Field(value = "QUESTION_TYPE")
    private QuestionType questionType;

    @Field(value = "ACTIVE")
    private Boolean active = Boolean.TRUE;

    @Field(value = "POSSIBLE_VALUES")
    private Set<String> possibleValues;




    public void mergeInMe(CustomerReviewQuestion customerReviewQuestion){
        this.setQuestionType(customerReviewQuestion.getQuestionType());
        this.setActive(customerReviewQuestion.getActive());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerReviewQuestion that = (CustomerReviewQuestion) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Set<String> getPossibleValues() {
        return possibleValues;
    }

    public void setPossibleValues(Set<String> possibleValues) {
        this.possibleValues = possibleValues;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
