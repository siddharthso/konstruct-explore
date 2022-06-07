package com.spacifii.konstruct.explore.repository.showcase;

import com.spacifii.konstruct.explore.entities.showcases.Material;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * This is DAO class for Material
 */
@Repository
public interface MaterialRepository extends MongoRepository<Material,String> {
}
