package com.spacifii.konstruct.explore.controller.explore;

import com.spacifii.konstruct.explore.entities.explore.FeaturedKeywordMenu;
import com.spacifii.konstruct.explore.integration.rbac.annotation.RbacRegister;
import com.spacifii.konstruct.explore.model.APIResponseKey;
import com.spacifii.konstruct.explore.model.ApiResponse;
import com.spacifii.konstruct.explore.model.dto.explore.FeaturedKeywordMenuRequestDto;
import com.spacifii.konstruct.explore.service.explore.FeaturedKeywordMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This is controller class for FeaturedKeywordMenu
 */

@RestController
@RequestMapping(value = "/api/featuredKeywordMenu")
public class FeaturedKeywordMenuController {


    @Autowired
    FeaturedKeywordMenuService featuredKeywordMenuService;

    /**
     * This controller method gets all FeaturedKeywordMenu
     * @return
     */
    @RbacRegister(excluded = true,authorityName = "getAllFeaturedKeywordMenu")
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/api/getAllFeaturedKeywordMenu")
    public ApiResponse<List<FeaturedKeywordMenu>> getAllFeaturedKeywordMenu(){

        return new ApiResponse<>(APIResponseKey.ALL_GOOD,featuredKeywordMenuService.getAll(),null);
    }


    /**
     * This controller method is used to add FeaturedKeywordMenu
     * @param featuredKeywordMenuRequestDto
     * @return
     */
    @RbacRegister(excluded = false,authorityName = "addFeaturedKeywordMenu")
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/api/addFeaturedKeywordMenu")
    public ApiResponse<FeaturedKeywordMenu> addFeaturedKeywordMenu(@RequestBody FeaturedKeywordMenuRequestDto featuredKeywordMenuRequestDto){

        return new ApiResponse<>(APIResponseKey.ALL_GOOD,featuredKeywordMenuService.save(featuredKeywordMenuRequestDto),null);
    }
}
