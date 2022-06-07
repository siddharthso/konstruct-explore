package com.spacifii.konstruct.explore.controller.showcase;

import com.spacifii.konstruct.explore.entities.showcases.OfferingProduct;
import com.spacifii.konstruct.explore.entities.showcases.OfferingViewTypeWiseMedia;
import com.spacifii.konstruct.explore.integration.rbac.annotation.RbacRegister;
import com.spacifii.konstruct.explore.model.APIResponseKey;
import com.spacifii.konstruct.explore.model.ApiResponse;
import com.spacifii.konstruct.explore.model.dto.explore.FilterRequestDto;
import com.spacifii.konstruct.explore.service.showcase.OfferingProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashSet;

/**
 * This controller class manages OfferingProduct Rest endpoints
 */
@RestController
@RequestMapping(value = "/api/showcaseOfferingProduct")
public class OfferingProductController {

    @Autowired
    OfferingProductService offeringProductService;


    /**
     * This controller method is used to add or update Offering Product
     * @param offeringProduct
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "saveShowcaseOfferingProduct")
    @PostMapping(value = "/saveShowcaseOfferingProduct", consumes = "application/json")
    public ApiResponse<OfferingProduct> saveOrUpdate(@RequestBody OfferingProduct offeringProduct,
                                                     @RequestHeader("subjectid") Long subjectid){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,offeringProductService.saveOfferingProduct(offeringProduct),null);
    }

    /**
     * This controller method is get Offering Product Paginated
     * @param filterRequestDto
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "getShowcaseOfferingProductPaginated", excluded = true)
    @PostMapping(value = "/getShowcaseOfferingProductPaginated", consumes = "application/json")
    public ApiResponse<Page<OfferingProduct>> getOfferingProductFiltered(@RequestBody FilterRequestDto filterRequestDto){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,offeringProductService.getOfferingProductFiltered(filterRequestDto),null);
    }


    /**
     * This controller method is get Offering Product by Id
     * @param showcaseOfferingId
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "getShowcaseOfferingProductById", excluded = true)
    @GetMapping(value = "/{showcaseOfferingId}/getShowcaseOfferingProductById", consumes = "application/json")
    public ApiResponse<OfferingProduct> getOfferingProductFiltered(@PathVariable("showcaseOfferingId") String showcaseOfferingId){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,offeringProductService.findById(showcaseOfferingId),null);
    }


    /**
     * This controller method adds OfferingViewTypeWiseMedia To OfferingProduct
     * @param showcaseOfferingId
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "addOfferingViewTypeWiseMediaToOfferingProduct")
    @PostMapping(value = "/{showcaseOfferingId}/addOfferingViewTypeWiseMediaToOfferingProduct", consumes = "application/json")
    public ApiResponse<OfferingProduct> addOfferingViewTypeWiseMedia(LinkedHashSet<OfferingViewTypeWiseMedia> offeringViewTypeWiseMedias,
                                                                     @PathVariable("showcaseOfferingId") String showcaseOfferingId){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,offeringProductService.addOfferingViewTypeWiseMedia(offeringViewTypeWiseMedias,showcaseOfferingId),null);
    }

    /**
     * This controller method adds OfferingViewTypeWiseMedia To OfferingProduct
     * @param showcaseOfferingId
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "removeOfferingViewTypeWiseMediaFromOfferingProduct")
    @PostMapping(value = "/{showcaseOfferingId}/removeOfferingViewTypeWiseMediaFromOfferingProduct", consumes = "application/json")
    public ApiResponse<OfferingProduct> removeOfferingViewTypeWiseMedia(LinkedHashSet<OfferingViewTypeWiseMedia> offeringViewTypeWiseMedias,
                                                                     @PathVariable("showcaseOfferingId") String showcaseOfferingId){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,offeringProductService.removeOfferingViewTypeWiseMedia(offeringViewTypeWiseMedias,showcaseOfferingId),null);
    }

}
