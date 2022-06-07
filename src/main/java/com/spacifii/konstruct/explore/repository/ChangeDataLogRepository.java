package com.spacifii.konstruct.explore.repository;

import com.spacifii.konstruct.explore.entities.ChangeDataLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * This is DAO class for ChangeDataLog
 */
@Repository
public interface ChangeDataLogRepository  extends MongoRepository<ChangeDataLog,String> {
}
