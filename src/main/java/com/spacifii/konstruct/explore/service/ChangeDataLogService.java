package com.spacifii.konstruct.explore.service;

import com.spacifii.konstruct.explore.entities.ChangeDataLog;

/**
 * This is service class for ChangeDataLog
 */
public interface ChangeDataLogService {

    /**
     * This service method is used to save ChangeDataLog
     * @param changeDataLog
     * @param subjectId
     * @return
     */
    Boolean save(ChangeDataLog changeDataLog, Long subjectId);
}
