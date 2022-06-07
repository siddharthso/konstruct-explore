package com.spacifii.konstruct.explore.controller.explore;

import com.spacifii.konstruct.explore.integration.rbac.annotation.RbacRegister;
import com.spacifii.konstruct.explore.model.APIResponseKey;
import com.spacifii.konstruct.explore.model.ApiResponse;
import com.spacifii.konstruct.explore.model.dto.explore.EnvelopedSpacifiiColor;
import com.spacifii.konstruct.explore.service.explore.SpacifiiColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * This is controller class for spacifiiColor
 */
@RestController(value = "/api/spacifiiColor")
public class SpacifiiColorController {


    @Autowired
    SpacifiiColorService spacifiiColorService;

    /**
     * This controller method is used to get all EvelopedSpacifiiColor
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @RbacRegister(authorityName = "getAllEnvelopedSpacifiiColor", excluded = true)
    @GetMapping(value = "/getAllEnvelopedSpacifiiColor")
    public ApiResponse<Set<EnvelopedSpacifiiColor>> getAllEnvelopedSpacifiiColor(){

        return new ApiResponse<>(APIResponseKey.ALL_GOOD,spacifiiColorService.getEnvelopedSpacifiiColor(),null);
    }

}
