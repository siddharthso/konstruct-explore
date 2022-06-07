package com.spacifii.konstruct.explore.service.explore;

import com.spacifii.konstruct.explore.entities.explore.Media;
import com.spacifii.konstruct.explore.model.dto.explore.MediaApprovalDto;
import com.spacifii.konstruct.explore.model.dto.explore.WorkflowDecision;
import com.spacifii.konstruct.explore.repository.explore.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MediaWorkFlowActionStrategyContext {

    @Autowired
    @Qualifier("MediaWorkFlowActionApproveStrategy")
    MediaWorkFlowActionStrategy mediaWorkFlowActionApproveStrategy;

    @Autowired
    @Qualifier("MediaWorkFlowActionAskMoreInformationStrategy")
    MediaWorkFlowActionStrategy mediaWorkFlowActionAskMoreInformationStrategy;

    @Autowired
    @Qualifier("MediaWorkFlowActionRejectStrategy")
    MediaWorkFlowActionStrategy mediaWorkFlowActionRejectStrategy;

    @Autowired
    MediaRepository mediaRepository;


    private static Map<WorkflowDecision,MediaWorkFlowActionStrategy> strategyMap = new HashMap<>();

    @PostConstruct
    public void fillMap(){
        strategyMap.put(WorkflowDecision.APPROVED,mediaWorkFlowActionApproveStrategy);
        strategyMap.put(WorkflowDecision.ASK_MORE_INFORMATION,mediaWorkFlowActionAskMoreInformationStrategy);
        strategyMap.put(WorkflowDecision.REJECT,mediaWorkFlowActionRejectStrategy);

       // approveAll();
    }

    public void doAction(List<MediaApprovalDto> mediaApprovalDtos, Long subject){

        for (MediaApprovalDto  mediaApprovalDto: mediaApprovalDtos) {

            strategyMap.get(mediaApprovalDto.getWorkflowDecision()).doAction(mediaApprovalDto.getMediaIds(),subject);
        }

    }

    void approveAll(){
        List<Media> medias = mediaRepository.findAll();
        MediaApprovalDto mediaApprovalDto = new MediaApprovalDto();
        mediaApprovalDto.setWorkflowDecision(WorkflowDecision.APPROVED);
         mediaApprovalDto.setMediaIds(medias.stream().map(Media::getId).collect(Collectors.toSet()));
         doAction(Arrays.asList(mediaApprovalDto),1L);
    }






}
