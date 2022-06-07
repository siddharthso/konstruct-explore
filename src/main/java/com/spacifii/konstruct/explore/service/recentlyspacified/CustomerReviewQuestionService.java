package com.spacifii.konstruct.explore.service.recentlyspacified;


import com.spacifii.konstruct.explore.entities.recentlyspacified.CustomerReviewQuestion;
import com.spacifii.konstruct.explore.model.dto.explore.FilterRequestDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

/**
 * This service class manages CustomerReviewQuestion
 */
public interface CustomerReviewQuestionService {


    /**
     * This service method is used to save or Update CustomerReviewQuestion
     * @param customerReviewQuestion
     * @param subjectId
     * @return
     */
    CustomerReviewQuestion saveOrUpdateCustomerQuestion(CustomerReviewQuestion customerReviewQuestion, Long subjectId);

    /**
     * This service method is used to get CustomerReviewQuestion By Id
     * @param customerReviewQuestion
     * @return
     */
    CustomerReviewQuestion getCustomerReviewQuestionById(String customerReviewQuestion);

    /**
     * This service method is used to find all CustomerReviewQuestion by Ids
     * @param ids
     * @return
     */
    List<CustomerReviewQuestion> findAllyByIds(Set<String> ids);

    /**
     * This service method is used to get CustomerReviewQuestion Filtered
     * @param filterRequestDto
     * @return
     */
    Page<CustomerReviewQuestion> getCustomerReviewQuestionFiltered(FilterRequestDto filterRequestDto);


}
