package com.spacifii.konstruct.explore.controller.conceptBoard;


import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardMedia;
import com.spacifii.konstruct.explore.entities.explore.InfoSpot;
import com.spacifii.konstruct.explore.integration.rbac.annotation.RbacRegister;
import com.spacifii.konstruct.explore.model.APIResponseKey;
import com.spacifii.konstruct.explore.model.ApiResponse;
import com.spacifii.konstruct.explore.model.dto.conceptBoard.ConceptBoardMediaUpdateDto;
import com.spacifii.konstruct.explore.service.conceptBoard.ConceptBoardMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * This is controller for ConceptBoardMedia
 */
@RestController
@RequestMapping(value = "/api/conceptBoardMedia")
public class ConceptBoardMediaController {

    @Autowired
    ConceptBoardMediaService conceptBoardMediaService;


    /**
     * This controller method is used to update ConceptBoardMedia
     * @param conceptBoardMediaUpdateDto
     * @param subjectId
     * @return
     */
    @CrossOrigin
    @RbacRegister(authorityName = "updateConceptBoardMedia")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/updateConceptBoardMedia", consumes = "application/json")
    public ApiResponse<ConceptBoardMedia> saveConceptBoard(@RequestBody ConceptBoardMediaUpdateDto conceptBoardMediaUpdateDto,
                                                            @RequestHeader("subjectid") Long subjectId){
        return new ApiResponse<>(APIResponseKey.NEW_CREATION,conceptBoardMediaService.updateConceptBoardMedia(conceptBoardMediaUpdateDto,subjectId),null);

    }

    /**
     * This controller method is used to add ConceptBoardMedia keywords
     * @param keywords
     * @param subjectId
     * @return
     */
    @CrossOrigin
    @RbacRegister(authorityName = "addKeywordsToConceptBoardMedia")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "{mediaId}/addKeywordsToConceptBoardMedia", consumes = "application/json")
    public ApiResponse<ConceptBoardMedia> saveConceptBoard(@PathVariable("mediaId") String mediaId,@RequestBody Set<String> keywords,
                                                           @RequestHeader("subjectid") Long subjectId){
        return new ApiResponse<>(APIResponseKey.NEW_CREATION,conceptBoardMediaService.addKeywords(mediaId,keywords,subjectId),null);

    }

    /**
     * This controller method is used to remove ConceptBoardMedia keywords
     * @param keywords
     * @param subjectId
     * @return
     */
    @CrossOrigin
    @RbacRegister(authorityName = "removeKeywordsToConceptBoardMedia")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "{mediaId}/removeKeywordsToConceptBoardMedia", consumes = "application/json")
    public ApiResponse<ConceptBoardMedia> removeKeywords(@PathVariable("mediaId") String mediaId,@RequestBody Set<String> keywords,
                                                           @RequestHeader("subjectid") Long subjectId){
        return new ApiResponse<>(APIResponseKey.NEW_CREATION,conceptBoardMediaService.removeKeywords(mediaId,keywords,subjectId),null);

    }

    /**
     * This method add of updates infoSpots of given Media
     * @param subjectId
     * @param conceptBoardId
     * @param mediaId
     * @param infoSpots
     * @return
     */
    @RbacRegister(authorityName = "addInfoSpotsToConceptBoardMedia")
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/conceptBoard/{conceptBoardId}/media/{mediaId}/addInfoSpotsToConceptBoardMedia", consumes = "application/json")
    public ApiResponse<ConceptBoardMedia> addInfoSpotsToMedia(@RequestHeader("subjectid") Long subjectId,
                                                  @PathVariable("conceptBoardId") String conceptBoardId,
                                                  @PathVariable("mediaId") String mediaId,
                                                  @RequestBody Set<InfoSpot> infoSpots){


        return new ApiResponse<>(APIResponseKey.NEW_CREATION,conceptBoardMediaService.addInfoSpotToMedia(conceptBoardId,mediaId,subjectId,infoSpots),null);


    }


    /**
     * This method remove infoSpots of given Media
     * @param subjectId
     * @param conceptBoardId
     * @param mediaId
     * @param infoSpotId
     * @return
     */
    @RbacRegister(authorityName = "removeInfoSpotsFromConceptBoardMedia")
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/conceptBoard/{conceptBoardId}/media/{mediaId}/removeInfoSpotsFromConceptBoardMedia", consumes = "application/json")
    public ApiResponse<ConceptBoardMedia> addInfoSpotsToMedia(@RequestHeader("subjectid") Long subjectId,
                                                              @PathVariable("conceptBoardId") String conceptBoardId,
                                                              @PathVariable("mediaId") String mediaId,
                                                              @RequestBody String infoSpotId){


        return new ApiResponse<>(APIResponseKey.NEW_CREATION,conceptBoardMediaService.removeInfoSpotToMedia(conceptBoardId,mediaId,subjectId,infoSpotId),null);


    }
}
