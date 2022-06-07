package com.spacifii.konstruct.explore.service.recentlyspacified.impl;



import com.spacifii.konstruct.explore.entities.recentlyspacified.CustomerTestimonial;
import com.spacifii.konstruct.explore.entities.recentlyspacified.CustomerTestimonialInitiateRequest;
import com.spacifii.konstruct.explore.exception.UserProfileNotFoundException;
import com.spacifii.konstruct.explore.exception.recentlyspacified.CustomerTestimonialIniateRequestNotFound;
import com.spacifii.konstruct.explore.exception.recentlyspacified.CustomerTestimonialRequestAlreadyInitiated;
import com.spacifii.konstruct.explore.integration.communication.CommunicationService;
import com.spacifii.konstruct.explore.model.dto.explore.FilterRequestDto;
import com.spacifii.konstruct.explore.model.dto.explore.UserProfileMiniDto;
import com.spacifii.konstruct.explore.model.dto.explore.UserProfileMiniDto2;
import com.spacifii.konstruct.explore.model.dto.query.SearchQuery;
import com.spacifii.konstruct.explore.repository.recentlyspacified.CustomerTestimonialInitiateRequestRepository;
import com.spacifii.konstruct.explore.service.explore.QueryUtilService;
import com.spacifii.konstruct.explore.service.explore.UserProfileService;
import com.spacifii.konstruct.explore.service.recentlyspacified.CustomerTestimonialInitiateRequestService;
import com.spacifii.konstruct.explore.service.recentlyspacified.CustomerTestimonialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * This service class manages CustomerTestimonialInitiateRequest
 */
@Service
public class CustomerTestimonialInitiateRequestServiceImpl implements CustomerTestimonialInitiateRequestService {

    @Autowired
    CustomerTestimonialInitiateRequestRepository customerTestimonialInitiateRequestRepository;

    @Autowired
    CustomerTestimonialService customerTestimonialService;

    @Autowired
    QueryUtilService queryUtilService;

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    CommunicationService communicationService;

    @Value("${testimonialDefaultUrl}")
    String testimonialDefaultUrl;



    /**
     * This service method is used to CustomerTestimonialInitiateRequest
     *
     * @param customerTestimonialInitiateRequest
     * @param subject
     * @return
     */
    @Override
    @Transactional
    public CustomerTestimonial saveCustomerTestimonialInitiateRequest(CustomerTestimonialInitiateRequest customerTestimonialInitiateRequest, Long subject) {
        UserProfileMiniDto2 userProfileMiniDto = userProfileService.getuserByMobileEmail(customerTestimonialInitiateRequest.getSearchProfileBy(),customerTestimonialInitiateRequest.getSearchValue());

        if(userProfileMiniDto == null){
            throw new UserProfileNotFoundException();
        }

        CustomerTestimonialInitiateRequest customerTestimonialInitiateRequestExiting = customerTestimonialInitiateRequestRepository.findFirstByProjectIdAndCustomerSubjectId(customerTestimonialInitiateRequest.getProjectId(), Long.valueOf(userProfileMiniDto.getSubjectId()));

        if(customerTestimonialInitiateRequestExiting != null){
            throw new CustomerTestimonialRequestAlreadyInitiated();
        }
        customerTestimonialInitiateRequest.setCustomerSubjectId(Long.valueOf(userProfileMiniDto.getSubjectId()));

        CustomerTestimonial customerTestimonial =customerTestimonialService.createCustomerTestimonialViaRequest(customerTestimonialInitiateRequest,subject);
        customerTestimonialInitiateRequest.setCustomerTestimonialId(customerTestimonial.getId());

        customerTestimonialInitiateRequest =customerTestimonialInitiateRequestRepository.save(customerTestimonialInitiateRequest);
        Map<Long,UserProfileMiniDto2> map = userProfileService.getUserProfileForSubjectIdsWithEmailMobile(new HashSet<>(Arrays.asList(subject)));
        sendCommunication(customerTestimonialInitiateRequest,userProfileMiniDto,map.get(subject));
        return customerTestimonial;
    }

    /**
     * This service method is used to Resend Customer Testimonial
     *
     * @param customerTestimonialInitiateRequestId
     * @param subjectId
     * @return
     */
    @Override
    public CustomerTestimonial resentCustomerTestimonialInitiateRequest(String customerTestimonialInitiateRequestId, Long subjectId) {
        CustomerTestimonialInitiateRequest customerTestimonialInitiateRequest = findById(customerTestimonialInitiateRequestId);
        Map<Long,UserProfileMiniDto2> map = userProfileService.getUserProfileForSubjectIdsWithEmailMobile(new HashSet<>(Arrays.asList(customerTestimonialInitiateRequest.getCustomerSubjectId(),subjectId)));
        CustomerTestimonial customerTestimonial = customerTestimonialService.findById(customerTestimonialInitiateRequest.getCustomerTestimonialId());
        sendCommunication(customerTestimonialInitiateRequest,map.get(customerTestimonialInitiateRequest.getCustomerSubjectId()),map.get(subjectId));
        return customerTestimonial;
    }

    /**
     * This service method is used to get CustomerTestimonialInitiateRequest via FilterRequestDto
     *
     * @param filterRequestDto
     * @return
     */
    @Override
    public Page<CustomerTestimonialInitiateRequest> getCustomerTestimonialInitiateRequestFiltered(FilterRequestDto filterRequestDto) {
        return queryUtilService.getResultList(filterRequestDto,new HashSet<>(Arrays.asList("creationDate")), new CustomerTestimonialInitiateRequest());
    }

    /**
     * This service method is used to get User's CustomerTestimonialInitiateRequest via FilterRequestDto
     *
     * @param filterRequestDto
     * @return
     */
    @Override
    public Page<CustomerTestimonialInitiateRequest> getMyCustomerTestimonialInitiateRequestFiltered(FilterRequestDto filterRequestDto, Long subjectId) {
        SearchQuery searchQuery = filterRequestDto.getSearchQuery() == null ? new SearchQuery(): filterRequestDto.getSearchQuery();
        Map<String, List<Object>> where = searchQuery.getWhere() == null ? new LinkedHashMap<>(): searchQuery.getWhere();
        where.put("createdBy",Arrays.asList(subjectId));
        searchQuery.setWhere(where);
        filterRequestDto.setSearchQuery(searchQuery);
        return getCustomerTestimonialInitiateRequestFiltered(filterRequestDto);
    }

    /**
     * This service method is used to get CustomerTestimonialInitiateRequest by Id
     *
     * @param id
     * @return
     */
    @Override
    public CustomerTestimonialInitiateRequest findById(String id) {
        Optional<CustomerTestimonialInitiateRequest> customerTestimonialInitiateRequest = customerTestimonialInitiateRequestRepository.findById(id);
        if(customerTestimonialInitiateRequest.isPresent()){
            return customerTestimonialInitiateRequest.get();
        }
        throw new CustomerTestimonialIniateRequestNotFound();
    }


    private void sendCommunication(CustomerTestimonialInitiateRequest customerTestimonialInitiateRequest, UserProfileMiniDto2 userProfileMiniDto, UserProfileMiniDto2 userProfileMiniDtoRequestor){
        final Map<String,Object> placeHolders = new HashMap<>();
        placeHolders.put("TESTIMONIAL_LINK",testimonialDefaultUrl+customerTestimonialInitiateRequest.getCustomerTestimonialId());
        placeHolders.put("B2B_USER",userProfileMiniDtoRequestor.getName());
        placeHolders.put("CUSTOMER_UNIQUECUSTOMERID",userProfileMiniDto.getSubjectId());
        placeHolders.put("CUSTOMER_EMAIL1",userProfileMiniDto.getEmail());
        placeHolders.put("CUSTOMER_MOBILE",userProfileMiniDto.getMobile());
        communicationService.sendCommunicationAsync("RECENTLY_SPACIFIED_TESTIMONIAL",placeHolders);

    }
}
