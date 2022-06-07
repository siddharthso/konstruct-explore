package com.spacifii.konstruct.explore.service.impl;

import com.spacifii.konstruct.explore.entities.ChangeDataLog;
import com.spacifii.konstruct.explore.repository.ChangeDataLogRepository;
import com.spacifii.konstruct.explore.service.ChangeDataLogService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This is service class for ChangeDataLog
 */
public class ChangeDataLogServiceImpl implements ChangeDataLogService {

    @Autowired
    ChangeDataLogRepository changeDataLogRepository;

    /**
     * This service method is used to save ChangeDataLog
     *
     * @param changeDataLog
     * @param subjectId
     * @return
     */
    @Override
    public Boolean save(ChangeDataLog changeDataLog, Long subjectId) {
        changeDataLog.preSave(subjectId);
        changeDataLogRepository.save(changeDataLog);
        return true;
    }
}
