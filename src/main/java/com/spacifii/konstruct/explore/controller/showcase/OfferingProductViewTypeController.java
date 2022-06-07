package com.spacifii.konstruct.explore.controller.showcase;

import com.spacifii.konstruct.explore.entities.showcases.OfferingProductViewType;
import com.spacifii.konstruct.explore.integration.rbac.annotation.RbacRegister;
import com.spacifii.konstruct.explore.model.APIResponseKey;
import com.spacifii.konstruct.explore.model.ApiResponse;
import com.spacifii.konstruct.explore.service.showcase.OfferingProductViewTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This controller class manages OfferingProductViewType rest endpoints
 *
 */
@RestController
@RequestMapping(value = "/api/showcaseOfferingProductViewType")
public class OfferingProductViewTypeController {

    @Autowired
    OfferingProductViewTypeService offeringProductViewTypeService;


    /**
     * This controller method adds  or updates OfferingProductViewType
     * @param offeringProductViewType
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "saveShowcaseOfferingProductViewType")
    @PostMapping(value = "/saveShowcaseOfferingProductViewType", consumes = "application/json")
    public ApiResponse<OfferingProductViewType> removeOfferingViewTypeWiseMedia(@RequestBody OfferingProductViewType offeringProductViewType,
                                                                                @RequestHeader("subjectid") Long subjectId){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,offeringProductViewTypeService.save(offeringProductViewType),null);
    }


    /**
     * This controller method find offeringProductViewType by  Id
     * @param offeringProductViewTypeId
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "getShowcaseOfferingProductViewTypeById", excluded = true)
    @GetMapping(value = "/{offeringProductViewTypeId}/getShowcaseOfferingProductViewTypeById")
    public ApiResponse<OfferingProductViewType> removeOfferingViewTypeWiseMedia(@PathVariable("offeringProductViewTypeId") String offeringProductViewTypeId){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,offeringProductViewTypeService.findById(offeringProductViewTypeId),null);
    }


    /**
     * This controller method adds  or updates OfferingProductViewType
     *
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "getAllShowcaseOfferingProductViewType", excluded = true)
    @GetMapping(value = "/getAllShowcaseOfferingProductViewType")
    public ApiResponse<List<OfferingProductViewType>> findAll(){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,offeringProductViewTypeService.findAll(),null);
    }
}
