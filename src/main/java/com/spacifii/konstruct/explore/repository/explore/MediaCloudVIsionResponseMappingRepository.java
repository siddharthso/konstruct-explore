package com.spacifii.konstruct.explore.repository.explore;

import com.spacifii.konstruct.explore.entities.explore.MediaCloudVIsionResponseMapping;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * This is DAO class for MediaCloudVIsionResponseMapping
 */
@Repository
public interface MediaCloudVIsionResponseMappingRepository  extends MongoRepository<MediaCloudVIsionResponseMapping,String> {

}
