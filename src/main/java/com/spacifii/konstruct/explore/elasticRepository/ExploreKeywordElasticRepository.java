package com.spacifii.konstruct.explore.elasticRepository;

import com.spacifii.konstruct.explore.entities.explore.ExploreKeywordElastic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExploreKeywordElasticRepository extends ElasticsearchCrudRepository<ExploreKeywordElastic, String> {


    @Query("{\n" +
            "\t\"bool\" : {\n" +
            "    \"should\": {\n" +
            "        \"multi_match\" : {\n" +
            "            \n" +
            "\"fuzziness\": \"AUTO\",\n" +
            "\"lenient\": true,\n" +
            "\"query\" : \"?0\",\n" +
            "            \"fields\": [\"keyword\",\"keywordDisplay\"]\n" +
            "\n" +
            "        }\n" +
            "    }\n" +
            "\n" +
            "}}")
    Page<ExploreKeywordElastic> findFuzzyMatchStringWithPage(String query, Pageable pageable);

    @Query("{\n" +
            "\t\"bool\" : {\n" +
            "    \"should\": {\n" +
            "        \"multi_match\" : {\n" +
            "            \n" +
            "\"fuzziness\": \"AUTO\",\n" +
            "\"lenient\": true,\n" +
            "\"query\" : \"?0\",\n" +
            "            \"fields\": [\"keyword\",\"keywordDisplay\"]\n" +
            "\n" +
            "        }\n" +
            "    }\n" +
            "\n" +
            "}}")
    List<ExploreKeywordElastic> findFuzzyMatchString(String query);
}

