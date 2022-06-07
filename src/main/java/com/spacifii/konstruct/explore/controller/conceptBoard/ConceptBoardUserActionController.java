package com.spacifii.konstruct.explore.controller.conceptBoard;

import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardUserAction;
import com.spacifii.konstruct.explore.integration.rbac.annotation.RbacRegister;
import com.spacifii.konstruct.explore.model.APIResponseKey;
import com.spacifii.konstruct.explore.model.ApiResponse;
import com.spacifii.konstruct.explore.model.dto.conceptBoard.ConceptBoardCommentNode;
import com.spacifii.konstruct.explore.model.dto.conceptBoard.ConceptBoardQuestion;
import com.spacifii.konstruct.explore.model.dto.conceptBoard.ConceptBoardReview;
import com.spacifii.konstruct.explore.model.dto.explore.MediaUserActionDTO;
import com.spacifii.konstruct.explore.service.conceptBoard.ConceptBoardUserActionService;
import com.spacifii.konstruct.explore.service.conceptBoard.SaveConceptBoardUserActionStrategyContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This is controller class for ConceptBoardMediaUserAction
 */
@RestController
@RequestMapping("/api/conceptBoardUserAction")
public class ConceptBoardUserActionController {


    @Autowired
    SaveConceptBoardUserActionStrategyContext saveConceptBoardUserActionStrategyContext;

    @Autowired
    ConceptBoardUserActionService conceptBoardUserActionService;


    /**
     * This method saves ConceptBoardUserAction
     * @param mediaUserActionDTO
     * @param subjectId
     * @return
     */
    @RbacRegister(authorityName = "saveConceptBoardUserAction")
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/saveConceptBoardUserAction", consumes = "application/json")
    public ApiResponse<ConceptBoardUserAction> save(@RequestBody MediaUserActionDTO mediaUserActionDTO,
                                                    @RequestHeader("subjectid") Long subjectId){

        return new ApiResponse<>(APIResponseKey.NEW_CREATION,saveConceptBoardUserActionStrategyContext.save(mediaUserActionDTO,subjectId),null);

    }



    /**
     * This method delete ConceptBoardUserAction
     * @param mediaUserActionDTO
     * @param subjectId
     * @return
     */
    @RbacRegister(authorityName = "deleteConceptBoardUserAction")
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/deleteConceptBoardUserAction", consumes = "application/json")
    public ApiResponse<Boolean> delete(@RequestBody MediaUserActionDTO mediaUserActionDTO,
                                                    @RequestHeader("subjectid") Long subjectId){

        return new ApiResponse<>(APIResponseKey.NEW_CREATION,saveConceptBoardUserActionStrategyContext.delete(mediaUserActionDTO,subjectId),null);

    }



    /**
     * This method updates ConceptBoardUserAction
     * @param mediaUserActionDTO
     * @param subjectId
     * @return
     */
    @RbacRegister(authorityName = "updateConceptBoardUserAction")
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/updateConceptBoardUserAction", consumes = "application/json")
    public ApiResponse<ConceptBoardUserAction> update(@RequestBody MediaUserActionDTO mediaUserActionDTO,
                                                    @RequestHeader("subjectid") Long subjectId){

        return new ApiResponse<>(APIResponseKey.NEW_CREATION,saveConceptBoardUserActionStrategyContext.update(mediaUserActionDTO,subjectId),null);

    }

    /**
     * This checks if ConceptBoard is liked by me
     * @param conceptBoardId
     * @param subjectId
     * @return
     */
    @RbacRegister(authorityName = "isConceptBoardLikedByMe",excluded = true)
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping(path = "{conceptBoardId}/isConceptBoardLikedByMe")
    public ApiResponse<Boolean> save(@PathVariable("conceptBoardId") String conceptBoardId,
                                     @RequestHeader("subjectid") Long subjectId){

        return new ApiResponse<>(APIResponseKey.NEW_CREATION,conceptBoardUserActionService.isConceptBoardLikedByMe(conceptBoardId,subjectId),null);

    }


    /**
     * This method is used to get All ConceptBoard Comments
     * @param conceptBoardId
     * @param subjectId
     * @return
     */
    @RbacRegister(authorityName = "getAllConceptBoardComments",excluded = true)
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping(path = "{conceptBoardId}/getAllConceptBoardComments")
    public ApiResponse<List<ConceptBoardCommentNode>> getAllConceptBoardComments(@PathVariable("conceptBoardId") String conceptBoardId,
                                                                                 @RequestHeader(value = "subjectid",required = false) Long subjectId){

        return new ApiResponse<>(APIResponseKey.NEW_CREATION,conceptBoardUserActionService.getAllConceptBoardComments(conceptBoardId,subjectId),null);

    }


    /**
     * This method is used to get All ConceptBoard Comments
     * @param subjectId
     * @return
     */
    @RbacRegister(authorityName = "getAllConceptBoardReviews",excluded = true)
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping(path = "{conceptBoardId}/getAllConceptBoardReviews")
    public ApiResponse<List<ConceptBoardReview>> getAllConceptBoardReviews(@PathVariable("conceptBoardId") String conceptBoardId,
                                                                           @RequestHeader(value = "subjectid",required = false) Long subjectId){

        return new ApiResponse<>(APIResponseKey.NEW_CREATION,conceptBoardUserActionService.getAllConceptBoardReviews(conceptBoardId,subjectId),null);

    }

    /**
     * This method is used to get All ConceptBoard  Questions
     * @param subjectId
     * @return
     */
    @RbacRegister(authorityName = "getAllConceptBoardQuestions",excluded = true)
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping(path = "{conceptBoardId}/getAllConceptBoardQuestions")
    public ApiResponse<List<ConceptBoardQuestion>> getAllProjectQuestions(@PathVariable("conceptBoardId") String conceptBoardId,
                                                                          @RequestHeader(value = "subjectid",required = false) Long subjectId){

        return new ApiResponse<>(APIResponseKey.NEW_CREATION,conceptBoardUserActionService.getAllConceptBoardQuestions(conceptBoardId,subjectId),null);

    }


    /**
     * This controller method is used to answer the Question
     * @param answer
     * @param questionId
     * @param subjectId
     * @return
     */
    @RbacRegister(authorityName = "answerConceptBoardQuestion")
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/question/{questionId}/answerConceptBoardQuestion", consumes = "application/json")
    public ApiResponse<ConceptBoardQuestion> answerQuestion(@RequestBody(required = false) String answer,@PathVariable("questionId") String questionId,
                                                       @RequestHeader("subjectid") Long subjectId){

        return new ApiResponse<>(APIResponseKey.NEW_CREATION,conceptBoardUserActionService.answerQuestion(questionId,answer,subjectId),null);

    }

}
