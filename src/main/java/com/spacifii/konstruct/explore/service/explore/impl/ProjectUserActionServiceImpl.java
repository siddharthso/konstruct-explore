package com.spacifii.konstruct.explore.service.explore.impl;

import com.spacifii.konstruct.explore.entities.explore.MediaUserActionType;
import com.spacifii.konstruct.explore.entities.explore.ProjectUserAction;
import com.spacifii.konstruct.explore.exception.explore.ProjectUserActionNotFoundException;
import com.spacifii.konstruct.explore.model.dto.explore.*;
import com.spacifii.konstruct.explore.repository.explore.ProjectUserActionRepository;
import com.spacifii.konstruct.explore.service.explore.ProjectUserActionService;
import com.spacifii.konstruct.explore.service.explore.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * This is service class for ProjectUserAction
 */
@Service
public class ProjectUserActionServiceImpl implements ProjectUserActionService {

    @Autowired
    ProjectUserActionRepository projectUserActionRepository;

    @Autowired
    UserProfileService userProfileService;

    /**
     * This service method saves ProjectUserAction
     * @param projectUserAction
     * @param subjectId
     * @return
     */
    @Override
    @Transactional
    public ProjectUserAction save(ProjectUserAction projectUserAction, Long subjectId) {
        if(projectUserAction.getCreationDate() == null) {
            projectUserAction.preSave(subjectId);
        } else {
            projectUserAction.preUpdate(subjectId);
        }
        return projectUserActionRepository.save(projectUserAction);
    }

    /**
     * This service method is used to do updates for ProjectUserAction. Just in case someone has to change comments.
     * You cannot change your ratings
     *
     * @param mediaUserActionDTO
     * @param id
     * @param subjectId
     * @return
     */
    @Override
    public ProjectUserAction update(MediaUserActionDTO mediaUserActionDTO, String id, Long subjectId) {
        ProjectUserAction projectUserAction = findById(id);
        projectUserAction.convertMediaUserActionDTOToMe(mediaUserActionDTO);
        projectUserAction.preUpdate(subjectId);
        return projectUserActionRepository.save(projectUserAction);
    }

    /**
     * This service method is used to get All projectComments
     *
     * @param projectId
     * @param subjectId
     * @return
     */
    @Override
    public List<ProjectCommentNode> getAllProjectComments(String projectId, Long subjectId) {
        List<ProjectUserAction> projectUserActions = projectUserActionRepository.findByProjectIdAndMediaUserActionType(projectId,MediaUserActionType.COMMENT);

        Map<String,ProjectUserAction> idMap = new HashMap<>();
        Map<String,List<ProjectUserAction>> childMap = new HashMap<>();
        List<ProjectUserAction> parentList = new ArrayList<>();
        Set<Long> subjectIds = new HashSet<>();
        if(projectUserActions != null && projectUserActions.size() > 0){
            for (ProjectUserAction projectUserAction: projectUserActions) {
                idMap.put(projectUserAction.getId(),projectUserAction);
                if(projectUserAction.getParentCommentId() == null){
                    parentList.add(projectUserAction);
                } else {
                    List<ProjectUserAction> childs = childMap.get(projectUserAction.getParentCommentId());
                    if(childs == null){
                        childs = new ArrayList<>();
                    }
                    childs.add(projectUserAction);
                    childMap.put(projectUserAction.getParentCommentId(),childs);
                }
                subjectIds.add(projectUserAction.getCreatedBy());
            }


            List<ProjectCommentNode> projectCommentNodes = new ArrayList<>();

            Map<Long, UserProfileMiniDto> map = userProfileService.getUserProfileForSubjectIds(subjectIds);

            for (ProjectUserAction projectUserAction: parentList) {
                Boolean isLikedByMe = projectUserAction.getCreatedBy().equals(subjectId);
                ProjectComment projectComment = new ProjectComment(map.get(projectUserAction.getCreatedBy()),isLikedByMe);
                projectComment.convertProjectUserActionToProjectComment(projectUserAction);
                ProjectCommentNode projectCommentNode = new ProjectCommentNode();
                projectCommentNode.setProjectComment(projectComment);
                List<ProjectCommentNode> childrenNodes = collectChildren(projectUserAction.getId(),childMap,map,subjectId);
                projectCommentNode.setChildrenNodes(childrenNodes);
                projectCommentNodes.add(projectCommentNode);
            }

            return projectCommentNodes;
        }

        return null;
    }

    private List<ProjectCommentNode> collectChildren(String id, Map<String, List<ProjectUserAction>> parentMap, Map<Long,UserProfileMiniDto> map, Long subjectId) {

        /**
         * TODO: Check Visited to Avoid infinite Recursion
         */
        List<ProjectCommentNode> projectCommentNodes = new ArrayList<>();

        if(parentMap.get(id) == null){

        } else {
            for (ProjectUserAction projectUserAction: parentMap.get(id)) {
                Boolean isLikedByMe = projectUserAction.getCreatedBy().equals(subjectId);
                ProjectComment projectComment = new ProjectComment(map.get(projectUserAction.getCreatedBy()),isLikedByMe);
                projectComment.convertProjectUserActionToProjectComment(projectUserAction);
                ProjectCommentNode projectCommentNode = new ProjectCommentNode();
                projectCommentNode.setProjectComment(projectComment);
                List<ProjectCommentNode> childrenNodes = collectChildren(projectUserAction.getId(),parentMap,map,subjectId);
                projectCommentNode.setChildrenNodes(childrenNodes);
                projectCommentNodes.add(projectCommentNode);
            }
        }

        return projectCommentNodes;
    }


    /**
     * This servie method is used to get all ProjectQuestions
     *
     * @param projectId
     * @param subjectId
     * @return
     */
    @Override
    public List<ProjectQuestion> getAllProjectQuestions(String projectId, Long subjectId) {
        List<ProjectQuestion> projectQuestions = new ArrayList<>();
        List<ProjectUserAction> projectUserActions =
                projectUserActionRepository.findByProjectIdAndMediaUserActionType(projectId,MediaUserActionType.QUESTION);
        if(projectUserActions != null & projectUserActions.size() > 0) {
            Set<Long> subjectIds = new HashSet<>();
            for (ProjectUserAction projectUserAction: projectUserActions) {
                subjectIds.add(projectUserAction.getCreatedBy());
                if(projectUserAction.getAnsweredBy() != null) {
                    subjectIds.add(projectUserAction.getAnsweredBy());
                }
            }
            Map<Long,UserProfileMiniDto> map = userProfileService.getUserProfileForSubjectIds(subjectIds);

            for (ProjectUserAction projectUserAction: projectUserActions) {
                Boolean isLikedByMe = projectUserAction.getCreatedBy().equals(subjectId);
                ProjectQuestion projectQuestion = new ProjectQuestion(isLikedByMe,map.get(projectUserAction.getCreatedBy()),map.get(projectUserAction.getAnsweredBy()));
                projectQuestions.add(projectQuestion);
            }
        }
        return projectQuestions;
    }

    /**
     * This service method is used to get all project reviews
     *
     * @param projectId
     * @param subjectId
     * @return
     */
    @Override
    public List<ProjectReview> getAllProjectReviews(String projectId, Long subjectId) {
        List<ProjectReview> projectReviews = new ArrayList<>();
        List<ProjectUserAction> projectUserActions =
                projectUserActionRepository.findByProjectIdAndMediaUserActionType(projectId,MediaUserActionType.REVIEW);
        if(projectUserActions != null & projectUserActions.size() > 0) {
            Set<Long> subjectIds = new HashSet<>();
            for (ProjectUserAction projectUserAction: projectUserActions) {
                subjectIds.add(projectUserAction.getCreatedBy());
                //subjectIds.add(projectUserAction.getLastModifiedBy());
            }
            Map<Long,UserProfileMiniDto> map = userProfileService.getUserProfileForSubjectIds(subjectIds);

            for (ProjectUserAction projectUserAction: projectUserActions) {
                Boolean isLikedByMe = projectUserAction.getCreatedBy().equals(subjectId);
                ProjectReview projectReview = new ProjectReview(isLikedByMe,map.get(projectUserAction.getCreatedBy()));
                projectReview.convertProjectUserActionToProjectReview(projectUserAction);
                projectReviews.add(projectReview);
            }

        }
        return projectReviews;
    }

    /**
     * This service method is used to answer a question
     *
     * @param questionId
     * @param answer
     * @param subjectId
     * @return
     */
    @Override
    public ProjectQuestion answerQuestion(String questionId, String answer, Long subjectId) {
        ProjectUserAction projectUserAction = findById(questionId);
        projectUserAction.setAnswer(answer);
        projectUserAction.setAnsweredBy(subjectId);
        projectUserAction.preUpdate(subjectId);
        projectUserAction = projectUserActionRepository.save(projectUserAction);
        ProjectQuestion projectQuestion = new ProjectQuestion();
        projectQuestion.convertProjectUserACtionToProjectQuestion(projectUserAction);
        projectQuestion.setAskedByMe(true);
        return projectQuestion;
    }

    /**
     * This service method is used to check if Project is liked by me
     *
     * @param projectId
     * @param subjectId
     * @return
     */
    @Override
    public Boolean isProjectLikedByMe(String projectId, Long subjectId) {
        ProjectUserAction projectUserAction =
                projectUserActionRepository.findFirstByProjectIdAndMediaUserActionTypeAndCreatedBy(projectId, MediaUserActionType.LIKE,subjectId);
        if(projectUserAction == null ){
            return true;
        }
        return false;
    }

    /**
     * This service method is used to find ProjectUserAction by Id
     *
     * @param id
     * @return
     */
    @Override
    public ProjectUserAction findById(String id) {
        Optional<ProjectUserAction> projectUserActionOptional = projectUserActionRepository.findById(id);
        if(projectUserActionOptional.isPresent()){
            return projectUserActionOptional.get();
        }
        throw new ProjectUserActionNotFoundException();
    }
}
