package com.spacifii.konstruct.explore.service.conceptBoard;

import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardMediaUserAction;
import com.spacifii.konstruct.explore.model.dto.explore.MediaUserActionDTO;

/**
 * This is strategy interface for ConceptBoardMediaUserAction
 */
public interface SaveConceptBoardMediaUserActionStrategy {

    ConceptBoardMediaUserAction save(MediaUserActionDTO mediaUserActionDTO, Long subjectId);

    ConceptBoardMediaUserAction update(ConceptBoardMediaUserAction conceptBoardMediaUserAction,MediaUserActionDTO mediaUserActionDTO, Long subjectId);

    Boolean delete(ConceptBoardMediaUserAction conceptBoardMediaUserAction,Long subjectId);
}
