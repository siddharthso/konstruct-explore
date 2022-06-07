package com.spacifii.konstruct.explore.controller.explore;

import com.spacifii.konstruct.explore.entities.explore.InfoSpot;
import com.spacifii.konstruct.explore.entities.explore.Media;
import com.spacifii.konstruct.explore.integration.rbac.annotation.RbacRegister;
import com.spacifii.konstruct.explore.model.APIResponseKey;
import com.spacifii.konstruct.explore.model.ApiResponse;
import com.spacifii.konstruct.explore.model.dto.explore.ExternalMediaDto;
import com.spacifii.konstruct.explore.model.dto.explore.MediaApprovalDto;
import com.spacifii.konstruct.explore.service.explore.MediaService;
import com.spacifii.konstruct.explore.service.explore.MediaWorkFlowActionStrategyContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

/**
 * This is controller class for Media
 */
@RestController
@RequestMapping("/api/media")
public class MediaController {


    @Autowired
    MediaService mediaService;

    @Autowired
    MediaWorkFlowActionStrategyContext mediaWorkFlowActionStrategyContext;

    /**
     * This method accepts MultiPartFile[] and saves into DB
     * @param uploadingFiles
     * @param subjectId
     * @param projectId
     * @return
     */
    @RbacRegister(authorityName = "uploadImagesToProject")
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/project/{projectId}/uploadImagesToProject", consumes = "multipart/form-data")
    public ApiResponse<List<Media>> save(@RequestParam("uploadingFiles") MultipartFile[] uploadingFiles,
                                         @RequestHeader("subjectid") Long subjectId,
                                         @PathVariable String projectId){


       return new ApiResponse<>(APIResponseKey.NEW_CREATION,mediaService.addMediaToExistingProject(projectId,subjectId,uploadingFiles,false),null);

    }


    /**
     * This method accepts MultiPartFile[] and saves into DB
     * @param uploadingFiles
     * @param subjectId
     * @param projectId
     * @return
     */
    @RbacRegister(authorityName = "upload360ImagesToProject")
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/project/{projectId}/upload360ImagesToProject", consumes = "multipart/form-data")
    public ApiResponse<List<Media>> upload360ImagesToProject(@RequestParam("uploadingFiles") MultipartFile[] uploadingFiles,
                                         @RequestHeader("subjectid") Long subjectId,
                                         @PathVariable String projectId){
        return new ApiResponse<>(APIResponseKey.NEW_CREATION,mediaService.addMediaToExistingProject(projectId,subjectId,uploadingFiles,true),null);

    }

    /**
     * This method accepts ExternalMediaDtos and saves into DB
     * @param
     * @param subjectId
     * @param projectId
     * @return
     */
    @RbacRegister(authorityName = "addExternalMediaToProject")
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/project/{projectId}/addExternalMediaToProject", consumes = "application/json")
    public ApiResponse<List<Media>> save(@RequestBody List<ExternalMediaDto> externalMediaDtos, @RequestHeader("subjectid") Long subjectId,
                                         @PathVariable String projectId){


        return new ApiResponse<>(APIResponseKey.NEW_CREATION,mediaService.addExternalMediaToExistingProject(projectId,subjectId,externalMediaDtos),null);

    }

    /**
     * This method add of updates infoSpots of given Media
     * @param subjectId
     * @param projectId
     * @param mediaId
     * @param infoSpots
     * @return
     */
    @RbacRegister(authorityName = "addInfoSpotsToMedia")
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/project/{projectId}/media/{mediaId}/addInfoSpotsToMedia", consumes = "application/json")
    public ApiResponse<Media> addInfoSpotsToMedia(@RequestHeader("subjectid") Long subjectId,
                                                  @PathVariable("projectId") String projectId,
                                                  @PathVariable("mediaId") String mediaId,
                                                  @RequestBody Set<InfoSpot> infoSpots){


        return new ApiResponse<>(APIResponseKey.NEW_CREATION,mediaService.addInfoSpotToMedia(projectId,mediaId,subjectId,infoSpots),null);


    }


    /**
     * This method add of remove infoSpot of given Media
     * @param subjectId
     * @param projectId
     * @param mediaId
     * @param infospotId
     * @return
     */
    @RbacRegister(authorityName = "removeInfoSpotFromMedia")
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/project/{projectId}/media/{mediaId}/removeInfoSpotFromMedia", consumes = "application/json")
    public ApiResponse<Media> addInfoSpotsToMedia(@RequestHeader("subjectid") Long subjectId,
                                                  @PathVariable("projectId") String projectId,
                                                  @PathVariable("mediaId") String mediaId,
                                                  @RequestBody String infospotId){


        return new ApiResponse<>(APIResponseKey.NEW_CREATION,mediaService.removeInfoSpotFromMedia(projectId,mediaId,subjectId,infospotId),null);


    }


    /**
     * This method toggles Media Status
     * @param subjectId
     * @param projectId
     * @param mediaId
     * @return
     */
    @RbacRegister(authorityName = "toggleMedia")
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/project/{projectId}/media/{mediaId}/toggleMedia", consumes = "application/json")
    public ApiResponse<Media> toggleMedia(@RequestHeader("subjectid") Long subjectId,
                                                  @PathVariable("projectId") String projectId,
                                                  @PathVariable("mediaId") String mediaId){
        return new ApiResponse<>(APIResponseKey.NEW_CREATION,mediaService.toggleMedia(projectId,mediaId,subjectId),null);


    }


    /**
     * This controller method is used to get app images of project
     * @param projectId
     * @return
     */
    @RbacRegister(authorityName = "getImagesForProject")
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping(path = "/project/{projectId}/getImagesForProject")
    public ApiResponse<List<Media>> getImagesForProject(
                                                  @PathVariable("projectId") String projectId
                                                  ){
        return new ApiResponse<>(APIResponseKey.NEW_CREATION,mediaService.findByProjectId(projectId),null);


    }




    /**
     * This controller method is used to get update images of project
     * @param subjectId
     * @param projectId
     * @param medias
     * @return
     */
    @RbacRegister(authorityName = "updateImagesForProject")
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/project/{projectId}/updateImagesForProject", consumes = "application/json")
    public ApiResponse<List<Media>> updateImagesForProject(@RequestHeader("subjectid") Long subjectId,
            @PathVariable("projectId") String projectId, @RequestBody List<Media> medias){
        return new ApiResponse<>(APIResponseKey.NEW_CREATION,mediaService.updateMedias(medias,projectId,subjectId),null);


    }


    /**
     * This controller method is used to get update image of project
     * @param subjectId
     * @param projectId
     * @param media
     * @return
     */
    @RbacRegister(authorityName = "updateImageForProject")
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/project/{projectId}/updateImageForProject", consumes = "application/json")
    public ApiResponse<Media> updateImagesForProject(@RequestHeader("subjectid") Long subjectId,
                                                           @PathVariable("projectId") String projectId, @RequestBody Media media){
        return new ApiResponse<>(APIResponseKey.NEW_CREATION,mediaService.updateSingle(media,projectId,subjectId),null);


    }




    @RbacRegister(authorityName = "approveMedias")
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/mediaWorkFlow", consumes = "application/json")
    public ApiResponse<Void> updateImagesForProject(@RequestHeader("subjectid") Long subjectId,
                                                    @RequestBody List<MediaApprovalDto> mediaApprovalDtos){

        mediaWorkFlowActionStrategyContext.doAction(mediaApprovalDtos,subjectId);
        return new ApiResponse<>(APIResponseKey.NEW_CREATION,null,null);


    }


}
