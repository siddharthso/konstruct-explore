package com.spacifii.konstruct.explore.service.showcase.impl;

import com.spacifii.konstruct.explore.annotation.NotToUseDuringMerge;
import com.spacifii.konstruct.explore.entities.showcases.ContainerMedia;
import com.spacifii.konstruct.explore.entities.showcases.ContainerMediaViewType;
import com.spacifii.konstruct.explore.entities.showcases.OfferingProductViewType;
import com.spacifii.konstruct.explore.exception.showcase.ContainerMediaViewTypeNotFoundException;
import com.spacifii.konstruct.explore.repository.showcase.ContainerMediaViewTypeRepository;
import com.spacifii.konstruct.explore.service.explore.BeanUtils;
import com.spacifii.konstruct.explore.service.showcase.ContainerMediaViewTypeService;
import com.spacifii.konstruct.explore.service.showcase.OfferingProductViewTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * This service class manges ContainerMediaViewType
 */
@Service
public class ContainerMediaViewTypeServiceImpl implements ContainerMediaViewTypeService {

    @Autowired
    ContainerMediaViewTypeRepository containerMediaViewTypeRepository;

    @Autowired
    OfferingProductViewTypeService offeringProductViewTypeService;



    /**
     * This method is used to save ContainerMediaViewType
     *
     * @param containerMediaViewType
     * @return
     */
    @Override
    public ContainerMediaViewType save(ContainerMediaViewType containerMediaViewType) {
        containerMediaViewType.setName(containerMediaViewType.getName().toUpperCase());
        containerMediaViewType.setId(containerMediaViewType.getName());
        Optional<ContainerMediaViewType> containerMediaViewTypeOptional = containerMediaViewTypeRepository.findById(containerMediaViewType.getId());
        if(containerMediaViewTypeOptional.isPresent()){
            ContainerMediaViewType containerMediaViewTypeExisting = containerMediaViewTypeOptional.get();
            String[] arr  = BeanUtils.findFields(ContainerMediaViewType.class, NotToUseDuringMerge.class);
            BeanUtils.copyProperties(containerMediaViewType,containerMediaViewTypeExisting,arr);
            return containerMediaViewTypeRepository.save(containerMediaViewTypeExisting);
        }
        return containerMediaViewTypeRepository.save(containerMediaViewType);
    }

    /**
     * This method is used to get All ContainerMediaViewType
     *
     * @return
     */
    @Override
    public List<ContainerMediaViewType> getAll() {
        return containerMediaViewTypeRepository.findAll();
    }

    /**
     * This method is used to get ContainerMediaViewType by Id
     *
     * @param id
     * @return
     */
    @Override
    public ContainerMediaViewType findById(String id) {
        Optional<ContainerMediaViewType> containerMediaViewTypeOptional = containerMediaViewTypeRepository.findById(id);
        if(containerMediaViewTypeOptional.isPresent()){
            return containerMediaViewTypeOptional.get();
        }
        throw new ContainerMediaViewTypeNotFoundException();
    }

    /**
     * This service method is used to add OfferingProductViewTypes to ContainerMediaViewType
     *
     * @param offeringProductViewTypeIds
     * @param containerMediaViewTypeId
     * @return
     */
    @Override
    public ContainerMediaViewType addOfferingProductViewType(Set<String> offeringProductViewTypeIds, String containerMediaViewTypeId) {
        ContainerMediaViewType containerMediaViewType = findById(containerMediaViewTypeId);
        Set<OfferingProductViewType> offeringProductViewTypes = offeringProductViewTypeService.findByIds(offeringProductViewTypeIds);
        Set<OfferingProductViewType> offeringProductViewTypesExisting = containerMediaViewType.getOfferingProductViewTypes();
        offeringProductViewTypesExisting.addAll(offeringProductViewTypes);
        containerMediaViewType.setOfferingProductViewTypes(offeringProductViewTypesExisting);
        return containerMediaViewTypeRepository.save(containerMediaViewType);
    }

    /**
     * This service method is used to remove OfferingProductViewTypes from ContainerMediaViewType
     *
     * @param offeringProductViewTypeIds
     * @param containerMediaViewTypeId
     * @return
     */
    @Override
    public ContainerMediaViewType removeOfferingProductViewType(Set<String> offeringProductViewTypeIds, String containerMediaViewTypeId) {
        ContainerMediaViewType containerMediaViewType = findById(containerMediaViewTypeId);
        Set<OfferingProductViewType> offeringProductViewTypes = offeringProductViewTypeService.findByIds(offeringProductViewTypeIds);
        Set<OfferingProductViewType> offeringProductViewTypesExisting = containerMediaViewType.getOfferingProductViewTypes();
        offeringProductViewTypesExisting.removeAll(offeringProductViewTypes);
        containerMediaViewType.setOfferingProductViewTypes(offeringProductViewTypesExisting);
        return containerMediaViewTypeRepository.save(containerMediaViewType);
    }
}
