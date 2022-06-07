package com.spacifii.konstruct.explore.service.explore;

import com.spacifii.konstruct.explore.entities.explore.ProjectUserAction;
import com.spacifii.konstruct.explore.model.dto.explore.MediaUserActionDTO;

/**
 * This is strategy interface for ProjectUserAction
 */
public interface SaveProjectUserActionStrategy {

    ProjectUserAction save(MediaUserActionDTO mediaUserActionDTO, Long subjectId);

    ProjectUserAction edit(ProjectUserAction projectUserAction,MediaUserActionDTO mediaUserActionDTO,Long subjectId);

    Boolean deleteAction(ProjectUserAction projectUserAction, Long subjectId);
}
