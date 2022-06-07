package com.spacifii.konstruct.explore.service.explore;

import com.spacifii.konstruct.explore.entities.explore.MediaUserAction;
import com.spacifii.konstruct.explore.model.dto.explore.MediaCommentNode;
import com.spacifii.konstruct.explore.model.dto.explore.MediaQuestion;
import com.spacifii.konstruct.explore.model.dto.explore.MediaReview;
import com.spacifii.konstruct.explore.model.dto.explore.MediaUserActionDTO;

import java.util.List;

/**
 * This is service class for ConceptBoardMediaUserAction
 */
public interface MediaUserActionService {

    /**
     * This service method saves ConceptBoardMediaUserAction
     * @param mediaUserAction
     * @param subjectId
     * @return
     */
    MediaUserAction save(MediaUserAction mediaUserAction, Long subjectId);



    /**
     * This service method is used to do updates for ConceptBoardMediaUserAction. Just in case someone has to change comments.
     * You cannot change your ratings
     * @param mediaUserActionDTO
     * @param id
     * @param subjectId
     * @return
     */
    MediaUserAction update(MediaUserActionDTO mediaUserActionDTO, String id, Long subjectId);

    /**
     * This service method is used to get All mediaComments
     * @param mediaId
     * @return
     */
    List<MediaCommentNode> getAllMediaComments(String mediaId, Long subjectId);

    /**
     * This servie method is used to get all MediaQuestions
     * @param mediaId
     * @return
     */
    List<MediaQuestion> getAllMediaQuestions(String mediaId, Long subjectId);

    /**
     * This service method is used to get all media reviews
     * @param mediaId
     * @return
     */
    List<MediaReview> getAllMediaReviews(String mediaId, Long subjectId);

    /**
     * This service method is used to answer a question
     * @param questionId
     * @param answer
     * @param subjectId
     * @return
     */
    MediaQuestion answerQuestion(String questionId, String answer, Long subjectId);

    /**
     * this service method is used to get ConceptBoardMediaUserAction by Id
     * @param id
     * @return
     */
    MediaUserAction findById(String id);

    /**
     * This service method is used to check if Media is like by a subject or not
     * @param mediaId
     * @param subjectId
     * @return
     */
    Boolean isMediaLikedByMe(String mediaId, Long subjectId);
}
