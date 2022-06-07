package com.spacifii.konstruct.explore.service.recentlyspacified.impl;


import com.spacifii.konstruct.explore.entities.recentlyspacified.*;
import com.spacifii.konstruct.explore.exception.recentlyspacified.CustomerReviewNotFound;
import com.spacifii.konstruct.explore.exception.recentlyspacified.CustomerReviewRequestAlreadyInitiated;
import com.spacifii.konstruct.explore.integration.communication.CommunicationService;
import com.spacifii.konstruct.explore.model.dto.explore.FilterRequestDto;
import com.spacifii.konstruct.explore.model.dto.explore.UserProfileMiniDto;
import com.spacifii.konstruct.explore.model.dto.explore.UserProfileMiniDto2;
import com.spacifii.konstruct.explore.model.dto.query.SearchQuery;
import com.spacifii.konstruct.explore.model.dto.recentlyspacified.EnvelopedCustomerReview;
import com.spacifii.konstruct.explore.model.dto.recentlyspacified.ReviewAnswerDto;
import com.spacifii.konstruct.explore.repository.recentlyspacified.RecentlySpacifiedCustomerReviewRepository;
import com.spacifii.konstruct.explore.service.explore.QueryUtilService;
import com.spacifii.konstruct.explore.service.explore.UserProfileService;
import com.spacifii.konstruct.explore.service.recentlyspacified.RecentlySpacifiedCustomerReviewService;
import com.spacifii.konstruct.explore.service.recentlyspacified.UserTypeCustomerReviewQuestionMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * This service class manages RecentlySpacifiedCustomerReview
 */
@Service
public class RecentlySpacifiedCustomerReviewServiceImpl implements RecentlySpacifiedCustomerReviewService {

    @Autowired
    RecentlySpacifiedCustomerReviewRepository recentlySpacifiedCustomerReviewRepository;

    @Autowired
    QueryUtilService queryUtilService;

    @Autowired
    CommunicationService communicationService;

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    UserTypeCustomerReviewQuestionMappingService userTypeCustomerReviewQuestionMappingService;



    @Value("${reviewDefaultUrl}")
    String reviewDefaultUrl;

    /**
     * This service method is used to crete new RecentlySpacifiedCustomerReview
     *
     * @param searchProfileBy
     * @param searchProfileValue
     * @param projectId
     * @param userType
     * @param subjectId
     * @return
     */
    @Override
    public RecentlySpacifiedCustomerReview createNewRecentlySpacifiedCustomerReview(SearchProfileBy searchProfileBy, String searchProfileValue, String projectId, String userType, Long subjectId) {
        UserProfileMiniDto2 userProfileMiniDto = userProfileService.getuserByMobileEmail(searchProfileBy,searchProfileValue);
        UserProfileMiniDto2 userProfileMiniDtoRequestor =  userProfileService.getUserProfileForSubjectIdsWithEmailMobile(new HashSet<>(Arrays.asList(subjectId))).get(subjectId);
        RecentlySpacifiedCustomerReview recentlySpacifiedCustomerReviewExiting = recentlySpacifiedCustomerReviewRepository.findFirstByProjectIdAndReviewerSubjectId(projectId, Long.valueOf(userProfileMiniDto.getSubjectId()));

        if(recentlySpacifiedCustomerReviewExiting != null){
            throw new CustomerReviewRequestAlreadyInitiated();
        }


        UserTypeCustomerReviewQuestionMapping userTypeCustomerReviewQuestionMapping = userTypeCustomerReviewQuestionMappingService.findByUserType(userType);
        LinkedHashMap<String,Set<CustomerReviewQuestionAnswerContainer>> map = new LinkedHashMap<>();
        for (Map.Entry<String, Set<UserTypeQuestionContainer>> entry: userTypeCustomerReviewQuestionMapping.getGroupQuestionSet().entrySet()) {
            Set<CustomerReviewQuestionAnswerContainer> customerReviewQuestionAnswerContainers = new LinkedHashSet<>();
            for (UserTypeQuestionContainer userTypeQuestionContainer: entry.getValue()) {
                customerReviewQuestionAnswerContainers.add(new CustomerReviewQuestionAnswerContainer(userTypeQuestionContainer.getCustomerReviewQuestion()));
            }
            map.put(entry.getKey(),customerReviewQuestionAnswerContainers);
        }



        RecentlySpacifiedCustomerReview recentlySpacifiedCustomerReview = new RecentlySpacifiedCustomerReview();
        recentlySpacifiedCustomerReview.setSearchProfileBy(searchProfileBy);
        recentlySpacifiedCustomerReview.setSearchProfileByValue(searchProfileValue);
        recentlySpacifiedCustomerReview.setReviewerSubjectId(Long.valueOf(userProfileMiniDto.getSubjectId()));
        recentlySpacifiedCustomerReview.setRevieweeSubjectId(subjectId);
        recentlySpacifiedCustomerReview.setRevieweeUserType(userType);
        recentlySpacifiedCustomerReview.setGroupQuestionSet(map);
        recentlySpacifiedCustomerReview.setProjectId(projectId);
        recentlySpacifiedCustomerReview.preSave(subjectId);

        recentlySpacifiedCustomerReview = recentlySpacifiedCustomerReviewRepository.save(recentlySpacifiedCustomerReview);

        sendReviewInitiateCommunication(recentlySpacifiedCustomerReview,userProfileMiniDto,userProfileMiniDtoRequestor);

        return recentlySpacifiedCustomerReview;
    }

    /**
     * This service method is used resend review communication
     *
     * @param reviewId
     * @param subjectId
     * @return
     */
    @Override
    public RecentlySpacifiedCustomerReview resendReviewCommunication(String reviewId, Long subjectId) {
        RecentlySpacifiedCustomerReview recentlySpacifiedCustomerReview = findById(reviewId);
        Map<Long,UserProfileMiniDto2> userProfileMiniDtoMap = userProfileService.getUserProfileForSubjectIdsWithEmailMobile(new HashSet<>(Arrays.asList(recentlySpacifiedCustomerReview.getReviewerSubjectId(),recentlySpacifiedCustomerReview.getRevieweeSubjectId())));
        sendReviewInitiateCommunication(recentlySpacifiedCustomerReview,userProfileMiniDtoMap.get(recentlySpacifiedCustomerReview.getReviewerSubjectId()),userProfileMiniDtoMap.get(recentlySpacifiedCustomerReview.getRevieweeSubjectId()));
        return recentlySpacifiedCustomerReview;
    }

    /**
     * This service method is used to answer Review
     *
     * @param map
     * @param reviewId
     * @param subjectId
     * @return
     */
    @Override
    public RecentlySpacifiedCustomerReview answerReview(LinkedHashMap<String,Set<ReviewAnswerDto>> map, String reviewId, Long subjectId) {

       RecentlySpacifiedCustomerReview recentlySpacifiedCustomerReview = findById(reviewId);
        LinkedHashMap<String,Set<CustomerReviewQuestionAnswerContainer>> groupQuestionSet = recentlySpacifiedCustomerReview.getGroupQuestionSet();
        for (Map.Entry<String,Set<ReviewAnswerDto>> entry: map.entrySet()) {
            Set<CustomerReviewQuestionAnswerContainer> customerReviewQuestionAnswerContainers = groupQuestionSet.get(entry.getKey());
            Map<String,CustomerReviewQuestionAnswerContainer> contanerMap= new LinkedHashMap<>();
            for (CustomerReviewQuestionAnswerContainer customerReviewQuestionAnswerContainer: customerReviewQuestionAnswerContainers)
                  {
                      contanerMap.put(customerReviewQuestionAnswerContainer.getCustomerReviewQuestion().getQuestion(),customerReviewQuestionAnswerContainer);

                  }

            for (ReviewAnswerDto reviewAnswerDto:entry.getValue()) {
                CustomerReviewQuestionAnswerContainer customerReviewQuestionAnswerContainer = contanerMap.get(reviewAnswerDto.getQuestion());
                if(customerReviewQuestionAnswerContainer != null){
                    customerReviewQuestionAnswerContainer.MergeInMe(reviewAnswerDto);
                    contanerMap.put(reviewAnswerDto.getQuestion(),customerReviewQuestionAnswerContainer);
                }
            }

            customerReviewQuestionAnswerContainers.removeAll(contanerMap.values());
            customerReviewQuestionAnswerContainers.addAll(contanerMap.values());

            groupQuestionSet.put(entry.getKey(),customerReviewQuestionAnswerContainers);


        }

        recentlySpacifiedCustomerReview.setGroupQuestionSet(groupQuestionSet);
        recentlySpacifiedCustomerReview.preUpdate(subjectId);

        recentlySpacifiedCustomerReview = recentlySpacifiedCustomerReviewRepository.save(recentlySpacifiedCustomerReview);

        Map<Long,UserProfileMiniDto2> userProfileMiniDtoMap = userProfileService.getUserProfileForSubjectIdsWithEmailMobile(new HashSet<>(Arrays.asList(recentlySpacifiedCustomerReview.getReviewerSubjectId(),recentlySpacifiedCustomerReview.getRevieweeSubjectId())));
        sendReviewThanksCommunication(recentlySpacifiedCustomerReview,userProfileMiniDtoMap.get(recentlySpacifiedCustomerReview.getReviewerSubjectId()),userProfileMiniDtoMap.get(recentlySpacifiedCustomerReview.getRevieweeSubjectId()));
        return recentlySpacifiedCustomerReview;
    }

    /**
     * This service method is used to get RecentlySpacifiedCustomerReview via FilterRequestDto
     *
     * @param filterRequestDto
     * @return
     */
    @Override
    public Page<RecentlySpacifiedCustomerReview> getRecentlySpacifiedCustomerReviewFiltered(FilterRequestDto filterRequestDto) {
        return queryUtilService.getResultList(filterRequestDto,new HashSet<>(Arrays.asList("lastModifiedDate")),new RecentlySpacifiedCustomerReview());
    }

    /**
     * This service method is used to get user Initiated RecentlySpacifiedCustomerReview via FilterRequestDto
     *
     * @param filterRequestDto
     * @param subjectId
     * @return
     */
    @Override
    public Page<RecentlySpacifiedCustomerReview> getMyInitiatedRecentlySpacifiedCustomerReviewFiltered(FilterRequestDto filterRequestDto, Long subjectId) {
        SearchQuery searchQuery = filterRequestDto.getSearchQuery() == null ? new SearchQuery(): filterRequestDto.getSearchQuery();
        Map<String, List<Object>> where = searchQuery.getWhere() == null ? new LinkedHashMap<>(): searchQuery.getWhere();
        where.put("revieweeSujectId",Arrays.asList(subjectId));
        searchQuery.setWhere(where);
        filterRequestDto.setSearchQuery(searchQuery);
        return getRecentlySpacifiedCustomerReviewFiltered(filterRequestDto);
    }

    /**
     * This service method is usd to get user Answered RecentlySpacifiedCustomerReview via FilterRequestDto
     *
     * @param filterRequestDto
     * @param subjectId
     * @return
     */
    @Override
    public Page<RecentlySpacifiedCustomerReview> getMyAnsweredRecentlySpacifiedCustomerReviewFiltered(FilterRequestDto filterRequestDto, Long subjectId) {
        SearchQuery searchQuery = filterRequestDto.getSearchQuery() == null ? new SearchQuery(): filterRequestDto.getSearchQuery();
        Map<String, List<Object>> where = searchQuery.getWhere() == null ? new LinkedHashMap<>(): searchQuery.getWhere();
        where.put("reviewerSubjectId",Arrays.asList(subjectId));
        searchQuery.setWhere(where);
        filterRequestDto.setSearchQuery(searchQuery);
        return getRecentlySpacifiedCustomerReviewFiltered(filterRequestDto);
    }

    /**
     * This service method is used to find RecentlySpacifiedCustomerReview by id
     *
     * @param id
     * @return
     */
    @Override
    public RecentlySpacifiedCustomerReview findById(String id) {
        Optional<RecentlySpacifiedCustomerReview> recentlySpacifiedCustomerReviewOptional = recentlySpacifiedCustomerReviewRepository.findById(id);
        if(recentlySpacifiedCustomerReviewOptional.isPresent()){
            return recentlySpacifiedCustomerReviewOptional.get();
        }
        throw new CustomerReviewNotFound();
    }

    /**
     * This service method is used to find EnvelopedCustomerReviewsById
     *
     * @param id
     * @return
     */
    @Override
    public EnvelopedCustomerReview findEnvelopedCustomerReviewById(String id) {
        RecentlySpacifiedCustomerReview recentlySpacifiedCustomerReview = findById(id);
        Map<Long, UserProfileMiniDto> map = userProfileService.getUserProfileForSubjectIds(new HashSet<>(Arrays.asList(recentlySpacifiedCustomerReview.getRevieweeSubjectId(),recentlySpacifiedCustomerReview.getReviewerSubjectId())));

        return new EnvelopedCustomerReview(map.get(recentlySpacifiedCustomerReview.getReviewerSubjectId()),map.get(recentlySpacifiedCustomerReview.getRevieweeSubjectId()),recentlySpacifiedCustomerReview);
    }

    /**
     * This service method is used to Find EnvelopedCustomerReview For the Project
     *
     * @param projectId
     * @return
     */
    @Override
    public List<EnvelopedCustomerReview> findEnvelopedCustomerReviewForProject(String projectId) {
        List<RecentlySpacifiedCustomerReview> reviews = findByProjectId(projectId);
        if(reviews == null){
            return null;
        }
        Set<Long> subjectIds = new HashSet<>();
        for (RecentlySpacifiedCustomerReview recentlySpacifiedCustomerReview: reviews) {
            subjectIds.add(recentlySpacifiedCustomerReview.getRevieweeSubjectId());
            subjectIds.add(recentlySpacifiedCustomerReview.getReviewerSubjectId());
        }
        Map<Long,UserProfileMiniDto> map = userProfileService.getUserProfileForSubjectIds(subjectIds);
        List<EnvelopedCustomerReview> envelopedCustomerReviews = new ArrayList<>();
        for(RecentlySpacifiedCustomerReview recentlySpacifiedCustomerReview: reviews ){
            envelopedCustomerReviews.add(new EnvelopedCustomerReview(map.get(recentlySpacifiedCustomerReview.getReviewerSubjectId()),map.get(recentlySpacifiedCustomerReview.getRevieweeSubjectId()),recentlySpacifiedCustomerReview));
        }
        return envelopedCustomerReviews;
    }

    /**
     * This service method is used to find RecentlySpacified Customer Review By ProjectId
     *
     * @param projectId
     * @return
     */
    @Override
    public List<RecentlySpacifiedCustomerReview> findByProjectId(String projectId) {
        return recentlySpacifiedCustomerReviewRepository.findByProjectId(projectId);
    }

    private void sendReviewInitiateCommunication(RecentlySpacifiedCustomerReview recentlySpacifiedCustomerReview,
                                                 UserProfileMiniDto2 userProfileMiniDto,
                                                 UserProfileMiniDto2 userProfileMiniDtoRequestor){

        final Map<String,Object> placeHolders = new HashMap<>();
        placeHolders.put("REVIEW_LINK",reviewDefaultUrl+recentlySpacifiedCustomerReview.getId());
        placeHolders.put("B2B_USER",userProfileMiniDtoRequestor.getName());
        placeHolders.put("CUSTOMER_UNIQUECUSTOMERID",userProfileMiniDto.getSubjectId());
        placeHolders.put("CUSTOMER_EMAIL1",userProfileMiniDto.getEmail());
        placeHolders.put("CUSTOMER_MOBILE",userProfileMiniDto.getMobile());
        communicationService.sendCommunicationAsync("RECENTLY_SPACIFIED_REVIEW",placeHolders);

    }

    private void sendReviewThanksCommunication(RecentlySpacifiedCustomerReview recentlySpacifiedCustomerReview,
                                               UserProfileMiniDto2 userProfileMiniDto,
                                               UserProfileMiniDto2 userProfileMiniDtoRequestor){

        final Map<String,Object> placeHolders = new HashMap<>();
        placeHolders.put("LINK",reviewDefaultUrl+recentlySpacifiedCustomerReview.getId());
        placeHolders.put("B2C_USER",userProfileMiniDto.getName());
        placeHolders.put("CUSTOMER_UNIQUECUSTOMERID",userProfileMiniDto.getSubjectId());
        placeHolders.put("CUSTOMER_EMAIL1",userProfileMiniDto.getEmail());
        placeHolders.put("CUSTOMER_MOBILE",userProfileMiniDto.getMobile());
        communicationService.sendCommunicationAsync("RECENTLY_SPACIFIED_REVIEW_THANKS",placeHolders);

        final Map<String,Object> placeHolders_new = new HashMap<>();
        placeHolders_new.put("LINK",reviewDefaultUrl+recentlySpacifiedCustomerReview.getId());
        placeHolders_new.put("B2C_CUSTOMER",userProfileMiniDto.getName());
        placeHolders_new.put("CUSTOMER_UNIQUECUSTOMERID",userProfileMiniDtoRequestor.getSubjectId());
        placeHolders_new.put("CUSTOMER_EMAIL1",userProfileMiniDtoRequestor.getEmail());
        placeHolders_new.put("CUSTOMER_MOBILE",userProfileMiniDtoRequestor.getMobile());
        communicationService.sendCommunicationAsync("RECENTLY_SPACIFIED_REVIEW_THANKS_INTERNAL",placeHolders_new);

    }
}
