package com.spacifii.konstruct.explore.repository.showcase;

import com.spacifii.konstruct.explore.entities.showcases.Showcase;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This is DAO class for Showcase
 */
@Repository
public interface ShowcaseRepository extends MongoRepository<Showcase,String> {

    /**
     * This repository method is used to get Showcases for Builder's property
     * @param builderId
     * @param propertyId
     * @return
     */
    List<Showcase> findByBuilderIdAndPropertyId(String builderId, String propertyId);

    /**
     * This repository method is used to get All Builder showcases
     * @param builder
     * @return
     */
    List<Showcase> findByBuilderId(String builder);

    /**
     * This repository method is used to get All Property Showcases
     * @param propertyId
     * @return
     */
    List<Showcase> findByPropertyId(String propertyId);
}
