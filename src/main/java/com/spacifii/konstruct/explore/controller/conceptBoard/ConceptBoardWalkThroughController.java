package com.spacifii.konstruct.explore.controller.conceptBoard;

import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardWalkThrough;
import com.spacifii.konstruct.explore.integration.rbac.annotation.RbacRegister;
import com.spacifii.konstruct.explore.model.APIResponseKey;
import com.spacifii.konstruct.explore.model.ApiResponse;
import com.spacifii.konstruct.explore.model.dto.explore.FilterRequestDto;
import com.spacifii.konstruct.explore.service.conceptBoard.ConceptBoardWalkThroughService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This is controller class for ConceptBoardWalkThrough
 */
@RestController(value = "/api/conceptBoardWalkThrough")
public class ConceptBoardWalkThroughController {

    @Autowired
    ConceptBoardWalkThroughService conceptBoardWalkThroughService;


    /**
     * This controller method is used to save or Update conceptBoardWalkThrough
     * @param conceptBoardWalkThrough
     * @param subjectId
     * @return
     */
    @CrossOrigin
    @RbacRegister(authorityName = "saveUpdateConceptBoardWalkThrough")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/saveUpdateConceptBoardWalkThrough", consumes = "application/json")
    public ApiResponse<ConceptBoardWalkThrough> saveUpdate(@RequestBody ConceptBoardWalkThrough conceptBoardWalkThrough,
                                                           @RequestHeader("subjectid") Long subjectId){
        return new ApiResponse<>(APIResponseKey.NEW_CREATION,conceptBoardWalkThroughService.saveOrUpdate(conceptBoardWalkThrough,subjectId),null);
    }


    /**
     * This controller method is used find conceptBoardWalkThrough by Id
     * @param id
     * @return
     */
    @CrossOrigin
    @RbacRegister(authorityName = "findConceptBoardWalkThroughById")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}/findConceptBoardWalkThroughById")
    public ApiResponse<ConceptBoardWalkThrough> getById(@PathVariable("id") String id){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,conceptBoardWalkThroughService.getById(id),null);
    }


    /**
     * This controller method is used find conceptBoardWalkThrough by ConceptBoardId
     * @param id
     * @return
     */
    @CrossOrigin
    @RbacRegister(authorityName = "findConceptBoardWalkThroughByConceptBoardId")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}/findConceptBoardWalkThroughByConceptBoardId")
    public ApiResponse<List<ConceptBoardWalkThrough>> findConceptBoardWalkThroughByConceptBoardId(@PathVariable("id") String id){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,conceptBoardWalkThroughService.findByConceptBoardId(id),null);
    }

    /**
     * This Controller method is used to get ConceptBoardWalkThrough via FilterRequestDto
     * @param filterRequestDto
     * @return
     */
    @CrossOrigin
    @RbacRegister(authorityName = "getConceptBoardWalkThroughFiltered")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/getConceptBoardWalkThroughFiltered", consumes = "application/json")
    public ApiResponse<Page<ConceptBoardWalkThrough>> saveUpdate(@RequestBody FilterRequestDto filterRequestDto){
        return new ApiResponse<>(APIResponseKey.NEW_CREATION,conceptBoardWalkThroughService.getConceptBoardWalkThroughFiltered(filterRequestDto),null);
    }

}
