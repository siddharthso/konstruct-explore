package com.spacifii.konstruct.explore.controller.conceptBoard;

import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoard;
import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardMedia;
import com.spacifii.konstruct.explore.integration.rbac.annotation.RbacRegister;
import com.spacifii.konstruct.explore.model.APIResponseKey;
import com.spacifii.konstruct.explore.model.ApiResponse;
import com.spacifii.konstruct.explore.model.dto.conceptBoard.ConceptBoardAllowedListRequestDto;
import com.spacifii.konstruct.explore.model.dto.conceptBoard.ConceptBoardEnveloped;
import com.spacifii.konstruct.explore.model.dto.conceptBoard.ConceptBoardFeaturedImageRequestDto;
import com.spacifii.konstruct.explore.model.dto.conceptBoard.ConceptBoardRequestDto;
import com.spacifii.konstruct.explore.model.dto.explore.AddExploreMediaToConceptBoardDto;
import com.spacifii.konstruct.explore.model.dto.explore.ExternalMediaDto;
import com.spacifii.konstruct.explore.model.dto.explore.FilterRequestDto;
import com.spacifii.konstruct.explore.service.conceptBoard.ConceptBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * This is controller for ConceptBoard
 */
@RestController
@RequestMapping(value = "/api/conceptBoard")
public class ConceptBoardController {

    @Autowired
    ConceptBoardService conceptBoardService;

    /**
     * This controller method is used to save new conceptBoard Folder
     * @param conceptBoardRequestDto
     * @param subjectId
     * @return
     */
    @CrossOrigin
    @RbacRegister(authorityName = "addConceptBoard")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/addConceptBoard", consumes = "application/json")
    public ApiResponse<ConceptBoard> saveConceptBoard(@RequestBody ConceptBoardRequestDto conceptBoardRequestDto,
                                                      @RequestHeader("subjectid") Long subjectId){
        return new ApiResponse<>(APIResponseKey.NEW_CREATION,conceptBoardService.saveConceptBoard(conceptBoardRequestDto,subjectId),null);

    }

    /**
     * This controller method is used to update conceptBoard
     * @param conceptBoardRequestDto
     * @param subjectId
     * @return
     */
    @CrossOrigin
    @RbacRegister(authorityName = "updateConceptBoard")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/updateConceptBoard", consumes = "application/json")
    public ApiResponse<ConceptBoard> updateConceptBoard(@RequestBody ConceptBoardRequestDto conceptBoardRequestDto,
                                                                  @RequestHeader("subjectid") Long subjectId){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,conceptBoardService.updateConceptBoard(conceptBoardRequestDto,subjectId),null);
    }


    /**
     * This controller method is used to add EmailIds of users which we need to allow for that user ConceptBoard
     * @param conceptBoardAllowedListRequestDto
     * @param subjectId
     * @return
     */
    @CrossOrigin
    @RbacRegister(authorityName = "addAllowedUsersToConceptBoard")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/addAllowedUsersToConceptBoard", consumes = "application/json")
    public ApiResponse<ConceptBoard> addAllowedUsersToConceptBoard(@RequestBody ConceptBoardAllowedListRequestDto conceptBoardAllowedListRequestDto,
                                                                 @RequestHeader("subjectid") Long subjectId){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,conceptBoardService.addAllowedUsersToConceptBoardFolder(conceptBoardAllowedListRequestDto,subjectId),null);

    }

    /**
     * This controller method is used to remove EmailIds of users which we allowed earlier for users ConceptBoard
     * @param conceptBoardAllowedListRequestDto
     * @param subjectId
     * @return
     */
    @CrossOrigin
    @RbacRegister(authorityName = "removeAllowedUsersFromConceptBoard")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/removeAllowedUsersFromConceptBoard", consumes = "application/json")
    public ApiResponse<ConceptBoard> removeAllowedUsersFromConceptBoard(@RequestBody ConceptBoardAllowedListRequestDto conceptBoardAllowedListRequestDto,
                                                                        @RequestHeader("subjectid") Long subjectId){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,conceptBoardService.removedAllowedUsersFromConceptBoardFolder(conceptBoardAllowedListRequestDto,subjectId),null);
    }

    /**
     * This controller method is used to upload medias to conceptBoard
     * @param multipartFiles
     * @param subjectId
     * @param conceptBoardId
     * @return
     */
    @CrossOrigin
    @RbacRegister(authorityName = "uploadMediasToConceptBoard")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/{conceptBoardId}/uploadMediasToConceptBoard", consumes = "multipart/form-data")
    public ApiResponse<ConceptBoard> uploadMediasToConceptBoard(@RequestParam("uploadingFiles") MultipartFile[] multipartFiles,
                                                                @RequestHeader("subjectid") Long subjectId,
                                                                @PathVariable("conceptBoardId") String conceptBoardId){
        return new ApiResponse<>(APIResponseKey.NEW_CREATION,conceptBoardService.addConceptBoardMediaToConceptBoard(multipartFiles,conceptBoardId,false,subjectId),null);

    }


    /**
     * This controller method is used to upload medias to conceptBoard
     * @param multipartFiles
     * @param subjectId
     * @param conceptBoardId
     * @return
     */
    @CrossOrigin
    @RbacRegister(authorityName = "uploadMedias360ToConceptBoard")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/{conceptBoardId}/uploadMedias360ToConceptBoard", consumes = "multipart/form-data")
    public ApiResponse<ConceptBoard> uploadMedias360ToConceptBoard(@RequestParam("uploadingFiles") MultipartFile[] multipartFiles,
                                                                @RequestHeader("subjectid") Long subjectId,
                                                                @PathVariable("conceptBoardId") String conceptBoardId){
        return new ApiResponse<>(APIResponseKey.NEW_CREATION,conceptBoardService.addConceptBoardMediaToConceptBoard(multipartFiles,conceptBoardId,true,subjectId),null);

    }


    /**
     * This controller method is used to add exploreMedia to Concept Board
     * @param addExploreMediaToConceptBoardDto
     * @param subjectId
     * @param conceptBoardId
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "addExploreMediaToConceptBoard")
    @PostMapping(value = "/{conceptBoardId}/addExploreMediaToConceptBoard", consumes = "application/json")
    public ApiResponse<ConceptBoard> addExploreMediaToConceptBoard(@RequestBody AddExploreMediaToConceptBoardDto addExploreMediaToConceptBoardDto,
                                                                   @RequestHeader("subjectid") Long subjectId,
                                                                   @PathVariable("conceptBoardId") String conceptBoardId){
        return new ApiResponse<>(APIResponseKey.NEW_CREATION,conceptBoardService.addExploreMediaToConceptBoard(addExploreMediaToConceptBoardDto.getMediaId(),addExploreMediaToConceptBoardDto.getDescription(),conceptBoardId,subjectId),null);

    }

    /**
     * This controller method is used to get all user's concept board. This will be shown in user's dashboard
     * @param subjectId
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/getMyConceptBoards")
    @RbacRegister(authorityName = "getMyConceptBoards")
    public ApiResponse<List<ConceptBoard>> getMyConceptBoards(@RequestHeader("subjectid") Long subjectId){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,conceptBoardService.getUserConceptBoards(subjectId), null);

    }


    /**
     * This method accepts ExternalMediaDtos and saves into DB
     * @param
     * @param subjectId
     * @param conceptBoardId
     * @return
     */
    @RbacRegister(authorityName = "addExternalMediaToConceptBoard")
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/conceptBoard/{conceptBoardId}/addExternalMediaToConceptBoard", consumes = "application/json")
    public ApiResponse<List<ConceptBoardMedia>> save(@RequestBody List<ExternalMediaDto> externalMediaDtos, @RequestHeader("subjectid") Long subjectId,
                                                     @PathVariable("conceptBoardId") String conceptBoardId){

        return new ApiResponse<>(APIResponseKey.NEW_CREATION,conceptBoardService.addExternalMediaToExistingConceptBoard(conceptBoardId,subjectId,externalMediaDtos),null);

    }

    /**
     * This controller method is used to get all user's public concept board.
     * @param subjectId
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/getAllUserPublicConceptBoards", consumes = "application/json")
    public ApiResponse<List<ConceptBoard>> getAllUserPublicConceptBoards(@RequestBody Long subjectId){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,conceptBoardService.getUserPublicConceptBoards(subjectId), null);

    }



    /**
     * This controller method gets paginated results of ConceptBoard for FilterRequest
     * @param filterRequestDto
     * @param subjectId
     * @return
     */
    @RbacRegister(authorityName = "paginateConceptBoardSearch")
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/paginateConceptBoardSearch",consumes = "application/json")
    public ApiResponse<Page<ConceptBoard>> findConceptBoardWithFilters(@RequestHeader("subjectid") Long subjectId,
                                                                  @RequestBody FilterRequestDto filterRequestDto){
        //Long subjectId = Long.valueOf(subjectId1);
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,conceptBoardService.findConceptBoardWithFilters(filterRequestDto,subjectId),null);

    }


    /**
     * This controller method gets paginated results of Public ConceptBoard for FilterRequest
     * @param filterRequestDto
     * @param
     * @return
     */
    @RbacRegister(authorityName = "paginatePublicConceptBoardSearch", excluded = true)
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/paginatePublicConceptBoardSearch",consumes = "application/json")
    public ApiResponse<Page<ConceptBoard>> findConceptBoardWithFilters(@RequestBody FilterRequestDto filterRequestDto){
        //Long subjectId = Long.valueOf(subjectId1);

        return new ApiResponse<>(APIResponseKey.ALL_GOOD,conceptBoardService.paginatePublicConceptBoardSearch(filterRequestDto),null);

    }


    /**
     * This controller method gets paginated results of ConceptBoard for FilterRequest
     * @param filterRequestDto
     * @param subjectId
     * @return
     */
    @RbacRegister(authorityName = "conceptBoardSharedWithMe")
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/conceptBoardSharedWithMe",consumes = "application/json")
    public ApiResponse<Page<ConceptBoard>> conceptBoardSharedWithMe(@RequestHeader("subjectid") Long subjectId,
                                                                       @RequestBody FilterRequestDto filterRequestDto){
        //Long subjectId = Long.valueOf(subjectId1);
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,conceptBoardService.conceptBoardSharedWithMe(filterRequestDto,subjectId),null);

    }

    /**
     * This controller method gets paginated results of Public ConceptBoard for FilterRequest via profileId
     * @param filterRequestDto
     * @param subjectId
     * @return
     */
    @RbacRegister(authorityName = "paginatePublicConceptBoardSearchByProfileId", excluded = true)
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "{profileId}/paginatePublicConceptBoardSearchByProfileId",consumes = "application/json")
    public ApiResponse<Page<ConceptBoard>> paginatePublicConceptBoardSearchByProfileId(@PathVariable("profileId") String profileId,@RequestHeader(value = "subjectid",required = false) Long subjectId,
                                                                       @RequestBody FilterRequestDto filterRequestDto){

        return new ApiResponse<>(APIResponseKey.ALL_GOOD,conceptBoardService.findPublicConceptBoardWithFiltersWithProfileId(filterRequestDto,profileId),null);

    }

    /**
     * This controller method gets ConceptBoardEnveloped by Id
     * @param conceptBoardId
     * @param subjectId
     * @return
     */
    @RbacRegister(authorityName = "getConceptBoardEnvelopedById")
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{conceptBoardId}/getConceptBoardEnvelopedById")
    public ApiResponse<ConceptBoardEnveloped> findConceptBoardWithFilters(@PathVariable("conceptBoardId") String conceptBoardId,
                                                                          @RequestHeader("subjectid") Long subjectId){

        return new ApiResponse<>(APIResponseKey.ALL_GOOD,conceptBoardService.getConceptBoardEnvelopedById(conceptBoardId,subjectId),null);

    }

    /**
     * This controller method gets ConceptBoardEnveloped by Id
     * @param subjectId
     * @return
     */
    @RbacRegister(authorityName = "getMyConceptBoardMap")
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/getMyConceptBoardMap")
    public ApiResponse<Map<String,String>> getMyConceptBoardMap(@RequestHeader("subjectid") Long subjectId){

        return new ApiResponse<>(APIResponseKey.ALL_GOOD,conceptBoardService.getAllMyParentConceptBoardNames(subjectId),null);

    }





    /**
     * This controller method update updateConceptBoardFeaturedImage
     * @param conceptBoardFeaturedImageRequestDto
     * @param subjectId
     * @return
     */
    @RbacRegister(authorityName = "updateConceptBoardFeaturedImage")
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/updateConceptBoardFeaturedImage", consumes = "application/json")
    public ApiResponse<ConceptBoard> findConceptBoardWithFilters(@RequestBody ConceptBoardFeaturedImageRequestDto conceptBoardFeaturedImageRequestDto,
                                                                          @RequestHeader Long subjectId){

        return new ApiResponse<>(APIResponseKey.ALL_GOOD,conceptBoardService.addChangeFeaturedMedia(conceptBoardFeaturedImageRequestDto,subjectId),null);

    }
}
