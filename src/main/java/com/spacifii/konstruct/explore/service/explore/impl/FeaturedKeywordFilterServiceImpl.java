package com.spacifii.konstruct.explore.service.explore.impl;

import com.spacifii.konstruct.explore.entities.explore.ExploreKeyword;
import com.spacifii.konstruct.explore.entities.explore.FeaturedKeywordFilter;
import com.spacifii.konstruct.explore.model.dto.explore.FeaturedKeywordFilterRequestDto;
import com.spacifii.konstruct.explore.repository.explore.FeaturedKeywordFilterRepository;
import com.spacifii.konstruct.explore.service.explore.ExploreKeywordService;
import com.spacifii.konstruct.explore.service.explore.FeaturedKeywordFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * This is service class for FeaturedKeywordFilter
 */
@Service
public class FeaturedKeywordFilterServiceImpl implements FeaturedKeywordFilterService {

    @Autowired
    FeaturedKeywordFilterRepository featuredKeywordFilterRepository;


    @Autowired
    ExploreKeywordService exploreKeywordService;

    /**
     * This method saves FeaturedKeywordFilter
     * @param featuredKeywordFilterRequestDto
     * @return
     */
    public FeaturedKeywordFilter save(FeaturedKeywordFilterRequestDto featuredKeywordFilterRequestDto) {

        FeaturedKeywordFilter featuredKeywordFilter = new FeaturedKeywordFilter();
        List<ExploreKeyword> exploreKeywords = new ArrayList<>();
        for (String s : featuredKeywordFilterRequestDto.getExploreKeywords()) {
            exploreKeywords.add(exploreKeywordService.getExploreKeywordByKeyword(s));
        }
        System.out.println(featuredKeywordFilterRequestDto.getKeyword_category().toString());
        featuredKeywordFilter.setExploreKeywords(exploreKeywords);
        Optional<FeaturedKeywordFilter> featuredKeywordFilterExistingOptinal = featuredKeywordFilterRepository.findById(featuredKeywordFilterRequestDto.getKeyword_category().toString());
        if(featuredKeywordFilterExistingOptinal.isPresent()){
            FeaturedKeywordFilter  featuredKeywordFilterExiting = featuredKeywordFilterExistingOptinal.get();
            featuredKeywordFilterExiting.setExploreKeywords(featuredKeywordFilter.getExploreKeywords());
            return featuredKeywordFilterRepository.save(featuredKeywordFilterExiting);
        }

        featuredKeywordFilter.setId(featuredKeywordFilterRequestDto.getKeyword_category().toString());
        return featuredKeywordFilterRepository.save(featuredKeywordFilter);
    }

    /**
     * This service method gets all FeaturedKeywordFilters
     * @return
     */
    @Override
    public List<FeaturedKeywordFilter> findAll() {
        return featuredKeywordFilterRepository.findAll();
    }

    /**
     * This service method is used to get All FeaturedKeywordFilters which are sorted
     *
     * @return
     */
    @Override
    public List<FeaturedKeywordFilter> findAllSorted() {
        List<FeaturedKeywordFilter> filters = new ArrayList<>();
        for (FeaturedKeywordFilter featuredKeywordFilter:featuredKeywordFilterRepository.findAll()) {
            List<ExploreKeyword> exploreKeywords = featuredKeywordFilter.getExploreKeywords();
            if(exploreKeywords.size() > 0){
                exploreKeywords.sort(new Comparator<ExploreKeyword>() {
                    @Override
                    public int compare(ExploreKeyword o1, ExploreKeyword o2) {
                        return o1.getId().compareTo(o2.getId());
                    }
                });
            }
            featuredKeywordFilter.setExploreKeywords(exploreKeywords);
            filters.add(featuredKeywordFilter);
        }
        return filters;
    }
}
