package com.spacifii.konstruct.explore.repository.explore;

import com.spacifii.konstruct.explore.entities.explore.ExploreKeyword;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExploreKeywordRepository extends MongoRepository<ExploreKeyword,String> {

    ExploreKeyword findFirstByKeyword(String keyword);

}
