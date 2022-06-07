package com.spacifii.konstruct.explore.entities.recentlyspacified;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.LinkedHashMap;
import java.util.Set;

@Document(collection = "USER_TYPE_CUSTOMER_REVIEW_QUESTION_MAPPING")
public class UserTypeCustomerReviewQuestionMapping {

    @Id
    @Field(value = "USER_TYPE")
    private String userType;


    @Field(value = "GROUP_QUESTION_SET")
    private LinkedHashMap<String,Set<UserTypeQuestionContainer>> groupQuestionSet = new LinkedHashMap<>();


    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public LinkedHashMap<String, Set<UserTypeQuestionContainer>> getGroupQuestionSet() {
        return groupQuestionSet;
    }

    public void setGroupQuestionSet(LinkedHashMap<String, Set<UserTypeQuestionContainer>> groupQuestionSet) {
        this.groupQuestionSet = groupQuestionSet;
    }
}
