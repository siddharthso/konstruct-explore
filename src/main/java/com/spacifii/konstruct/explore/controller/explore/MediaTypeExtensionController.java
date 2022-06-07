package com.spacifii.konstruct.explore.controller.explore;

import com.spacifii.konstruct.explore.entities.explore.MediaType;
import com.spacifii.konstruct.explore.entities.explore.MediaTypeExtension;
import com.spacifii.konstruct.explore.integration.rbac.annotation.RbacRegister;
import com.spacifii.konstruct.explore.model.APIResponseKey;
import com.spacifii.konstruct.explore.model.ApiResponse;
import com.spacifii.konstruct.explore.service.explore.MediaTypeExtensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

/**
 * This is controller for MediaTypeExtension
 */
@RestController
@RequestMapping("/api/mediaTypeExtension")
public class MediaTypeExtensionController {

    @Autowired
    MediaTypeExtensionService mediaTypeExtensionService;

    /**
     * This method is used add MediaTypeExtension
     * @param mediaTypeExtension
     * @return
     */
    @RbacRegister(authorityName = "addMediaTypeExtension")
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/addMediaTypeExtension", consumes = "application/json")
    public ApiResponse<Void> save(@RequestBody MediaTypeExtension mediaTypeExtension,
                                  @RequestHeader("subjectid") Long subjectId){

       mediaTypeExtensionService.save(mediaTypeExtension,subjectId);
        return new ApiResponse<>(APIResponseKey.NEW_CREATION,null,null);

    }

    /**
     * This method gets All Extensions for a particular MediaType
     * @return
     */
    @RbacRegister(authorityName = "getAllExtensionsPerMediaType", excluded = true)
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/getAllExtensionsPerMediaType")
    public ApiResponse<Map<MediaType,Set<String>>> getAllExtensionsPerMediaType(){

        return new ApiResponse<>(APIResponseKey.ALL_GOOD,
                mediaTypeExtensionService.getAllExtensionsPerMediaType(),null);

    }

    /**
     * This method gets Map of Extension (String) to MediaType
     * @return
     */
    @RbacRegister(authorityName = "getMediaTypeForExtensions", excluded = true)
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/getMediaTypeForExtensions")
    public ApiResponse<Map<String,MediaType>> getMediaTypeForExtensions(){

        return new ApiResponse<>(APIResponseKey.ALL_GOOD,
                mediaTypeExtensionService.getMediaTypeForExtensions(),null);

    }
}
