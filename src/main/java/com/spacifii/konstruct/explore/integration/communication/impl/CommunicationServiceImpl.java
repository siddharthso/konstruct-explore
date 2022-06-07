package com.spacifii.konstruct.explore.integration.communication.impl;

import com.spacifii.konstruct.explore.exception.MessagingEngineException;
import com.spacifii.konstruct.explore.exception.MessagingEngineNotReachableException;
import com.spacifii.konstruct.explore.integration.communication.CommunicationService;
import com.spacifii.konstruct.explore.model.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class CommunicationServiceImpl implements CommunicationService {


    private final Logger logger = LoggerFactory.getLogger(CommunicationServiceImpl.class);

    @Autowired
    RestTemplate restTemplate;

    @Value("${communication.send.url}")
    String communicationSendUrl;



    /**
     * This service method is used to send communication
     *
     * @param event
     * @param placeholders
     */
    @Override
    public void sendCommunication(String event, Map<String, Object> placeholders) {

        sendCommunicationInternal(event, placeholders);
    }

    /**
     * This service method is used to send communication asynchronously
     *
     * @param event
     * @param placeholders
     */
    @Override
    @Async("exploreCachedThreadPool")
    public void sendCommunicationAsync(String event, Map<String, Object> placeholders) {

        sendCommunicationInternal(event, placeholders);
    }


    private void sendCommunicationInternal(String event, Map<String, Object> placeholders) {
        String urlToSend = communicationSendUrl.replace("{event}",event);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<Map<String,Object>> request = new HttpEntity<>(placeholders, headers);

        try {

            ApiResponse<Map<String,Object>> response = restTemplate.postForObject(urlToSend, request, ApiResponse.class);
            logger.debug("RESPONSE FROM COMMUNICATION FOR SENDING OTP : ->" + response.getPayload().toString());

        } catch (HttpClientErrorException e){
            e.printStackTrace();
            throw new MessagingEngineException();

        } catch (ResourceAccessException e){
            e.printStackTrace();
            throw  new MessagingEngineNotReachableException();
        }
    }
}
