package com.spacifii.konstruct.explore.service.conceptBoard.impl;

import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardUserActionStatistics;
import com.spacifii.konstruct.explore.repository.conceptBoard.ConceptBoardUserActionStatisticsRepository;
import com.spacifii.konstruct.explore.service.conceptBoard.ConceptBoardUserActionStatisticsService;
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
public class ConceptBoardUserActionStatisticsServiceImpl implements ConceptBoardUserActionStatisticsService {

    @Autowired
    ConceptBoardUserActionStatisticsRepository conceptBoardUserActionStatisticsRepository;


    /**
     * This service method saves or Updates ConceptBoardMediaUserActionStatistics
     *
     * @param conceptBoardUserActionStatistics
     * @param subjectId
     * @return
     */
    @Override
    public ConceptBoardUserActionStatistics saveOrUpdate(ConceptBoardUserActionStatistics conceptBoardUserActionStatistics, Long subjectId) {
        if(conceptBoardUserActionStatistics.getCreationDate() == null){
            conceptBoardUserActionStatistics.preSave(subjectId);
        } else {
            conceptBoardUserActionStatistics.preUpdate(subjectId);
        }
        return conceptBoardUserActionStatisticsRepository.save(conceptBoardUserActionStatistics);
    }

    /**
     * This service method gets ConceptBoardUserActionStatistics
     *
     * @param conceptBoardId
     * @return
     */
    @Override
    public ConceptBoardUserActionStatistics findByConceptBoardId(String conceptBoardId) {
        Optional<ConceptBoardUserActionStatistics> conceptBoardUserActionStatisticsOptional = conceptBoardUserActionStatisticsRepository.findById(conceptBoardId);
        if (conceptBoardUserActionStatisticsOptional.isPresent()){
            return conceptBoardUserActionStatisticsOptional.get();
        }

        ConceptBoardUserActionStatistics conceptBoardUserActionStatistics = new ConceptBoardUserActionStatistics();
        conceptBoardUserActionStatistics.setConceptBoardId(conceptBoardId);
        return conceptBoardUserActionStatistics;
    }

    /**
     * This service method is used to get ConceptBoardUserActionStatistics by MediaIds
     *
     * @param conceptBoardIds
     * @return
     */
    @Override
    public Map<String, ConceptBoardUserActionStatistics> findByConceptBoardIds(Set<String> conceptBoardIds) {
        return BeanUtils.makeCollection(
                conceptBoardUserActionStatisticsRepository.findAllById(conceptBoardIds)).stream()
                .collect(Collectors.toMap(ConceptBoardUserActionStatistics::getConceptBoardId, Function.identity()));
    }
}
