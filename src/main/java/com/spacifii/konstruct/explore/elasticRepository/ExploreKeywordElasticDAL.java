package com.spacifii.konstruct.explore.elasticRepository;

import com.spacifii.konstruct.explore.entities.explore.ExploreKeywordElastic;

import java.util.List;

public interface ExploreKeywordElasticDAL {

    List<ExploreKeywordElastic> getFuzzyMatchOnKeyword(String keyword);
}
