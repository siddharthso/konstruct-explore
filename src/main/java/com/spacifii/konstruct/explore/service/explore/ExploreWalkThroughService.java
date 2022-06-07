package com.spacifii.konstruct.explore.service.explore;

import com.spacifii.konstruct.explore.entities.explore.ExploreWalkThrough;
import com.spacifii.konstruct.explore.model.dto.explore.FilterRequestDto;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * This is service class for ExploreWalkThrough
 */
public interface ExploreWalkThroughService {

    /**
     * This method saves of update ExploreWalkThrough
     * @param exploreWalkThrough
     * @param subjectId
     * @return
     */
    ExploreWalkThrough saveOrUpdate(ExploreWalkThrough exploreWalkThrough, Long subjectId);

    /**
     * This method gets ExploreWalkThrough by Id
     * @param id
     * @return
     */
    ExploreWalkThrough getById(String id);

    /**
     * This method gets Page of ExploreWalkThrough by FilterRequestDto
     * @param filterRequestDto
     * @return
     */
    Page<ExploreWalkThrough> getExploreWalkThroughFiltered(FilterRequestDto filterRequestDto);

    /**
     * This service method is used to get ExploreWalkThrough by ProjectId
     * @param projectId
     * @return
     */
    List<ExploreWalkThrough> findByProjectId(String projectId);

}
