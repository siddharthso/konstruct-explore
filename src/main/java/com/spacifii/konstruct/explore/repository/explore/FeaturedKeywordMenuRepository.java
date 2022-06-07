package com.spacifii.konstruct.explore.repository.explore;

import com.spacifii.konstruct.explore.entities.explore.FeaturedKeywordMenu;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * This is DAO class for FeaturedKeywordMenu
 */
@Repository
public interface FeaturedKeywordMenuRepository extends MongoRepository<FeaturedKeywordMenu,String> {
}
