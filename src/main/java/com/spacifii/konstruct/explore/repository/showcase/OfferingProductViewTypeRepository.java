package com.spacifii.konstruct.explore.repository.showcase;

import com.spacifii.konstruct.explore.entities.showcases.OfferingProductViewType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * This is DAO class for OfferingProductViewType
 */
@Repository
public interface OfferingProductViewTypeRepository extends MongoRepository<OfferingProductViewType,String> {
}
