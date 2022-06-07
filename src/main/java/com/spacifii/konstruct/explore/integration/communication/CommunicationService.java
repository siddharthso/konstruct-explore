package com.spacifii.konstruct.explore.integration.communication;

import java.util.Map;

/**
 * This service class is used to send Comunication
 */
public interface CommunicationService {


    /**
     * This service method is used to send communication
     * @param event
     * @param placeholders
     */
    void sendCommunication(String event, Map<String,Object> placeholders);

    /**
     * This service method is used to send communication asynchronously
     * @param event
     * @param placehoders
     */
    void sendCommunicationAsync(String event, Map<String,Object> placehoders);
}
