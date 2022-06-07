package com.spacifii.konstruct.explore.repository.showcase;

import com.spacifii.konstruct.explore.entities.showcases.ContainerMedia;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * This is DAO class for ContainerMedia
 */
@Repository
public interface ContainerMediaRepository extends MongoRepository<ContainerMedia,String> {
}
