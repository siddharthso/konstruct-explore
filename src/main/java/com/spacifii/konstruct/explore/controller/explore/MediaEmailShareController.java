package com.spacifii.konstruct.explore.controller.explore;

import com.spacifii.konstruct.explore.entities.explore.MediaEmailShare;
import com.spacifii.konstruct.explore.integration.rbac.annotation.RbacRegister;
import com.spacifii.konstruct.explore.model.APIResponseKey;
import com.spacifii.konstruct.explore.model.ApiResponse;
import com.spacifii.konstruct.explore.service.explore.MediaEmailShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/mediaEmailShare")
public class MediaEmailShareController {

    @Autowired
    MediaEmailShareService mediaEmailShareService;


    @RbacRegister(authorityName = "shareExploreMedia")
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/shareExploreMedia", consumes = "application/json")
    public ApiResponse<Void> shareMedia(@RequestHeader(value = "subjectid", required = false) Long subjectId,
                                                    @RequestBody MediaEmailShare mediaEmailShare){

        mediaEmailShareService.shareMedia(mediaEmailShare,subjectId);
        return new ApiResponse<>(APIResponseKey.NEW_CREATION,null,null);


    }
}
