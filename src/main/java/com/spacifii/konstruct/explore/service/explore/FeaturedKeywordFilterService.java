package com.spacifii.konstruct.explore.service.explore;

import com.spacifii.konstruct.explore.entities.explore.FeaturedKeywordFilter;
import com.spacifii.konstruct.explore.model.dto.explore.FeaturedKeywordFilterRequestDto;

import java.util.List;

/**
 * This is service class for FeaturedKeywordFilter
 */
public interface FeaturedKeywordFilterService {

    /**
     * This method saves FeaturedKeywordFilter
     * @param featuredKeywordFilterRequestDto
     * @return
     */
    FeaturedKeywordFilter save(FeaturedKeywordFilterRequestDto featuredKeywordFilterRequestDto);

    /**
     * This service method gets all FeaturedKeywordFilters
     * @return
     */
    List<FeaturedKeywordFilter> findAll();

    /**
     * This service method is used to get All FeaturedKeywordFilters which are sorted
     * @return
     */
    List<FeaturedKeywordFilter> findAllSorted();
}
