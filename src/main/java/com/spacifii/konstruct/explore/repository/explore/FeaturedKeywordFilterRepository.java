package com.spacifii.konstruct.explore.repository.explore;

import com.spacifii.konstruct.explore.entities.explore.FeaturedKeywordFilter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * This is DAO class for FeaturedKeywordFilter
 */
@Repository
public interface FeaturedKeywordFilterRepository extends MongoRepository<FeaturedKeywordFilter,String> {
}
