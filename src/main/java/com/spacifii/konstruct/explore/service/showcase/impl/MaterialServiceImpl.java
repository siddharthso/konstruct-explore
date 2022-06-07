package com.spacifii.konstruct.explore.service.showcase.impl;

import com.spacifii.konstruct.explore.annotation.NotToUseDuringMerge;
import com.spacifii.konstruct.explore.entities.showcases.Material;
import com.spacifii.konstruct.explore.exception.showcase.MaterialNotFoundException;
import com.spacifii.konstruct.explore.model.dto.explore.FilterRequestDto;
import com.spacifii.konstruct.explore.repository.showcase.MaterialRepository;
import com.spacifii.konstruct.explore.service.explore.BeanUtils;
import com.spacifii.konstruct.explore.service.explore.QueryUtilService;
import com.spacifii.konstruct.explore.service.showcase.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * This service class manges Material
 */
@Service
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    MaterialRepository materialRepository;

    @Autowired
    QueryUtilService queryUtilService;



    /**
     * This service method is used to save or Update Material
     *
     * @param material
     * @return
     */
    @Override
    public Material saveOrUpdate(Material material) {
        if(material.getId() != null) {
            Optional<Material> materialOptional = materialRepository.findById(material.getId());
            if(materialOptional.isPresent()){
                Material materialExisting = materialOptional.get();
                String[] arr  = BeanUtils.findFields(Material.class, NotToUseDuringMerge.class);
                BeanUtils.copyProperties(material,materialExisting,arr);
                return materialRepository.save(materialExisting);
            }
        }
        return materialRepository.save(material);
    }

    /**
     * This service method is used to find all Materials
     *
     * @return
     */
    @Override
    public List<Material> findAll() {
        return materialRepository.findAll();
    }

    /**
     * This service method is used to find Material by Id
     *
     * @param id
     * @return
     */
    @Override
    public Material findById(String id) {
        Optional<Material> materialOptional = materialRepository.findById(id);
        if(materialOptional.isPresent()){
            return materialOptional.get();
        }
        throw new MaterialNotFoundException();
    }

    /**
     * This service method is used to find Paginated Materials via FilterRequestDto
     *
     * @param filterRequestDto
     * @return
     */
    @Override
    public Page<Material> getMaterialFilterred(FilterRequestDto filterRequestDto) {
        return queryUtilService.getResultList(filterRequestDto,new HashSet<>(Arrays.asList("name")),new Material());
    }

    /**
     * This service method is used to get Materials by Ids
     *
     * @param ids
     * @return
     */
    @Override
    public Set<Material> getMaterialsByIds(Set<String> ids) {
        return new HashSet<>(BeanUtils.makeCollection(materialRepository.findAllById(ids)));
    }
}
