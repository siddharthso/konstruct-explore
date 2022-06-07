package com.spacifii.konstruct.explore.controller.explore;

import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardWalkThrough;
import com.spacifii.konstruct.explore.entities.explore.ExploreWalkThrough;
import com.spacifii.konstruct.explore.integration.rbac.annotation.RbacRegister;
import com.spacifii.konstruct.explore.model.APIResponseKey;
import com.spacifii.konstruct.explore.model.ApiResponse;
import com.spacifii.konstruct.explore.model.dto.explore.FilterRequestDto;
import com.spacifii.konstruct.explore.service.explore.ExploreWalkThroughService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * This is controller class for ExploreBoardWalkThrough
 */
@RestController(value = "/api/exploreWalkThrough")
public class ExploreWalkThroughController {

    @Autowired
    ExploreWalkThroughService exploreWalkThroughService;


    /**
     * This controller method is used to save or Update ExploreWalkThrough
     * @param exploreWalkThrough
     * @param subjectId
     * @return
     */
    @CrossOrigin
    @RbacRegister(authorityName = "saveUpdateExploreWalkThrough")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/saveUpdateExploreWalkThrough", consumes = "application/json")
    public ApiResponse<ExploreWalkThrough> saveUpdate(@RequestBody ExploreWalkThrough exploreWalkThrough,
                                                      @RequestHeader("subjectid") Long subjectId){
        return new ApiResponse<>(APIResponseKey.NEW_CREATION,exploreWalkThroughService.saveOrUpdate(exploreWalkThrough,subjectId),null);
    }


    /**
     * This controller method is used find ExploreWalkThrough by Id
     * @param id
     * @return
     */
    @CrossOrigin
    @RbacRegister(authorityName = "findExploreWalkThroughById", excluded = true)
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}/findExploreWalkThroughById")
    public ApiResponse<ExploreWalkThrough> getById(@PathVariable("id") String id){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,exploreWalkThroughService.getById(id),null);
    }


    /**
     * This Controller method is used to get ExploreWalkThrough via FilterRequestDto
     * @param filterRequestDto
     * @return
     */
    @CrossOrigin
    @RbacRegister(authorityName = "getExploreWalkThroughFiltered")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/getExploreWalkThroughFiltered", consumes = "application/json")
    public ApiResponse<Page<ExploreWalkThrough>> saveUpdate(@RequestBody FilterRequestDto filterRequestDto){
        return new ApiResponse<>(APIResponseKey.NEW_CREATION,exploreWalkThroughService.getExploreWalkThroughFiltered(filterRequestDto),null);
    }

}
