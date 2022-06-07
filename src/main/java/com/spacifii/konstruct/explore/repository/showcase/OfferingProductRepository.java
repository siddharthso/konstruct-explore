package com.spacifii.konstruct.explore.repository.showcase;

import com.spacifii.konstruct.explore.entities.showcases.OfferingProduct;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * This is DAO class for Offering Product
 */
@Repository
public interface OfferingProductRepository extends MongoRepository<OfferingProduct,String> {


}
