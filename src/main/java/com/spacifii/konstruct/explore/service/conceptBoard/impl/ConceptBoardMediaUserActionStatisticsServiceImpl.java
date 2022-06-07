package com.spacifii.konstruct.explore.service.conceptBoard.impl;

import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardMediaUserActionStatistics;
import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardUserActionStatistics;
import com.spacifii.konstruct.explore.repository.conceptBoard.ConceptBoardMediaUserActionStatisticsRepository;
import com.spacifii.konstruct.explore.service.conceptBoard.ConceptBoardMediaUserActionStatisticsService;
import com.spacifii.konstruct.explore.service.explore.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * This is Service Class for ConceptBoardMediaUserActionStatistics
 */
@Service
public class ConceptBoardMediaUserActionStatisticsServiceImpl implements ConceptBoardMediaUserActionStatisticsService {

    @Autowired
    ConceptBoardMediaUserActionStatisticsRepository conceptBoardMediaUserActionStatisticsRepository;

    /**
     * This service method saves or Updates ConceptBoardMediaUserActionStatistics
     *
     * @param conceptBoardMediaUserActionStatistics
     * @param subjectId
     * @return
     */
    @Override
    public ConceptBoardMediaUserActionStatistics saveOrUpdate(ConceptBoardMediaUserActionStatistics conceptBoardMediaUserActionStatistics, Long subjectId) {
        if(conceptBoardMediaUserActionStatistics.getCreationDate() == null){
            conceptBoardMediaUserActionStatistics.preSave(subjectId);
        } else {
            conceptBoardMediaUserActionStatistics.preUpdate(subjectId);
        }
        return conceptBoardMediaUserActionStatisticsRepository.save(conceptBoardMediaUserActionStatistics);
    }

    /**
     * This service method gets MediaStatistics
     *
     * @param mediaId
     * @return
     */
    @Override
    public ConceptBoardMediaUserActionStatistics findByMediaId(String mediaId) {
        Optional<ConceptBoardMediaUserActionStatistics> conceptBoardMediaUserActionStatisticsOptional = conceptBoardMediaUserActionStatisticsRepository.findById(mediaId);
        if (conceptBoardMediaUserActionStatisticsOptional.isPresent()){
            return conceptBoardMediaUserActionStatisticsOptional.get();
        }

        ConceptBoardMediaUserActionStatistics conceptBoardMediaUserActionStatistics = new ConceptBoardMediaUserActionStatistics();
        conceptBoardMediaUserActionStatistics.setMediaId(mediaId);
        return conceptBoardMediaUserActionStatistics;
    }

    /**
     * This service method is used to get ConceptBoardMediaUserActionStatistics by MediaIds
     *
     * @param mediaIds
     * @return
     */
    @Override
    public Map<String, ConceptBoardMediaUserActionStatistics> findByMediaIds(Set<String> mediaIds) {
        return BeanUtils.makeCollection(
                conceptBoardMediaUserActionStatisticsRepository.findAllById(mediaIds)).stream()
                .collect(Collectors.toMap(ConceptBoardMediaUserActionStatistics::getMediaId, Function.identity()));
    }
}
