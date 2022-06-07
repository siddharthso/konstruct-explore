package com.spacifii.konstruct.explore.elasticRepository;

import com.spacifii.konstruct.explore.entities.explore.ExploreProjectElastic;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExploreProjectElasticRepository extends ElasticsearchCrudRepository<ExploreProjectElastic, String> {


}
