package com.spacifii.konstruct.explore.service.recentlyspacified.impl;


import com.spacifii.konstruct.explore.entities.recentlyspacified.CustomerTestimonial;
import com.spacifii.konstruct.explore.entities.recentlyspacified.CustomerTestimonialInitiateRequest;
import com.spacifii.konstruct.explore.exception.recentlyspacified.CustomerTestimonialNotFound;
import com.spacifii.konstruct.explore.integration.communication.CommunicationService;
import com.spacifii.konstruct.explore.model.dto.explore.FilterRequestDto;
import com.spacifii.konstruct.explore.model.dto.explore.UserProfileMiniDto;
import com.spacifii.konstruct.explore.model.dto.explore.UserProfileMiniDto2;
import com.spacifii.konstruct.explore.model.dto.query.SearchQuery;
import com.spacifii.konstruct.explore.model.dto.recentlyspacified.EnvelopedCustomerTestimonial;
import com.spacifii.konstruct.explore.model.dto.recentlyspacified.TestimonialDto;
import com.spacifii.konstruct.explore.repository.recentlyspacified.CustomerTestimonialRepository;
import com.spacifii.konstruct.explore.service.explore.QueryUtilService;
import com.spacifii.konstruct.explore.service.explore.UserProfileService;
import com.spacifii.konstruct.explore.service.recentlyspacified.CustomerTestimonialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * This service class manges CustomerTestimonial
 */
@Service
public class CustomerTestimonialServiceImpl implements CustomerTestimonialService {

    @Autowired
    CustomerTestimonialRepository customerTestimonialRepository;


    @Autowired
    QueryUtilService queryUtilService;

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    CommunicationService communicationService;

    @Value("${testimonialDefaultUrl}")
    String testimonialDefaultUrl;
    

    /**
     * This service method is used to create CustomerTestimonial via CustomerTestimonialInitiateRequest
     *
     * @param customerTestimonialInitiateRequest
     * @param subjectId
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public CustomerTestimonial createCustomerTestimonialViaRequest(CustomerTestimonialInitiateRequest customerTestimonialInitiateRequest, Long subjectId) {
        CustomerTestimonial customerTestimonial = new CustomerTestimonial();
        customerTestimonial.mergeInMe(customerTestimonialInitiateRequest);
        customerTestimonial.preSave(subjectId);
        return customerTestimonialRepository.save(customerTestimonial);
    }

    /**
     * This service method is used to create CustomerTestimonial directly by Customer
     *
     * @param customerTestimonial
     * @param subjectId
     * @return
     */
    @Override
    public CustomerTestimonial createCustomerTestimonialB2CCustomer(CustomerTestimonial customerTestimonial, Long subjectId) {
        customerTestimonial.preSave(subjectId);
        return customerTestimonialRepository.save(customerTestimonial);
    }

    /**
     * This service method is used to create Customer Response for Testimonial
     *
     * @param testimonialDto
     * @param subjectId
     * @return
     */
    @Override
    public CustomerTestimonial createCustomerResponse(TestimonialDto testimonialDto, Long subjectId) {
        CustomerTestimonial customerTestimonial = findById(testimonialDto.getId());
        customerTestimonial.mergeInMe(testimonialDto);
        customerTestimonial.setTestimonialCustomerResponseDate(System.currentTimeMillis());
        Map<Long, UserProfileMiniDto2> map = userProfileService.getUserProfileForSubjectIdsWithEmailMobile(new HashSet<>(Arrays.asList(customerTestimonial.getCustomerSubjectId(),customerTestimonial.getInitiatedBySubjectId())));
        customerTestimonial =  customerTestimonialRepository.save(customerTestimonial);
        sendCommunication(customerTestimonial,map.get(customerTestimonial.getCustomerSubjectId()),map.get(customerTestimonial.getInitiatedBySubjectId()));
        return customerTestimonial;
    }

    /**
     * This service method is used to get CustomerTestimonial pages filterred
     *
     * @param filterRequestDto
     * @return
     */
    @Override
    public Page<CustomerTestimonial> getCustomerTestimonialFilterred(FilterRequestDto filterRequestDto) {
        return queryUtilService.getResultList(filterRequestDto,new HashSet<>(Arrays.asList("creationDate")),new CustomerTestimonial());
    }

    /**
     * This service method is used to get my Written CustomerTestimonial
     *
     * @param filterRequestDto
     * @param subjectId
     * @return
     */
    @Override
    public Page<CustomerTestimonial> getMyWrittenCustomerTestimonial(FilterRequestDto filterRequestDto, Long subjectId) {
        //customerSubjectId
        SearchQuery searchQuery = filterRequestDto.getSearchQuery() == null ? new SearchQuery(): filterRequestDto.getSearchQuery();
        Map<String, List<Object>> where = searchQuery.getWhere() == null ? new LinkedHashMap<>(): searchQuery.getWhere();
        where.put("customerSubjectId",Arrays.asList(subjectId));
        searchQuery.setWhere(where);
        filterRequestDto.setSearchQuery(searchQuery);
        return getCustomerTestimonialFilterred(filterRequestDto);

    }

    /**
     * This service method is used to get CustomerTestimonial which are initiated by Me
     *
     * @param filterRequestDto
     * @param subjectId
     * @return
     */
    @Override
    public Page<CustomerTestimonial> getCustomerTestimonialInitiatedByMe(FilterRequestDto filterRequestDto, Long subjectId) {
        //initiatedBySubjectId
        SearchQuery searchQuery = filterRequestDto.getSearchQuery() == null ? new SearchQuery(): filterRequestDto.getSearchQuery();
        Map<String, List<Object>> where = searchQuery.getWhere() == null ? new LinkedHashMap<>(): searchQuery.getWhere();
        where.put("initiatedBySubjectId",Arrays.asList(subjectId));
        searchQuery.setWhere(where);
        filterRequestDto.setSearchQuery(searchQuery);
        return getCustomerTestimonialFilterred(filterRequestDto);
    }

    /**
     * This service method is usd to find CustomerTestimonial By Id
     *
     * @param customerTestimonialId
     * @return
     */
    @Override
    public CustomerTestimonial findById(String customerTestimonialId) {
        Optional<CustomerTestimonial> customerTestimonialOptional = customerTestimonialRepository.findById(customerTestimonialId);
        if(customerTestimonialOptional.isPresent()){
           return customerTestimonialOptional.get();
        }
        throw new CustomerTestimonialNotFound();
    }

    /**
     * This service method is used to find EnvelopedCustomerTestimonial by Id
     *
     * @param customerTesimonialId
     * @return
     */
    @Override
    public EnvelopedCustomerTestimonial findEnvelopedCustomerTestimonialById(String customerTesimonialId) {
        CustomerTestimonial customerTestimonial = findById(customerTesimonialId);
        Map<Long,UserProfileMiniDto>  map = userProfileService.getUserProfileForSubjectIds(new HashSet<>(Arrays.asList(customerTestimonial.getCustomerSubjectId(),customerTestimonial.getInitiatedBySubjectId())));
        return new EnvelopedCustomerTestimonial(map.get(customerTestimonial.getCustomerSubjectId()),map.get(customerTestimonial.getInitiatedBySubjectId()),customerTestimonial);
    }

    /**
     * This service method is used to find EnvelopedCustomerTestimonial filtered via filterRequestDto
     *
     * @param filterRequestDto
     * @return
     */
    @Override
    public Page<EnvelopedCustomerTestimonial> findEnvelopedCustomerTestimonialFilterred(FilterRequestDto filterRequestDto) {
        return getEnvelopedCustomerTesimonialViaCustomerTestimonialPage(getCustomerTestimonialFilterred(filterRequestDto));
    }

    /**
     * This service method is used to find active EnvelopedCustomerTestimonial filtered via filterRequestDto
     *
     * @param filterRequestDto
     * @return
     */
    @Override
    public Page<EnvelopedCustomerTestimonial> findActiveEnvelopedCustomerTestimonialFilterred(FilterRequestDto filterRequestDto) {
        SearchQuery searchQuery = filterRequestDto.getSearchQuery() == null ? new SearchQuery(): filterRequestDto.getSearchQuery();
        Map<String, List<Object>> where = searchQuery.getWhere() == null ? new LinkedHashMap<>(): searchQuery.getWhere();
        where.put("active",Arrays.asList(true));
        searchQuery.setWhere(where);
        filterRequestDto.setSearchQuery(searchQuery);
        return findEnvelopedCustomerTestimonialFilterred(filterRequestDto);
    }

    /**
     * This service method is used to find active EnvelopedCustomerTestimonial filtered via filterRequestDto
     *
     * @param filterRequestDto
     * @return
     */
    @Override
    public Page<EnvelopedCustomerTestimonial> findActiveFeatureHomeEnvelopedCustomerTestimonialFilterred(FilterRequestDto filterRequestDto) {
        SearchQuery searchQuery = filterRequestDto.getSearchQuery() == null ? new SearchQuery(): filterRequestDto.getSearchQuery();
        Map<String, List<Object>> where = searchQuery.getWhere() == null ? new LinkedHashMap<>(): searchQuery.getWhere();
        where.put("active",Arrays.asList(true));
        where.put("featuredHome",Arrays.asList(true));
        searchQuery.setWhere(where);
        filterRequestDto.setSearchQuery(searchQuery);
        return findEnvelopedCustomerTestimonialFilterred(filterRequestDto);
    }

    /**
     * This service method is used to get EnvelopedCustomerTestimonial Via CustomerTestimonalPage
     *
     * @param customerTestimonialPage
     * @return
     */
    @Override
    public Page<EnvelopedCustomerTestimonial> getEnvelopedCustomerTesimonialViaCustomerTestimonialPage(Page<CustomerTestimonial> customerTestimonialPage) {
        if(!customerTestimonialPage.hasContent()){
            return null;
        }
        List<CustomerTestimonial> customerTestimonials = customerTestimonialPage.getContent();
        Set<Long> ids = new HashSet<>();
        for (CustomerTestimonial customerTestimonial: customerTestimonials) {
            ids.add(customerTestimonial.getCustomerSubjectId());
            ids.add(customerTestimonial.getInitiatedBySubjectId());
        }
        Map<Long,UserProfileMiniDto> map = userProfileService.getUserProfileForSubjectIds(ids);
        List<EnvelopedCustomerTestimonial> envelopedCustomerTestimonials  = new ArrayList<>();
        for (CustomerTestimonial customerTestimonial: customerTestimonials) {
            envelopedCustomerTestimonials.add(new EnvelopedCustomerTestimonial(map.get(customerTestimonial.getInitiatedBySubjectId()),map.get(customerTestimonial.getCustomerSubjectId()),customerTestimonial));
        }
        return new PageImpl<>(envelopedCustomerTestimonials,customerTestimonialPage.getPageable(),customerTestimonialPage.getTotalElements());
    }

    /**
     * This service method is used to toggle Home
     *
     * @param customerTestimonialId
     * @return
     */
    @Override
    public CustomerTestimonial toggleHome(String customerTestimonialId) {
        CustomerTestimonial customerTestimonial = findById(customerTestimonialId);
        customerTestimonial.setFeaturedHome(!customerTestimonial.getFeaturedHome());
        return customerTestimonialRepository.save(customerTestimonial);
    }

    /**
     * This service method is used to toggle Active
     *
     * @param customerTestimonialId
     * @return
     */
    @Override
    public CustomerTestimonial toggleActive(String customerTestimonialId) {
        CustomerTestimonial customerTestimonial = findById(customerTestimonialId);
        customerTestimonial.setActive(!customerTestimonial.isActive());
        return customerTestimonialRepository.save(customerTestimonial);
    }

    /**
     * This service method is used to find Customer Testimonial by ProjectId
     *
     * @param projectId
     * @return
     */
    @Override
    public List<CustomerTestimonial> findByProjectId(String projectId) {
        return customerTestimonialRepository.findByProjectId(projectId);
    }

    /**
     * This service method is used to find EnvelopedCustomerTestimonial By ProjectId
     *
     * @param projectId
     * @return
     */
    @Override
    public List<EnvelopedCustomerTestimonial> findEnvelopedCustomerTestimonialByProjectId(String projectId) {
        List<CustomerTestimonial> customerTestimonials = findByProjectId(projectId);
        if(customerTestimonials == null){
            return null;
        }

        Set<Long> ids = new HashSet<>();
        for (CustomerTestimonial customerTestimonial: customerTestimonials) {
            ids.add(customerTestimonial.getCustomerSubjectId());
            ids.add(customerTestimonial.getInitiatedBySubjectId());
        }
        Map<Long,UserProfileMiniDto> map = userProfileService.getUserProfileForSubjectIds(ids);
        List<EnvelopedCustomerTestimonial> envelopedCustomerTestimonials  = new ArrayList<>();
        for (CustomerTestimonial customerTestimonial: customerTestimonials) {
            envelopedCustomerTestimonials.add(new EnvelopedCustomerTestimonial(map.get(customerTestimonial.getInitiatedBySubjectId()),map.get(customerTestimonial.getCustomerSubjectId()),customerTestimonial));
        }
        return envelopedCustomerTestimonials;
    }


    private void sendCommunication(CustomerTestimonial customerTestimonial, UserProfileMiniDto2 userProfileMiniDto, UserProfileMiniDto2 userProfileMiniDtoRequestor){
        final Map<String,Object> placeHolders = new HashMap<>();
        placeHolders.put("LINK",testimonialDefaultUrl+customerTestimonial.getId());
        placeHolders.put("B2C_USER",userProfileMiniDto.getName());
        placeHolders.put("CUSTOMER_UNIQUECUSTOMERID",userProfileMiniDto.getSubjectId());
        placeHolders.put("CUSTOMER_EMAIL1",userProfileMiniDto.getEmail());
        placeHolders.put("CUSTOMER_MOBILE",userProfileMiniDto.getMobile());
        communicationService.sendCommunicationAsync("RECENTLY_SPACIFIED_TESTIMONIAL_THANKS",placeHolders);

        final Map<String,Object> placeHolders_new = new HashMap<>();
        placeHolders_new.put("LINK",testimonialDefaultUrl+customerTestimonial.getId());
        placeHolders_new.put("B2C_CUSTOMER",userProfileMiniDto.getName());
        placeHolders_new.put("CUSTOMER_UNIQUECUSTOMERID",userProfileMiniDtoRequestor.getSubjectId());
        placeHolders_new.put("CUSTOMER_EMAIL1",userProfileMiniDtoRequestor.getEmail());
        placeHolders_new.put("CUSTOMER_MOBILE",userProfileMiniDtoRequestor.getMobile());
        communicationService.sendCommunicationAsync("RECENTLY_SPACIFIED_TESTIMONIAL_THANKS_INTERNAL",placeHolders_new);

    }
}
