package com.spacifii.konstruct.explore.service.conceptBoard.impl;

import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoard;
import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardShare;
import com.spacifii.konstruct.explore.exception.conceptBoard.ConceptBoardShareNotFoundException;
import com.spacifii.konstruct.explore.integration.communication.CommunicationService;
import com.spacifii.konstruct.explore.model.dto.conceptBoard.ConceptBoardEmailShareProfileDto;
import com.spacifii.konstruct.explore.model.dto.explore.UserProfileMiniDto;
import com.spacifii.konstruct.explore.model.dto.explore.UserProfileMiniDto2;
import com.spacifii.konstruct.explore.repository.conceptBoard.ConceptBoardShareRepository;
import com.spacifii.konstruct.explore.service.conceptBoard.ConceptBoardService;
import com.spacifii.konstruct.explore.service.conceptBoard.ConceptBoardShareService;
import com.spacifii.konstruct.explore.service.explore.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * This service class manages ConceptBoardShares
 */
@Service
public class ConceptBoardShareServiceImpl implements ConceptBoardShareService {

    @Autowired
    ConceptBoardShareRepository conceptBoardShareRepository;

    @Autowired
    UserProfileService userProfileService;


    @Autowired
    ConceptBoardService conceptBoardService;

    @Autowired
    CommunicationService communicationService;

    @Value("${defaultConceptBoardImageUrl}")
    private String conceptBoardDefaultImageUrl;

    @Value("${conceptBoardPublicUrl}")
    private String conceptBoardPublicUrl;


    /**
     * This method is used to fix my shares where Someone has shared me ConceptBoard which I dont have access to because never registered
     *
     * @param subjectId
     */
    @Override
    public void fixMyShare(Long subjectId) {
      String email = userProfileService.getEmailIdForSubjectId(subjectId);
      List<ConceptBoardShare> conceptBoardShares = conceptBoardShareRepository.findByEmailAndInviteeSubjectIdNull(email);
      if(conceptBoardShares != null && conceptBoardShares.size() > 0){
          for (ConceptBoardShare conceptBoardShare: conceptBoardShares) {
              conceptBoardShare.setInviteeSubjectId(subjectId);
              conceptBoardShare.preUpdate(subjectId);
              conceptBoardShareRepository.save(conceptBoardShare);
          }
          return;
      }
      return;
    }

    /**
     * This service class is used to save or updateConceptBoardShares
     *
     * @param emailIds
     * @param conceptBoardId
     * @param subjectId
     * @param message
     * @return
     */
    @Override
    public List<ConceptBoardShare> addUpdateEmailIdsForSharingConceptBoard(Set<String> emailIds, String conceptBoardId, String message ,Long subjectId) {
        ConceptBoard conceptBoard = conceptBoardService.findConceptBoardById(conceptBoardId);
        UserProfileMiniDto userProfileMiniDto = userProfileService.getUserProfileForSubjectIds(Collections.singleton(subjectId)).get(subjectId);
        List<ConceptBoardShare> conceptBoardShares = conceptBoardShareRepository.findByConceptBoardIdAndEmailIn(conceptBoardId,emailIds);

        // Code to remove which was added earlier
        List<ConceptBoardShare> conceptBoardSharesEarlier = conceptBoardShareRepository.findByConceptBoardIdAndIsRevokedFalse(conceptBoardId);
        Set<String> emailIdsToRevoke = new HashSet<>();
        Map<String,ConceptBoardShare> earlierMap = new HashMap<>();
        if(conceptBoardSharesEarlier != null && conceptBoardSharesEarlier.size()>0){
            for (ConceptBoardShare conceptBoardShare: conceptBoardSharesEarlier) {
                emailIdsToRevoke.add(conceptBoardShare.getEmail());
                earlierMap.put(conceptBoardShare.getEmail(),conceptBoardShare);
            }
        }
        emailIdsToRevoke.removeAll(emailIds);
        if(emailIdsToRevoke != null && emailIdsToRevoke.size() > 0){
            for (String s: emailIdsToRevoke) {
                ConceptBoardShare conceptBoardShare = earlierMap.get(s);
                conceptBoardShare.setRevoked(true);
                conceptBoardShare.setRevokeComments("REVOKED BY CONCEPT BOARD OWNER");
                conceptBoardShareRepository.save(conceptBoardShare);
            }
        }
        //end of code to remove which was added earlier

        // Start of code which check if earlier we have added those emailIds
        Set<String> emailIdsMissing = new HashSet<>();
        emailIdsMissing.addAll(emailIds);
        List<ConceptBoardShare> toSend = new ArrayList<>();
        if(conceptBoardShares != null && conceptBoardShares.size() > 0){
            for (ConceptBoardShare conceptBoardShare: conceptBoardShares) {
                if(conceptBoardShare.getRevoked()){
                    conceptBoardShare.setRevoked(false);
                    toSend.add(conceptBoardShareRepository.save(conceptBoardShare));
                    emailIdsMissing.remove(conceptBoardShare.getEmail());
                } else {
                    toSend.add(conceptBoardShare);
                    emailIdsMissing.remove(conceptBoardShare.getEmail());
                }
            }
        }
        //End of code to check if earlier we have any email Ids where we have shared same conceptBoardIds

        if(emailIdsMissing != null && emailIdsMissing.size()> 0){
            Map<String, UserProfileMiniDto2> map = userProfileService.getUserProfileForEmailIds(emailIdsMissing);
            for (String s: emailIds) {
                ConceptBoardShare conceptBoardShare = new ConceptBoardShare();
                conceptBoardShare.setConceptBoardId(conceptBoardId);
                conceptBoardShare.setInviterSubjectId(subjectId);
                conceptBoardShare.setEmail(s);
                if(map.get(s) != null){
                    conceptBoardShare.setInviteeSubjectId(Long.valueOf(map.get(s).getSubjectId()));
                }
                conceptBoardShare.setRevoked(false);
                conceptBoardShare.preSave(subjectId);
                toSend.add(conceptBoardShareRepository.save(conceptBoardShare));
            }

            sendShareCommunication(emailIdsMissing,conceptBoard,userProfileMiniDto,message);
            //TODO send Communication Asynchronously. Make ExecutorService

        }

        return toSend;
    }

    /**
     * This service method is used to add EmailIds for Sharing conceptBoard
     *
     * @param emailIds
     * @param conceptBoardId
     * @param message
     * @param subjectId
     * @return
     */
    @Override
    public List<ConceptBoardShare> addEmailsIdForSharingConceptBoard(Set<String> emailIds, String conceptBoardId, String message, Long subjectId) {
        ConceptBoard conceptBoard = conceptBoardService.findConceptBoardById(conceptBoardId);
        UserProfileMiniDto userProfileMiniDto = userProfileService.getUserProfileForSubjectIds(Collections.singleton(subjectId)).get(subjectId);
        List<ConceptBoardShare> conceptBoardShares = conceptBoardShareRepository.findByConceptBoardIdAndEmailIn(conceptBoardId,emailIds);
        List<ConceptBoardShare> toReturn = new ArrayList<>();
        Map<String,ConceptBoardShare> earlierMap = new HashMap<>();

        if(conceptBoardShares != null && conceptBoardShares.size()>0){
            for (ConceptBoardShare conceptBoardShare: conceptBoardShares) {
                earlierMap.put(conceptBoardShare.getEmail(),conceptBoardShare);
            }
        }
        Map<String, UserProfileMiniDto2> map = userProfileService.getUserProfileForEmailIds(emailIds);

        for (String s: emailIds) {
            if (earlierMap.get(s) != null) {
                ConceptBoardShare conceptBoardShare = earlierMap.get(s);
                if (conceptBoardShare.getRevoked()) {
                    conceptBoardShare.setRevoked(false);
                    conceptBoardShare.preUpdate(subjectId);
                    conceptBoardShare = conceptBoardShareRepository.save(conceptBoardShare);
                    toReturn.add(conceptBoardShare);
                }
            } else {
                ConceptBoardShare conceptBoardShare = new ConceptBoardShare();
                conceptBoardShare.setConceptBoardId(conceptBoardId);
                conceptBoardShare.setInviterSubjectId(subjectId);
                conceptBoardShare.setEmail(s);
                if (map.get(s) != null) {
                    conceptBoardShare.setInviteeSubjectId(Long.valueOf(map.get(s).getSubjectId()));
                }
                conceptBoardShare.setRevoked(false);
                conceptBoardShare.preSave(subjectId);
                toReturn.add(conceptBoardShareRepository.save(conceptBoardShare));
            }
        }
            sendShareCommunication(emailIds,conceptBoard,userProfileMiniDto,message);

        return toReturn;
    }

    /**
     * This service method is used to remove EmailIds for Sharing conceptBoard
     *
     * @param emailIds
     * @param conceptBoardId
     * @param subjectId
     * @return
     */
    @Override
    public List<ConceptBoardShare> removeEmailsIdForSharingConceptBoard(Set<String> emailIds, String conceptBoardId, Long subjectId) {
        ConceptBoard conceptBoard = conceptBoardService.findConceptBoardById(conceptBoardId);
        List<ConceptBoardShare> conceptBoardSharesEarlier = conceptBoardShareRepository.findByConceptBoardIdAndIsRevokedFalse(conceptBoardId);
        Set<String> emailIdsToRevoke = new HashSet<>();
        Map<String,ConceptBoardShare> earlierMap = new HashMap<>();
        if(conceptBoardSharesEarlier != null && conceptBoardSharesEarlier.size()>0){
            for (ConceptBoardShare conceptBoardShare: conceptBoardSharesEarlier) {
                emailIdsToRevoke.add(conceptBoardShare.getEmail());
                earlierMap.put(conceptBoardShare.getEmail(),conceptBoardShare);
            }
        }
        emailIds.removeAll(emailIdsToRevoke);
        if(emailIds != null && emailIds.size() > 0){
            for (String s: emailIds) {
                ConceptBoardShare conceptBoardShare = earlierMap.get(s);
                conceptBoardShare.setRevoked(true);
                conceptBoardShare.setRevokeComments("REVOKED BY CONCEPT BOARD OWNER");
                conceptBoardShare.preUpdate(subjectId);
                conceptBoardShareRepository.save(conceptBoardShare);
            }
        }
        return conceptBoardShareRepository.findByConceptBoardIdAndIsRevokedFalse(conceptBoardId);
    }

    /**
     * This service method  is used to get EmailIds for Users which we have shared ConceptBoard
     *
     * @param conceptBoardId
     * @param subejctId
     * @return
     */
    @Override
    public Set<String> getShareListForconceptBoard(String conceptBoardId, Long subejctId) {
        List<ConceptBoardShare> conceptBoardShares = conceptBoardShareRepository.findByConceptBoardIdAndIsRevokedFalse(conceptBoardId);
        Set<String> strings = getEmailIdsFromConceptBoards(conceptBoardShares);
        if (strings != null) return strings;
        return null;
    }

    private Set<String> getEmailIdsFromConceptBoards(List<ConceptBoardShare> conceptBoardShares) {
        if(conceptBoardShares != null && conceptBoardShares.size() > 0){
            Set<String> strings = new HashSet<>();
            for (ConceptBoardShare conceptBoardShare: conceptBoardShares) {
                strings.add(conceptBoardShare.getEmail());
            }
            return strings;
        }
        return null;
    }

    private Set<String> getConceptBoardIdsIdsFromConceptBoards(List<ConceptBoardShare> conceptBoardShares) {
        if(conceptBoardShares != null && conceptBoardShares.size() > 0){
            Set<String> strings = new HashSet<>();
            for (ConceptBoardShare conceptBoardShare: conceptBoardShares) {
                strings.add(conceptBoardShare.getConceptBoardId());
            }
            return strings;
        }
        return null;
    }

    /**
     * This service method is used to get All ConceptBoardIds which are shared to a subject
     *
     * @param subjectId
     * @return
     */
    @Override
    public Set<String> getAllConcetpBoardIdsSharedToMe(Long subjectId) {
        List<ConceptBoardShare> conceptBoardShares = conceptBoardShareRepository.findByInviteeSubjectIdAndIsRevokedFalse(subjectId);
        Set<String> strings = getConceptBoardIdsIdsFromConceptBoards(conceptBoardShares);
        if (strings != null) return strings;
        return null;
    }

    /**
     * This service method is used to get EmailIds and Profile (if present) for the users which we have shared ConceptBoard
     *
     * @param conceptBoardId
     * @param subjectId
     * @return
     */
    @Override
    public List<ConceptBoardEmailShareProfileDto> getShareListWithProfileForconceptBoard(String conceptBoardId, Long subjectId) {
        Set<String> emaiIds =  getShareListForconceptBoard(conceptBoardId,subjectId);
        if (emaiIds != null) {
            Map<String, UserProfileMiniDto2> map = userProfileService.getUserProfileForEmailIds(emaiIds);
            List<ConceptBoardEmailShareProfileDto> conceptBoardEmailShareProfileDtos = new ArrayList<>();
            for (String s: emaiIds) {
                conceptBoardEmailShareProfileDtos.add(new ConceptBoardEmailShareProfileDto(s,map.get(s)));
            }

            return conceptBoardEmailShareProfileDtos;
        }
        return null;
    }

    /**
     * THis service method is used to find conceptBoardShare via ConceptBoardId and SubjectId
     *
     * @param conceptBoardId
     * @param subjectId
     * @return
     */
    @Override
    public ConceptBoardShare findByConceptBoardIdAndSubjectId(String conceptBoardId, Long subjectId) {
        ConceptBoardShare  conceptBoardShare = conceptBoardShareRepository.findFirstByConceptBoardIdAndInviteeSubjectId(conceptBoardId,subjectId);
        if(conceptBoardShare != null){
            return conceptBoardShare;
        }
        throw new ConceptBoardShareNotFoundException();
    }

    /**
     * This service method is used to
     *
     * @param conceptBoardId
     * @param subjectId
     * @return
     */
    @Override
    public Boolean unshareMeFromSharedConceptBoard(String conceptBoardId, Long subjectId) {
        ConceptBoardShare conceptBoardShare = findByConceptBoardIdAndSubjectId(conceptBoardId,subjectId);
        conceptBoardShare.setRevoked(true);
        conceptBoardShare.setRevokeComments("REVOKED BY SELF");
        conceptBoardShare.preUpdate(subjectId);
        conceptBoardShareRepository.save(conceptBoardShare);
        return true;
    }

    /**
     * This service method is used get  Map of ConceptBoardShare via Emailids
     *
     * @param conceptBoardId
     * @param emailIds
     * @return
     */
    @Override
    public Map<String, ConceptBoardShare> getConceptBoardShareMapViaEmailIds(String conceptBoardId, Set<String> emailIds) {
        List<ConceptBoardShare> conceptBoardShares = conceptBoardShareRepository.findByConceptBoardIdAndEmailInAndIsRevokedFalse(conceptBoardId,emailIds);
        Map<String,ConceptBoardShare> map = new TreeMap<>();
        if(conceptBoardShares != null && conceptBoardShares.size() > 0){
            map.putAll(conceptBoardShares.stream().collect(Collectors.toMap(ConceptBoardShare:: getEmail,Function.identity())));
        }
        return map;
    }

    private void sendShareCommunication(Set<String> emailIds, ConceptBoard conceptBoard, UserProfileMiniDto userProfileMiniDto ,String shareMessage){
        String conceptBoardImageUrl = conceptBoardDefaultImageUrl;
        if(conceptBoard.getFeaturedMedia() != null){
            conceptBoardImageUrl = conceptBoard.getFeaturedMedia().getUrl().replace("CHANGE_MY_SIZE","400x400");
        }

        for (String s: emailIds) {
            final String emailid  = s;
           final Map<String,Object> placeHolders = new HashMap<>();
            placeHolders.put("CONCEPTBOARD_CREATEDBY",userProfileMiniDto.getName());
            placeHolders.put("CONCEPTBOARD_SHAREURL",conceptBoardPublicUrl+conceptBoard.getId());
            placeHolders.put("CONCEPTBOARD_FEATUREDIMAGEURL",conceptBoardImageUrl);
            placeHolders.put("CONCEPTBOARD_NAME",conceptBoard.getFolderName());
            placeHolders.put("CUSTOMER_UNIQUECUSTOMERID","ABC");
            placeHolders.put("CUSTOMER_EMAIL1",emailid);
            communicationService.sendCommunicationAsync("CONCEPTBOARDSHARE",placeHolders);
        }

    }
}
