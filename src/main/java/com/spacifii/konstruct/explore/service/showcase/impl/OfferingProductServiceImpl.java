package com.spacifii.konstruct.explore.service.showcase.impl;

import com.spacifii.konstruct.explore.annotation.NotToUseDuringMerge;
import com.spacifii.konstruct.explore.entities.showcases.OfferingProduct;
import com.spacifii.konstruct.explore.entities.showcases.OfferingViewTypeWiseMedia;
import com.spacifii.konstruct.explore.exception.showcase.OfferingProductNotFoundException;
import com.spacifii.konstruct.explore.model.dto.explore.FilterRequestDto;
import com.spacifii.konstruct.explore.repository.showcase.OfferingProductRepository;
import com.spacifii.konstruct.explore.service.explore.BeanUtils;
import com.spacifii.konstruct.explore.service.explore.QueryUtilService;
import com.spacifii.konstruct.explore.service.showcase.OfferingProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * This service class Manages OfferingProduct for ShowCase
 */
@Service
public class OfferingProductServiceImpl implements OfferingProductService {

    @Autowired
    OfferingProductRepository offeringProductRepository;

    @Autowired
    QueryUtilService queryUtilService;



    /**
     * This service method is used to save OfferingProduct
     *
     * @param offeringProduct
     * @return
     */
    @Override
    public OfferingProduct saveOfferingProduct(OfferingProduct offeringProduct) {
        Optional<OfferingProduct> offeringProductOptional = offeringProductRepository.findById(offeringProduct.getProductId());
        if(offeringProductOptional.isPresent()){
            OfferingProduct offeringProductExisting = offeringProductOptional.get();
            String[] arr  = BeanUtils.findFields(OfferingProduct.class, NotToUseDuringMerge.class);
            BeanUtils.copyProperties(offeringProduct,offeringProductExisting,arr);
            return offeringProductRepository.save(offeringProductExisting);
        }
        return offeringProductRepository.save(offeringProduct);
    }

    /**
     * This service method is used to get OfferingProductFiltered
     *
     * @param filterRequestDto
     * @return
     */
    @Override
    public Page<OfferingProduct> getOfferingProductFiltered(FilterRequestDto filterRequestDto) {

        return queryUtilService.getResultList(filterRequestDto,new HashSet<>(Arrays.asList("name")),new OfferingProduct());
    }

    /**
     * This service method is used to find OfferingProduct By id
     *
     * @param id
     * @return
     */
    @Override
    public OfferingProduct findById(String id) {
        Optional<OfferingProduct> offeringProductOptional = offeringProductRepository.findById(id);
        if(offeringProductOptional.isPresent()){
            return  offeringProductOptional.get();
        }
        throw new OfferingProductNotFoundException();
    }

    /**
     * This service method is used to add OfferingViewTypeWiseMedia to offering Product
     *
     * @param offeringViewTypeWiseMedias
     * @param productId
     * @return
     */
    @Override
    public OfferingProduct addOfferingViewTypeWiseMedia(LinkedHashSet<OfferingViewTypeWiseMedia> offeringViewTypeWiseMedias, String productId) {
        OfferingProduct offeringProduct = findById(productId);
        LinkedHashSet<OfferingViewTypeWiseMedia> offeringViewTypeWiseMediasExisting= offeringProduct.getOfferingViewTypeWiseMedia();
        offeringViewTypeWiseMediasExisting.removeAll(offeringViewTypeWiseMedias);
        offeringViewTypeWiseMediasExisting.addAll(offeringViewTypeWiseMedias);
        offeringProduct.setOfferingViewTypeWiseMedia(offeringViewTypeWiseMediasExisting);
        return offeringProductRepository.save(offeringProduct);
    }

    /**
     * This service method is sued to remove OfferingTypeWiseMedia from the OfferingProduct
     *
     * @param offeringViewTypeWiseMedias
     * @param productId
     * @return
     */
    @Override
    public OfferingProduct removeOfferingViewTypeWiseMedia(LinkedHashSet<OfferingViewTypeWiseMedia> offeringViewTypeWiseMedias, String productId) {
        OfferingProduct offeringProduct = findById(productId);
        LinkedHashSet<OfferingViewTypeWiseMedia> offeringViewTypeWiseMediasExisting= offeringProduct.getOfferingViewTypeWiseMedia();
        offeringViewTypeWiseMediasExisting.removeAll(offeringViewTypeWiseMedias);
        offeringProduct.setOfferingViewTypeWiseMedia(offeringViewTypeWiseMediasExisting);
        return offeringProductRepository.save(offeringProduct);
    }

    /**
     * This service method is used to find OfferingProducts by Ids
     *
     * @param offeringIds
     * @return
     */
    @Override
    public Set<OfferingProduct> findByIds(Set<String> offeringIds) {
        return new HashSet<>(BeanUtils.makeCollection(offeringProductRepository.findAllById(offeringIds)));
    }


}
