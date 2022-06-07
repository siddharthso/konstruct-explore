package com.spacifii.konstruct.explore.controller.explore;

import com.spacifii.konstruct.explore.entities.explore.ExploreProject;
import com.spacifii.konstruct.explore.entities.explore.Media;
import com.spacifii.konstruct.explore.integration.rbac.annotation.RbacRegister;
import com.spacifii.konstruct.explore.integration.rbac.entity.ApiType;
import com.spacifii.konstruct.explore.model.APIResponseKey;
import com.spacifii.konstruct.explore.model.ApiResponse;
import com.spacifii.konstruct.explore.model.dto.explore.EnvelopedB2CMedia;
import com.spacifii.konstruct.explore.model.dto.explore.EnvelopedMedia;
import com.spacifii.konstruct.explore.model.dto.explore.EnvelopedProject;
import com.spacifii.konstruct.explore.model.dto.explore.FilterRequestDto;
import com.spacifii.konstruct.explore.model.dto.query.SearchQuery;
import com.spacifii.konstruct.explore.service.explore.AdhocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


/**
 * This controller class is mostly used for searching
 */
@RestController
@RequestMapping("/api/extra")
public class AdhocController {


    @Autowired
    AdhocService adhocService;

    /**
     * This controller method returns SearchQuery results for Media
     * @param searchQueries
     * @return
     */
    @RbacRegister(authorityName = "mediaAdhoc", excluded = false,apiType = ApiType.INTERNAL)
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/mediaAdhoc",consumes = "application/json")
    public ApiResponse<List<List<Map<String, Object>>>> findMedia(@RequestBody List<SearchQuery> searchQueries){

        return new ApiResponse<>(APIResponseKey.ALL_GOOD,adhocService.findMediaAdhoc(searchQueries),null);

    }

    /**
     * This controller method returns SearchQuery results for Project
     * @param searchQueries
     * @return
     */
    @RbacRegister(authorityName = "exploreProjectAdhoc", excluded = false,apiType = ApiType.INTERNAL)
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/exploreProjectAdhoc",consumes = "application/json")
    public ApiResponse<List<List<Map<String, Object>>>> findProject(@RequestBody List<SearchQuery> searchQueries){

        return new ApiResponse<>(APIResponseKey.ALL_GOOD,adhocService.findProjectAdhoc(searchQueries),null);

    }


    /**
     * This controller method gets paginated results of ExploreProject for FilterRequest
     * @param filterRequestDto
     * @return
     */
    @RbacRegister(authorityName = "paginateProjectSearch", excluded = true)
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/paginateProjectSearch",consumes = "application/json")
    public ApiResponse<Page<ExploreProject>> findProjectWithFilters(@RequestBody FilterRequestDto filterRequestDto){

        return new ApiResponse<>(APIResponseKey.ALL_GOOD,adhocService.findProjectWithFilters(filterRequestDto),null);

    }


    /**
     * This controller method gets paginated results of Media for FilterRequest
     * @param filterRequestDto
     * @return
     */
    @RbacRegister(authorityName = "paginateMediaSearch", excluded = true)
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/paginateMediaSearch",consumes = "application/json")
    public ApiResponse<Page<Media>> findMediaWithFilters(@RequestBody FilterRequestDto filterRequestDto){

        return new ApiResponse<>(APIResponseKey.ALL_GOOD,adhocService.findMediaWithFilters(filterRequestDto),null);

    }

    /**
     * This controller method gets paginated results of EnvelopedProject for FilterRequest
     * @param filterRequestDto
     * @return
     */
    @RbacRegister(authorityName = "paginateEnvelopedProjectSearch", excluded = true)
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/paginateEnvelopedProjectSearch",consumes = "application/json")
    public ApiResponse<Page<EnvelopedProject>> findEnvelopedProjectWithFilters(@RequestBody FilterRequestDto filterRequestDto){

        return new ApiResponse<>(APIResponseKey.ALL_GOOD,adhocService.findEnvelopedProjectWithFilters(filterRequestDto),null);

    }

    /**
     * This controller method gets paginated results of EnvelopedMedia for FilterRequest
     * @param filterRequestDto
     * @return
     */
    @RbacRegister(authorityName = "paginateEnvelopedMediaSearch", excluded = true)
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/paginateEnvelopedMediaSearch",consumes = "application/json")
    public ApiResponse<Page<EnvelopedMedia>> findEnvelopedMediaWithFilters(@RequestBody FilterRequestDto filterRequestDto){

        return new ApiResponse<>(APIResponseKey.ALL_GOOD,adhocService.findEnvelopedMediaWithFilters(filterRequestDto),null);

    }


    /**
     * This controller method gets paginated results of EnvelopedProject for FilterRequest for a user
     * @param filterRequestDto
     * @return
     */
    @RbacRegister(authorityName = "paginateMyEnvelopedProjectSearch")
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/paginateMyEnvelopedProjectSearch",consumes = "application/json")
    public ApiResponse<Page<EnvelopedProject>> findMyEnvelopedProjectWithFilters(@RequestHeader("subjectid") Long subjectId,
            @RequestBody FilterRequestDto filterRequestDto){

        return new ApiResponse<>(APIResponseKey.ALL_GOOD,adhocService.findMyEnvelopedProjectWithFilters(filterRequestDto,subjectId),null);

    }


    /**
     * This controller method gets paginated results of EnvelopedProject for FilterRequest for a profileId
     * @param filterRequestDto
     * @return
     */
    @RbacRegister(authorityName = "findEnvelopedProjectWithFiltersByProfileId", excluded = true)
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "{profileId}/findEnvelopedProjectWithFiltersByProfileId",consumes = "application/json")
    public ApiResponse<Page<EnvelopedProject>> findEnvelopedProjectWithFiltersByProfileId(@PathVariable("profileId") String profileId,
                                                                                          @RequestHeader(value = "subjectid", required = false) Long subjectId,
                                                                                 @RequestBody FilterRequestDto filterRequestDto){

        return new ApiResponse<>(APIResponseKey.ALL_GOOD,adhocService.findEnvelopedProjectWithFiltersByProfileId(filterRequestDto,profileId),null);

    }



    /**
     * This controller method gets paginated results of EnvelopedB2CMedia for FilterRequest
     * @param filterRequestDto
     * @return
     */
    @RbacRegister(authorityName = "paginateEnvelopedB2CMediaSearch", excluded = true)
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/paginateEnvelopedB2CMediaSearch",consumes = "application/json")
    public ApiResponse<Page<EnvelopedB2CMedia>> findEnvelopedB2CMediaWithFilters(@RequestBody FilterRequestDto filterRequestDto){

        return new ApiResponse<>(APIResponseKey.ALL_GOOD,adhocService.findEnvelopedB2CMediaWithFilters(filterRequestDto),null);

    }


    /**
     * This controller method gets EnvelopedB2CMedia for specific media
     * @param mediaId
     * @return
     */
    @RbacRegister(authorityName = "getEnvelopedB2CMediaForMedia", excluded = true)
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{mediaId}/getEnvelopedB2CMediaForMedia")
    public ApiResponse<EnvelopedB2CMedia> findEnvelopedB2CMediaWithFilters(@PathVariable("mediaId") String mediaId,
                                                                           @RequestHeader(value = "subjectid", required = false) Long subjectId){

        return new ApiResponse<>(APIResponseKey.ALL_GOOD,adhocService.findEnvelopedB2CMediaForMedia(mediaId,subjectId),null);

    }

    /**
     * This controller method gets EnvelopedB2CMedia for specific media
     * @param projectId
     * @return
     */
    @RbacRegister(authorityName = "getEnvelopedProjectByProjectId", excluded = true)
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{projectId}/getEnvelopedProjectByProjectId")
    public ApiResponse<EnvelopedProject> getEnvelopedProjectByProjectId(@PathVariable("projectId") String projectId,
                                                                           @RequestHeader(value = "subjectid", required = false) Long subjectId){

        return new ApiResponse<>(APIResponseKey.ALL_GOOD,adhocService.findEnvelopedProjectForProject(projectId,subjectId),null);

    }

    /**
     * This controller method gets paginated results of EnvelopedB2CMedia for FilterRequest of globalSearch
     * @param filterRequestDto
     * @return
     */
    @RbacRegister(authorityName = "paginateEnvelopedB2CMediaGlobalSearch", excluded = true)
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/paginateEnvelopedB2CMediaGlobalSearch",consumes = "application/json")
    public ApiResponse<Page<EnvelopedB2CMedia>> findEnvelopedB2CMediaWithFiltersGlobal(@RequestBody FilterRequestDto filterRequestDto){

        return new ApiResponse<>(APIResponseKey.ALL_GOOD,adhocService.findEnvelopedB2CMediaWithFiltersGlobal(filterRequestDto),null);

    }

    /**
     * This controller method gets MultipartFile and returns search Results
     * @param uploadingFile
     * @return
     */
    @RbacRegister(authorityName = "paginateEnvelopedB2CMediaGlobalSearch", excluded = true)
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/page/{page}/size/{size}/paginateEnvelopedB2CMediaGlobalSearch",  consumes = "multipart/form-data")
    public ApiResponse<Page<EnvelopedB2CMedia>> findEnvelopedB2CMediaWithFiltersGlobal(@RequestParam("uploadingFiles") MultipartFile uploadingFile,
                                                                                       @PathVariable("page") Integer page,
                                                                                       @PathVariable("size") Integer size){

        return new ApiResponse<>(APIResponseKey.ALL_GOOD,adhocService.findEnvelopedB2CMediaWithImage(uploadingFile,page,size),null);

    }




}
