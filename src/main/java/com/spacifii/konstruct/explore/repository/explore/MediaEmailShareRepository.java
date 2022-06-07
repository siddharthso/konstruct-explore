package com.spacifii.konstruct.explore.repository.explore;

import com.spacifii.konstruct.explore.entities.explore.MediaEmailShare;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * This is DAO class for MediaEmailShare
 */
@Repository
public interface MediaEmailShareRepository extends MongoRepository<MediaEmailShare,String> {
}
