package com.spacifii.konstruct.explore.service.showcase.impl;

import com.spacifii.konstruct.explore.annotation.NotToUseDuringMerge;
import com.spacifii.konstruct.explore.entities.showcases.ContainerMedia;
import com.spacifii.konstruct.explore.entities.showcases.CoordinateContainer;
import com.spacifii.konstruct.explore.exception.showcase.ContainerMediaNotFoundException;
import com.spacifii.konstruct.explore.model.dto.explore.FilterRequestDto;
import com.spacifii.konstruct.explore.repository.showcase.ContainerMediaRepository;
import com.spacifii.konstruct.explore.service.explore.BeanUtils;
import com.spacifii.konstruct.explore.service.explore.QueryUtilService;
import com.spacifii.konstruct.explore.service.showcase.ContainerMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * This service class manges ContainerMedia
 */
@Service
public class ContainerMediaServiceImpl implements ContainerMediaService {

    @Autowired
    ContainerMediaRepository containerMediaRepository;

    @Autowired
    QueryUtilService queryUtilService;


    /**
     * This service method is used to save Container Media
     *
     * @param containerMedia
     * @return
     */
    @Override
    public ContainerMedia save(ContainerMedia containerMedia, Long subjectId) {
        if(containerMedia.getId() != null){
            ContainerMedia containerMediaExisting = findById(containerMedia.getId());
            String[] arr  = BeanUtils.findFields(ContainerMedia.class, NotToUseDuringMerge.class);
            BeanUtils.copyProperties(containerMedia,containerMediaExisting,arr);
            containerMediaExisting.preUpdate(subjectId);
            return  containerMediaRepository.save(containerMediaExisting);
        }
        containerMedia.preSave(subjectId);
        return containerMediaRepository.save(containerMedia);
    }

    /**
     * This service method is used to find Paginated ContainerMedia
     *
     * @param filterRequestDto
     * @return
     */
    @Override
    public Page<ContainerMedia> getPaginatedContainerMedia(FilterRequestDto filterRequestDto) {
        return queryUtilService.getResultList(filterRequestDto,new HashSet<>(Arrays.asList("lastModifiedDate")),new ContainerMedia());
    }

    /**
     * This service method is used to find ContainerMedia by id
     *
     * @param id
     * @return
     */
    @Override
    public ContainerMedia findById(String id) {
        Optional<ContainerMedia> containerMediaOptional = containerMediaRepository.findById(id);
        if(containerMediaOptional.isPresent()){
            return containerMediaOptional.get();
        }
        throw new ContainerMediaNotFoundException();
    }

    /**
     * This service method updates Co-ordinate Containers
     *
     * @param mediaId
     * @param coordinateContainers
     * @return
     */
    @Override
    public ContainerMedia updateCoordinateContainers(String mediaId, LinkedHashSet<CoordinateContainer> coordinateContainers, Long subjectId) {
        ContainerMedia containerMedia = findById(mediaId);
        LinkedHashSet<CoordinateContainer> coordinateContainersExisting = containerMedia.getCoordinateContainers();
        for(CoordinateContainer coordinateContainer: coordinateContainers){
            if(coordinateContainer.getId() == null){
                coordinateContainer.setId(UUID.randomUUID().toString());
            }
        }
        coordinateContainersExisting.addAll(coordinateContainers);
        containerMedia.setCoordinateContainers(coordinateContainersExisting);
        containerMedia.preUpdate(subjectId);
        return containerMediaRepository.save(containerMedia);
    }

    /**
     * This service method is used to remove
     *
     * @param mediaId
     * @param  containerId
     * @return
     */
    @Override
    public ContainerMedia removeCoordinateContainerById(String mediaId, Set<String> containerId, Long subjectId) {
        ContainerMedia containerMedia = findById(mediaId);
        LinkedHashSet<CoordinateContainer> coordinateContainersExisting = containerMedia.getCoordinateContainers();
       // LinkedHashSet<CoordinateContainer> coordinateContainersToRemove = new LinkedHashSet<>();
        Map<String,CoordinateContainer> map = new LinkedHashMap<>();
        if(coordinateContainersExisting.size() > 0){
            map= coordinateContainersExisting.stream().collect(Collectors.toMap(CoordinateContainer::getId, Function.identity()));
            for (String s : containerId) {
                if(map.get(s) != null){
                    coordinateContainersExisting.remove(map.get(s));
                }
            }

        }

        containerMedia.setCoordinateContainers(coordinateContainersExisting);
        containerMedia.preUpdate(subjectId);
        return containerMediaRepository.save(containerMedia);
    }
}
