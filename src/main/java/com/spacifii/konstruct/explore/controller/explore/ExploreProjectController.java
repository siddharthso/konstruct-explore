package com.spacifii.konstruct.explore.controller.explore;


import com.spacifii.konstruct.explore.entities.explore.ExploreProject;
import com.spacifii.konstruct.explore.integration.rbac.annotation.RbacRegister;
import com.spacifii.konstruct.explore.model.APIResponseKey;
import com.spacifii.konstruct.explore.model.ApiResponse;
import com.spacifii.konstruct.explore.model.dto.explore.EnvelopedProject;
import com.spacifii.konstruct.explore.model.dto.explore.ExploreProjectRequestDto;
import com.spacifii.konstruct.explore.service.explore.BeanUtils;
import com.spacifii.konstruct.explore.service.explore.ExploreProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This is controller class for ExploreProject
 */
@RestController
@RequestMapping("/api/exploreProject")
public class ExploreProjectController {

    @Autowired
    ExploreProjectService exploreProjectService;


    /**
     * This Controller method is used to save a new ExploreProject
     * @param exploreProjectRequestDto
     * @param subjectId
     * @return
     */
    @RbacRegister(authorityName = "createExploreProject")
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/createExploreProject", consumes = "application/json")
    public ApiResponse<ExploreProject> save(@RequestBody ExploreProjectRequestDto exploreProjectRequestDto,
                                            @RequestHeader("subjectid") Long subjectId){

        ExploreProject exploreProject = BeanUtils.getConverted(exploreProjectRequestDto,ExploreProject.class);

        return new ApiResponse<>(APIResponseKey.NEW_CREATION,exploreProjectService.save(exploreProject,subjectId),null);

    }


    /**
     * This Controller method is used to save a new ExploreProject
     * @param proejectIds
     * @param subjectId
     * @return
     */
    @RbacRegister(authorityName = "getExploreProjectsByIds")
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/getExploreProjectsByIds", consumes = "application/json")
    public ApiResponse<Map<String,ExploreProject>> save(@RequestBody Set<String> proejectIds,
                                                          @RequestHeader(value = "subjectid", required = false) Long subjectId){

        return new ApiResponse<>(APIResponseKey.NEW_CREATION,exploreProjectService.getExploreProjectsByIds(proejectIds),null);

    }

    /**
     * This Controller method is used to update existing Explore project
     * @param exploreProjectRequestDto
     * @param subjectId
     * @param projectId
     * @return
     */
    @RbacRegister(authorityName = "updateExploreProject")
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(path = "/{projectId}/updateExploreProject", consumes = "application/json" )
    public ApiResponse<ExploreProject> update(@RequestBody ExploreProjectRequestDto exploreProjectRequestDto,
                                              @RequestHeader("subjectid") Long subjectId, @PathVariable("projectId") String projectId){

        ExploreProject exploreProject = BeanUtils.getConverted(exploreProjectRequestDto,ExploreProject.class);

        return new ApiResponse<>(APIResponseKey.ALL_GOOD,exploreProjectService.updateProject(exploreProject,projectId,subjectId),null);

    }


    /**
     * This Controller Method gets a ExploreProjectEnveloped
     * @param subjectId
     * @param projectId
     * @return
     */
    @RbacRegister(authorityName = "getMyEnvelopedProject")
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/{projectId}/getMyEnvelopedProject")
    public ApiResponse<EnvelopedProject> getEnvelopedProject( @RequestHeader("subjectid") Long subjectId,
                                                              @PathVariable String projectId) {

        return new ApiResponse<>(APIResponseKey.ALL_GOOD,exploreProjectService.getEnvelopedProject(projectId,subjectId),null);

    }


    /**
     * This Controller Method gets a ExploreProjectEnveloped
     * @param subjectId
     * @return
     */
    @RbacRegister(authorityName = "getAllMyEnvelopedProject")
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/getAllMyEnvelopedProject")
    public ApiResponse<List<EnvelopedProject>> getAllEnvelopedProject( @RequestHeader("subjectid") Long subjectId) {

        return new ApiResponse<>(APIResponseKey.ALL_GOOD,exploreProjectService.getAllEnvelopedProjectForUser(subjectId),null);

    }

    /**
     * This Controller Method updates FeaturedProjectMedia
     * @param subjectId
     * @return
     */
    @RbacRegister(authorityName = "setFeaturedExploreProjectMedia")
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(path = "/{projectId}/setFeaturedExploreProjectMedia", consumes = "application/json")
    public ApiResponse<Boolean> getAllEnvelopedProject(@RequestBody String mediaId ,
                                                       @PathVariable("projectId") String projectId,
                                                       @RequestHeader("subjectid") Long subjectId) {

        return new ApiResponse<>(APIResponseKey.ALL_GOOD,exploreProjectService.setFeaturedExploreProjectMedia(projectId,mediaId,subjectId),null);

    }
}
