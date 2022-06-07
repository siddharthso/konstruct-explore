package com.spacifii.konstruct.explore.controller.showcase;

import com.spacifii.konstruct.explore.entities.showcases.Coordinate;
import com.spacifii.konstruct.explore.entities.showcases.Showcase;
import com.spacifii.konstruct.explore.integration.rbac.annotation.RbacRegister;
import com.spacifii.konstruct.explore.model.APIResponseKey;
import com.spacifii.konstruct.explore.model.ApiResponse;
import com.spacifii.konstruct.explore.model.dto.explore.FilterRequestDto;
import com.spacifii.konstruct.explore.service.showcase.ShowcaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * This controller class manges rest endpoints of Showcase
 */
@RestController
@RequestMapping(value = "/api/showcase")
public class ShowcaseController {

    @Autowired
    ShowcaseService showcaseService;



    /**
     * This controller method is used to add or update showcase
     * @param showcase
     * @param subjectid
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "saveOrUpdateShowcase")
    @PostMapping(value = "/saveOrUpdateShowcase", consumes = "application/json")
    public ApiResponse<Showcase> saveOrUpdate(@RequestBody Showcase showcase,
                                              @RequestHeader("subjectid") Long subjectid){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,showcaseService.saveOrUpdateShowcase(showcase,subjectid),null);
    }


    /**
     * This controller method is used to add or update showcase
     * @param filterRequestDto
     *
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "getShowcasePaginated", excluded = true)
    @PostMapping(value = "/getShowcasePaginated", consumes = "application/json")
    public ApiResponse<Page<Showcase>> getShowcasePaginated(@RequestBody FilterRequestDto filterRequestDto){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,showcaseService.getShowcasePaginated(filterRequestDto),null);
    }


    /**
     * This controller method is used to get showcase by id
     * @param showcaseId
     *
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "getShowcaseById", excluded = true)
    @GetMapping(value = "/{showcaseId}/getShowcaseById")
    public ApiResponse<Showcase> getShowcaseById(@PathVariable("showcaseId") String showcaseId){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,showcaseService.findById(showcaseId),null);
    }


    /**
     * This controller method is used to add or update showcase
     * @param showcaseId
     * @param containerMediaId
     * @param subjectid
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "addUpdateContainerMediaOfShowcase")
    @PostMapping(value = "/{showcaseId}/addUpdateContainerMediaOfShowcase", consumes = "application/json")
    public ApiResponse<Showcase> addUpdateContainerMedia(@PathVariable("showcaseId") String showcaseId,
                                              @RequestBody String containerMediaId,
                                              @RequestHeader("subjectid") Long subjectid){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,showcaseService.addUpdateContainerMedia(containerMediaId,showcaseId,subjectid),null);
    }


    /**
     * This controller method is used to add or update showcase
     * @param showcaseId
     * @param containerMediaId
     * @param subjectid
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "removeContainerMediaOfShowcase")
    @PostMapping(value = "/{showcaseId}/removeContainerMediaOfShowcase", consumes = "application/json")
    public ApiResponse<Showcase> removeUpdateContainerMedia(@PathVariable("showcaseId") String showcaseId,
                                                         @RequestBody String containerMediaId,
                                                         @RequestHeader("subjectid") Long subjectid){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,showcaseService.addUpdateContainerMedia(containerMediaId,showcaseId,subjectid),null);
    }


    /**
     * This controller method is used to add GroupCoordinates to Showcase
     * @param showcaseId
     * @param groupName
     * @param coordinate
     * @param subjectid
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "addGroupCoordinateToShowcase")
    @PostMapping(value = "/{showcaseId}/group/{groupName}/addGroupCoordinateToShowcase", consumes = "application/json")
    public ApiResponse<Showcase> removeUpdateContainerMedia(@PathVariable("showcaseId") String showcaseId,
                                                            @PathVariable("groupName") String groupName,
                                                            @RequestBody Coordinate coordinate,
                                                            @RequestHeader("subjectid") Long subjectid){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,showcaseService.addGroupCoordinate(groupName,coordinate,showcaseId,subjectid),null);
    }


    /**
     * This controller method is used to remove GroupCoordinates From showcase
     * @param showcaseId
     * @param groupName
     * @param subjectid
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "removeGroupCoordinateFromShowcase")
    @PostMapping(value = "/{showcaseId}/removeGroupCoordinateFromShowcase", consumes = "application/json")
    public ApiResponse<Showcase> removeGroupGroupCoordinate(@PathVariable("showcaseId") String showcaseId,
                                                            @RequestBody String groupName,
                                                            @RequestHeader("subjectid") Long subjectid){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,showcaseService.removeGroupGroupCoordinate(groupName,showcaseId,subjectid),null);
    }

    /**
     * This controller method is used to add  Offering Products to showcase groups
     * @param showcaseId
     * @param groupName
     * @param offeringIds
     * @param subjectid
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "addGroupOfferingProductsToShowcase")
    @PostMapping(value = "/{showcaseId}/group/{groupName}/addGroupOfferingProductsToShowcase", consumes = "application/json")
    public ApiResponse<Showcase> addGroupOfferingProductsToShowcase(@PathVariable("showcaseId") String showcaseId,
                                                          @PathVariable("groupName") String groupName,
                                                            @RequestBody Set<String> offeringIds,
                                                            @RequestHeader("subjectid") Long subjectid){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,showcaseService.addGroupOfferingProducts(groupName,offeringIds,showcaseId,subjectid),null);
    }


    /**
     * This controller method is used to add  Offering Products to showcase groups
     * @param showcaseId
     * @param groupName
     * @param offeringIds
     * @param subjectid
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "removeGroupOfferingProductsFromShowcase")
    @PostMapping(value = "/{showcaseId}/group/{groupName}/removeGroupOfferingProductsFromShowcase", consumes = "application/json")
    public ApiResponse<Showcase> removeGroupOfferingProducts(@PathVariable("showcaseId") String showcaseId,
                                                                    @PathVariable("groupName") String groupName,
                                                                    @RequestBody Set<String> offeringIds,
                                                                    @RequestHeader("subjectid") Long subjectid){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,showcaseService.removeGroupOfferingProducts(groupName,offeringIds,showcaseId,subjectid),null);
    }



    /**
     * This controller method is used to add  Default Offering Products to showcase groups
     * @param showcaseId
     * @param groupName
     * @param offeringId
     * @param subjectid
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "setGroupDefaultOfferingProductToShowcase")
    @PostMapping(value = "/{showcaseId}/group/{groupName}/setGroupDefaultOfferingProductToShowcase", consumes = "application/json")
    public ApiResponse<Showcase> setGroupDefaultOfferingProductToShowcase(@PathVariable("showcaseId") String showcaseId,
                                                             @PathVariable("groupName") String groupName,
                                                             @RequestBody String offeringId,
                                                             @RequestHeader("subjectid") Long subjectid){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,showcaseService.setGroupDefaultOfferingProduct(groupName,offeringId,showcaseId,subjectid),null);
    }

    /**
     * This controller method is used to add  Default Offering Products to showcase groups
     * @param showcaseId
     * @param groupName
     * @param subjectid
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "removeGroupDefaultOfferingProductFromShowcase")
    @PostMapping(value = "/{showcaseId}/removeGroupDefaultOfferingProductFromShowcase", consumes = "application/json")
    public ApiResponse<Showcase> removeGroupDefaultOfferingProductFromShowcase(@PathVariable("showcaseId") String showcaseId,
                                                                          @RequestBody String groupName,
                                                                          @RequestHeader("subjectid") Long subjectid){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,showcaseService.removeGroupDefaultOfferingProduct(groupName,showcaseId,subjectid),null);
    }


    /**
     * This controller method is used to add materials To showcase
     * @param showcaseId
     * @param materialIds
     * @param subjectid
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "addMaterialsToShowcase")
    @PostMapping(value = "/{showcaseId}/addMaterialsToShowcase", consumes = "application/json")
    public ApiResponse<Showcase> addMaterialsToShowcase(@PathVariable("showcaseId") String showcaseId,
                                                                               @RequestBody Set<String> materialIds,
                                                                               @RequestHeader("subjectid") Long subjectid){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,showcaseService.addMaterials(materialIds,showcaseId,subjectid),null);
    }

    /**
     * This controller method is used to remove materials from showcase
     * @param showcaseId
     * @param materialIds
     * @param subjectid
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "removeMaterialsFromShowcase")
    @PostMapping(value = "/{showcaseId}/removeMaterialsFromShowcase", consumes = "application/json")
    public ApiResponse<Showcase> removeMaterials(@PathVariable("showcaseId") String showcaseId,
                                                        @RequestBody Set<String> materialIds,
                                                        @RequestHeader("subjectid") Long subjectid){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,showcaseService.removeMaterials(materialIds,showcaseId,subjectid),null);
    }
}
