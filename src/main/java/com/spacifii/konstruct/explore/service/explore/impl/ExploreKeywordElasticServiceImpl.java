package com.spacifii.konstruct.explore.service.explore.impl;

import com.spacifii.konstruct.explore.elasticRepository.ExploreKeywordElasticRepository;
import com.spacifii.konstruct.explore.entities.explore.ExploreKeyword;
import com.spacifii.konstruct.explore.entities.explore.ExploreKeywordElastic;
import com.spacifii.konstruct.explore.service.explore.ExploreKeywordElasticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExploreKeywordElasticServiceImpl implements ExploreKeywordElasticService {

    @Autowired
    ExploreKeywordElasticRepository exploreKeywordElasticRepository;

    @Override
    @Transactional
    public ExploreKeywordElastic save(ExploreKeyword exploreKeyword) {
        ExploreKeywordElastic exploreKeywordElastic = new ExploreKeywordElastic();
        exploreKeywordElastic.updateMe(exploreKeyword);
        return exploreKeywordElasticRepository.save(exploreKeywordElastic);

    }

    @Override
    public List<ExploreKeywordElastic> searchFuzzyForKeyword(String keyword) {

        return exploreKeywordElasticRepository.findFuzzyMatchString(keyword);
    }
}
