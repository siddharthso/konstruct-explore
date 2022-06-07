package com.spacifii.konstruct.explore.repository.explore;

import com.spacifii.konstruct.explore.entities.explore.SpacifiiColor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This is DAO class for SpacifiiColor
 */
@Repository
public interface SpacifiiColorRepository extends MongoRepository<SpacifiiColor,String> {

    /**
     * This repository method returns all PrimaryColors
     * @return
     */
    List<SpacifiiColor> findByPrimaryTrue();
}
