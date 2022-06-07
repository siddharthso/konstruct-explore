package com.spacifii.konstruct.explore.service.conceptBoard;

import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardUserAction;
import com.spacifii.konstruct.explore.model.dto.explore.MediaUserActionDTO;

/**
 * This is strategy interface for ConceptBoardMediaUserAction
 */
public interface SaveConceptBoardUserActionStrategy {

    ConceptBoardUserAction save(MediaUserActionDTO mediaUserActionDTO, Long subjectId);

    ConceptBoardUserAction update(ConceptBoardUserAction conceptBoardUserAction, MediaUserActionDTO mediaUserActionDTO, Long subjectId);

    Boolean delete(ConceptBoardUserAction conceptBoardUserAction, Long subjectId);
}
