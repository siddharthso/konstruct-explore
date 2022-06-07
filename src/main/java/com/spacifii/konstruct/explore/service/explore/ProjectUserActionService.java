package com.spacifii.konstruct.explore.service.explore;

import com.spacifii.konstruct.explore.entities.explore.ProjectUserAction;
import com.spacifii.konstruct.explore.model.dto.explore.MediaUserActionDTO;
import com.spacifii.konstruct.explore.model.dto.explore.ProjectCommentNode;
import com.spacifii.konstruct.explore.model.dto.explore.ProjectQuestion;
import com.spacifii.konstruct.explore.model.dto.explore.ProjectReview;

import java.util.List;

/**
 * This is service class for ProjectUserAction
 */
public interface ProjectUserActionService {

    /**
     * This service method saves ProjectUserAction
     * @param projectUserAction
     * @param subjectId
     * @return
     */
    ProjectUserAction save(ProjectUserAction projectUserAction, Long subjectId);

    /**
     * This service method is used to do updates for ProjectUserAction. Just in case someone has to change comments.
     * You cannot change your ratings
     * @param mediaUserActionDTO
     * @param id
     * @param subjectId
     * @return
     */
    ProjectUserAction update(MediaUserActionDTO mediaUserActionDTO, String id, Long subjectId);

    /**
     * This service method is used to get All projectComments
     * @param projectId
     * @param subjectId
     * @return
     */
    List<ProjectCommentNode> getAllProjectComments(String projectId, Long subjectId);

    /**
     * This servie method is used to get all ProjectQuestions
     * @param projectId
     * @param subjectId
     * @return
     */
    List<ProjectQuestion> getAllProjectQuestions(String projectId, Long subjectId);

    /**
     * This service method is used to get all project reviews
     * @param projectId
     * @param subjectId
     * @return
     */
    List<ProjectReview> getAllProjectReviews(String projectId, Long subjectId);

    /**
     * This service method is used to answer a question
     * @param questionId
     * @param answer
     * @param subjectId
     * @return
     */
    ProjectQuestion answerQuestion(String questionId, String answer, Long subjectId);

    /**
     * This service method is used to check if Project is liked by me
     * @param projectId
     * @param subjectId
     * @return
     */
    Boolean isProjectLikedByMe(String projectId, Long subjectId);

    /**
     * This service method is used to find ProjectUserAction by Id
     * @param id
     * @return
     */
    ProjectUserAction findById(String id);

}
