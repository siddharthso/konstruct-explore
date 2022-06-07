package com.spacifii.konstruct.explore.repository.showcase;

import com.spacifii.konstruct.explore.entities.showcases.ContainerMediaViewType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * This is DAO class for ContainerMediaViewType
 */
@Repository
public interface ContainerMediaViewTypeRepository extends MongoRepository<ContainerMediaViewType,String> {
}
