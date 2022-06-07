package com.spacifii.konstruct.explore.service.explore.impl;

import com.spacifii.konstruct.explore.elasticRepository.ExploreMediaElasticRepository;
import com.spacifii.konstruct.explore.entities.explore.ExploreMediaElastic;
import com.spacifii.konstruct.explore.entities.explore.ExploreProject;
import com.spacifii.konstruct.explore.entities.explore.Media;
import com.spacifii.konstruct.explore.entities.explore.STATUS;
import com.spacifii.konstruct.explore.service.explore.ExploreMediaElasticService;
import com.spacifii.konstruct.explore.service.explore.ExploreProjectService;
import com.spacifii.konstruct.explore.service.explore.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This is service class for ExploreMediaElastic
 */
@Service
public class ExploreMediaElasticServiceImpl implements ExploreMediaElasticService {

    @Autowired
    ExploreMediaElasticRepository exploreMediaElasticRepository;

    @Autowired
    ExploreProjectService exploreProjectService;

    @Autowired
    MediaService mediaService;


    /**
     * This is Method saves ExploreMediaElastic using Media
     * @param media
     * @return
     */
    @Override
    public ExploreMediaElastic save(Media media) {
        if(media.getActive()) {

            ExploreProject exploreProject = exploreProjectService.getExploreProjectById(media.getProjectId());
            ExploreMediaElastic exploreMediaElastic = findById(media.getId());
            if(exploreMediaElastic == null) {
                exploreMediaElastic = new ExploreMediaElastic();
            }
            exploreMediaElastic.mergeMeWithMedia(media);
            exploreMediaElastic.mergeMeWithProject(exploreProject);
            return exploreMediaElasticRepository.save(exploreMediaElastic);
        }
        return null;
    }

    /**
     * This method searches ExploreMediaElastic using fuzzy Match
     * @param keyword
     * @return
     */
    @Override
    public List<ExploreMediaElastic> getFuzzy(String keyword) {
        return null;
    }

    /**
     * his method searches ExploreMediaElastic using fuzzy Match with page
     * @param keyword
     * @param pageable
     * @return
     */
    @Override
    public Page<ExploreMediaElastic> getFuzzy(String keyword, Pageable pageable) {
        return exploreMediaElasticRepository.findFuzzyMatchKeywodStringWithPage(keyword,pageable);
    }

    /**
     * his method searches ExploreMediaElastic using fuzzy Match with page in global search
     * @param keyword
     * @param pageable
     * @return
     */
    @Override
    public Page<ExploreMediaElastic> getFuzzyglobal(String keyword, Pageable pageable) {
        return exploreMediaElasticRepository.findFuzzyMatchStringWithPage(keyword,pageable);
    }


    /**
     * If its active and Not present and Approved, We add. Else we remove from ElasticSearch
     * @param media
     */
    @Override
    public void toggleMediaElastic(Media media) {

        ExploreMediaElastic exploreMediaElastic = findById(media.getId());
        if(media.getActive()) {
            if (media.getStatus().equals(STATUS.APPROVED)) {

                if (exploreMediaElastic == null) {
                    save(media);
                }
            }
        } else {
            if (exploreMediaElastic != null) {
                exploreMediaElasticRepository.delete(exploreMediaElastic);
            }
        }
    }


    /**
     * This method findExploreMediaElastic on Id
     * @param mediaId
     * @return
     */
    @Override
    public ExploreMediaElastic findById(String mediaId) {
        Optional<ExploreMediaElastic> exploreMediaElasticOptional = exploreMediaElasticRepository.findById(mediaId);

        if(exploreMediaElasticOptional.isPresent()){
            return exploreMediaElasticOptional.get();
        }
        return null;
    }

    /**
     * This method returns related medias from elastic search
     * @param media
     * @return
     */
    @Override
    public List<Media> getRelatedMedias(Media media) {
        PageRequest pageRequest= new PageRequest(0,10);
        ExploreMediaElastic exploreMediaElastic = new ExploreMediaElastic();
        exploreMediaElastic.mergeMeWithMedia(media);
        Page<ExploreMediaElastic> exploreMediaElasticPage = getFuzzy(exploreMediaElastic.getKeywords(),pageRequest);
        if(exploreMediaElasticPage.hasContent()){
            return mediaService.findByIds(exploreMediaElasticPage.getContent().stream().map(ExploreMediaElastic::getId).collect(Collectors.toSet()));
        }
        return null;
    }

    /**
     * This method returns searched medias from elastic search
     * @param keyword
     * @param  pageable
     * @return
     */
    @Override
    public Page<Media> getMediaSearch(String keyword, Pageable pageable) {
        Page<ExploreMediaElastic> exploreMediaElasticPage = getFuzzyglobal(keyword,pageable);
        if(exploreMediaElasticPage.hasContent()){
            List<Media> media = mediaService.findByIds(exploreMediaElasticPage.getContent().stream().map(ExploreMediaElastic::getId).collect(Collectors.toSet()));
            return new PageImpl<>(media,exploreMediaElasticPage.getPageable(),exploreMediaElasticPage.getTotalElements());
        }
        return null;
    }


}
