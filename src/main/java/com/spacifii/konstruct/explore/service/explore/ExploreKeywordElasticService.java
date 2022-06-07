package com.spacifii.konstruct.explore.service.explore;

import com.spacifii.konstruct.explore.entities.explore.ExploreKeyword;
import com.spacifii.konstruct.explore.entities.explore.ExploreKeywordElastic;

import java.util.List;

public interface ExploreKeywordElasticService {

    ExploreKeywordElastic save(ExploreKeyword exploreKeyword);

    List<ExploreKeywordElastic> searchFuzzyForKeyword(String keyword);
}
