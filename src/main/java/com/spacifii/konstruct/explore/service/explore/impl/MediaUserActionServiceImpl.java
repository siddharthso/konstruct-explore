package com.spacifii.konstruct.explore.service.explore.impl;

import com.spacifii.konstruct.explore.entities.explore.MediaUserAction;
import com.spacifii.konstruct.explore.entities.explore.MediaUserActionType;
import com.spacifii.konstruct.explore.exception.explore.MediaUserActionNotFoundException;
import com.spacifii.konstruct.explore.model.dto.explore.*;
import com.spacifii.konstruct.explore.repository.explore.MediaUserActionRepository;
import com.spacifii.konstruct.explore.service.explore.MediaUserActionService;
import com.spacifii.konstruct.explore.service.explore.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * This is service class for ConceptBoardMediaUserAction
 */
@Service
public class MediaUserActionServiceImpl implements MediaUserActionService {

    @Autowired
    MediaUserActionRepository mediaUserActionRepository;

    @Autowired
    UserProfileService userProfileService;

    /**
     * This service method saves ConceptBoardMediaUserAction
     * @param mediaUserAction
     * @param subjectId
     * @return
     */
    @Override
    @Transactional
    public MediaUserAction save(MediaUserAction mediaUserAction, Long subjectId) {
        if(mediaUserAction.getCreationDate() == null) {
            mediaUserAction.preSave(subjectId);
        } else {
            mediaUserAction.preUpdate(subjectId);
        }
        return mediaUserActionRepository.save(mediaUserAction);
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
    public MediaUserAction update(MediaUserActionDTO mediaUserActionDTO, String id, Long subjectId) {
        MediaUserAction mediaUserAction = findById(id);
        mediaUserAction.convertMediaUserActionDTOToMe(mediaUserActionDTO);
        mediaUserAction.preUpdate(subjectId);
        return mediaUserActionRepository.save(mediaUserAction);
    }

    /**
     * This service method is used to get All mediaComments
     *
     * @param mediaId
     * @param subjectId
     * @return
     */
    @Override
    public List<MediaCommentNode> getAllMediaComments(String mediaId, Long subjectId) {
        List<MediaUserAction> mediaUserActions = mediaUserActionRepository.findByMediaIdAndMediaUserActionType(mediaId,MediaUserActionType.COMMENT);

        Map<String,MediaUserAction> idMap = new HashMap<>();
        Map<String,List<MediaUserAction>> childMap = new HashMap<>();
        List<MediaUserAction> parentList = new ArrayList<>();
        Set<Long> subjectIds = new HashSet<>();
        if(mediaUserActions != null && mediaUserActions.size() > 0) {
            for (MediaUserAction mediaUserAction : mediaUserActions) {
                idMap.put(mediaUserAction.getId(), mediaUserAction);
                if (mediaUserAction.getParentCommentId() == null) {
                    parentList.add(mediaUserAction);
                } else {
                    List<MediaUserAction> childs = childMap.get(mediaUserAction.getParentCommentId());
                    if (childs == null) {
                        childs = new ArrayList<>();
                    }
                    childs.add(mediaUserAction);
                    childMap.put(mediaUserAction.getParentCommentId(), childs);
                }
                subjectIds.add(mediaUserAction.getCreatedBy());
            }


            List<MediaCommentNode> mediaCommentNodes = new ArrayList<>();

            Map<Long, UserProfileMiniDto> map = userProfileService.getUserProfileForSubjectIds(subjectIds);

            for (MediaUserAction mediaUserAction : parentList) {
                Boolean isLikedByMe = mediaUserAction.getCreatedBy().equals(subjectId);
                MediaComment mediaComment = new MediaComment(isLikedByMe, map.get(mediaUserAction.getCreatedBy()));
                mediaComment.convertProjectUserActionToProjectComment(mediaUserAction);
                MediaCommentNode mediaCommentNode = new MediaCommentNode();
                mediaCommentNode.setMediaComment(mediaComment);
                List<MediaCommentNode> childrenNodes = collectChildren(mediaUserAction.getId(), childMap, map, subjectId);
                mediaCommentNode.setChildrenNodes(childrenNodes);
                mediaCommentNodes.add(mediaCommentNode);
            }


            return mediaCommentNodes;
        }
        return null;
    }

    private List<MediaCommentNode> collectChildren(String id, Map<String, List<MediaUserAction>> parentMap, Map<Long, UserProfileMiniDto> map, Long subjectId) {
        /**
         * TODO: Check Visited to Avoid infinite Recursion
         */
        List<MediaCommentNode> mediaCommentNodes = new ArrayList<>();

        if(parentMap.get(id) == null){

        } else {
            for (MediaUserAction mediaUserAction: parentMap.get(id)) {
                Boolean isLikedByMe = mediaUserAction.getCreatedBy().equals(subjectId);
                MediaComment mediaComment = new MediaComment(isLikedByMe,map.get(mediaUserAction.getCreatedBy()));
                mediaComment.convertProjectUserActionToProjectComment(mediaUserAction);
                MediaCommentNode mediaCommentNode = new MediaCommentNode();
                mediaCommentNode.setMediaComment(mediaComment);
                List<MediaCommentNode> childrenNodes = collectChildren(mediaUserAction.getId(),parentMap,map,subjectId);
                mediaCommentNode.setChildrenNodes(childrenNodes);
                mediaCommentNodes.add(mediaCommentNode);
            }
        }

        return mediaCommentNodes;
    }

    /**
     * This servie method is used to get all MediaQuestions
     *
     * @param mediaId
     * @param subjectId
     * @return
     */
    @Override
    public List<MediaQuestion> getAllMediaQuestions(String mediaId, Long subjectId) {
        List<MediaQuestion> mediaQuestions = new ArrayList<>();
        List<MediaUserAction> mediaUserActions =
                mediaUserActionRepository.findByMediaIdAndMediaUserActionType(mediaId, MediaUserActionType.QUESTION);
        if(mediaUserActions != null && mediaUserActions.size() > 0) {
            Set<Long> subjectIds = new HashSet<>();
            for (MediaUserAction mediaUserAction : mediaUserActions) {
                subjectIds.add(mediaUserAction.getCreatedBy());
                if (mediaUserAction.getAnsweredBy() != null) {
                    subjectIds.add(mediaUserAction.getAnsweredBy());
                }
            }

            Map<Long, UserProfileMiniDto> map = userProfileService.getUserProfileForSubjectIds(subjectIds);
            for (MediaUserAction mediaUserAction : mediaUserActions) {
                Boolean isLikedByMe = mediaUserAction.getCreatedBy().equals(subjectId);
                MediaQuestion mediaQuestion = new MediaQuestion
                        (isLikedByMe,map.get(mediaUserAction.getCreatedBy()),map.get(mediaUserAction.getAnsweredBy()));
                mediaQuestion.convertMediaUserACtionToMediaQuestion(mediaUserAction);
                mediaQuestions.add(mediaQuestion);
            }
        }

        return mediaQuestions;
    }

    /**
     * This service method is used to get all media reviews
     *
     * @param mediaId
     * @param subjectId
     * @return
     */
    @Override
    public List<MediaReview> getAllMediaReviews(String mediaId, Long subjectId) {
        List<MediaReview> mediaReviews = new ArrayList<>();
        List<MediaUserAction> mediaUserActions =
                mediaUserActionRepository.findByMediaIdAndMediaUserActionType(mediaId, MediaUserActionType.REVIEW);
        if(mediaUserActions != null && mediaUserActions.size() > 0){
            Set<Long> subjectIds = new HashSet<>();
            for (MediaUserAction mediaUserAction: mediaUserActions) {
                subjectIds.add(mediaUserAction.getCreatedBy());
            }

            Map<Long,UserProfileMiniDto> map = userProfileService.getUserProfileForSubjectIds(subjectIds);
            for (MediaUserAction mediaUserAction: mediaUserActions) {
                Boolean isLikedByMe = mediaUserAction.getCreatedBy().equals(subjectId);
                MediaReview mediaReview = new MediaReview(isLikedByMe,map.get(mediaUserAction.getCreatedBy()));
                mediaReview.convertMediaUserActionToMediaReview(mediaUserAction);
                mediaReviews.add(mediaReview);
            }

        }
        return mediaReviews;
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
    public MediaQuestion answerQuestion(String questionId, String answer, Long subjectId) {
        MediaUserAction mediaUserAction = findById(questionId);
        mediaUserAction.setAnswer(answer);
        mediaUserAction.setAnsweredBy(subjectId);
        mediaUserAction.preUpdate(subjectId);
        mediaUserAction = mediaUserActionRepository.save(mediaUserAction);

        MediaQuestion mediaQuestion = new MediaQuestion();
        mediaQuestion.convertMediaUserACtionToMediaQuestion(mediaUserAction);
        return mediaQuestion;
    }

    /**
     * this service method is used to get ConceptBoardMediaUserAction by Id
     *
     * @param id
     * @return
     */
    @Override
    public MediaUserAction findById(String id) {
        Optional<MediaUserAction> mediaUserActionOptional = mediaUserActionRepository.findById(id);
        if(mediaUserActionOptional.isPresent()){
            return mediaUserActionOptional.get();
        }
        throw new MediaUserActionNotFoundException();
    }

    /**
     * This service method is used to check if Media is like by a subject or not
     * @param mediaId
     * @param subjectId
     * @return
     */
    @Override
    public Boolean isMediaLikedByMe(String mediaId, Long subjectId) {
        MediaUserAction mediaUserAction = mediaUserActionRepository.findFirstByMediaIdAndMediaUserActionTypeAndCreatedBy(mediaId,MediaUserActionType.LIKE,subjectId);
        if(mediaUserAction == null){
            return false;
        }
        return true;
    }
}


