package com.spacifii.konstruct.explore.service.explore;

import com.spacifii.konstruct.explore.entities.explore.FeaturedKeywordMenu;
import com.spacifii.konstruct.explore.model.dto.explore.FeaturedKeywordMenuRequestDto;

import java.util.List;

/**
 * This is service cass for FeaturedKeywordMenu
 */
public interface FeaturedKeywordMenuService {


    /**
     * This service method is used to save FeaturedKeywordMenu
     * @param featuredKeywordMenuRequestDto
     * @return
     */
    FeaturedKeywordMenu save(FeaturedKeywordMenuRequestDto featuredKeywordMenuRequestDto);

    /**
     * This service method is used to get all FeaturedKeywordMenus
     * @return
     */
    List<FeaturedKeywordMenu> getAll();

}
