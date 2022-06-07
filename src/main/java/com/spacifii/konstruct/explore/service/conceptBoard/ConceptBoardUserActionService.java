package com.spacifii.konstruct.explore.service.conceptBoard;

import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardUserAction;
import com.spacifii.konstruct.explore.model.dto.conceptBoard.ConceptBoardCommentNode;
import com.spacifii.konstruct.explore.model.dto.conceptBoard.ConceptBoardQuestion;
import com.spacifii.konstruct.explore.model.dto.conceptBoard.ConceptBoardReview;
import com.spacifii.konstruct.explore.model.dto.explore.MediaUserActionDTO;

import java.util.List;

/**
 * This is service class for ConceptBoardUserAction
 */
public interface ConceptBoardUserActionService {

    /**
     * This service method saves ConceptBoardMediaUserAction
     * @param conceptBoardUserAction
     * @param subjectId
     * @return
     */
    ConceptBoardUserAction save(ConceptBoardUserAction conceptBoardUserAction, Long subjectId);



    /**
     * This service method is used to do updates for ConceptBoardMediaUserAction. Just in case someone has to change comments.
     * You cannot change your ratings
     * @param mediaUserActionDTO
     * @param id
     * @param subjectId
     * @return
     */
    ConceptBoardUserAction update(MediaUserActionDTO mediaUserActionDTO, String id, Long subjectId);

    /**
     * This service method is used to get All mediaComments
     * @param conceptBoardId
     * @return
     */
    List<ConceptBoardCommentNode> getAllConceptBoardComments(String conceptBoardId, Long subjectId);

    /**
     * This servie method is used to get all ConceptBoardQuestions
     * @param conceptBoardId
     * @return
     */
    List<ConceptBoardQuestion> getAllConceptBoardQuestions(String conceptBoardId, Long subjectId);

    /**
     * This service method is used to get all conceptBoard reviews
     * @param conceptBoardId
     * @return
     */
    List<ConceptBoardReview> getAllConceptBoardReviews(String conceptBoardId, Long subjectId);

    /**
     * This service method is used to answer a question
     * @param questionId
     * @param answer
     * @param subjectId
     * @return
     */
    ConceptBoardQuestion answerQuestion(String questionId, String answer, Long subjectId);

    /**
     * this service method is used to get ConceptBoardMediaUserAction by Id
     * @param id
     * @return
     */
    ConceptBoardUserAction findById(String id);

    /**
     * This service method is used to check if Media is like by a subject or not
     * @param conceptBoardId
     * @param subjectId
     * @return
     */
    Boolean isConceptBoardLikedByMe(String conceptBoardId, Long subjectId);
}
