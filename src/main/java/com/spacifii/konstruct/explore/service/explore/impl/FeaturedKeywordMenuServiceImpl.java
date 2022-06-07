package com.spacifii.konstruct.explore.service.explore.impl;

import com.spacifii.konstruct.explore.entities.explore.ExploreKeyword;
import com.spacifii.konstruct.explore.entities.explore.FeaturedKeywordMenu;
import com.spacifii.konstruct.explore.entities.explore.FeaturedKeywordMenuGroup;
import com.spacifii.konstruct.explore.model.dto.explore.FeaturedKeywordMenuRequestDto;
import com.spacifii.konstruct.explore.repository.explore.FeaturedKeywordMenuRepository;
import com.spacifii.konstruct.explore.service.explore.ExploreKeywordService;
import com.spacifii.konstruct.explore.service.explore.FeaturedKeywordMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * This is service cass for FeaturedKeywordMenu
 */
@Service
public class FeaturedKeywordMenuServiceImpl implements FeaturedKeywordMenuService {


    @Autowired
    FeaturedKeywordMenuRepository featuredKeywordMenuRepository;

    @Autowired
    ExploreKeywordService exploreKeywordService;

    /**
     * This service method is used to save FeaturedKeywordMenu
     * @param featuredKeywordMenuRequestDto
     * @return
     */
    @Override
    public FeaturedKeywordMenu save(FeaturedKeywordMenuRequestDto featuredKeywordMenuRequestDto) {
        FeaturedKeywordMenu featuredKeywordMenu = new FeaturedKeywordMenu();
        featuredKeywordMenu.setKeyword_category(featuredKeywordMenuRequestDto.getKeyword_category());
        List<FeaturedKeywordMenuGroup> groups = new ArrayList<>();
        for (Map.Entry<String,List<String>> entry: featuredKeywordMenuRequestDto.getExploreKeywords().entrySet()) {
            List<ExploreKeyword> keywords = new ArrayList<>();
            for (String s: entry.getValue()) {
                keywords.add(exploreKeywordService.getExploreKeywordByKeyword(s));
            }
          groups.add(new FeaturedKeywordMenuGroup(entry.getKey(),keywords));
        }
        featuredKeywordMenu.setGroups(groups);

        //featuredKeywordMenu.setExploreKeywords(stringListMap);

        Optional<FeaturedKeywordMenu> featuredKeywordMenuOptional = featuredKeywordMenuRepository.findById(featuredKeywordMenuRequestDto.getKeyword_category().toString());
        if(featuredKeywordMenuOptional.isPresent()){
            FeaturedKeywordMenu featuredKeywordMenuExisting = featuredKeywordMenuOptional.get();
           featuredKeywordMenuExisting.setGroups(groups);
            return featuredKeywordMenuRepository.save(featuredKeywordMenuExisting);
        }
        featuredKeywordMenu.setId(featuredKeywordMenuRequestDto.getKeyword_category().toString());
        return featuredKeywordMenuRepository.save(featuredKeywordMenu);
    }

    /**
     * This service method is used to get all FeaturedKeywordMenus
     * @return
     */
    @Override
    public List<FeaturedKeywordMenu> getAll() {
        return featuredKeywordMenuRepository.findAll();
    }
}
