package com.spacifii.konstruct.explore.service.showcase.impl;

import com.spacifii.konstruct.explore.annotation.NotToUseDuringMerge;
import com.spacifii.konstruct.explore.entities.showcases.OfferingProductViewType;
import com.spacifii.konstruct.explore.exception.showcase.OfferingProductViewTypeNotFoundException;
import com.spacifii.konstruct.explore.repository.showcase.OfferingProductViewTypeRepository;
import com.spacifii.konstruct.explore.service.explore.BeanUtils;
import com.spacifii.konstruct.explore.service.showcase.OfferingProductViewTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * This service class manages OfferingProductViewType
 */
@Service
public class OfferingProductViewTypeServiceImpl implements OfferingProductViewTypeService {

    @Autowired
    OfferingProductViewTypeRepository offeringProductViewTypeRepository;


    /**
     * This service method saves OfferingProductViewType
     *
     * @param offeringProductViewType
     * @return
     */
    @Override
    public OfferingProductViewType save(OfferingProductViewType offeringProductViewType) {
        if(offeringProductViewType.getId() != null){
            Optional<OfferingProductViewType> offeringProductViewTypeOptional = offeringProductViewTypeRepository.findById(offeringProductViewType.getId());
            if(offeringProductViewTypeOptional.isPresent()){
                OfferingProductViewType offeringProductViewTypeExisting = offeringProductViewTypeOptional.get();
                String[] arr  = BeanUtils.findFields(OfferingProductViewType.class, NotToUseDuringMerge.class);
                BeanUtils.copyProperties(offeringProductViewType,offeringProductViewTypeExisting,arr);
                return offeringProductViewTypeRepository.save(offeringProductViewTypeExisting);
            }
        }
        return offeringProductViewTypeRepository.save(offeringProductViewType);
    }

    /**
     * This service method find all OfferingProductViewType
     *
     * @return
     */
    @Override
    public List<OfferingProductViewType> findAll() {
        return offeringProductViewTypeRepository.findAll();
    }

    /**
     * This service method finds OfferingProductViewType by Id
     *
     * @param id
     * @return
     */
    @Override
    public OfferingProductViewType findById(String id) {
        Optional<OfferingProductViewType> offeringProductViewTypeOptional = offeringProductViewTypeRepository.findById(id);
        if(offeringProductViewTypeOptional.isPresent()){
            return offeringProductViewTypeOptional.get();
        }
       throw new OfferingProductViewTypeNotFoundException();
    }

    /**
     * This service method is used to find OfferingProductViewType By id
     *
     * @param ids
     * @return
     */
    @Override
    public Set<OfferingProductViewType> findByIds(Set<String> ids) {
        return new HashSet<>(BeanUtils.makeCollection(offeringProductViewTypeRepository.findAllById(ids)));
    }
}
