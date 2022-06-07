package com.spacifii.konstruct.explore.service.showcase;

import com.spacifii.konstruct.explore.entities.showcases.OfferingProduct;
import com.spacifii.konstruct.explore.entities.showcases.OfferingViewTypeWiseMedia;
import com.spacifii.konstruct.explore.model.dto.explore.FilterRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * This service class Manages OfferingProduct for ShowCase
 */
public interface OfferingProductService {

    /**
     * This service method is used to save OfferingProduct
     * @param offeringProduct
     * @return
     */
    OfferingProduct saveOfferingProduct(OfferingProduct offeringProduct);

    /**
     * This service method is used to get OfferingProductFiltered
     * @param filterRequestDto
     * @return
     */
    Page<OfferingProduct> getOfferingProductFiltered(FilterRequestDto filterRequestDto);


    /**
     * This service method is used to find OfferingProduct By id
     * @param id
     * @return
     */
    OfferingProduct findById(String id);

    /**
     * This service method is used to add OfferingViewTypeWiseMedia to offering Product
     * @param offeringViewTypeWiseMedias
     * @param productId
     * @return
     */
    OfferingProduct addOfferingViewTypeWiseMedia(LinkedHashSet<OfferingViewTypeWiseMedia> offeringViewTypeWiseMedias, String productId);


    /**
     * This service method is sued to remove OfferingTypeWiseMedia from the OfferingProduct
     * @param offeringViewTypeWiseMedias,
     * @param productId
     * @return
     */
    OfferingProduct removeOfferingViewTypeWiseMedia(LinkedHashSet<OfferingViewTypeWiseMedia> offeringViewTypeWiseMedias, String productId);

    /**
     * This service method is used to find OfferingProducts by Ids
     * @param offeringIds
     * @return
     */
    Set<OfferingProduct> findByIds(Set<String> offeringIds);

}
