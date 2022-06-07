package com.spacifii.konstruct.explore.controller.recentlyspacified;


import com.spacifii.konstruct.explore.entities.recentlyspacified.BeforeAfterMedia;
import com.spacifii.konstruct.explore.entities.recentlyspacified.RecentlySpacifiedMedia;
import com.spacifii.konstruct.explore.integration.rbac.annotation.RbacRegister;
import com.spacifii.konstruct.explore.model.APIResponseKey;
import com.spacifii.konstruct.explore.model.ApiResponse;
import com.spacifii.konstruct.explore.model.dto.explore.FilterRequestDto;
import com.spacifii.konstruct.explore.model.dto.recentlyspacified.*;
import com.spacifii.konstruct.explore.service.recentlyspacified.RecentlySpacifiedProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashSet;

@RestController
@RequestMapping(value = "/api/recentlySpacifiedProject")
public class RecentlySpacifiedProjectController {


    @Autowired
    RecentlySpacifiedProjectService recentlySpacifiedProjectService;


    /**
     * This controller method is used to create RecentlySpacifiedProject From Explore Project
     * @param projectid
     * @param subjectid
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "createRecentlySpacifiedProjectFromExploreProject")
    @PostMapping(value = "/createRecentlySpacifiedProjectFromExploreProject", consumes = "application/json")
    public ApiResponse<EnvelopedRecentlySpacifiedProjectDto> createRecentlySpacifiedProjectFromExploreProject(@RequestBody String projectid,
                                                                                                              @RequestHeader("subjectid") Long subjectid){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,recentlySpacifiedProjectService.saveFromExploreProject(projectid,subjectid),null);
    }

    /**
     * This controller method is used to update RecentlySpacifiedProject From Explore Project
     * @param projectid
     * @param subjectid
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "updateRecentlySpacifiedProjectFromExploreProject")
    @PostMapping(value = "/updateRecentlySpacifiedProjectFromExploreProject", consumes = "application/json")
    public ApiResponse<EnvelopedRecentlySpacifiedProjectDto> updateRecentlySpacifiedProjectFromExploreProject(@RequestBody String projectid,
                                                                                                              @RequestHeader("subjectid") Long subjectid){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,recentlySpacifiedProjectService.updateFromExploreProject(projectid,subjectid),null);
    }




    /**
     * This controller method is used to update recentlySpacifiedMedia Medias From Explore Project
     * @param projectid
     * @param subjectid
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "updateRecentlySpacifiedProjectRecentlySpacifiedMedia")
    @PostMapping(value = "/{projectId}/updateRecentlySpacifiedProjectRecentlySpacifiedMedia", consumes = "application/json")
    public ApiResponse<EnvelopedRecentlySpacifiedProjectDto> updateRecentlySpacifiedMedia(@RequestBody LinkedHashSet<RecentlySpacifiedMedia> recentlySpacifiedMedia,
                                                                                                              @PathVariable("projectId") String projectid,
                                                                                                              @RequestHeader("subjectid") Long subjectid){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,recentlySpacifiedProjectService.updateRecentlySpacifiedMedia(recentlySpacifiedMedia,projectid,subjectid),null);
    }

    /**
     * This controller method is used to update recentlySpacifiedMedia Medias From Explore Project
     * @param projectid
     * @param subjectid
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "updateRecentlySpacifiedProjectBeforeAfterMedia")
    @PostMapping(value = "/{projectId}/updateRecentlySpacifiedProjectBeforeAfterMedia", consumes = "application/json")
    public ApiResponse<EnvelopedRecentlySpacifiedProjectDto> updateBeforeAfterMedias(@RequestBody LinkedHashSet<BeforeAfterMedia> beforeAfterMedia,
                                                                                          @PathVariable("projectId") String projectid,
                                                                                          @RequestHeader("subjectid") Long subjectid){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,recentlySpacifiedProjectService.updateBeforeAfterMedias(beforeAfterMedia,projectid,subjectid),null);
    }


    /**
     * This controller method is used to update recentlySpacifiedMedia Medias From Explore Project
     * @param projectid
     * @param subjectid
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "updateRecentlySpacifiedFeaturedProperties")
    @PostMapping(value = "/{projectId}/updateRecentlySpacifiedFeaturedProperties", consumes = "application/json")
    public ApiResponse<EnvelopedRecentlySpacifiedProjectDto> updateRecentlySpacifiedFeaturedProperties(@RequestBody RecentlySpacifiedRequestFeaturedDto recentlySpacifiedRequestFeaturedDto,
                                                                                          @PathVariable("projectId") String projectid,
                                                                                          @RequestHeader("subjectid") Long subjectid){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,recentlySpacifiedProjectService.updateRecentlySpacifiedFeaturedProperties(recentlySpacifiedRequestFeaturedDto,projectid,subjectid),null);
    }



    /**
     * This controller method is used to update recentlySpacifiedMedia Tags Keywords and Categories
     * @param projectid
     * @param subjectid
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "updateRecentlySpacifiedKeywordsTagsCategories")
    @PostMapping(value = "/{projectId}/updateRecentlySpacifiedKeywordsTagsCategories", consumes = "application/json")
    public ApiResponse<EnvelopedRecentlySpacifiedProjectDto> updateRecentlySpacifiedKeywordsTagsCategories(@RequestBody RecentlySpacifiedRequestKeywordTagsCagtegoriesDto recentlySpacifiedRequestKeywordTagsCagtegoriesDto,
                                                                                                       @PathVariable("projectId") String projectid,
                                                                                                       @RequestHeader("subjectid") Long subjectid){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,recentlySpacifiedProjectService.updateRecentlySpacifiedKeywordsTagsCategories(recentlySpacifiedRequestKeywordTagsCagtegoriesDto,projectid,subjectid),null);
    }

    /**
     * This controller method is used to update recentlySpacifiedMedia Main Section
     * @param projectid
     * @param subjectid
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "updateRecentlySpacifiedMainSection")
    @PostMapping(value = "/{projectId}/updateRecentlySpacifiedMainSection", consumes = "application/json")
    public ApiResponse<EnvelopedRecentlySpacifiedProjectDto> updateRecentlySpacifiedMainSection(@RequestBody RecentlySpacifiedRequestMainSectionDto recentlySpacifiedRequestMainSectionDto,
                                                                                                           @PathVariable("projectId") String projectid,
                                                                                                           @RequestHeader("subjectid") Long subjectid){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,recentlySpacifiedProjectService.updateRecentlySpacifiedMainSection(recentlySpacifiedRequestMainSectionDto,projectid,subjectid),null);
    }


    /**
     * This controller method is used to update recentlySpacifiedMedia Medias From Explore Project
     * @param projectid
     * @param subjectid
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "updateRecentlySpacifiedPermission")
    @PostMapping(value = "/{projectId}/updateRecentlySpacifiedPermission", consumes = "application/json")
    public ApiResponse<EnvelopedRecentlySpacifiedProjectDto> updateRecentlySpacifiedPermission(@RequestBody RecentlySpacifiedRequestPermissionDto recentlySpacifiedRequestPermissionDto,
                                                                                                @PathVariable("projectId") String projectid,
                                                                                                @RequestHeader("subjectid") Long subjectid){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,recentlySpacifiedProjectService.updateRecentlySpacifiedPermission(recentlySpacifiedRequestPermissionDto,projectid,subjectid),null);
    }



    /**
     * This controller method is used to update recentlySpacifiedMedia Main Section
     * @param projectid
     * @param subjectid
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "findEnvelopedRecentlySpacifiedProjectDtoExtendedByProjectId", excluded = true)
    @GetMapping(value = "/{projectId}/findEnvelopedRecentlySpacifiedProjectDtoExtendedByProjectId")
    public ApiResponse<EnvelopedRecentlySpacifiedProjectDtoExtended> findEnvelopedRecentlySpacifiedProjectDtoExtendedByProjectId(@PathVariable("projectId") String projectid,
                                                                                                @RequestHeader("subjectid") Long subjectid){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,recentlySpacifiedProjectService.findEnvelopedRecentlySpacifiedProjectDtoExtendedByProjectId(projectid),null);
    }


    /**
     * This controller method is used to update recentlySpacifiedMedia Main Section
     * @param routeUrl
     * @param subjectid
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "findEnvelopedRecentlySpacifiedProjectDtoExtendedViaRouteUrl", excluded = true)
    @PostMapping(value = "/findEnvelopedRecentlySpacifiedProjectDtoExtendedViaRouteUrl", consumes = "application/json")
    public ApiResponse<EnvelopedRecentlySpacifiedProjectDtoExtended> findEnvelopedRecentlySpacifiedProjectDtoExtendedViaRouteUrl(@RequestBody String routeUrl,
                                                                                                                                 @RequestHeader("subjectid") Long subjectid){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,recentlySpacifiedProjectService.findEnvelopedRecentlySpacifiedProjectDtoExtendedViaRouteUrl(routeUrl),null);
    }


    /**
     * This controller method is used to get recentlySpacifiedMedia via filterRequestDto
     * @param filterRequestDto
     * @param
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "getPageEnvelopedRecentlySpacifiedProjectPublic", excluded = true)
    @PostMapping(value = "/getPageEnvelopedRecentlySpacifiedProjectPublic", consumes = "application/json")
    public ApiResponse<Page<EnvelopedRecentlySpacifiedProjectDto>> getPageEnvelopedRecentlySpacifiedProjectPublic(@RequestBody FilterRequestDto filterRequestDto){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,recentlySpacifiedProjectService.getPageEnvelopedRecentlySpacifiedProjectPublic(filterRequestDto),null);
    }

    /**
     * This controller method is used to get recentlySpacifiedMedia via FilterRequestDto
     * @param filterRequestDto
     * @param
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "getRecentlySpacifiedProjectFiltered")
    @PostMapping(value = "/getRecentlySpacifiedProjectFiltered", consumes = "application/json")
    public ApiResponse<Page<EnvelopedRecentlySpacifiedProjectDto>> getRecentlySpacifiedProjectFiltered(@RequestBody FilterRequestDto filterRequestDto){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,recentlySpacifiedProjectService.getRecentlySpacifiedProjectFiltered(filterRequestDto),null);
    }



}
