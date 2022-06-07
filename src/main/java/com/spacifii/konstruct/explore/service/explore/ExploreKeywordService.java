package com.spacifii.konstruct.explore.service.explore;

import com.spacifii.konstruct.explore.entities.explore.ExploreKeyword;

import java.util.List;

public interface ExploreKeywordService {

    ExploreKeyword save(ExploreKeyword exploreKeyword, Long subjecId);

    List<ExploreKeyword> getExploreKeywords();

    ExploreKeyword getExploreKeywordByKeyword(String keyword);

}
