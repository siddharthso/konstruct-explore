package com.spacifii.konstruct.explore.service.recentlyspacified.impl;


import com.spacifii.konstruct.explore.entities.recentlyspacified.CustomerReviewQuestion;
import com.spacifii.konstruct.explore.exception.recentlyspacified.CustomerReviewQuestionNotFoundException;
import com.spacifii.konstruct.explore.model.dto.explore.FilterRequestDto;
import com.spacifii.konstruct.explore.repository.recentlyspacified.CustomerReviewQuestionRespository;
import com.spacifii.konstruct.explore.service.explore.BeanUtils;
import com.spacifii.konstruct.explore.service.explore.QueryUtilService;
import com.spacifii.konstruct.explore.service.recentlyspacified.CustomerReviewQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * This service class manages CustomerReviewQuestion
 */
@Service
public class CustomerReviewQuestionServiceImpl implements CustomerReviewQuestionService {

    @Autowired
    CustomerReviewQuestionRespository customerReviewQuestionRespository;

    @Autowired
    QueryUtilService queryUtilService;




    /**
     * This service method is used to save or Update CustomerReviewQuestion
     *
     * @param customerReviewQuestion
     * @param subjectId
     * @return
     */
    @Override
    public CustomerReviewQuestion saveOrUpdateCustomerQuestion(CustomerReviewQuestion customerReviewQuestion, Long subjectId) {
        customerReviewQuestion.getQuestion().toUpperCase();
        //customerReviewQuestion.setId(customerReviewQuestion.getQuestion());
        if(customerReviewQuestion.getId() != null) {
            CustomerReviewQuestion customerReviewQuestionExisting = getCustomerReviewQuestionById(customerReviewQuestion.getId());
            //Optional<CustomerReviewQuestion> customerReviewQuestionOptional = customerReviewQuestionRespository.findById(customerReviewQuestion.getQuestion().toUpperCase());

                customerReviewQuestionExisting.mergeInMe(customerReviewQuestion);

                return customerReviewQuestionRespository.save(customerReviewQuestionExisting);

        }
        return customerReviewQuestionRespository.save(customerReviewQuestion);
    }

    /**
     * This service method is used to get CustomerReviewQuestion By Id
     *
     * @param customerReviewQuestion
     * @return
     */
    @Override
    public CustomerReviewQuestion getCustomerReviewQuestionById(String customerReviewQuestion) {
        Optional<CustomerReviewQuestion> customerReviewQuestionOptional = customerReviewQuestionRespository.findById(customerReviewQuestion.toUpperCase());
        if(customerReviewQuestionOptional.isPresent()){
            return customerReviewQuestionOptional.get();
        }

        throw new CustomerReviewQuestionNotFoundException();
    }

    /**
     * This service method is used to find all CustomerReviewQuestion by Ids
     *
     * @param ids
     * @return
     */
    @Override
    public List<CustomerReviewQuestion> findAllyByIds(Set<String> ids) {
        return new ArrayList<>(BeanUtils.makeCollection(customerReviewQuestionRespository.findAllById(ids)));
    }

    /**
     * This service method is used to get CustomerReviewQuestion Filtered
     *
     * @param filterRequestDto
     * @return
     */
    @Override
    public Page<CustomerReviewQuestion> getCustomerReviewQuestionFiltered(FilterRequestDto filterRequestDto) {
        Set<String> defaults =new HashSet<>();
        defaults.add("id");
        return queryUtilService.getResultList(filterRequestDto,defaults,new CustomerReviewQuestion());
    }
}
