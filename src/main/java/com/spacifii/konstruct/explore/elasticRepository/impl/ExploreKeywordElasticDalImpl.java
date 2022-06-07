package com.spacifii.konstruct.explore.elasticRepository.impl;

import com.spacifii.konstruct.explore.elasticRepository.ExploreKeywordElasticDAL;
import com.spacifii.konstruct.explore.entities.explore.ExploreKeywordElastic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExploreKeywordElasticDalImpl implements ExploreKeywordElasticDAL {
    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;


    @Override
    public List<ExploreKeywordElastic> getFuzzyMatchOnKeyword(String keyword) {

       /* SearchQuery searchQuery = new NativeSearchQueryBuilder().withIndices("")
                .withTypes("exploreKeyword")
                .withQuery()
                .build();*/
        return null;
    }
}
