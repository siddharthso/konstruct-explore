package com.spacifii.konstruct.explore.controller.conceptBoard;

import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardMediaUserAction;
import com.spacifii.konstruct.explore.integration.rbac.annotation.RbacRegister;
import com.spacifii.konstruct.explore.model.APIResponseKey;
import com.spacifii.konstruct.explore.model.ApiResponse;
import com.spacifii.konstruct.explore.model.dto.conceptBoard.ConceptBoardMediaCommentNode;
import com.spacifii.konstruct.explore.model.dto.conceptBoard.ConceptBoardMediaQuestion;
import com.spacifii.konstruct.explore.model.dto.conceptBoard.ConceptBoardMediaReview;
import com.spacifii.konstruct.explore.model.dto.explore.MediaUserActionDTO;
import com.spacifii.konstruct.explore.service.conceptBoard.ConceptBoardMediaUserActionService;
import com.spacifii.konstruct.explore.service.conceptBoard.SaveConceptBoardMediaUserActionStrategyContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This is controller class for ConceptBoardMediaUserAction
 */
@RestController
@RequestMapping("/api/conceptBoardMediaUserAction")
public class ConceptBoardMediaUserActionController {


    @Autowired
    SaveConceptBoardMediaUserActionStrategyContext saveConceptBoardMediaUserActionStrategyContext;

    @Autowired
    ConceptBoardMediaUserActionService conceptBoardMediaUserActionService;


    /**
     * This method saves ConceptBoardMediaUserAction
     * @param mediaUserActionDTO
     * @param subjectId
     * @return
     */
    @RbacRegister(authorityName = "saveConceptBoardMediaUserAction")
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/saveConceptBoardMediaUserAction", consumes = "application/json")
    public ApiResponse<ConceptBoardMediaUserAction> save(@RequestBody MediaUserActionDTO mediaUserActionDTO,
                                                         @RequestHeader("subjectid") Long subjectId){

        return new ApiResponse<>(APIResponseKey.NEW_CREATION,saveConceptBoardMediaUserActionStrategyContext.save(mediaUserActionDTO,subjectId),null);

    }

    /**
     * This method updates ConceptBoardMediaUserAction
     * @param mediaUserActionDTO
     * @param subjectId
     * @return
     */
    @RbacRegister(authorityName = "updateConceptBoardMediaUserAction")
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/updateConceptBoardMediaUserAction", consumes = "application/json")
    public ApiResponse<ConceptBoardMediaUserAction> update(@RequestBody MediaUserActionDTO mediaUserActionDTO,
                                                         @RequestHeader("subjectid") Long subjectId){

        return new ApiResponse<>(APIResponseKey.NEW_CREATION,saveConceptBoardMediaUserActionStrategyContext.update(mediaUserActionDTO,subjectId),null);

    }

    /**
     * This method deletes ConceptBoardMediaUserAction
     * @param mediaUserActionDTO
     * @param subjectId
     * @return
     */
    @RbacRegister(authorityName = "deleteConceptBoardMediaUserAction")
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/deleteConceptBoardMediaUserAction", consumes = "application/json")
    public ApiResponse<Boolean> delete(@RequestBody MediaUserActionDTO mediaUserActionDTO,
                                                         @RequestHeader("subjectid") Long subjectId){
        return new ApiResponse<>(APIResponseKey.NEW_CREATION,saveConceptBoardMediaUserActionStrategyContext.delete(mediaUserActionDTO,subjectId),null);
    }

    /**
     * This checks if Media is liked by me
     * @param mediaId
     * @param subjectId
     * @return
     */
    @RbacRegister(authorityName = "isConceptBoardMediaLikedByMe",excluded = true)
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping(path = "{mediaId}/isConceptBoardMediaLikedByMe")
    public ApiResponse<Boolean> save(@PathVariable("mediaId") String mediaId,
                                     @RequestHeader("subjectid") Long subjectId){

        return new ApiResponse<>(APIResponseKey.NEW_CREATION,conceptBoardMediaUserActionService.isMediaLikedByMe(mediaId,subjectId),null);

    }


    /**
     * This method is used to get All Media Comments
     * @param mediaId
     * @param subjectId
     * @return
     */
    @RbacRegister(authorityName = "getAllConceptBoardMediaComments",excluded = true)
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping(path = "{mediaId}/getAllConceptBoardMediaComments")
    public ApiResponse<List<ConceptBoardMediaCommentNode>> getAllMediaComments(@PathVariable("mediaId") String mediaId,
                                                                               @RequestHeader(value = "subjectid",required = false) Long subjectId){

        return new ApiResponse<>(APIResponseKey.NEW_CREATION,conceptBoardMediaUserActionService.getAllMediaComments(mediaId,subjectId),null);

    }


    /**
     * This method is used to get All Media Comments
     * @param subjectId
     * @return
     */
    @RbacRegister(authorityName = "getAllConceptBoardMediaReviews",excluded = true)
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping(path = "{mediaId}/getAllConceptBoardMediaReviews")
    public ApiResponse<List<ConceptBoardMediaReview>> getAllProjectReviews(@PathVariable("mediaId") String mediaId,
                                                                           @RequestHeader(value = "subjectid",required = false) Long subjectId){

        return new ApiResponse<>(APIResponseKey.NEW_CREATION,conceptBoardMediaUserActionService.getAllMediaReviews(mediaId,subjectId),null);

    }

    /**
     * This method is used to get All ConceptBoard Media Questions
     * @param subjectId
     * @return
     */
    @RbacRegister(authorityName = "getAllConceptBoardMediaQuestions",excluded = true)
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping(path = "{mediaId}/getAllConceptBoardMediaQuestions")
    public ApiResponse<List<ConceptBoardMediaQuestion>> getAllProjectQuestions(@PathVariable("mediaId") String mediaId,
                                                                               @RequestHeader(value = "subjectid",required = false) Long subjectId){

        return new ApiResponse<>(APIResponseKey.NEW_CREATION,conceptBoardMediaUserActionService.getAllMediaQuestions(mediaId,subjectId),null);

    }

    /**
     * This controller method is used to answer the Question
     * @param answer
     * @param questionId
     * @param subjectId
     * @return
     */
    @RbacRegister(authorityName = "answerConceptBoardMediaQuestion")
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/question/{questionId}/answerConceptBoardMediaQuestion", consumes = "application/json")
    public ApiResponse<ConceptBoardMediaQuestion> answerQuestion(@RequestBody(required = false) String answer,@PathVariable("questionId") String questionId,
                                                            @RequestHeader("subjectid") Long subjectId){

        return new ApiResponse<>(APIResponseKey.NEW_CREATION,conceptBoardMediaUserActionService.answerQuestion(questionId,answer,subjectId),null);

    }


}
