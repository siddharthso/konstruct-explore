package com.spacifii.konstruct.explore.repository.recentlyspacified;


import com.spacifii.konstruct.explore.entities.recentlyspacified.CustomerReviewQuestion;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * This is DAO class for Customer Review Question
 */
public interface CustomerReviewQuestionRespository extends MongoRepository<CustomerReviewQuestion,String> {


    /**
     * This repository method is used to find question by Question String
     * @param question
     * @return
     */
    CustomerReviewQuestion findFirstByQuestion(String question);

}
