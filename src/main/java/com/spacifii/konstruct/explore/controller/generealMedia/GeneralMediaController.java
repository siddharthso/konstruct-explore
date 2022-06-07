package com.spacifii.konstruct.explore.controller.generealMedia;

import com.spacifii.konstruct.explore.entities.generalMedia.GeneralMedia;
import com.spacifii.konstruct.explore.integration.rbac.annotation.RbacRegister;
import com.spacifii.konstruct.explore.model.APIResponseKey;
import com.spacifii.konstruct.explore.model.ApiResponse;
import com.spacifii.konstruct.explore.service.generalMedia.GeneralMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * This is controller for GeneralMedia
 */
@RestController
@RequestMapping(value = "/api/generalMedia")
public class GeneralMediaController {



    @Autowired
    GeneralMediaService generalMediaService;


    /**
     * This controller method saves GeneralMedia
     * @param multipartFiles
     * @param subjectId
     * @return
     */
    @CrossOrigin
    @RbacRegister(authorityName = "uploadGeneralMedia")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "{uploadKey}/uploadGeneralMedia", consumes = "multipart/form-data")
    public ApiResponse<List<GeneralMedia>> saveGeneralMedia(@PathVariable("uploadKey") String key,
                                                            @RequestParam("uploadingFiles") MultipartFile[] multipartFiles,
                                                            @RequestHeader("subjectid") Long subjectId){

        return new ApiResponse<>(APIResponseKey.NEW_CREATION,generalMediaService.upload(multipartFiles,subjectId,key),null);
    }


    /**
     * This controller method saves GeneralMedia with Keywords
     * @param multipartFiles
     * @param subjectId
     * @return
     */
    @CrossOrigin
    @RbacRegister(authorityName = "uploadGeneralMediaAndGetKeywords", excluded = true)
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "{uploadKey}/uploadGeneralMediaAndGetKeywords", consumes = "multipart/form-data")
    public ApiResponse<List<GeneralMedia>> saveGeneralMediaAndGetKeywords(@PathVariable("uploadKey") String key,
                                                                          @RequestParam("uploadingFiles") MultipartFile[] multipartFiles,
                                                                          @RequestHeader(value = "subjectid",required = false) Long subjectId){

        return new ApiResponse<>(APIResponseKey.NEW_CREATION,generalMediaService.uploadAndGeKeyword(multipartFiles,subjectId,key),null);
    }
}
