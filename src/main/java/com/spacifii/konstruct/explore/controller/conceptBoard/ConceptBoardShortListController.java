package com.spacifii.konstruct.explore.controller.conceptBoard;


import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardShortList;
import com.spacifii.konstruct.explore.integration.rbac.annotation.RbacRegister;
import com.spacifii.konstruct.explore.model.APIResponseKey;
import com.spacifii.konstruct.explore.model.ApiResponse;
import com.spacifii.konstruct.explore.model.dto.conceptBoard.EnvelopedConceptBoardShortList;
import com.spacifii.konstruct.explore.model.dto.explore.ConceptBoardShortlstDto;
import com.spacifii.konstruct.explore.service.conceptBoard.ConceptBoardShortListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/conceptBoardShortList")
public class ConceptBoardShortListController {

    @Autowired
    ConceptBoardShortListService conceptBoardShortListService;

    /**
     * This controller method is used to  Add ConceptBoardShortList
     * @param conceptBoardShortlstDto
     * @param subjectId
     * @return
     */
    @CrossOrigin
    @RbacRegister(authorityName = "shortListConceptBoardMedia")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/shortListConceptBoardMedia", consumes = "application/json")
    public ApiResponse<ConceptBoardShortList> shortListConceptBoardMedia(@RequestBody ConceptBoardShortlstDto conceptBoardShortlstDto,
                                                                                  @RequestHeader("subjectid") Long subjectId){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,conceptBoardShortListService.shortListConceptBoardMedia(conceptBoardShortlstDto.getConceptBoardId(),conceptBoardShortlstDto.getConceptBoardMediaId(),conceptBoardShortlstDto.getComment(),subjectId),null);

    }


    /**
     * This controller method is used to  unShortList ConceptBoardShortList
     * @param conceptBoardShortlstDto
     * @param subjectId
     * @return
     */
    @CrossOrigin
    @RbacRegister(authorityName = "unShortListConceptBoardMedia")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/unShortListConceptBoardMedia", consumes = "application/json")
    public ApiResponse<Boolean> unShortListConceptBoardMedia(@RequestBody ConceptBoardShortlstDto conceptBoardShortlstDto,
                                                                         @RequestHeader("subjectid") Long subjectId){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,conceptBoardShortListService.unShortListConceptBoardMedia(conceptBoardShortlstDto.getConceptBoardId(),conceptBoardShortlstDto.getConceptBoardMediaId(),conceptBoardShortlstDto.getComment(),subjectId),null);

    }



    /**
     * This controller method is used to   ConceptBoardShortList of myself for this conceptBoard
     * @param conceptBoardShortlstDto
     * @param subjectId
     * @return
     */
    @CrossOrigin
    @RbacRegister(authorityName = "getMyShortListedImagesForConceptBoard")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/getMyShortListedImagesForConceptBoard", consumes = "application/json")
    public ApiResponse<List<ConceptBoardShortList>> getMyShortListedImagesForConceptBoard(@RequestBody ConceptBoardShortlstDto conceptBoardShortlstDto,
                                                                                 @RequestHeader("subjectid") Long subjectId){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,conceptBoardShortListService.getMyShortListedImagesForConceptBoard(conceptBoardShortlstDto.getConceptBoardId(),subjectId),null);

    }


    /**
     * This controller method is used to   ConceptBoardShortList of myself for this conceptBoard and MediaId
     * @param conceptBoardShortlstDto
     * @param subjectId
     * @return
     */
    @CrossOrigin
    @RbacRegister(authorityName = "getMyConceptBoardShortListForConceptBoardAndConceptBoardMediaId")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/getMyConceptBoardShortListForConceptBoardAndConceptBoardMediaId", consumes = "application/json")
    public ApiResponse<ConceptBoardShortList> getMyConceptBoardShortListForConceptBoardAndConceptBoardMediaId(@RequestBody ConceptBoardShortlstDto conceptBoardShortlstDto,
                                                                                          @RequestHeader("subjectid") Long subjectId){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,conceptBoardShortListService.getMyConceptBoardShortListForConceptBoardAndConceptBoardMediaId(conceptBoardShortlstDto.getConceptBoardId(),conceptBoardShortlstDto.getConceptBoardMediaId(),subjectId),null);

    }


    /**
     * This controller method is used to   ConceptBoardShortList of myself for this conceptBoard and MediaId
     * @param conceptBoardShortlstDto
     * @param subjectId
     * @return
     */
    @CrossOrigin
    @RbacRegister(authorityName = "getShortListedImageForConceptBoardByEmailId")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/getShortListedImageForConceptBoardByEmailId", consumes = "application/json")
    public ApiResponse<EnvelopedConceptBoardShortList> getShortListedImageForConceptBoardByEmailId(@RequestBody ConceptBoardShortlstDto conceptBoardShortlstDto,
                                                                                                                       @RequestHeader("subjectid") Long subjectId){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,conceptBoardShortListService.getShortListedImageForConceptBoardByEmailId(conceptBoardShortlstDto.getConceptBoardId(),conceptBoardShortlstDto.getEmailId(),subjectId),null);

    }

    /**
     * This controller method is used to   ConceptBoardShortList of myself for this conceptBoard and MediaId
     * @param conceptBoardShortlstDto
     * @param subjectId
     * @return
     */
    @CrossOrigin
    @RbacRegister(authorityName = "getMediaShortListMappingForConceptBoardByEmailId")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/getMediaShortListMappingForConceptBoardByEmailId", consumes = "application/json")
    public ApiResponse<EnvelopedConceptBoardShortList> getMediaShortListMappingForConceptBoardByEmailId(@RequestBody ConceptBoardShortlstDto conceptBoardShortlstDto,
                                                                                                   @RequestHeader("subjectid") Long subjectId){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,conceptBoardShortListService.getMediaShortListMappingForConceptBoardByEmailId(conceptBoardShortlstDto.getConceptBoardId(),conceptBoardShortlstDto.getEmailId(),subjectId),null);

    }

}
