package com.spacifii.konstruct.explore.service.explore;

import com.spacifii.konstruct.explore.entities.explore.ExploreMediaElastic;
import com.spacifii.konstruct.explore.entities.explore.Media;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * This is service class for ExploreMediaElastic
 */
public interface ExploreMediaElasticService {

    /**
     * This is Method saves ExploreMediaElastic using Media
     * @param media
     * @return
     */
    ExploreMediaElastic save(Media media);

    /**
     * This method searches ExploreMediaElastic using fuzzy Match
     * @param keyword
     * @return
     */
    List<ExploreMediaElastic> getFuzzy(String keyword);


    /**
     * his method searches ExploreMediaElastic using fuzzy Match with page
     * @param keyword
     * @param pageable
     * @return
     */
    Page<ExploreMediaElastic> getFuzzy(String keyword, Pageable pageable);


    /**
     * his method searches ExploreMediaElastic using fuzzy Match with page in global search
     * @param keyword
     * @param pageable
     * @return
     */
    Page<ExploreMediaElastic> getFuzzyglobal(String keyword, Pageable pageable);


    /**
     * If its active and Not present and Approved, We add. Else we remove from ElasticSearch
     * @param media
     */
    void toggleMediaElastic(Media media);

    /**
     * This method findExploreMediaElastic on Id
     * @param mediaId
     * @return
     */
    ExploreMediaElastic findById(String mediaId);

    /**
     * This method returns related medias from elastic search
     * @param media
     * @return
     */
    List<Media> getRelatedMedias(Media media);


    /**
     * This method returns searched medias from elastic search
     * @param keyword
     * @param  pageable
     * @return
     */
    Page<Media> getMediaSearch(String keyword, Pageable pageable);


}
