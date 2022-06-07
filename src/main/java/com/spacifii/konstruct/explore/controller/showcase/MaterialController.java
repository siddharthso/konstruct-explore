package com.spacifii.konstruct.explore.controller.showcase;

import com.spacifii.konstruct.explore.entities.showcases.Material;
import com.spacifii.konstruct.explore.integration.rbac.annotation.RbacRegister;
import com.spacifii.konstruct.explore.model.APIResponseKey;
import com.spacifii.konstruct.explore.model.ApiResponse;
import com.spacifii.konstruct.explore.model.dto.explore.FilterRequestDto;
import com.spacifii.konstruct.explore.service.showcase.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This controller class manages Material
 */
@RestController
@RequestMapping(value = "/api/material")
public class MaterialController {

    @Autowired
    MaterialService materialService;


    /**
     * This controller method is used to add or update Material
     * @param material
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "saveUpdateMaterial")
    @PostMapping(value = "/saveUpdateMaterial", consumes = "application/json")
    public ApiResponse<Material> saveOrUpdate(@RequestBody Material material,
                                                          @RequestHeader("subjectid") Long subjectid){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,materialService.saveOrUpdate(material),null);
    }



    /**
     * This controller method gets Material By Id
     * @param materialId
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "getMaterialById", excluded = true)
    @GetMapping(value = "{materialId}/getMaterialById")
    public ApiResponse<Material> findById(@PathVariable("materialId") String materialId){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,materialService.findById(materialId),null);
    }


    /**
     * This controller method gets Materials
     *
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "getAllMaterial", excluded = true)
    @GetMapping(value = "/getAllMaterial")
    public ApiResponse<List<Material>> findAll(){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,materialService.findAll(),null);
    }

    /**
     * This controller method is used to Material Paginated
     * @param filterRequestDto
     * @return
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @RbacRegister(authorityName = "saveOrUpdate", excluded = true)
    @PostMapping(value = "/getMaterialPaginated", consumes = "application/json")
    public ApiResponse<Page<Material>> saveOrUpdate(@RequestBody FilterRequestDto filterRequestDto){
        return new ApiResponse<>(APIResponseKey.ALL_GOOD,materialService.getMaterialFilterred(filterRequestDto),null);
    }
}
