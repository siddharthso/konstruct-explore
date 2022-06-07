package com.spacifii.konstruct.explore.service.conceptBoard;


import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardWalkThrough;
import com.spacifii.konstruct.explore.model.dto.explore.FilterRequestDto;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * This service class manages ConceptBoardWalkThrough
 */
public interface ConceptBoardWalkThroughService {

    /**
     * This method saves of update ConceptBoardWalkThrough
     * @param conceptBoardWalkThrough
     * @param subjectId
     * @return
     */
    ConceptBoardWalkThrough saveOrUpdate(ConceptBoardWalkThrough conceptBoardWalkThrough, Long subjectId);

    /**
     * This method gets ConceptBoardWalkThrough by Id
     * @param id
     * @return
     */
    ConceptBoardWalkThrough getById(String id);

    /**
     * This method gets Page of ConceptBoardWalkThrough by FilterRequestDto
     * @param filterRequestDto
     * @return
     */
    Page<ConceptBoardWalkThrough> getConceptBoardWalkThroughFiltered(FilterRequestDto filterRequestDto);

    /**
     * This method is used to get ConceptBoardWalkThrough by Id
     * @param conceptBoardId
     * @return
     */
    List<ConceptBoardWalkThrough> findByConceptBoardId(String conceptBoardId);
}
