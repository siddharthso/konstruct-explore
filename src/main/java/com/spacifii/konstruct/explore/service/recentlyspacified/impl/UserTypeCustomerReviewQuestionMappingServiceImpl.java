package com.spacifii.konstruct.explore.service.recentlyspacified.impl;

import com.spacifii.konstruct.explore.entities.recentlyspacified.CustomerReviewQuestion;
import com.spacifii.konstruct.explore.entities.recentlyspacified.UserTypeCustomerReviewQuestionMapping;
import com.spacifii.konstruct.explore.entities.recentlyspacified.UserTypeQuestionContainer;
import com.spacifii.konstruct.explore.model.dto.recentlyspacified.UserTypeQuestionContainerRequestDto;
import com.spacifii.konstruct.explore.repository.recentlyspacified.UserTypeCustomerReviewQuestionMappingRepository;
import com.spacifii.konstruct.explore.service.recentlyspacified.CustomerReviewQuestionService;
import com.spacifii.konstruct.explore.service.recentlyspacified.UserTypeCustomerReviewQuestionMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * This service class manages UserTypeQuestionContainer
 */
@Service
public class UserTypeCustomerReviewQuestionMappingServiceImpl implements UserTypeCustomerReviewQuestionMappingService {

    @Autowired
    UserTypeCustomerReviewQuestionMappingRepository userTypeCustomerReviewQuestionMappingRepository;

    @Autowired
    CustomerReviewQuestionService customerReviewQuestionService;

    /**
     * This service method is used to add Questions to UserTypeCustomerReviewQuestionMapping
     *
     * @param containers
     * @param groupName
     * @param userType
     * @param subjectId
     * @return
     */
    @Override
    public UserTypeCustomerReviewQuestionMapping addQuestions(LinkedHashSet<UserTypeQuestionContainerRequestDto> containers, String groupName, String userType, Long subjectId) {
        UserTypeCustomerReviewQuestionMapping userTypeCustomerReviewQuestionMapping = findByUserType(userType);
        LinkedHashSet<UserTypeQuestionContainer> userTypeQuestionContainers =getUserTypeQuestionContainerFromUseUserTypeQuestionMapAndUserTypeQuestionContainerRequestDtos(customerReviewQuestionService.findAllyByIds(containers.stream().
                map(UserTypeQuestionContainerRequestDto::getQuestion).collect(Collectors.toSet())).stream().
                collect(Collectors.toMap(CustomerReviewQuestion::getId, Function.identity())),containers);
        LinkedHashMap<String, Set<UserTypeQuestionContainer>> map = userTypeCustomerReviewQuestionMapping.getGroupQuestionSet();
        Set<UserTypeQuestionContainer> set = map.get(groupName);
        if(set == null){
            set = new LinkedHashSet<>();
        }
        set.removeAll(userTypeQuestionContainers);
        set.addAll(userTypeQuestionContainers);
        map.put(groupName,set);
        userTypeCustomerReviewQuestionMapping.setGroupQuestionSet(map);

        return userTypeCustomerReviewQuestionMappingRepository.save(userTypeCustomerReviewQuestionMapping);
    }

    private LinkedHashSet<UserTypeQuestionContainer> getUserTypeQuestionContainerFromUseUserTypeQuestionMapAndUserTypeQuestionContainerRequestDtos(Map<String,CustomerReviewQuestion> map,LinkedHashSet<UserTypeQuestionContainerRequestDto> containers){

        LinkedHashSet<UserTypeQuestionContainer> userTypeQuestionContainers = new LinkedHashSet<>();
        for (UserTypeQuestionContainerRequestDto dto: containers) {
            userTypeQuestionContainers.add(new UserTypeQuestionContainer(map.get(dto.getQuestion()),dto.getRequired(),dto.getRegex(),dto.getPlaceHolder(),dto.getToolTip(),dto.getHint(),dto.getErrorMessage(),dto.getGroupName()));
        }

        return userTypeQuestionContainers;

    }

    /**
     * This service method is used to remove Questions to UserTypeCustomerReviewQuestionMapping
     *
     * @param containers
     * @param groupName
     * @param userType
     * @param subjectId
     * @return
     */
    @Override
    public UserTypeCustomerReviewQuestionMapping removeQuestions(LinkedHashSet<UserTypeQuestionContainerRequestDto> containers, String groupName, String userType, Long subjectId) {
        UserTypeCustomerReviewQuestionMapping userTypeCustomerReviewQuestionMapping = findByUserType(userType);
        LinkedHashSet<UserTypeQuestionContainer> userTypeQuestionContainers =getUserTypeQuestionContainerFromUseUserTypeQuestionMapAndUserTypeQuestionContainerRequestDtos(customerReviewQuestionService.findAllyByIds(containers.stream().
                map(UserTypeQuestionContainerRequestDto::getQuestion).collect(Collectors.toSet())).stream().
                collect(Collectors.toMap(CustomerReviewQuestion::getId, Function.identity())),containers);
        LinkedHashMap<String, Set<UserTypeQuestionContainer>> map = userTypeCustomerReviewQuestionMapping.getGroupQuestionSet();
        Set<UserTypeQuestionContainer> set = map.get(groupName);
        if(set == null){
            return userTypeCustomerReviewQuestionMapping;
        }
        set.removeAll(userTypeQuestionContainers);
        if(set == null){
            map.remove(groupName);
        } else {
            map.put(groupName,set);
        }

        userTypeCustomerReviewQuestionMapping.setGroupQuestionSet(map);

        return userTypeCustomerReviewQuestionMappingRepository.save(userTypeCustomerReviewQuestionMapping);
    }

    /**
     * This service method is used to find UserTypeCustomerReviewQuestionMapping by UserType
     *
     * @param userType
     * @return
     */
    @Override
    public UserTypeCustomerReviewQuestionMapping findByUserType(String userType) {
        Optional<UserTypeCustomerReviewQuestionMapping> userTypeCustomerReviewQuestionMappingOptional = userTypeCustomerReviewQuestionMappingRepository.findById(userType);
        if(userTypeCustomerReviewQuestionMappingOptional.isPresent()){
            return userTypeCustomerReviewQuestionMappingOptional.get();
        }
        UserTypeCustomerReviewQuestionMapping userTypeCustomerReviewQuestionMapping = new UserTypeCustomerReviewQuestionMapping();
        userTypeCustomerReviewQuestionMapping.setUserType(userType);
        return userTypeCustomerReviewQuestionMapping;
    }

    /**
     * This service method is used to all UserTypeCustomerReviewQuestionMapping
     *
     * @return
     */
    @Override
    public List<UserTypeCustomerReviewQuestionMapping> findAll() {
        return userTypeCustomerReviewQuestionMappingRepository.findAll();
    }
}
