package com.spacifii.konstruct.explore.controller.explore;

import com.spacifii.konstruct.explore.entities.explore.FeaturedKeywordFilter;
import com.spacifii.konstruct.explore.integration.rbac.annotation.RbacRegister;
import com.spacifii.konstruct.explore.model.APIResponseKey;
import com.spacifii.konstruct.explore.model.ApiResponse;
import com.spacifii.konstruct.explore.model.dto.explore.FeaturedKeywordFilterRequestDto;
import com.spacifii.konstruct.explore.service.explore.FeaturedKeywordFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * This is controller class for FeaturedKeywordFilter
 */
@RestController
@RequestMapping(value = "/api/featuredKeywordFilter")
public class FeaturedKeywordFilterController {


    @Autowired
    FeaturedKeywordFilterService featuredKeywordFilterService;

    /**
     * This controller method gets all FeaturedKeywordFilter
     * @return
     */
    @RbacRegister(excluded = true,authorityName = "getAllFeaturedKeywordFilter")
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/api/getAllFeaturedKeywordFilter")
    public ApiResponse<List<FeaturedKeywordFilter>> getAllFeaturedKeywordFilter(){

        return new ApiResponse<>(APIResponseKey.ALL_GOOD,featuredKeywordFilterService.findAllSorted(),null);
    }


    /**
     * This controller method is used to add FeaturedKeywordFilter
     * @param featuredKeywordMenuRequestDto
     * @return
     */
    @RbacRegister(excluded = false,authorityName = "addFeaturedKeywordFilter")
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/api/addFeaturedKeywordFilter")
    public ApiResponse<FeaturedKeywordFilter> addFeaturedKeywordFilter(@RequestBody FeaturedKeywordFilterRequestDto featuredKeywordMenuRequestDto){

        return new ApiResponse<>(APIResponseKey.ALL_GOOD,featuredKeywordFilterService.save(featuredKeywordMenuRequestDto),null);
    }

}
