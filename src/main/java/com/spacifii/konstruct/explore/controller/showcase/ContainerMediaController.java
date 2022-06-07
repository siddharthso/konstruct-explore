package com.spacifii.konstruct.explore.controller.showcase;


import com.spacifii.konstruct.explore.entities.showcases.ContainerMedia;
import com.spacifii.konstruct.explore.entities.showcases.CoordinateContainer;
import com.spacifii.konstruct.explore.integration.rbac.annotation.RbacRegister;
import com.spacifii.konstruct.explore.model.APIResponseKey;
import com.spacifii.konstruct.explore.model.ApiResponse;
import com.spacifii.konstruct.explore.model.dto.explore.FilterRequestDto;
import com.spacifii.konstruct.explore.service.showcase.ContainerMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * This is Controller class that Manages ContainerMedia
 */
@RestController
@RequestMapping(value = "/api/containerMedia")
public class ContainerMediaController {


    @Autowired
    ContainerMediaService containerMediaService;


    /**
     * This controller method is used to add or update ContainerMedia
     * @param containerMedia
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "saveUpdateContainerMedia")
    @PostMapping(value = "/saveUpdateContainerMedia", consumes = "application/json")
    public ApiResponse<ContainerMedia> saveUpdateContainerMedia(@RequestBody ContainerMedia containerMedia,
                                                                    @RequestHeader("subjectid") Long subjectid){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,containerMediaService.save(containerMedia,subjectid),null);
    }


    /**
     * This controller method get ContainerMedia Paginated
     * @param filterRequestDto
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @RbacRegister(authorityName = "getContainerMediaPaginated", excluded = true)
    @PostMapping(value = "/getContainerMediaPaginated", consumes = "application/json")
    public ApiResponse<Page<ContainerMedia>> saveUpdateContainerMedia(@RequestBody FilterRequestDto filterRequestDto){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,containerMediaService.getPaginatedContainerMedia(filterRequestDto),null);
    }


    /**
     * This controller method get ContainerMedia by Id
     * @param containerMediaId
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @RbacRegister(authorityName = "getContainerMediaById", excluded = true)
    @GetMapping(value = "/{containerMediaId}/getContainerMediaById")
    public ApiResponse<ContainerMedia> getContainerMediaById(@PathVariable("containerMediaId") String containerMediaId){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,containerMediaService.findById(containerMediaId),null);
    }

    /**
     * This controller method update ContainerMedia CoordinateContainer by Id
     * @param containerMediaId
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @RbacRegister(authorityName = "updateCoordinateContainersByContainerMediaId")
    @GetMapping(value = "/{containerMediaId}/updateCoordinateContainersByContainerMediaId")
    public ApiResponse<ContainerMedia> updateCoordinateContainers(@PathVariable("containerMediaId") String containerMediaId,
                                                             @RequestBody LinkedHashSet<CoordinateContainer> coordinateContainers,
                                                             @RequestHeader("subjectid") Long subjectid){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,containerMediaService.updateCoordinateContainers(containerMediaId,coordinateContainers,subjectid),null);
    }

    /**
     * This controller method remove ContainerMedia CoordinateContainer by Id
     * @param containerMediaId
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @RbacRegister(authorityName = "removeCoordinateContainerByContainerMediaId")
    @GetMapping(value = "/{containerMediaId}/removeCoordinateContainerByContainerMediaId")
    public ApiResponse<ContainerMedia> removeCoordinateContainerById(@PathVariable("containerMediaId") String containerMediaId,
                                                             @RequestBody Set<String> coordinateContainerids,
                                                             @RequestHeader("subjectid") Long subjectid){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,containerMediaService.removeCoordinateContainerById(containerMediaId,coordinateContainerids,subjectid),null);
    }
}
