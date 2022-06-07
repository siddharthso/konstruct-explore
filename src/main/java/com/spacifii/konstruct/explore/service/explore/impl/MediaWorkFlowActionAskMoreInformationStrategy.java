package com.spacifii.konstruct.explore.service.explore.impl;

import com.spacifii.konstruct.explore.service.explore.MediaWorkFlowActionStrategy;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service("MediaWorkFlowActionAskMoreInformationStrategy")
public class MediaWorkFlowActionAskMoreInformationStrategy implements MediaWorkFlowActionStrategy {


    @Override
    public void doAction(Set<String> mediaIds, Long subjectId) {
        //TODO: DO SOMETHING
    }
}
