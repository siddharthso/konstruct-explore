package com.spacifii.konstruct.explore.service.explore;

import com.spacifii.konstruct.explore.entities.recentlyspacified.SearchProfileBy;
import com.spacifii.konstruct.explore.model.dto.explore.UserProfileMiniDto;
import com.spacifii.konstruct.explore.model.dto.explore.UserProfileMiniDto2;

import java.util.Map;
import java.util.Set;

/**
 * This service class connects with member service  for UserProfile Data Exchange
 */
public interface UserProfileService {

    /**
     * This service method is used to get UserProfile Map from MemberService
     * @param subjectIds
     * @return
     */
    Map<Long, UserProfileMiniDto> getUserProfileForSubjectIds(Set<Long> subjectIds);


    /**
     * This service method is used to get UserProfile Map from MemberService
     * @param subjectIds
     * @return
     */
    Map<Long, UserProfileMiniDto2> getUserProfileForSubjectIdsWithEmailMobile(Set<Long> subjectIds);

    /**
     * This service method is used to get UserProfile Map for MemberService via emailIds
     * @param emailIds
     * @return
     */
    Map<String, UserProfileMiniDto2> getUserProfileForEmailIds(Set<String> emailIds);

    /**
     * This service method is used to get EmailId for SubjectId
     * @param subjectId
     * @return
     */
    String getEmailIdForSubjectId(Long subjectId);

    /**
     * This service method is used to get subjectId from ProfileId
     * @param profileId
     * @return
     */
    Long getSubjectIdForProfileId(String profileId);

    /**
     * This service method is used to get UserProfile by Email or Mobile
     * @param searchProfileBy
     * @param value
     * @return
     */
    UserProfileMiniDto2 getuserByMobileEmail(SearchProfileBy searchProfileBy, String value);
}
