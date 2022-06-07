package com.spacifii.konstruct.explore.controller.explore;

import com.spacifii.konstruct.explore.entities.explore.MediaUserAction;
import com.spacifii.konstruct.explore.integration.rbac.annotation.RbacRegister;
import com.spacifii.konstruct.explore.model.APIResponseKey;
import com.spacifii.konstruct.explore.model.ApiResponse;
import com.spacifii.konstruct.explore.model.dto.explore.MediaCommentNode;
import com.spacifii.konstruct.explore.model.dto.explore.MediaQuestion;
import com.spacifii.konstruct.explore.model.dto.explore.MediaReview;
import com.spacifii.konstruct.explore.model.dto.explore.MediaUserActionDTO;
import com.spacifii.konstruct.explore.service.explore.MediaUserActionService;
import com.spacifii.konstruct.explore.service.explore.SaveMediaUserActionStrategyContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This is controller class for ConceptBoardMediaUserAction
 */
@RestController
@RequestMapping("/api/mediaUserAction")
public class MediaUserActionController {


    @Autowired
    SaveMediaUserActionStrategyContext saveMediaUserActionStrategyContext;

    @Autowired
    MediaUserActionService mediaUserActionService;


    /**
     * This method saves MediaUserAction
     * @param mediaUserActionDTO
     * @param subjectId
     * @return
     */
    @RbacRegister(authorityName = "addMediaUserAction")
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/addMediaUserAction", consumes = "application/json")
    public ApiResponse<MediaUserAction> save(@RequestBody MediaUserActionDTO mediaUserActionDTO,
                                             @RequestHeader("subjectid") Long subjectId){

        return new ApiResponse<>(APIResponseKey.NEW_CREATION,saveMediaUserActionStrategyContext.save(mediaUserActionDTO,subjectId),null);

    }

    /**
     * This method saves MediaUserAction
     * @param mediaUserActionDTO
     * @param subjectId
     * @return
     */
    @RbacRegister(authorityName = "updateMediaUserAction")
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/updateMediaUserAction", consumes = "application/json")
    public ApiResponse<MediaUserAction> updateMediaUserAction(@RequestBody MediaUserActionDTO mediaUserActionDTO,
                                             @RequestHeader("subjectid") Long subjectId){

        return new ApiResponse<>(APIResponseKey.NEW_CREATION,saveMediaUserActionStrategyContext.save(mediaUserActionDTO,subjectId),null);

    }


    /**
     * This method saves MediaUserAction
     * @param mediaUserActionDTO
     * @param subjectId
     * @return
     */
    @RbacRegister(authorityName = "deleteMediaUserAction")
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/deleteMediaUserAction", consumes = "application/json")
    public ApiResponse<Boolean> deleteMediaUserAction(@RequestBody MediaUserActionDTO mediaUserActionDTO,
                                                              @RequestHeader("subjectid") Long subjectId){

        return new ApiResponse<>(APIResponseKey.NEW_CREATION,saveMediaUserActionStrategyContext.delete(mediaUserActionDTO,subjectId),null);

    }

    /**
     * This checks if Media is liked by me
     * @param mediaId
     * @param subjectId
     * @return
     */
    @RbacRegister(authorityName = "isMediaLikedByMe",excluded = true)
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping(path = "{mediaId}/isMediaLikedByMe")
    public ApiResponse<Boolean> save(@PathVariable("mediaId") String mediaId,
                                     @RequestHeader("subjectid") Long subjectId){

        return new ApiResponse<>(APIResponseKey.NEW_CREATION,mediaUserActionService.isMediaLikedByMe(mediaId,subjectId),null);

    }


    /**
     * This method is used to get All Media Comments
     * @param mediaId
     * @param subjectId
     * @return
     */
    @RbacRegister(authorityName = "getAllMediaComments",excluded = true)
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping(path = "{mediaId}/getAllMediaComments")
    public ApiResponse<List<MediaCommentNode>> getAllMediaComments(@PathVariable("mediaId") String mediaId,
                                                                   @RequestHeader(value = "subjectid",required = false) Long subjectId){

        return new ApiResponse<>(APIResponseKey.NEW_CREATION,mediaUserActionService.getAllMediaComments(mediaId,subjectId),null);

    }


    /**
     * This method is used to get All Media Comments
     * @param subjectId
     * @return
     */
    @RbacRegister(authorityName = "getAllMediaReviews",excluded = true)
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping(path = "{mediaId}/getAllMediaReviews")
    public ApiResponse<List<MediaReview>> getAllMediaReviews(@PathVariable("mediaId") String mediaId,
                                                               @RequestHeader(value = "subjectid",required = false) Long subjectId){

        return new ApiResponse<>(APIResponseKey.NEW_CREATION,mediaUserActionService.getAllMediaReviews(mediaId,subjectId),null);

    }

    /**
     * This method is used to get All Media Questions
     * @param subjectId
     * @return
     */
    @RbacRegister(authorityName = "getAllMediaQuestions",excluded = true)
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping(path = "{mediaId}/getAllMediaQuestions")
    public ApiResponse<List<MediaQuestion>> getAllProjectQuestions(@PathVariable("mediaId") String mediaId,
                                                                   @RequestHeader(value = "subjectid",required = false) Long subjectId){

        return new ApiResponse<>(APIResponseKey.NEW_CREATION,mediaUserActionService.getAllMediaQuestions(mediaId,subjectId),null);

    }


    /**
     * This controller method is used to answer the Question
     * @param answer
     * @param questionId
     * @param subjectId
     * @return
     */
    @RbacRegister(authorityName = "answerMediaQuestion")
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/question/{questionId}/answerMediaQuestion", consumes = "application/json")
    public ApiResponse<MediaQuestion> answerQuestion(@RequestBody(required = false) String answer,@PathVariable("questionId") String questionId,
                                             @RequestHeader("subjectid") Long subjectId){

        return new ApiResponse<>(APIResponseKey.NEW_CREATION,mediaUserActionService.answerQuestion(questionId,answer,subjectId),null);

    }



}
