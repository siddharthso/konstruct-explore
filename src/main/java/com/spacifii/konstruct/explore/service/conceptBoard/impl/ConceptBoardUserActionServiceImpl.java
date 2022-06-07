package com.spacifii.konstruct.explore.service.conceptBoard.impl;

import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardUserAction;
import com.spacifii.konstruct.explore.entities.explore.MediaUserActionType;
import com.spacifii.konstruct.explore.exception.conceptBoard.ConceptBoardUserActionNotFoundException;
import com.spacifii.konstruct.explore.model.dto.conceptBoard.ConceptBoardComment;
import com.spacifii.konstruct.explore.model.dto.conceptBoard.ConceptBoardCommentNode;
import com.spacifii.konstruct.explore.model.dto.conceptBoard.ConceptBoardQuestion;
import com.spacifii.konstruct.explore.model.dto.conceptBoard.ConceptBoardReview;
import com.spacifii.konstruct.explore.model.dto.explore.MediaUserActionDTO;
import com.spacifii.konstruct.explore.model.dto.explore.UserProfileMiniDto;
import com.spacifii.konstruct.explore.repository.conceptBoard.ConceptBoardUserActionRepository;
import com.spacifii.konstruct.explore.service.conceptBoard.ConceptBoardUserActionService;
import com.spacifii.konstruct.explore.service.explore.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * This is service class for ConceptBoardUserAction
 */
@Service
public class ConceptBoardUserActionServiceImpl implements ConceptBoardUserActionService {

    @Autowired
    ConceptBoardUserActionRepository conceptBoardUserActionRepository;

    @Autowired
    UserProfileService userProfileService;

    /**
     * This service method saves ConceptBoardMediaUserAction
     *
     * @param conceptBoardUserAction
     * @param subjectId
     * @return
     */
    @Override
    public ConceptBoardUserAction save(ConceptBoardUserAction conceptBoardUserAction, Long subjectId) {
        if(conceptBoardUserAction.getCreationDate() == null) {
            conceptBoardUserAction.preSave(subjectId);
        } else {
            conceptBoardUserAction.preUpdate(subjectId);
        }
        return conceptBoardUserActionRepository.save(conceptBoardUserAction);
    }

    /**
     * This service method is used to do updates for ConceptBoardMediaUserAction. Just in case someone has to change comments.
     * You cannot change your ratings
     *
     * @param mediaUserActionDTO
     * @param id
     * @param subjectId
     * @return
     */
    @Override
    public ConceptBoardUserAction update(MediaUserActionDTO mediaUserActionDTO, String id, Long subjectId) {
       ConceptBoardUserAction conceptBoardUserAction = findById(id);
       conceptBoardUserAction.convertCOnceUserActionDTOToMe(mediaUserActionDTO);
       conceptBoardUserAction.preUpdate(subjectId);
        return conceptBoardUserActionRepository.save(conceptBoardUserAction);
    }

    /**
     * This service method is used to get All mediaComments
     *
     * @param conceptBoardId
     * @param subjectId
     * @return
     */
    @Override
    public List<ConceptBoardCommentNode> getAllConceptBoardComments(String conceptBoardId, Long subjectId) {
        List<ConceptBoardUserAction> conceptBoardUserActions = conceptBoardUserActionRepository.findByConceptBoardIdAndMediaUserActionType(conceptBoardId,MediaUserActionType.COMMENT);

        Map<String,ConceptBoardUserAction> idMap = new HashMap<>();
        Map<String,List<ConceptBoardUserAction>> childMap = new HashMap<>();
        List<ConceptBoardUserAction> parentList = new ArrayList<>();
        Set<Long> subjectIds = new HashSet<>();
        if(conceptBoardUserActions != null && conceptBoardUserActions.size() > 0) {
            for (ConceptBoardUserAction conceptBoardUserAction : conceptBoardUserActions) {
                idMap.put(conceptBoardUserAction.getId(), conceptBoardUserAction);
                if (conceptBoardUserAction.getParentCommentId() == null) {
                    parentList.add(conceptBoardUserAction);
                } else {
                    List<ConceptBoardUserAction> childs = childMap.get(conceptBoardUserAction.getParentCommentId());
                    if (childs == null) {
                        childs = new ArrayList<>();
                    }
                    childs.add(conceptBoardUserAction);
                    childMap.put(conceptBoardUserAction.getParentCommentId(), childs);
                }
                subjectIds.add(conceptBoardUserAction.getCreatedBy());
            }


            List<ConceptBoardCommentNode> conceptBoardCommentNodes = new ArrayList<>();

            Map<Long, UserProfileMiniDto> map = userProfileService.getUserProfileForSubjectIds(subjectIds);

            for (ConceptBoardUserAction conceptBoardUserAction : parentList) {
                Boolean isLikedByMe = conceptBoardUserAction.getCreatedBy().equals(subjectId);
                ConceptBoardComment conceptBoardComment = new ConceptBoardComment(isLikedByMe, map.get(conceptBoardUserAction.getCreatedBy()));
                conceptBoardComment.convertConceptBoardUserActionToConceptBoardComment(conceptBoardUserAction);
                ConceptBoardCommentNode conceptBoardCommentNode = new ConceptBoardCommentNode();
                conceptBoardCommentNode.setConceptBoardComment(conceptBoardComment);
                List<ConceptBoardCommentNode> childrenNodes = collectChildren(conceptBoardUserAction.getId(), childMap, map, subjectId);
                conceptBoardCommentNode.setChildrenNodes(childrenNodes);
                conceptBoardCommentNodes.add(conceptBoardCommentNode);
            }


            return conceptBoardCommentNodes;
        }
        return null;
    }

    private List<ConceptBoardCommentNode> collectChildren(String id, Map<String, List<ConceptBoardUserAction>> parentMap, Map<Long, UserProfileMiniDto> map, Long subjectId) {

        /**
         * TODO: Check Visited to Avoid infinite Recursion
         */
        List<ConceptBoardCommentNode> conceptBoardCommentNodes = new ArrayList<>();

        if(parentMap.get(id) == null){

        } else {
            for (ConceptBoardUserAction conceptBoardUserAction: parentMap.get(id)) {
                Boolean isLikedByMe = conceptBoardUserAction.getCreatedBy().equals(subjectId);
                ConceptBoardComment conceptBoardComment = new ConceptBoardComment(isLikedByMe, map.get(conceptBoardUserAction.getCreatedBy()));
                conceptBoardComment.convertConceptBoardUserActionToConceptBoardComment(conceptBoardUserAction);
                ConceptBoardCommentNode conceptBoardCommentNode = new ConceptBoardCommentNode();
                conceptBoardCommentNode.setConceptBoardComment(conceptBoardComment);
                List<ConceptBoardCommentNode> childrenNodes = collectChildren(conceptBoardUserAction.getId(), parentMap, map, subjectId);
                conceptBoardCommentNode.setChildrenNodes(childrenNodes);
                conceptBoardCommentNodes.add(conceptBoardCommentNode);
            }
        }

        return conceptBoardCommentNodes;
    }

    /**
     * This servie method is used to get all ConceptBoardQuestions
     *
     * @param conceptBoardId
     * @param subjectId
     * @return
     */
    @Override
    public List<ConceptBoardQuestion> getAllConceptBoardQuestions(String conceptBoardId, Long subjectId) {
        List<ConceptBoardQuestion> conceptBoardQuestions = new ArrayList<>();
        List<ConceptBoardUserAction> conceptBoardUserActions =
                conceptBoardUserActionRepository.findByConceptBoardIdAndMediaUserActionType(conceptBoardId, MediaUserActionType.QUESTION);
        if(conceptBoardUserActions != null && conceptBoardUserActions.size() > 0) {
            Set<Long> subjectIds = new HashSet<>();
            for (ConceptBoardUserAction conceptBoardUserAction : conceptBoardUserActions) {
                subjectIds.add(conceptBoardUserAction.getCreatedBy());
                if (conceptBoardUserAction.getAnsweredBy() != null) {
                    subjectIds.add(conceptBoardUserAction.getAnsweredBy());
                }
            }

            Map<Long, UserProfileMiniDto> map = userProfileService.getUserProfileForSubjectIds(subjectIds);
            for (ConceptBoardUserAction conceptBoardUserAction : conceptBoardUserActions) {
                Boolean isLikedByMe = conceptBoardUserAction.getCreatedBy().equals(subjectId);
                ConceptBoardQuestion conceptBoardQuestion = new ConceptBoardQuestion
                        (isLikedByMe,map.get(conceptBoardUserAction.getCreatedBy()),map.get(conceptBoardUserAction.getAnsweredBy()));
                conceptBoardQuestion.convertConceptBoardUserACtionToConceptBoardQuestion(conceptBoardUserAction);
                conceptBoardQuestions.add(conceptBoardQuestion);
            }
        }

        return conceptBoardQuestions;
    }

    /**
     * This service method is used to get all conceptBoard reviews
     *
     * @param conceptBoardId
     * @param subjectId
     * @return
     */
    @Override
    public List<ConceptBoardReview> getAllConceptBoardReviews(String conceptBoardId, Long subjectId) {
        List<ConceptBoardReview> conceptBoardReviews = new ArrayList<>();
        List<ConceptBoardUserAction> conceptBoardUserActions =
                conceptBoardUserActionRepository.findByConceptBoardIdAndMediaUserActionType(conceptBoardId, MediaUserActionType.REVIEW);
        if(conceptBoardUserActions != null && conceptBoardUserActions.size() > 0){
            Set<Long> subjectIds = new HashSet<>();
            for (ConceptBoardUserAction conceptBoardUserAction: conceptBoardUserActions) {
                subjectIds.add(conceptBoardUserAction.getCreatedBy());
            }

            Map<Long, UserProfileMiniDto> map = userProfileService.getUserProfileForSubjectIds(subjectIds);
            for (ConceptBoardUserAction conceptBoardUserAction: conceptBoardUserActions) {
                Boolean isLikedByMe = conceptBoardUserAction.getCreatedBy().equals(subjectId);
                ConceptBoardReview conceptBoardReview = new ConceptBoardReview(isLikedByMe,map.get(conceptBoardUserAction.getCreatedBy()));
                conceptBoardReview.convertConceptBoardUserActionToConceptBoardReview(conceptBoardUserAction);
                conceptBoardReviews.add(conceptBoardReview);
            }

        }
        return conceptBoardReviews;
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
    public ConceptBoardQuestion answerQuestion(String questionId, String answer, Long subjectId) {
        ConceptBoardUserAction conceptBoardUserAction = findById(questionId);
        conceptBoardUserAction.setAnswer(answer);
        conceptBoardUserAction.setAnsweredBy(subjectId);
        conceptBoardUserAction.preUpdate(subjectId);
        conceptBoardUserAction = conceptBoardUserActionRepository.save(conceptBoardUserAction);

        ConceptBoardQuestion conceptBoardQuestion = new ConceptBoardQuestion();
        conceptBoardQuestion.convertConceptBoardUserACtionToConceptBoardQuestion(conceptBoardUserAction);
        return conceptBoardQuestion;
    }


    /**
     * this service method is used to get ConceptBoardMediaUserAction by Id
     *
     * @param id
     * @return
     */
    @Override
    public ConceptBoardUserAction findById(String id) {
        Optional<ConceptBoardUserAction> conceptBoardUserActionOptional = conceptBoardUserActionRepository.findById(id);
        if(conceptBoardUserActionOptional.isPresent()){
            return conceptBoardUserActionOptional.get();
        }
        throw new ConceptBoardUserActionNotFoundException();
    }

    /**
     * This service method is used to check if Media is like by a subject or not
     *
     * @param conceptBoardId
     * @param subjectId
     * @return
     */
    @Override
    public Boolean isConceptBoardLikedByMe(String conceptBoardId, Long subjectId) {
        ConceptBoardUserAction conceptBoardUserAction = conceptBoardUserActionRepository.findFirstByConceptBoardIdAndMediaUserActionTypeAndCreatedBy(conceptBoardId, MediaUserActionType.LIKE,subjectId);
        if(conceptBoardUserAction == null){
            return false;
        }
        return true;
    }
}
