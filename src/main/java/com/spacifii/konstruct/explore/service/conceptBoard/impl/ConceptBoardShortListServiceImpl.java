package com.spacifii.konstruct.explore.service.conceptBoard.impl;

import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoard;
import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardMedia;
import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardShortList;
import com.spacifii.konstruct.explore.model.dto.conceptBoard.ConceptBoardEnveloped;
import com.spacifii.konstruct.explore.model.dto.conceptBoard.EnvelopedConceptBoardShortList;
import com.spacifii.konstruct.explore.model.dto.conceptBoard.MediaShortListMapping;
import com.spacifii.konstruct.explore.model.dto.explore.UserProfileMiniDto;
import com.spacifii.konstruct.explore.model.dto.explore.UserProfileMiniDto2;
import com.spacifii.konstruct.explore.repository.conceptBoard.ConceptBoardShortListRepository;
import com.spacifii.konstruct.explore.service.conceptBoard.ConceptBoardService;
import com.spacifii.konstruct.explore.service.conceptBoard.ConceptBoardShortListService;
import com.spacifii.konstruct.explore.service.explore.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *  This service class is used to manage ConceptBoardShortList
 */
@Service
public class ConceptBoardShortListServiceImpl implements ConceptBoardShortListService {

    @Autowired
    ConceptBoardShortListRepository conceptBoardShortListRepository;

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    ConceptBoardService conceptBoardService;

    /**
     * This service method is used to shortList Media
     *
     * @param conceptBoardId
     * @param conceptBoardMediaId
     * @param comment
     * @param SubjectId
     * @return
     */
    @Override
    public ConceptBoardShortList shortListConceptBoardMedia(String conceptBoardId, String conceptBoardMediaId, String comment, Long SubjectId) {
       ConceptBoardShortList conceptBoardShortListExisting =
               conceptBoardShortListRepository.findFirstByConceptBoardIdAndAndConceptBoardMediaIdAndShortListedBySubjectId(conceptBoardId,conceptBoardMediaId,SubjectId);
       if(conceptBoardShortListExisting != null){
           conceptBoardShortListExisting.setShortListed(true);
           conceptBoardShortListExisting.preUpdate(SubjectId);
           conceptBoardShortListExisting.setDescription(comment);
           return conceptBoardShortListRepository.save(conceptBoardShortListExisting);
       }
       ConceptBoardShortList conceptBoardShortList = new ConceptBoardShortList(conceptBoardId,conceptBoardMediaId,comment,SubjectId);
        conceptBoardShortList.preSave(SubjectId);
        conceptBoardShortList.setShortListed(true);
        return conceptBoardShortListRepository.save(conceptBoardShortList);
    }

    /**
     * This service method is used to unshortList a Media
     *
     * @param conceptBoardId
     * @param conceptBoardMediaId
     * @param comment
     * @param SubjectId
     * @return
     */
    @Override
    public Boolean unShortListConceptBoardMedia(String conceptBoardId, String conceptBoardMediaId, String comment, Long SubjectId) {
        ConceptBoardShortList conceptBoardShortListExisting =
                conceptBoardShortListRepository.findFirstByConceptBoardIdAndAndConceptBoardMediaIdAndShortListedBySubjectId(conceptBoardId,conceptBoardMediaId,SubjectId);
        if(conceptBoardShortListExisting != null){
            conceptBoardShortListExisting.setShortListed(false);
            conceptBoardShortListExisting.setDescription(comment);
            conceptBoardShortListExisting.preUpdate(SubjectId);
             conceptBoardShortListRepository.save(conceptBoardShortListExisting);
             return true;
        }
        return false;
    }

    /**
     * This service method is used to get Shortlisted ConceptBoard For myself
     *
     * @param conceptBoardId
     * @param subjectId
     * @return
     */
    @Override
    public List<ConceptBoardShortList> getMyShortListedImagesForConceptBoard(String conceptBoardId, Long subjectId) {
        return conceptBoardShortListRepository.findByConceptBoardIdAndShortListedBySubjectIdAndIsShortListedTrue(conceptBoardId,subjectId);
    }

    /**
     * This service method is used to get ConceptBoardShortList for user for MediaId
     *
     * @param conceptBoardId
     * @param conceptBoardMediaId
     * @param subjectId
     * @return
     */
    @Override
    public ConceptBoardShortList getMyConceptBoardShortListForConceptBoardAndConceptBoardMediaId(String conceptBoardId, String conceptBoardMediaId, Long subjectId) {
        return conceptBoardShortListRepository.findFirstByConceptBoardIdAndAndConceptBoardMediaIdAndShortListedBySubjectIdAndIsShortListedTrue(conceptBoardId,conceptBoardMediaId,subjectId);
    }

    /**
     * This service method is used to get ShortListed Images for EmailId User
     *
     * @param conceptBoardId
     * @param emailId
     * @param subjectId
     * @return
     */
    @Override
    public EnvelopedConceptBoardShortList getShortListedImageForConceptBoardByEmailId(String conceptBoardId, String emailId, Long subjectId) {
        Map<String, UserProfileMiniDto2> map = userProfileService.getUserProfileForEmailIds(Collections.singleton(emailId));
        UserProfileMiniDto2 userProfileMiniDto = null;
        if(map != null && map.size() > 0){
            userProfileMiniDto = map.get(emailId);
        } else{
            return null;
        }
        EnvelopedConceptBoardShortList envelopedConceptBoardShortList = new EnvelopedConceptBoardShortList();
        envelopedConceptBoardShortList.setConceptBoardShortLists(getMyShortListedImagesForConceptBoard(conceptBoardId, Long.valueOf(userProfileMiniDto.getSubjectId())));
        envelopedConceptBoardShortList.setEmailid(emailId);
        envelopedConceptBoardShortList.setProfileMiniDto(userProfileMiniDto);
        return envelopedConceptBoardShortList;
    }

    /**
     * This service method is used to get MediaListMapping Images for EmailId User
     *
     * @param conceptBoardId
     * @param emailId
     * @param subjectId
     * @return
     */
    @Override
    public EnvelopedConceptBoardShortList getMediaShortListMappingForConceptBoardByEmailId(String conceptBoardId, String emailId, Long subjectId) {
        ConceptBoard conceptBoard = conceptBoardService.findConceptBoardById(conceptBoardId);
        ConceptBoardEnveloped conceptBoardEnveloped = conceptBoardService.getConceptBoardEnvelopedById(conceptBoardId,subjectId);
        Map<String,UserProfileMiniDto2> map = userProfileService.getUserProfileForEmailIds(Collections.singleton(emailId));
        UserProfileMiniDto2 userProfileMiniDto = null;
        if(map != null && map.size() > 0){
            userProfileMiniDto = map.get(emailId);
        } else{
            return null;
        }
        EnvelopedConceptBoardShortList envelopedConceptBoardShortList = new EnvelopedConceptBoardShortList();
        envelopedConceptBoardShortList.setConceptBoardShortLists(getMyShortListedImagesForConceptBoard(conceptBoardId, Long.valueOf(userProfileMiniDto.getSubjectId())));
        if(envelopedConceptBoardShortList.getConceptBoardShortLists() != null && envelopedConceptBoardShortList.getConceptBoardShortLists().size() > 0){
            Map<String,ConceptBoardShortList> stringConceptBoardShortListMap =
                    envelopedConceptBoardShortList.getConceptBoardShortLists().stream().collect(Collectors.toMap(ConceptBoardShortList::getConceptBoardMediaId, Function.identity()));
            List<MediaShortListMapping> mediaShortListMappings = new ArrayList<>();
            for (ConceptBoardMedia conceptBoardMedia:conceptBoard.getMedias()) {
                mediaShortListMappings.add(new MediaShortListMapping(conceptBoardMedia,stringConceptBoardShortListMap.get(conceptBoardMedia.getId())));
            }
            envelopedConceptBoardShortList.setMediaShortListMappings(mediaShortListMappings);
        }
        envelopedConceptBoardShortList.setEmailid(emailId);
        envelopedConceptBoardShortList.setProfileMiniDto(userProfileMiniDto);
        envelopedConceptBoardShortList.setConceptBoardEnveloped(conceptBoardEnveloped);
        return envelopedConceptBoardShortList;
    }
}
