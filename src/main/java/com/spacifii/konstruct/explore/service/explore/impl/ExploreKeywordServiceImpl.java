package com.spacifii.konstruct.explore.service.explore.impl;

import com.spacifii.konstruct.explore.entities.explore.ExploreKeyword;
import com.spacifii.konstruct.explore.exception.explore.KeywordAlreadyExistsException;
import com.spacifii.konstruct.explore.exception.explore.KeywordNotFoundException;
import com.spacifii.konstruct.explore.repository.explore.ExploreKeywordRepository;
import com.spacifii.konstruct.explore.service.explore.ExploreKeywordElasticService;
import com.spacifii.konstruct.explore.service.explore.ExploreKeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * This is service class for ExploreKeyword
 */
@Service
public class ExploreKeywordServiceImpl implements ExploreKeywordService {

    @Autowired
    ExploreKeywordRepository exploreKeywordRepository;


    @Autowired
    ExploreKeywordElasticService exploreKeywordElasticService;

    /**
     * This method saves Explore Keyword
     * @param exploreKeyword
     * @param subjecId
     * @return
     */
    @Override
    @Transactional
    public ExploreKeyword save(ExploreKeyword exploreKeyword, Long subjecId) {
        exploreKeyword.setKeyword(exploreKeyword.getKeyword().toUpperCase());
        if(exploreKeywordRepository.findFirstByKeyword(exploreKeyword.getKeyword()) != null){
            throw new KeywordAlreadyExistsException();
        }
        exploreKeyword.setId(exploreKeyword.getKeyword());
        exploreKeyword.preSave(subjecId);
        exploreKeywordElasticService.save(exploreKeyword);
        return exploreKeywordRepository.save(exploreKeyword);
    }

    /**
     * This method returns all Keywords
     * @return
     */
    @Override
    public List<ExploreKeyword> getExploreKeywords() {
        return exploreKeywordRepository.findAll();
    }

    /**
     * This method returns ExploreKeyword using keyword
     * @param keyword
     * @return
     */
    @Override
    public ExploreKeyword getExploreKeywordByKeyword(String keyword) {
        Optional<ExploreKeyword> exploreKeywordOptional = exploreKeywordRepository.findById(keyword.toUpperCase());
        if(exploreKeywordOptional.isPresent()){
            return exploreKeywordOptional.get();
        }
        throw new KeywordNotFoundException();
    }


}
