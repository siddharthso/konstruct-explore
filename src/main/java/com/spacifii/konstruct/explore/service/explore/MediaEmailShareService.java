package com.spacifii.konstruct.explore.service.explore;

import com.spacifii.konstruct.explore.entities.explore.MediaEmailShare;

/**
 * This service class manages MediaEmailShare
 */
public interface MediaEmailShareService {

    /**
     * This service method is used to Share Media via Email
     * @param mediaEmailShare
     * @param subjectId
     * @return
     */
    Boolean shareMedia(MediaEmailShare mediaEmailShare, Long subjectId);
}
