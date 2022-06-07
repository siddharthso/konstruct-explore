package com.spacifii.konstruct.explore.controller.showcase;

import com.spacifii.konstruct.explore.entities.showcases.ContainerMedia;
import com.spacifii.konstruct.explore.entities.showcases.ContainerMediaViewType;
import com.spacifii.konstruct.explore.integration.rbac.annotation.RbacRegister;
import com.spacifii.konstruct.explore.model.APIResponseKey;
import com.spacifii.konstruct.explore.model.ApiResponse;
import com.spacifii.konstruct.explore.service.showcase.ContainerMediaViewTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * This controller method is used to mange ContainerMediaViewType
 */
@RestController
@RequestMapping(value = "/api/containerMediaViewType")
public class ContainerMediaViewTypeController {

    @Autowired
    ContainerMediaViewTypeService containerMediaViewTypeService;

    /**
     * This controller method is used to add or update ContainerMediaViewType
     * @param containerMediaViewType
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "saveUpdateContainerMediaViewType")
    @PostMapping(value = "/saveUpdateContainerMediaViewType", consumes = "application/json")
    public ApiResponse<ContainerMediaViewType> saveUpdateContainerMediaViewType(@RequestBody ContainerMediaViewType containerMediaViewType,
                                                                        @RequestHeader("subjectid") Long subjectid){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,containerMediaViewTypeService.save(containerMediaViewType),null);
    }



    /**
     * This controller method is used to get All ContainerMediaViewTypes
     *
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "findAllContainerMediaViewType")
    @GetMapping(value = "/findAllContainerMediaViewType")
    public ApiResponse<List<ContainerMediaViewType>> findAllContainerMediaViewType(){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,containerMediaViewTypeService.getAll(),null);
    }


    /**
     * This controller method is used to get  ContainerMediaViewTypes by Id
     * @param containerMediaViewTypeId
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "getContainerMediaViewTypeById")
    @GetMapping(value = "(containerMediaViewTypeId)/getContainerMediaViewTypeById")
    public ApiResponse<ContainerMediaViewType> findAllContainerMediaViewType(@PathVariable("containerMediaViewTypeId") String containerMediaViewTypeId){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,containerMediaViewTypeService.findById(containerMediaViewTypeId),null);
    }

    /**
     * This controller method is used to add or update OfferingProductViewType To ContainerMediaViewType
     * @param containerMediaViewTypeId
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "addOfferingProductViewTypeToContainerMediaViewType")
    @PostMapping(value = "(containerMediaViewTypeId)/addOfferingProductViewTypeToContainerMediaViewType", consumes = "application/json")
    public ApiResponse<ContainerMediaViewType> addOfferingProductViewType(@RequestBody Set<String> offeringProductViewTypeIds,
                                                                                @PathVariable("containerMediaViewTypeId") String containerMediaViewTypeId,
                                                                                @RequestHeader("subjectid") Long subjectid){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,containerMediaViewTypeService.addOfferingProductViewType(offeringProductViewTypeIds,containerMediaViewTypeId),null);
    }


    /**
     * This controller method is used to  remove OfferingProductViewType from ContainerMediaViewType
     * @param containerMediaViewTypeId
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "removeOfferingProductViewTypeFromContainerMediaViewType")
    @PostMapping(value = "(containerMediaViewTypeId)/removeOfferingProductViewTypeFromContainerMediaViewType", consumes = "application/json")
    public ApiResponse<ContainerMediaViewType> removeOfferingProductViewType(@RequestBody Set<String> offeringProductViewTypeIds,
                                                                                @PathVariable("containerMediaViewTypeId") String containerMediaViewTypeId,
                                                                                @RequestHeader("subjectid") Long subjectid){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,containerMediaViewTypeService.removeOfferingProductViewType(offeringProductViewTypeIds,containerMediaViewTypeId),null);
    }
}
