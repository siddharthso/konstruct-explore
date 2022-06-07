package com.spacifii.konstruct.explore.service.showcase;

import com.spacifii.konstruct.explore.entities.showcases.ContainerMedia;
import com.spacifii.konstruct.explore.entities.showcases.CoordinateContainer;
import com.spacifii.konstruct.explore.model.dto.explore.FilterRequestDto;
import org.springframework.data.domain.Page;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * This service class manages ContainerMedia
 */
public interface ContainerMediaService {

    /**
     * This service method is used to save Container Media
     * @param containerMedia
     * @param subjectId
     * @return
     */
    ContainerMedia save(ContainerMedia containerMedia, Long subjectId);

    /**
     * This service method is used to find Paginated ContainerMedia
     * @param filterRequestDto
     * @return
     */
    Page<ContainerMedia> getPaginatedContainerMedia(FilterRequestDto filterRequestDto);

    /**
     * This service method is used to find ContainerMedia by id
     * @param id
     * @return
     */
    ContainerMedia findById(String id);


    /**
     * This service method updates Co-ordinate Containers
     * @param mediaId
     * @param coordinateContainers
     * @param subjectId
     * @return
     */
    ContainerMedia updateCoordinateContainers(String mediaId, LinkedHashSet<CoordinateContainer> coordinateContainers, Long subjectId);

    /**
     * This service method is used to remove
     * @param mediaId
     * @param containerId
     * @param subjectId
     * @return
     */
    ContainerMedia removeCoordinateContainerById(String mediaId, Set<String> containerId, Long subjectId);


}
