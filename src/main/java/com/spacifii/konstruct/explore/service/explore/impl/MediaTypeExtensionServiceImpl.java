package com.spacifii.konstruct.explore.service.explore.impl;

import com.spacifii.konstruct.explore.entities.explore.MediaType;
import com.spacifii.konstruct.explore.entities.explore.MediaTypeExtension;
import com.spacifii.konstruct.explore.exception.explore.ExtensionAlreadyExistsException;
import com.spacifii.konstruct.explore.exception.explore.MediaTypeExtensionNotFoundException;
import com.spacifii.konstruct.explore.repository.explore.MediaTypeExtensionRepository;
import com.spacifii.konstruct.explore.service.explore.MediaTypeExtensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class MediaTypeExtensionServiceImpl implements MediaTypeExtensionService {

    @Autowired
    MediaTypeExtensionRepository mediaTypeExtensionRepository;


    @Override
    @Transactional
    public MediaTypeExtension save(MediaTypeExtension mediaTypeExtension, Long subjectId) {
        mediaTypeExtension.setExtension(mediaTypeExtension.getExtension().toUpperCase());
        if(mediaTypeExtensionRepository.findFirstByExtension(mediaTypeExtension.getExtension()) != null){
            throw new ExtensionAlreadyExistsException();
        }
        return mediaTypeExtensionRepository.save(mediaTypeExtension);
    }

    @Override
    public Map<MediaType, Set<String>> getAllExtensionsPerMediaType() {
        List<MediaTypeExtension> mediaTypeExtensions = mediaTypeExtensionRepository.findAll();
        Map<MediaType,Set<String>> mediaTypeSetMap = new TreeMap<>();
        if(mediaTypeExtensions != null){
            for (MediaTypeExtension mediaTypeExtension: mediaTypeExtensions) {
                Set<String> strings = mediaTypeSetMap.get(mediaTypeExtension.getMediaType());
                if(strings == null){
                    strings = new TreeSet<>();
                   // mediaTypeSetMap.put(mediaTypeExtension.getMediaType(), null);
                }
                strings.add(mediaTypeExtension.getExtension());
                mediaTypeSetMap.put(mediaTypeExtension.getMediaType(),strings);
            }
        }
        return mediaTypeSetMap;
    }

    @Override
    public Map<String, MediaType> getMediaTypeForExtensions() {
        List<MediaTypeExtension> mediaTypeExtensions = mediaTypeExtensionRepository.findAll();
        Map<String, MediaType> stringMediaTypeMap = new TreeMap<>();
        if(mediaTypeExtensions != null) {
            for (MediaTypeExtension mediaTypeExtension : mediaTypeExtensions) {
                stringMediaTypeMap.put(mediaTypeExtension.getExtension(),mediaTypeExtension.getMediaType());
            }
        }
        return stringMediaTypeMap;
    }

    @Override
    public MediaTypeExtension getMediaTypeExtensionByExtension(String extension) {
       MediaTypeExtension mediaTypeExtension = mediaTypeExtensionRepository.findFirstByExtension(extension.toUpperCase());
       if(mediaTypeExtension != null){
           return mediaTypeExtension;
       }
         throw new MediaTypeExtensionNotFoundException();
    }
}
