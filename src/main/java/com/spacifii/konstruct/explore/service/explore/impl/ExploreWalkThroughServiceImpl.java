package com.spacifii.konstruct.explore.service.explore.impl;

import com.spacifii.konstruct.explore.annotation.NotToUseDuringMerge;
import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardWalkThrough;
import com.spacifii.konstruct.explore.entities.explore.ExploreWalkThrough;
import com.spacifii.konstruct.explore.exception.explore.ExploreWalkThroughNotFoundException;
import com.spacifii.konstruct.explore.model.dto.explore.FilterRequestDto;
import com.spacifii.konstruct.explore.repository.explore.ExploreWalkThroughRepository;
import com.spacifii.konstruct.explore.service.explore.BeanUtils;
import com.spacifii.konstruct.explore.service.explore.ExploreWalkThroughService;
import com.spacifii.konstruct.explore.service.explore.QueryUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

/**
 * This is service class for ExploreWalkThrough
 */
@Service
public class ExploreWalkThroughServiceImpl implements ExploreWalkThroughService {

    @Autowired
    ExploreWalkThroughRepository exploreWalkThroughRepository;

    @Autowired
    QueryUtilService queryUtilService;

    /**
     * This method saves of update ExploreWalkThrough
     *
     * @param exploreWalkThrough
     * @param subjectId
     * @return
     */
    @Override
    public ExploreWalkThrough saveOrUpdate(ExploreWalkThrough exploreWalkThrough, Long subjectId) {
        if(exploreWalkThrough.getId() != null){
            ExploreWalkThrough exploreWalkThroughExisting = getById(exploreWalkThrough.getId());
            String[] arr  = BeanUtils.findFields(ExploreWalkThrough.class, NotToUseDuringMerge.class);
            BeanUtils.copyProperties(exploreWalkThrough,exploreWalkThroughExisting,arr);
            exploreWalkThroughExisting.preUpdate(subjectId);
            return exploreWalkThroughRepository.save(exploreWalkThroughExisting);
        }
        exploreWalkThrough.preSave(subjectId);
        return exploreWalkThroughRepository.save(exploreWalkThrough);
    }

    /**
     * This method gets ExploreWalkThrough by Id
     *
     * @param id
     * @return
     */
    @Override
    public ExploreWalkThrough getById(String id) {
        Optional<ExploreWalkThrough> exploreWalkThroughOptional = exploreWalkThroughRepository.findById(id);
        if(exploreWalkThroughOptional.isPresent()){
            return exploreWalkThroughOptional.get();
        }
        throw new ExploreWalkThroughNotFoundException();
    }

    /**
     * This method gets Page of ExploreWalkThrough by FilterRequestDto
     *
     * @param filterRequestDto
     * @return
     */
    @Override
    public Page<ExploreWalkThrough> getExploreWalkThroughFiltered(FilterRequestDto filterRequestDto) {
        return queryUtilService.getResultList(filterRequestDto,new HashSet<>(Arrays.asList("lastModifiedData")),new ExploreWalkThrough());
    }

    /**
     * This service method is used to get ExploreWalkThrough by ProjectId
     *
     * @param projectId
     * @return
     */
    @Override
    public List<ExploreWalkThrough> findByProjectId(String projectId) {
        return exploreWalkThroughRepository.findByProjectId(projectId);
    }
}
