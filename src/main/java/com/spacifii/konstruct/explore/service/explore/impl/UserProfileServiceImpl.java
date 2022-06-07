package com.spacifii.konstruct.explore.service.explore.impl;

import com.spacifii.konstruct.explore.entities.recentlyspacified.SearchProfileBy;
import com.spacifii.konstruct.explore.exception.explore.MemberServiceException;
import com.spacifii.konstruct.explore.exception.explore.MemberServiceNotReabableException;
import com.spacifii.konstruct.explore.model.ApiResponse;
import com.spacifii.konstruct.explore.model.dto.explore.UserProfileMiniDto;
import com.spacifii.konstruct.explore.model.dto.explore.UserProfileMiniDto2;
import com.spacifii.konstruct.explore.service.explore.UserProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * This service class connects with member service  for UserProfile Data Exchange
 */
@Service
public class UserProfileServiceImpl implements UserProfileService {


    private final Logger logger = LoggerFactory.getLogger(UserProfileServiceImpl.class);

    @Autowired
    RestTemplate restTemplate;


    @Value("${member.fetch.url}")
    private String memberUrl;


    @Value("${member.fetchViaEmail.url}")
    private String memberUrlViaEmail;

    @Value("${member.fetchEmailViaSubjectId.url}")
    private String emailViaSubjectId;


    @Value("${member.subjectIdFromProfileId.url}")
    private String subjectIdFromProfileId;

    @Value("${member.getUserProfileBuCustomerSearchDto.url}")
    private String memberMobileEmailUrl;



    /**
     * This service method is used to get UserProfile Map from MemberService
     * @param subjectIds
     * @return
     */
    @Override
    public Map<Long, UserProfileMiniDto> getUserProfileForSubjectIds(Set<Long> subjectIds) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<Set<Long>> request = new HttpEntity<>(subjectIds, headers);
        try {

            ParameterizedTypeReference<ApiResponse<Map<Long, UserProfileMiniDto>>> typeRef = new ParameterizedTypeReference<ApiResponse<Map<Long, UserProfileMiniDto>>>(){};
            ResponseEntity<ApiResponse<Map<Long, UserProfileMiniDto>>> responseEntity = restTemplate.exchange(memberUrl, HttpMethod.POST,request,typeRef);
            //ApiResponse<Map<Long, UserProfileMiniDto>> response = restTemplate.postForObject(memberUrl, request, ApiResponse.class);


            logger.debug("RESPONSE FROM MEMBER SERVICE FOR GETTING USER : ->" + responseEntity.getBody().getPayload().toString());

            return responseEntity.getBody().getPayload();
        }catch (HttpClientErrorException e){

            e.printStackTrace();

            throw new MemberServiceException();

        } catch (ResourceAccessException e){

            e.printStackTrace();

            throw new MemberServiceNotReabableException();

        }

    }

    /**
     * This service method is used to get UserProfile Map from MemberService
     *
     * @param subjectIds
     * @return
     */
    @Override
    public Map<Long, UserProfileMiniDto2> getUserProfileForSubjectIdsWithEmailMobile(Set<Long> subjectIds) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<Set<Long>> request = new HttpEntity<>(subjectIds, headers);
        try {

            ParameterizedTypeReference<ApiResponse<Map<Long, UserProfileMiniDto2>>> typeRef = new ParameterizedTypeReference<ApiResponse<Map<Long, UserProfileMiniDto2>>>() {
            };
            ResponseEntity<ApiResponse<Map<Long, UserProfileMiniDto2>>> responseEntity = restTemplate.exchange(memberUrl, HttpMethod.POST, request, typeRef);
            //ApiResponse<Map<Long, UserProfileMiniDto>> response = restTemplate.postForObject(memberUrl, request, ApiResponse.class);


            logger.debug("RESPONSE FROM MEMBER SERVICE FOR GETTING USER : ->" + responseEntity.getBody().getPayload().toString());

            return responseEntity.getBody().getPayload();
        } catch (HttpClientErrorException e) {

            e.printStackTrace();

            throw new MemberServiceException();

        } catch (ResourceAccessException e) {

            e.printStackTrace();

            throw new MemberServiceNotReabableException();

        }
    }

    /**
     * This service method is used to get UserProfile Map for MemberService via emailIds
     *
     * @param emailIds
     * @return
     */
    @Override
    public Map<String, UserProfileMiniDto2> getUserProfileForEmailIds(Set<String> emailIds) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<Set<String>> request = new HttpEntity<>(emailIds, headers);
        try {

            ParameterizedTypeReference<ApiResponse<Map<String, UserProfileMiniDto2>>> typeRef = new ParameterizedTypeReference<ApiResponse<Map<String, UserProfileMiniDto2>>>(){};
            ResponseEntity<ApiResponse<Map<String, UserProfileMiniDto2>>> responseEntity = restTemplate.exchange(memberUrlViaEmail, HttpMethod.POST,request,typeRef);
            //ApiResponse<Map<Long, UserProfileMiniDto>> response = restTemplate.postForObject(memberUrl, request, ApiResponse.class);


            logger.debug("RESPONSE FROM MEMBER SERVICE FOR GETTING USER : ->" + responseEntity.getBody().getPayload().toString());

            return responseEntity.getBody().getPayload();
        }catch (HttpClientErrorException e){

            e.printStackTrace();

            throw new MemberServiceException();

        } catch (ResourceAccessException e){

            e.printStackTrace();

            throw new MemberServiceNotReabableException();

        }
    }

    /**
     * This service method is used to get EmailId for SubjectId
     *
     * @param subjectId
     * @return
     */
    @Override
    public String getEmailIdForSubjectId(Long subjectId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<Long> request = new HttpEntity<>(subjectId, headers);
        try {

            ParameterizedTypeReference<ApiResponse<String>> typeRef = new ParameterizedTypeReference<ApiResponse<String>>(){};
            ResponseEntity<ApiResponse<String>> responseEntity = restTemplate.exchange(emailViaSubjectId, HttpMethod.POST,request,typeRef);
            //ApiResponse<Map<Long, UserProfileMiniDto>> response = restTemplate.postForObject(memberUrl, request, ApiResponse.class);


            logger.debug("RESPONSE FROM MEMBER SERVICE FOR GETTING USER : ->" + responseEntity.getBody().getPayload().toString());

            return responseEntity.getBody().getPayload();
        }catch (HttpClientErrorException e){

            e.printStackTrace();

            throw new MemberServiceException();

        } catch (ResourceAccessException e){

            e.printStackTrace();

            throw new MemberServiceNotReabableException();

        }
    }

    /**
     * This service method is used to get subjectId from ProfileId
     *
     * @param profileId
     * @return
     */
    @Override
    public Long getSubjectIdForProfileId(String profileId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<String> request = new HttpEntity<>(profileId, headers);
        try {

            ParameterizedTypeReference<ApiResponse<Long>> typeRef = new ParameterizedTypeReference<ApiResponse<Long>>(){};
            ResponseEntity<ApiResponse<Long>> responseEntity = restTemplate.exchange(subjectIdFromProfileId, HttpMethod.POST,request,typeRef);
            //ApiResponse<Map<Long, UserProfileMiniDto>> response = restTemplate.postForObject(memberUrl, request, ApiResponse.class);


            logger.debug("RESPONSE FROM MEMBER SERVICE FOR GETTING USER : ->" + responseEntity.getBody().getPayload().toString());

            return responseEntity.getBody().getPayload();
        }catch (HttpClientErrorException e){

            e.printStackTrace();

            throw new MemberServiceException();

        } catch (ResourceAccessException e){

            e.printStackTrace();

            throw new MemberServiceNotReabableException();

        }

    }


    /**
     * This service method is used to get UserProfile by Email or Mobile
     *
     * @param searchProfileBy
     * @param value
     * @return
     */
    @Override
    public UserProfileMiniDto2 getuserByMobileEmail(SearchProfileBy searchProfileBy, String value) {
        Map<SearchProfileBy,String> map = new HashMap<>();
        map.put(searchProfileBy,value);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<Map<SearchProfileBy,String>> request = new HttpEntity<>(map, headers);
        try {

            ParameterizedTypeReference<ApiResponse<UserProfileMiniDto2>> typeRef = new ParameterizedTypeReference<ApiResponse<UserProfileMiniDto2>>(){};
            ResponseEntity<ApiResponse<UserProfileMiniDto2>> responseEntity = restTemplate.exchange(memberMobileEmailUrl, HttpMethod.POST,request,typeRef);
            //ApiResponse<Map<Long, UserProfileMiniDto>> response = restTemplate.postForObject(memberUrl, request, ApiResponse.class);


            logger.debug("RESPONSE FROM MEMBER SERVICE FOR GETTING USER : ->" + responseEntity.getBody().getPayload().toString());

            return responseEntity.getBody().getPayload();
        }catch (HttpClientErrorException e){

            e.printStackTrace();

            throw new MemberServiceException();

        } catch (ResourceAccessException e){

            e.printStackTrace();

            throw new MemberServiceNotReabableException();

        }
    }

    //@PostConstruct
    void doTest(){
        /*Set<Long> longs = new HashSet<>();
        longs.add(1L);
        Map<Long, UserProfileMiniDto> map = getUserProfileForSubjectIds(longs);
        System.out.println("THIS IS ME" + map.toString());*/
        Long subjectId = getSubjectIdForProfileId("BOND007");
        System.out.println("THIS IS ME" + subjectId);

    }
}
