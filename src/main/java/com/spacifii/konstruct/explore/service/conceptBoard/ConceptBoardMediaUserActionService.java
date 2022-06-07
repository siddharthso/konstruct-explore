package com.spacifii.konstruct.explore.service.conceptBoard;

import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardMediaUserAction;
import com.spacifii.konstruct.explore.model.dto.conceptBoard.ConceptBoardMediaCommentNode;
import com.spacifii.konstruct.explore.model.dto.conceptBoard.ConceptBoardMediaQuestion;
import com.spacifii.konstruct.explore.model.dto.conceptBoard.ConceptBoardMediaReview;
import com.spacifii.konstruct.explore.model.dto.explore.MediaUserActionDTO;

import java.util.List;

/**
 * This is service class for ConceptBoardMediaUserAction
 */
public interface ConceptBoardMediaUserActionService {

    /**
     * This service method saves ConceptBoardMediaUserAction
     * @param conceptBoardMediaUserAction
     * @param subjectId
     * @return
     */
    ConceptBoardMediaUserAction save(ConceptBoardMediaUserAction conceptBoardMediaUserAction, Long subjectId);


    /**
     * This service method is used to do updates for ConceptBoardMediaUserAction. Just in case someone has to change comments.
     * You cannot change your ratings
     * @param mediaUserActionDTO
     * @param id
     * @param subjectId
     * @return
     */
    ConceptBoardMediaUserAction update(MediaUserActionDTO mediaUserActionDTO, String id, Long subjectId);

    /**
     * This service method is used to get All mediaComments
     * @param mediaId
     * @return
     */
    List<ConceptBoardMediaCommentNode> getAllMediaComments(String mediaId, Long subjectId);

    /**
     * This servie method is used to get all MediaQuestions
     * @param mediaId
     * @return
     */
    List<ConceptBoardMediaQuestion> getAllMediaQuestions(String mediaId, Long subjectId);

    /**
     * This service method is used to get all media reviews
     * @param mediaId
     * @return
     */
    List<ConceptBoardMediaReview> getAllMediaReviews(String mediaId, Long subjectId);

    /**
     * This service method is used to answer a question
     * @param questionId
     * @param answer
     * @param subjectId
     * @return
     */
    ConceptBoardMediaQuestion answerQuestion(String questionId, String answer, Long subjectId);

    /**
     * this service method is used to get ConceptBoardMediaUserAction by Id
     * @param id
     * @return
     */
    ConceptBoardMediaUserAction findById(String id);

    /**
     * This service method is used to check if Media is like by a subject or not
     * @param mediaId
     * @param subjectId
     * @return
     */
    Boolean isMediaLikedByMe(String mediaId, Long subjectId);
}
