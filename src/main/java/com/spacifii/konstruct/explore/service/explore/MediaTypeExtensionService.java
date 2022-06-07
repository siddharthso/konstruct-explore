package com.spacifii.konstruct.explore.service.explore;

import com.spacifii.konstruct.explore.entities.explore.MediaType;
import com.spacifii.konstruct.explore.entities.explore.MediaTypeExtension;

import java.util.Map;
import java.util.Set;

public interface MediaTypeExtensionService {

    MediaTypeExtension save(MediaTypeExtension mediaTypeExtension, Long subjectId);

    Map<MediaType,Set<String>> getAllExtensionsPerMediaType();

    Map<String,MediaType> getMediaTypeForExtensions();

    MediaTypeExtension getMediaTypeExtensionByExtension(String extension);

}
