package com.spacifii.konstruct.explore.controller.explore;

import com.spacifii.konstruct.explore.entities.explore.ProjectUserAction;
import com.spacifii.konstruct.explore.integration.rbac.annotation.RbacRegister;
import com.spacifii.konstruct.explore.model.APIResponseKey;
import com.spacifii.konstruct.explore.model.ApiResponse;
import com.spacifii.konstruct.explore.model.dto.explore.MediaUserActionDTO;
import com.spacifii.konstruct.explore.model.dto.explore.ProjectCommentNode;
import com.spacifii.konstruct.explore.model.dto.explore.ProjectQuestion;
import com.spacifii.konstruct.explore.model.dto.explore.ProjectReview;
import com.spacifii.konstruct.explore.service.explore.ProjectUserActionService;
import com.spacifii.konstruct.explore.service.explore.SaveProjectUserActionStrategyContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This is controller class for ProjectUserAction
 */
@RestController
@RequestMapping("/api/projectUserAction")
public class ProjectUserActionController {


    @Autowired
    SaveProjectUserActionStrategyContext saveProjectUserActionStrategyContext;

    @Autowired
    ProjectUserActionService projectUserActionService;


    /**
     * This method saves ProjectUserAction
     * @param mediaUserActionDTO
     * @param subjectId
     * @return
     */
    @RbacRegister(authorityName = "addProjectUserAction")
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/addProjectUserAction", consumes = "application/json")
    public ApiResponse<ProjectUserAction> save(@RequestBody MediaUserActionDTO mediaUserActionDTO,
                                               @RequestHeader("subjectid") Long subjectId){

        return new ApiResponse<>(APIResponseKey.NEW_CREATION,saveProjectUserActionStrategyContext.save(mediaUserActionDTO,subjectId),null);

    }


    /**
     * This method updates ProjectUserAction
     * @param mediaUserActionDTO
     * @param subjectId
     * @return
     */
    @RbacRegister(authorityName = "updateProjectUserAction")
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/updateProjectUserAction", consumes = "application/json")
    public ApiResponse<ProjectUserAction> update(@RequestBody MediaUserActionDTO mediaUserActionDTO,
                                               @RequestHeader("subjectid") Long subjectId){

        return new ApiResponse<>(APIResponseKey.NEW_CREATION,saveProjectUserActionStrategyContext.update(mediaUserActionDTO,subjectId),null);

    }


    /**
     * This method deletes ProjectUserAction
     * @param mediaUserActionDTO
     * @param subjectId
     * @return
     */
    @RbacRegister(authorityName = "deleteProjectUserAction")
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/deleteProjectUserAction", consumes = "application/json")
    public ApiResponse<Boolean> delete(@RequestBody MediaUserActionDTO mediaUserActionDTO,
                                               @RequestHeader("subjectid") Long subjectId){

        return new ApiResponse<>(APIResponseKey.NEW_CREATION,saveProjectUserActionStrategyContext.delete(mediaUserActionDTO,subjectId),null);

    }


    /**
     * This checks if Project is liked by me
     * @param subjectId
     * @return
     */
    @RbacRegister(authorityName = "isProjectLikedByMe",excluded = true)
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping(path = "{projectId}/isProjectLikedByMe")
    public ApiResponse<Boolean> save(@PathVariable("projectId") String projectId,
                                               @RequestHeader("subjectid") Long subjectId){

        return new ApiResponse<>(APIResponseKey.NEW_CREATION,projectUserActionService.isProjectLikedByMe(projectId,subjectId),null);

    }


    /**
     * This method is used to get All Project Comments
     * @param subjectId
     * @return
     */
    @RbacRegister(authorityName = "getAllProjectComments",excluded = true)
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping(path = "{projectId}/getAllProjectComments")
    public ApiResponse<List<ProjectCommentNode>> getAllProjectComments(@PathVariable("projectId") String projectId,
                                                      @RequestHeader(value = "subjectid",required = false) Long subjectId){

        return new ApiResponse<>(APIResponseKey.NEW_CREATION,projectUserActionService.getAllProjectComments(projectId,subjectId),null);

    }


    /**
     * This method is used to get All Project Comments
     * @param subjectId
     * @return
     */
    @RbacRegister(authorityName = "getAllProjectReviews",excluded = true)
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping(path = "{projectId}/getAllProjectReviews")
    public ApiResponse<List<ProjectReview>> getAllProjectReviews(@PathVariable("projectId") String projectId,
                                                                 @RequestHeader(value = "subjectid",required = false) Long subjectId){

        return new ApiResponse<>(APIResponseKey.NEW_CREATION,projectUserActionService.getAllProjectReviews(projectId,subjectId),null);

    }

    /**
     * This method is used to get All Project Comments
     * @param subjectId
     * @return
     */
    @RbacRegister(authorityName = "getAllProjectQuestions",excluded = true)
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping(path = "{projectId}/getAllProjectQuestions")
    public ApiResponse<List<ProjectQuestion>> getAllProjectQuestions(@PathVariable("projectId") String projectId,
                                                                     @RequestHeader(value = "subjectid",required = false) Long subjectId){

        return new ApiResponse<>(APIResponseKey.NEW_CREATION,projectUserActionService.getAllProjectQuestions(projectId,subjectId),null);

    }


    /**
     * This controller method is used to answer the Question
     * @param answer
     * @param questionId
     * @param subjectId
     * @return
     */
    @RbacRegister(authorityName = "answerProjectQuestion")
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/question/{questionId}/answerProjectQuestion", consumes = "application/json")
    public ApiResponse<ProjectQuestion> answerQuestion(@RequestBody(required = false) String answer,@PathVariable("questionId") String questionId,
                                                     @RequestHeader("subjectid") Long subjectId){

        return new ApiResponse<>(APIResponseKey.NEW_CREATION,projectUserActionService.answerQuestion(questionId,answer,subjectId),null);

    }


}
