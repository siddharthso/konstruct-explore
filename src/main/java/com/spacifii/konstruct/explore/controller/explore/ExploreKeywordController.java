package com.spacifii.konstruct.explore.controller.explore;

import com.spacifii.konstruct.explore.entities.explore.ExploreKeyword;
import com.spacifii.konstruct.explore.integration.rbac.annotation.RbacRegister;
import com.spacifii.konstruct.explore.model.APIResponseKey;
import com.spacifii.konstruct.explore.model.ApiResponse;
import com.spacifii.konstruct.explore.model.dto.explore.ExploreKeywordRequestDto;
import com.spacifii.konstruct.explore.service.explore.BeanUtils;
import com.spacifii.konstruct.explore.service.explore.ExploreKeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This is controller class for ExploreKeyword
 */
@RestController
@RequestMapping("/api/exploreKeyword")
public class ExploreKeywordController {

    @Autowired
    ExploreKeywordService exploreKeywordService;


    /**
     * This Controller Method is used for adding new ExploreKeyword
     * @param exploreKeywordRequestDto
     * @param subjecId
     * @return
     */
    @RbacRegister(authorityName = "addExploreKeyword")
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/addExploreKeyword", consumes = "application/json")
    public ApiResponse<Void> save(@RequestBody ExploreKeywordRequestDto exploreKeywordRequestDto,
                                    @RequestHeader("subjectid") Long subjecId ){

        ExploreKeyword exploreKeyword = BeanUtils.getConverted(exploreKeywordRequestDto,ExploreKeyword.class);
        exploreKeywordService.save(exploreKeyword,subjecId);
        return new ApiResponse<>(APIResponseKey.NEW_CREATION,null,null);

    }

    /**
     * This Controller Method is used for getting all exploreKeywords
     * @return
     */
    @RbacRegister(authorityName = "getExploreKeywords",excluded = true)
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/getExploreKeywords")
    public ApiResponse<List<ExploreKeyword>> getExploreKeywords(){

        return new ApiResponse<>(APIResponseKey.ALL_GOOD,
               exploreKeywordService.getExploreKeywords(),null);

    }


}
