package com.spacifii.konstruct.explore.elasticRepository;

import com.spacifii.konstruct.explore.entities.explore.ExploreMediaElastic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExploreMediaElasticRepository extends ElasticsearchCrudRepository<ExploreMediaElastic, String> {


    @Query("{\n" +
            "\t\"bool\" : {\n" +
            "    \"should\": {\n" +
            "        \"multi_match\" : {\n" +
            "            \n" +
            "\"fuzziness\": \"AUTO\",\n" +
            "\"lenient\": true,\n" +
            "\"query\" : \"?0\",\n" +
            "            \"fields\": [\"keywords\",\"tags\",\"description\",\"project\"]\n" +
            "\n" +
            "        }\n" +
            "    }\n" +
            "\n" +
            "}}")
    Page<ExploreMediaElastic> findFuzzyMatchStringWithPage(String query, Pageable pageable);


    @Query("{\n" +
            "\t\"bool\" : {\n" +
            "    \"should\": {\n" +
            "        \"multi_match\" : {\n" +
            "            \n" +
            "\"fuzziness\": \"AUTO\",\n" +
            "\"lenient\": true,\n" +
            "\"query\" : \"?0\",\n" +
            "            \"fields\": [\"keywords\"]\n" +
            "\n" +
            "        }\n" +
            "    }\n" +
            "\n" +
            "}}")
    Page<ExploreMediaElastic> findFuzzyMatchKeywodStringWithPage(String query, Pageable pageable);
}

