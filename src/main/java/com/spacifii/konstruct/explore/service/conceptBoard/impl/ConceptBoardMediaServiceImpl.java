package com.spacifii.konstruct.explore.service.conceptBoard.impl;

import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoard;
import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardMedia;
import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardMediaType;
import com.spacifii.konstruct.explore.entities.explore.InfoSpot;
import com.spacifii.konstruct.explore.entities.explore.Media;
import com.spacifii.konstruct.explore.entities.explore.MediaType;
import com.spacifii.konstruct.explore.entities.explore.MediaTypeExtension;
import com.spacifii.konstruct.explore.entities.generalMedia.GeneralMedia;
import com.spacifii.konstruct.explore.exception.conceptBoard.ConceptBoardMediaDuplicateForSameSubjectException;
import com.spacifii.konstruct.explore.exception.conceptBoard.ConceptBoardMediaNotFoundException;
import com.spacifii.konstruct.explore.model.dto.conceptBoard.ConceptBoardMediaUpdateDto;
import com.spacifii.konstruct.explore.repository.conceptBoard.ConceptBoardMediaRepository;
import com.spacifii.konstruct.explore.service.conceptBoard.ConceptBoardMediaService;
import com.spacifii.konstruct.explore.service.conceptBoard.ConceptBoardService;
import com.spacifii.konstruct.explore.service.explore.MediaService;
import com.spacifii.konstruct.explore.service.explore.MediaTypeExtensionService;
import com.spacifii.konstruct.explore.service.generalMedia.GeneralMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * This is service class for ConceptBoardMedia
 */
@Service
public class ConceptBoardMediaServiceImpl implements ConceptBoardMediaService {

    @Autowired
    private GeneralMediaService generalMediaService;

    @Autowired
    private ConceptBoardMediaRepository conceptBoardMediaRepository;

    @Autowired
    ConceptBoardService conceptBoardService;

    @Autowired
    MediaService mediaService;

    @Autowired
    SaveConceptBoardMediaStrategyContext saveConceptBoardMediaStrategyContext;

    @Autowired
    MediaTypeExtensionService mediaTypeExtensionService;

    /**
     * This service method is used to add ConceptBoardMedias via MultipartFiles
     * @param multipartFiles
     * @param conceptBoard
     * @param subjectId
     * @return
     */
    @Override
    public List<ConceptBoardMedia> save(MultipartFile[] multipartFiles, ConceptBoard conceptBoard,Boolean isMediaImage360 ,Long subjectId)
    {
       List<ConceptBoardMedia> conceptBoardMedias = new ArrayList<>();

       for (MultipartFile multipartFile: multipartFiles){
           ConceptBoardMedia conceptBoardMedia = new ConceptBoardMedia();
           String extension = getFileExtension(multipartFile.getOriginalFilename());
           MediaTypeExtension mediaTypeExtension =  mediaTypeExtensionService.getMediaTypeExtensionByExtension(extension);

           MediaType mediaType = mediaTypeExtension.getMediaType();

           if(isMediaImage360){
               mediaType = MediaType.IMAGE360;
           }
           conceptBoardMedia.setMediaType(mediaType);
           conceptBoardMedias.add(saveConceptBoardMediaStrategyContext.saveMedia(mediaType,conceptBoard,conceptBoardMedia,multipartFile));
       }

        return conceptBoardMedias;
    }




    /**
     * This service method is used to add conceptBoardMedia via Explore
     * @param mediaId
     * @param conceptBoard
     * @param description
     * @param subjectId
     * @return
     */
    @Override
    public ConceptBoardMedia saveFromExplore(String mediaId, ConceptBoard conceptBoard, String description, Long subjectId) {
        Media media = mediaService.findById(mediaId);

        for (ConceptBoardMedia conceptBoardMedia: conceptBoard.getMedias()) {
            if(conceptBoardMedia.getChecksum().equals(media.getChecksum())){
                throw new ConceptBoardMediaDuplicateForSameSubjectException(media.getName());
            }
        }

        /*if(conceptBoardMediaRepository.findFirstByCreatedByAndChecksum(subjectId,media.getChecksum()) != null){
            throw new ConceptBoardMediaDuplicateForSameSubjectException(media.getName());
        }*/
        ConceptBoardMedia conceptBoardMedia = new ConceptBoardMedia();
        conceptBoardMedia.mergeInMe(media);

        return conceptBoardMediaRepository.save(conceptBoardMedia);
    }

    /**
     * This service method is used to update conceptBoardMedia information
     * @param conceptBoardMediaUpdateDto
     * @param subjectId
     * @return
     */
    @Override
    public ConceptBoardMedia updateConceptBoardMedia(ConceptBoardMediaUpdateDto conceptBoardMediaUpdateDto, Long subjectId) {

        //This does some checks and throws exception
        ConceptBoard conceptBoard = conceptBoardService.findConceptBoardByIdAndSubjectId(conceptBoardMediaUpdateDto.getConceptBoardId(),subjectId);
        for (ConceptBoardMedia conceptBoardMedia: conceptBoard.getMedias()) {
            if(conceptBoardMedia.getId().equalsIgnoreCase(conceptBoardMediaUpdateDto.getConceptBoardMediaId())){
            conceptBoardMedia.setActive(conceptBoardMediaUpdateDto.getActive());
            conceptBoardMedia.setName(conceptBoardMediaUpdateDto.getName());
            conceptBoardMedia.setDescription(conceptBoardMediaUpdateDto.getDescription());
            conceptBoardMedia.preUpdate(subjectId);
            return conceptBoardMediaRepository.save(conceptBoardMedia);
            }
        }

       throw new ConceptBoardMediaNotFoundException();
    }

    /**
     * This service method is used to get ConceptBoardMedia by id
     * @param id
     * @return
     */
    @Override
    public ConceptBoardMedia findById(String id) {

        Optional<ConceptBoardMedia> conceptBoardMediaOptional = conceptBoardMediaRepository.findById(id);
        if(conceptBoardMediaOptional.isPresent()){
            return conceptBoardMediaOptional.get();
        }
        throw new ConceptBoardMediaNotFoundException();
    }

    /**
     * This service method is used to get ConceptBoardMedia by id and subjectId
     * @param id
     * @param subjectId
     * @return
     */
    @Override
    public ConceptBoardMedia findByMediaIdAndSubjectId(String id, Long subjectId) {

        ConceptBoardMedia conceptBoardMedia = conceptBoardMediaRepository.findFirstByIdAndCreatedBy(id,subjectId);
        if(conceptBoardMedia == null){
            throw new ConceptBoardMediaNotFoundException();
        }
        return conceptBoardMedia;
    }

    /**
     * This service method is used to add Kewords to ConceptBoardMedia By Id
     *
     * @param id
     * @param keywords
     * @param subjectId
     * @return
     */
    @Override
    public ConceptBoardMedia addKeywords(String id, Set<String> keywords, Long subjectId) {
        ConceptBoardMedia conceptBoardMedia = findById(id);
        Set<String> strings = conceptBoardMedia.getKeywords();
        strings.addAll(keywords);
        conceptBoardMedia.setKeywords(strings);
        conceptBoardMedia.preUpdate(subjectId);
        return conceptBoardMediaRepository.save(conceptBoardMedia);
    }

    /**
     * This service method is used to remove Keywords to ConceptBoardMedia By Id
     *
     * @param id
     * @param keywords
     * @param subjectId
     * @return
     */
    @Override
    public ConceptBoardMedia removeKeywords(String id, Set<String> keywords, Long subjectId) {
        ConceptBoardMedia conceptBoardMedia = findById(id);
        Set<String> strings = conceptBoardMedia.getKeywords();
        strings.removeAll(keywords);
        conceptBoardMedia.setKeywords(strings);
        conceptBoardMedia.preUpdate(subjectId);
        return conceptBoardMediaRepository.save(conceptBoardMedia);
    }

    /**
     * This method adds InfoSpots to Media
     *
     * @param conceptBoardId
     * @param conceptBoardMediaId
     * @param subjectId
     * @param infoSpots
     * @return
     */
    @Override
    public ConceptBoardMedia addInfoSpotToMedia(String conceptBoardId, String conceptBoardMediaId, Long subjectId, Set<InfoSpot> infoSpots) {
        ConceptBoardMedia media = findById(conceptBoardMediaId);
        ConceptBoard conceptBoard = conceptBoardService.findConceptBoardById(conceptBoardId);
        Map<String,String> stringStringMap = new HashMap<>();
        for (ConceptBoardMedia conceptBoardMedia: conceptBoard.getMedias()) {
            stringStringMap.put(conceptBoardMedia.getId(),conceptBoardMedia.getId());
        }
        if(stringStringMap.get(conceptBoardMediaId) == null){
            throw new ConceptBoardMediaNotFoundException();
        }
        for (InfoSpot infoSpot: infoSpots) {
            if(infoSpot.getId() == null){
                infoSpot.setId(UUID.randomUUID().toString());
            }
        }
        Set<InfoSpot> infoSpotsExisting= media.getInfoSpots();
        infoSpotsExisting.removeAll(infoSpots);
        infoSpotsExisting.addAll(infoSpots);

        media.setInfoSpots(infoSpotsExisting);
        media.preUpdate(subjectId);

        return conceptBoardMediaRepository.save(media);
    }

    /**
     * This method removes InfoSpots to Media
     *
     * @param conceptBoardId
     * @param conceptBoardMediaId
     * @param subjectId
     * @param infoSpotId
     * @return
     */
    @Override
    public ConceptBoardMedia removeInfoSpotToMedia(String conceptBoardId, String conceptBoardMediaId, Long subjectId, String infoSpotId) {
        ConceptBoardMedia media = findById(conceptBoardMediaId);
        ConceptBoard conceptBoard = conceptBoardService.findConceptBoardById(conceptBoardId);
        Map<String,String> stringStringMap = new HashMap<>();
        for (ConceptBoardMedia conceptBoardMedia: conceptBoard.getMedias()) {
            stringStringMap.put(conceptBoardMedia.getId(),conceptBoardMedia.getId());
        }
        if(stringStringMap.get(conceptBoardMediaId) == null){
            throw new ConceptBoardMediaNotFoundException();
        }
        Set<InfoSpot> infoSpotsExisting= media.getInfoSpots();
        InfoSpot infoSpotToRemove = null;
        for (InfoSpot infoSpot: infoSpotsExisting) {
            if(infoSpot.getId().equalsIgnoreCase(infoSpotId)){
                infoSpotToRemove =  infoSpot;
                break;
            }
        }
        if(infoSpotToRemove != null){
            infoSpotsExisting.remove(infoSpotToRemove);
        }
        media.setInfoSpots(infoSpotsExisting);
        media.preUpdate(subjectId);

        return conceptBoardMediaRepository.save(media);
    }

    /**
     * This private method gets Extension from Filename String
     * @param fileName
     * @return
     */
    private static String getFileExtension(String fileName) {

        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".")+1).toUpperCase();
        else return "";
    }


}
