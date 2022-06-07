package com.spacifii.konstruct.explore.service.conceptBoard.impl;

import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardMediaUserAction;
import com.spacifii.konstruct.explore.entities.explore.MediaUserActionType;
import com.spacifii.konstruct.explore.exception.conceptBoard.ConceptBoardMediaUserActionNotFoundException;
import com.spacifii.konstruct.explore.model.dto.conceptBoard.ConceptBoardMediaComment;
import com.spacifii.konstruct.explore.model.dto.conceptBoard.ConceptBoardMediaCommentNode;
import com.spacifii.konstruct.explore.model.dto.conceptBoard.ConceptBoardMediaQuestion;
import com.spacifii.konstruct.explore.model.dto.conceptBoard.ConceptBoardMediaReview;
import com.spacifii.konstruct.explore.model.dto.explore.MediaUserActionDTO;
import com.spacifii.konstruct.explore.model.dto.explore.UserProfileMiniDto;
import com.spacifii.konstruct.explore.repository.conceptBoard.ConceptBoardMediaUserActionRepository;
import com.spacifii.konstruct.explore.service.conceptBoard.ConceptBoardMediaUserActionService;
import com.spacifii.konstruct.explore.service.explore.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ConceptBoardMediaUserActionServiceImpl implements ConceptBoardMediaUserActionService {

    @Autowired
    ConceptBoardMediaUserActionRepository conceptBoardMediaUserActionRepository;

    @Autowired
    UserProfileService userProfileService;

    /**
     * This service method saves ConceptBoardMediaUserAction
     *
     * @param conceptBoardMediaUserAction
     * @param subjectId
     * @return
     */
    @Override
    public ConceptBoardMediaUserAction save(ConceptBoardMediaUserAction conceptBoardMediaUserAction, Long subjectId) {
        if(conceptBoardMediaUserAction.getCreationDate() == null) {
            conceptBoardMediaUserAction.preSave(subjectId);
        } else {
            conceptBoardMediaUserAction.preUpdate(subjectId);
        }
        return conceptBoardMediaUserActionRepository.save(conceptBoardMediaUserAction);
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
    public ConceptBoardMediaUserAction update(MediaUserActionDTO mediaUserActionDTO, String id, Long subjectId) {
        ConceptBoardMediaUserAction conceptBoardMediaUserAction = findById(id);
        conceptBoardMediaUserAction.convertMediaUserActionDTOToMe(mediaUserActionDTO);
        conceptBoardMediaUserAction.preUpdate(subjectId);
        return conceptBoardMediaUserActionRepository.save(conceptBoardMediaUserAction);
    }

    /**
     * This service method is used to get All mediaComments
     *
     * @param mediaId
     * @param subjectId
     * @return
     */
    @Override
    public List<ConceptBoardMediaCommentNode> getAllMediaComments(String mediaId, Long subjectId) {
        List<ConceptBoardMediaUserAction> conceptBoardMediaUserActions = conceptBoardMediaUserActionRepository.findByMediaIdAndMediaUserActionType(mediaId, MediaUserActionType.COMMENT);

        Map<String,ConceptBoardMediaUserAction> idMap = new HashMap<>();
        Map<String,List<ConceptBoardMediaUserAction>> childMap = new HashMap<>();
        List<ConceptBoardMediaUserAction> parentList = new ArrayList<>();
        Set<Long> subjectIds = new HashSet<>();
        if(conceptBoardMediaUserActions != null && conceptBoardMediaUserActions.size() > 0) {
            for (ConceptBoardMediaUserAction conceptBoardMediaUserAction : conceptBoardMediaUserActions) {
                idMap.put(conceptBoardMediaUserAction.getId(), conceptBoardMediaUserAction);
                if (conceptBoardMediaUserAction.getParentCommentId() == null) {
                    parentList.add(conceptBoardMediaUserAction);
                } else {
                    List<ConceptBoardMediaUserAction> childs = childMap.get(conceptBoardMediaUserAction.getParentCommentId());
                    if (childs == null) {
                        childs = new ArrayList<>();
                    }
                    childs.add(conceptBoardMediaUserAction);
                    childMap.put(conceptBoardMediaUserAction.getParentCommentId(), childs);
                }
                subjectIds.add(conceptBoardMediaUserAction.getCreatedBy());
            }


            List<ConceptBoardMediaCommentNode> conceptBoardCommentNodes = new ArrayList<>();

            Map<Long, UserProfileMiniDto> map = userProfileService.getUserProfileForSubjectIds(subjectIds);

            for (ConceptBoardMediaUserAction conceptBoardMediaUserAction : parentList) {
                Boolean isLikedByMe = conceptBoardMediaUserAction.getCreatedBy().equals(subjectId);
                ConceptBoardMediaComment conceptBoardMediaComment = new ConceptBoardMediaComment(isLikedByMe, map.get(conceptBoardMediaUserAction.getCreatedBy()));
                conceptBoardMediaComment.convertConceptBoardMediaUserActionToConceptBoardMediaComment(conceptBoardMediaUserAction);
                ConceptBoardMediaCommentNode conceptBoardMediaCommentNode = new ConceptBoardMediaCommentNode();
                conceptBoardMediaCommentNode.setMediaComment(conceptBoardMediaComment);
                List<ConceptBoardMediaCommentNode> childrenNodes = collectChildren(conceptBoardMediaUserAction.getId(), childMap, map, subjectId);
                conceptBoardMediaCommentNode.setChildrenNodes(childrenNodes);
                conceptBoardCommentNodes.add(conceptBoardMediaCommentNode);
            }


            return conceptBoardCommentNodes;
        }
        return null;
    }

    private List<ConceptBoardMediaCommentNode> collectChildren(String id, Map<String, List<ConceptBoardMediaUserAction>> parentMap, Map<Long, UserProfileMiniDto> map, Long subjectId) {
        /**
         * TODO: Check Visited to Avoid infinite Recursion
         */
        List<ConceptBoardMediaCommentNode> conceptBoardCommentNodes = new ArrayList<>();

        if(parentMap.get(id) == null){

        } else {
            for (ConceptBoardMediaUserAction conceptBoardMediaUserAction : parentMap.get(id)) {
                Boolean isLikedByMe = conceptBoardMediaUserAction.getCreatedBy().equals(subjectId);
                ConceptBoardMediaComment conceptBoardMediaComment = new ConceptBoardMediaComment(isLikedByMe, map.get(conceptBoardMediaUserAction.getCreatedBy()));
                conceptBoardMediaComment.convertConceptBoardMediaUserActionToConceptBoardMediaComment(conceptBoardMediaUserAction);
                ConceptBoardMediaCommentNode conceptBoardMediaCommentNode = new ConceptBoardMediaCommentNode();
                conceptBoardMediaCommentNode.setMediaComment(conceptBoardMediaComment);
                List<ConceptBoardMediaCommentNode> childrenNodes = collectChildren(conceptBoardMediaUserAction.getId(), parentMap, map, subjectId);
                conceptBoardMediaCommentNode.setChildrenNodes(childrenNodes);
                conceptBoardCommentNodes.add(conceptBoardMediaCommentNode);
            }
        }

        return conceptBoardCommentNodes;
    }

    /**
     * This servie method is used to get all MediaQuestions
     *
     * @param mediaId
     * @param subjectId
     * @return
     */
    @Override
    public List<ConceptBoardMediaQuestion> getAllMediaQuestions(String mediaId, Long subjectId) {
        List<ConceptBoardMediaQuestion> conceptBoardMediaQuestions = new ArrayList<>();
        List<ConceptBoardMediaUserAction> conceptBoardMediaUserActions =
                conceptBoardMediaUserActionRepository.findByMediaIdAndMediaUserActionType(mediaId, MediaUserActionType.QUESTION);
        if(conceptBoardMediaUserActions != null && conceptBoardMediaUserActions.size() > 0) {
            Set<Long> subjectIds = new HashSet<>();
            for (ConceptBoardMediaUserAction conceptBoardMediaUserAction : conceptBoardMediaUserActions) {
                subjectIds.add(conceptBoardMediaUserAction.getCreatedBy());
                if (conceptBoardMediaUserAction.getAnsweredBy() != null) {
                    subjectIds.add(conceptBoardMediaUserAction.getAnsweredBy());
                }
            }

            Map<Long, UserProfileMiniDto> map = userProfileService.getUserProfileForSubjectIds(subjectIds);
            for (ConceptBoardMediaUserAction conceptBoardMediaUserAction : conceptBoardMediaUserActions) {
                Boolean isLikedByMe = conceptBoardMediaUserAction.getCreatedBy().equals(subjectId);
                ConceptBoardMediaQuestion conceptBoardQuestion = new ConceptBoardMediaQuestion
                        (isLikedByMe,map.get(conceptBoardMediaUserAction.getCreatedBy()),map.get(conceptBoardMediaUserAction.getAnsweredBy()));
                conceptBoardQuestion.convertConceptBoardMediaUserACtionToConceptBoardMediaQuestion(conceptBoardMediaUserAction);
                conceptBoardMediaQuestions.add(conceptBoardQuestion);
            }
        }

        return conceptBoardMediaQuestions;
    }

    /**
     * This service method is used to get all media reviews
     *
     * @param mediaId
     * @param subjectId
     * @return
     */
    @Override
    public List<ConceptBoardMediaReview> getAllMediaReviews(String mediaId, Long subjectId) {
        List<ConceptBoardMediaReview> conceptBoardMediaReviews = new ArrayList<>();
        List<ConceptBoardMediaUserAction> conceptBoardMediaUserActions =
                conceptBoardMediaUserActionRepository.findByMediaIdAndMediaUserActionType(mediaId, MediaUserActionType.REVIEW);
        if(conceptBoardMediaUserActions != null && conceptBoardMediaUserActions.size() > 0){
            Set<Long> subjectIds = new HashSet<>();
            for (ConceptBoardMediaUserAction conceptBoardMediaUserAction: conceptBoardMediaUserActions) {
                subjectIds.add(conceptBoardMediaUserAction.getCreatedBy());
            }

            Map<Long, UserProfileMiniDto> map = userProfileService.getUserProfileForSubjectIds(subjectIds);
            for (ConceptBoardMediaUserAction conceptBoardMediaUserAction: conceptBoardMediaUserActions) {
                Boolean isLikedByMe = conceptBoardMediaUserAction.getCreatedBy().equals(subjectId);
                ConceptBoardMediaReview conceptBoardMediaReview = new ConceptBoardMediaReview(isLikedByMe,map.get(conceptBoardMediaUserAction.getCreatedBy()));
                conceptBoardMediaReview.convertMediaUserActionToMediaReview(conceptBoardMediaUserAction);
                conceptBoardMediaReviews.add(conceptBoardMediaReview);
            }

        }
        return conceptBoardMediaReviews;
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
    public ConceptBoardMediaQuestion answerQuestion(String questionId, String answer, Long subjectId) {
        ConceptBoardMediaUserAction conceptBoardMediaUserAction = findById(questionId);
        conceptBoardMediaUserAction.setAnswer(answer);
        conceptBoardMediaUserAction.setAnsweredBy(subjectId);
        conceptBoardMediaUserAction.preUpdate(subjectId);
        conceptBoardMediaUserAction = conceptBoardMediaUserActionRepository.save(conceptBoardMediaUserAction);

        ConceptBoardMediaQuestion conceptBoardMediaQuestion = new ConceptBoardMediaQuestion();
        conceptBoardMediaQuestion.convertConceptBoardMediaUserACtionToConceptBoardMediaQuestion(conceptBoardMediaUserAction);
        return conceptBoardMediaQuestion;
    }

    /**
     * this service method is used to get ConceptBoardMediaUserAction by Id
     *
     * @param id
     * @return
     */
    @Override
    public ConceptBoardMediaUserAction findById(String id) {
        Optional<ConceptBoardMediaUserAction> conceptBoardMediaUserActionOptional = conceptBoardMediaUserActionRepository.findById(id);
        if(conceptBoardMediaUserActionOptional.isPresent()){
            return conceptBoardMediaUserActionOptional.get();
        }
        throw new ConceptBoardMediaUserActionNotFoundException();
    }

    /**
     * This service method is used to check if Media is like by a subject or not
     *
     * @param mediaId
     * @param subjectId
     * @return
     */
    @Override
    public Boolean isMediaLikedByMe(String mediaId, Long subjectId) {
        ConceptBoardMediaUserAction conceptBoardMediaUserAction = conceptBoardMediaUserActionRepository.findFirstByMediaIdAndMediaUserActionTypeAndCreatedBy(mediaId, MediaUserActionType.LIKE,subjectId);
        if(conceptBoardMediaUserAction == null){
            return false;
        }
        return true;
    }
}
