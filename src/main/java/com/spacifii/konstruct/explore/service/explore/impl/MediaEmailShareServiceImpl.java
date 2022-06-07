package com.spacifii.konstruct.explore.service.explore.impl;

import com.spacifii.konstruct.explore.entities.explore.Media;
import com.spacifii.konstruct.explore.entities.explore.MediaEmailShare;
import com.spacifii.konstruct.explore.integration.communication.CommunicationService;
import com.spacifii.konstruct.explore.repository.explore.MediaEmailShareRepository;
import com.spacifii.konstruct.explore.service.explore.MediaEmailShareService;
import com.spacifii.konstruct.explore.service.explore.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * This service class manages MediaEmailShare
 */
@Service
public class MediaEmailShareServiceImpl implements MediaEmailShareService {

    @Autowired
    MediaEmailShareRepository mediaEmailShareRepository;

    @Autowired
    MediaService mediaService;

    @Autowired
    CommunicationService communicationService;

    @Value("${mediaPublicUrl}")
    String mediaPublicUrl;

    /**
     * This service method is used to Share Media via Email
     *
     * @param mediaEmailShare
     * @param subjectId
     * @return
     */
    @Override
    public Boolean shareMedia(MediaEmailShare mediaEmailShare, Long subjectId) {
        Media media = mediaService.findById(mediaEmailShare.getMediaId());
        mediaEmailShare.preSave(subjectId);
        mediaEmailShareRepository.save(mediaEmailShare);
        sendShareCommunication(media,mediaEmailShare);
        return true;
    }


    private void sendShareCommunication (Media media, MediaEmailShare mediaEmailShare ) {
        for (String s : mediaEmailShare.getEmailIdsTo()) {
           final Map<String, Object> placeHolders = new HashMap<>();
            placeHolders.put("MEDIA_IMAGEURL", media.getUrl().replace("CHANGE_MY_SIZE", "400x400"));
            placeHolders.put("USEREMAIL", mediaEmailShare.getEmailIdFrom());
            placeHolders.put("USERNAME", mediaEmailShare.getName() == null ? "SPACIFII USER": mediaEmailShare.getName());
            placeHolders.put("MEDIA_SHAREURL", mediaPublicUrl + media.getId());
            placeHolders.put("CUSTOMER_UNIQUECUSTOMERID", "ABC");
            placeHolders.put("MEDIA_NAME", media.getName() == null ? "SPACIFII MEDIA" : media.getName());
            placeHolders.put("CUSTOMER_EMAIL1", s);
            communicationService.sendCommunicationAsync("EXPLOREMEDIASHARE", placeHolders);
        }

    }

}
