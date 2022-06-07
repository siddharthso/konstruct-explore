package com.spacifii.konstruct.explore.controller.conceptBoard;


import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardShare;
import com.spacifii.konstruct.explore.integration.rbac.annotation.RbacRegister;
import com.spacifii.konstruct.explore.model.APIResponseKey;
import com.spacifii.konstruct.explore.model.ApiResponse;
import com.spacifii.konstruct.explore.model.dto.conceptBoard.ConceptBoardAllowedListRequestDto;
import com.spacifii.konstruct.explore.model.dto.conceptBoard.ConceptBoardEmailShareProfileDto;
import com.spacifii.konstruct.explore.service.conceptBoard.ConceptBoardShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/conceptBoardShare")
public class ConceptBoardShareController {


    @Autowired
    ConceptBoardShareService conceptBoardShareService;

    /**
     * This controller method is used to add EmailIds of users which we need to allow for that user ConceptBoard
     * @param conceptBoardAllowedListRequestDto
     * @param subjectId
     * @return
     */
    @CrossOrigin
    @RbacRegister(authorityName = "addUpdateEmailsToConceptBoardShare")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/addUpdateEmailsToConceptBoardShare", consumes = "application/json")
    public ApiResponse<List<ConceptBoardShare>> addAllowedUsersToConceptBoard(@RequestBody ConceptBoardAllowedListRequestDto conceptBoardAllowedListRequestDto,
                                                                              @RequestHeader("subjectid") Long subjectId){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,conceptBoardShareService.addUpdateEmailIdsForSharingConceptBoard(conceptBoardAllowedListRequestDto.getAllowedList(),conceptBoardAllowedListRequestDto.getId(),conceptBoardAllowedListRequestDto.getMessage(),subjectId),null);

    }


    /**
     * This controller method is used to remove EmailIds of users which we need to allowed for that user ConceptBoard
     * @param conceptBoardAllowedListRequestDto
     * @param subjectId
     * @return
     */
    @CrossOrigin
    @RbacRegister(authorityName = "removeEmailsIdForSharingConceptBoard")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/removeEmailsIdForSharingConceptBoard", consumes = "application/json")
    public ApiResponse<List<ConceptBoardShare>> removeEmailsIdForSharingConceptBoard(@RequestBody ConceptBoardAllowedListRequestDto conceptBoardAllowedListRequestDto,
                                                                              @RequestHeader("subjectid") Long subjectId){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,conceptBoardShareService.removeEmailsIdForSharingConceptBoard(conceptBoardAllowedListRequestDto.getAllowedList(),conceptBoardAllowedListRequestDto.getId(),subjectId),null);

    }

    /**
     * This controller method is used to add EmailIds of users which we need to allow for that user ConceptBoard
     * @param conceptBoardAllowedListRequestDto
     * @param subjectId
     * @return
     */
    @CrossOrigin
    @RbacRegister(authorityName = "addEmailsIdForSharingConceptBoard")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/addEmailsIdForSharingConceptBoard", consumes = "application/json")
    public ApiResponse<List<ConceptBoardShare>> addEmailsIdForSharingConceptBoard(@RequestBody ConceptBoardAllowedListRequestDto conceptBoardAllowedListRequestDto,
                                                                              @RequestHeader("subjectid") Long subjectId){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,conceptBoardShareService.addEmailsIdForSharingConceptBoard(conceptBoardAllowedListRequestDto.getAllowedList(),conceptBoardAllowedListRequestDto.getId(),conceptBoardAllowedListRequestDto.getMessage(),subjectId),null);

    }

    /**
     * This controller method remove myself from shared conceptBoard
     * @param conceptBoardId
     * @param subjectId
     * @return
     */
    @CrossOrigin
    @RbacRegister(authorityName = "unshareMeFromSharedConceptBoard")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/unshareMeFromSharedConceptBoard", consumes = "application/json")
    public ApiResponse<Boolean> addAllowedUsersToConceptBoard(@RequestBody String conceptBoardId,
                                                              @RequestHeader("subjectid") Long subjectId){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,conceptBoardShareService.unshareMeFromSharedConceptBoard(conceptBoardId,subjectId),null);

    }


    /**
     * This controller method get EmailIds shared for ConceptBoard
     * @param conceptBoardId
     * @param subjectId
     * @return
     */
    @CrossOrigin
    @RbacRegister(authorityName = "getShareListForconceptBoard")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/getShareListForconceptBoard", consumes = "application/json")
    public ApiResponse<Set<String>> getShareListForconceptBoard(@RequestBody String conceptBoardId,
                                                                  @RequestHeader("subjectid") Long subjectId){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,conceptBoardShareService.getShareListForconceptBoard(conceptBoardId,subjectId),null);

    }

    /**
     * This controller method get EmailIds shared for ConceptBoard
     * @param conceptBoardId
     * @param subjectId
     * @return
     */
    @CrossOrigin
    @RbacRegister(authorityName = "getShareListWithProfileForconceptBoard")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/getShareListWithProfileForconceptBoard", consumes = "application/json")
    public ApiResponse<List<ConceptBoardEmailShareProfileDto>> getShareListWithProfileForconceptBoard(@RequestBody String conceptBoardId,
                                                                                           @RequestHeader("subjectid") Long subjectId){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,conceptBoardShareService.getShareListWithProfileForconceptBoard(conceptBoardId,subjectId),null);

    }
    //unshareMeFromSharedConceptBoard

    //getShareListForconceptBoard
}
