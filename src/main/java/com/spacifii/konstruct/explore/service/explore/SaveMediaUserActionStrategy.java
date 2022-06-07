package com.spacifii.konstruct.explore.service.explore;

import com.spacifii.konstruct.explore.entities.explore.MediaUserAction;
import com.spacifii.konstruct.explore.model.dto.explore.MediaUserActionDTO;

/**
 * This is strategy interface for ConceptBoardMediaUserAction
 */
public interface SaveMediaUserActionStrategy {

    MediaUserAction save(MediaUserActionDTO mediaUserActionDTO, Long subjectId);

    MediaUserAction edit(MediaUserAction mediaUserAction,MediaUserActionDTO mediaUserActionDTO,Long subjectId);

    Boolean deleteAction(MediaUserAction mediaUserAction, Long subjectId);
}
