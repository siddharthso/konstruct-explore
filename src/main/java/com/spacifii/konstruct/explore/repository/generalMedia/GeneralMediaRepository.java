package com.spacifii.konstruct.explore.repository.generalMedia;

import com.spacifii.konstruct.explore.entities.generalMedia.GeneralMedia;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * This is DAO class for GeneralMedia
 */
@Repository
public interface GeneralMediaRepository extends MongoRepository<GeneralMedia,String> {
}
