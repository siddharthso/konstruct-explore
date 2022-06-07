package com.spacifii.konstruct.explore.service.conceptBoard.impl;

import com.spacifii.konstruct.explore.annotation.NotToUseDuringMerge;
import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoard;
import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardWalkThrough;
import com.spacifii.konstruct.explore.exception.conceptBoard.ConceptBoardWalkThroughNotFoundException;
import com.spacifii.konstruct.explore.model.dto.explore.FilterRequestDto;
import com.spacifii.konstruct.explore.repository.conceptBoard.ConceptBoardWalkThroughRepository;
import com.spacifii.konstruct.explore.service.conceptBoard.ConceptBoardWalkThroughService;
import com.spacifii.konstruct.explore.service.explore.BeanUtils;
import com.spacifii.konstruct.explore.service.explore.QueryUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

/**
 * This service class manages ConceptBoardWalkThrough
 */
@Service
public class ConceptBoardWalkThroughServiceImpl implements ConceptBoardWalkThroughService {

    @Autowired
    ConceptBoardWalkThroughRepository conceptBoardWalkThroughRepository;

    @Autowired
    QueryUtilService queryUtilService;

    /**
     * This method saves of update ConceptBoardWalkThrough
     *
     * @param conceptBoardWalkThrough
     * @param subjectId
     * @return
     */
    @Override
    public ConceptBoardWalkThrough saveOrUpdate(ConceptBoardWalkThrough conceptBoardWalkThrough, Long subjectId) {
        if(conceptBoardWalkThrough.getId() != null){
            ConceptBoardWalkThrough conceptBoardWalkThroughExisting = getById(conceptBoardWalkThrough.getId());
            String[] arr  = BeanUtils.findFields(ConceptBoardWalkThrough.class, NotToUseDuringMerge.class);
            BeanUtils.copyProperties(conceptBoardWalkThrough,conceptBoardWalkThroughExisting,arr);
            conceptBoardWalkThroughExisting.preUpdate(subjectId);
            return conceptBoardWalkThroughRepository.save(conceptBoardWalkThroughExisting);
        }
        conceptBoardWalkThrough.preSave(subjectId);
        return conceptBoardWalkThroughRepository.save(conceptBoardWalkThrough);
    }

    /**
     * This method gets ConceptBoardWalkThrough by Id
     *
     * @param id
     * @return
     */
    @Override
    public ConceptBoardWalkThrough getById(String id) {
        Optional<ConceptBoardWalkThrough> conceptBoardWalkThroughOptional = conceptBoardWalkThroughRepository.findById(id);
        if(conceptBoardWalkThroughOptional.isPresent()){
            return conceptBoardWalkThroughOptional.get();
        }
        throw new ConceptBoardWalkThroughNotFoundException();
    }

    /**
     * This method gets Page of ConceptBoardWalkThrough by FilterRequestDto
     *
     * @param filterRequestDto
     * @return
     */
    @Override
    public Page<ConceptBoardWalkThrough> getConceptBoardWalkThroughFiltered(FilterRequestDto filterRequestDto) {
         return queryUtilService.getResultList(filterRequestDto,new HashSet<>(Arrays.asList("lastModifiedData")),new ConceptBoardWalkThrough());
    }

    /**
     * This method is used to get ConceptBoardWalkThrough by Id
     *
     * @param conceptBoardId
     * @return
     */
    @Override
    public List<ConceptBoardWalkThrough> findByConceptBoardId(String conceptBoardId) {
        return conceptBoardWalkThroughRepository.findByConceptBoardId(conceptBoardId);
    }
}
