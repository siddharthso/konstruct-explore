package com.spacifii.konstruct.explore.repository.explore;

import com.spacifii.konstruct.explore.entities.explore.MediaTypeExtension;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface MediaTypeExtensionRepository extends MongoRepository<MediaTypeExtension, String> {

    MediaTypeExtension findFirstByExtension(String extension);

}
