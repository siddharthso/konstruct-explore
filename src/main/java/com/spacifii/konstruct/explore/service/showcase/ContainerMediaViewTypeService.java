package com.spacifii.konstruct.explore.service.showcase;

import com.spacifii.konstruct.explore.entities.showcases.ContainerMediaViewType;

import java.util.List;
import java.util.Set;

/**
 * This service class manges ContainerMediaViewType
 */
public interface ContainerMediaViewTypeService {

    /**
     * This method is used to save ContainerMediaViewType
     * @param containerMediaViewType
     * @return
     */
    ContainerMediaViewType save(ContainerMediaViewType containerMediaViewType);

    /**
     * This method is used to get All ContainerMediaViewType
     * @return
     */
    List<ContainerMediaViewType> getAll();


    /**
     * This method is used to get ContainerMediaViewType by Id
     * @param id
     * @return
     */
    ContainerMediaViewType findById(String id);


    /**
     * This service method is used to add OfferingProductViewTypes to ContainerMediaViewType
     * @param offeringProductViewTypeIds
     * @param containerMediaViewTypeId
     * @return
     */
    ContainerMediaViewType addOfferingProductViewType(Set<String> offeringProductViewTypeIds, String containerMediaViewTypeId);

    /**
     * This service method is used to remove OfferingProductViewTypes from ContainerMediaViewType
     * @param offeringProductViewTypeIds
     * @param containerMediaViewTypeId
     * @return
     */
    ContainerMediaViewType removeOfferingProductViewType(Set<String> offeringProductViewTypeIds, String containerMediaViewTypeId);



}
