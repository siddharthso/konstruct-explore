package com.spacifii.konstruct.explore.service.showcase;

import com.spacifii.konstruct.explore.entities.showcases.OfferingProductViewType;

import java.util.List;
import java.util.Set;

/**
 * This service class manages OfferingProductViewType
 */
public interface OfferingProductViewTypeService {

    /**
     * This service method saves OfferingProductViewType
     * @param offeringProductViewType
     * @return
     */
    OfferingProductViewType save(OfferingProductViewType offeringProductViewType);

    /**
     * This service method find all OfferingProductViewType
     * @return
     */
    List<OfferingProductViewType> findAll();

    /**
     * This service method finds OfferingProductViewType by Id
     * @param id
     * @return
     */
    OfferingProductViewType findById(String id);

    /**
     * This service method is used to find OfferingProductViewType By id
     * @param ids
     * @return
     */
    Set<OfferingProductViewType> findByIds(Set<String> ids);
}
