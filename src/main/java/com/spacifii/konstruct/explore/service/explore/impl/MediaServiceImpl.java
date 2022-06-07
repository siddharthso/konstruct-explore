package com.spacifii.konstruct.explore.service.explore.impl;

import com.spacifii.konstruct.explore.annotation.NotToUseDuringMerge;
import com.spacifii.konstruct.explore.entities.explore.*;
import com.spacifii.konstruct.explore.exception.explore.DuplicateMediaFoundException;
import com.spacifii.konstruct.explore.exception.explore.MediaNotFoundException;
import com.spacifii.konstruct.explore.exception.explore.MediasNotForThisProjectException;
import com.spacifii.konstruct.explore.exception.explore.ProjectNotFoundException;
import com.spacifii.konstruct.explore.model.dto.explore.ExternalMediaDto;
import com.spacifii.konstruct.explore.model.dto.explore.MediaApprovalDto;
import com.spacifii.konstruct.explore.model.dto.explore.WorkflowDecision;
import com.spacifii.konstruct.explore.repository.explore.MediaRepository;
import com.spacifii.konstruct.explore.service.explore.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This is service class for Media
 */
@Service
public class MediaServiceImpl implements MediaService {

    @Autowired
    MediaRepository mediaRepository;

    @Autowired
    ExploreProjectService exploreProjectService;

    @Autowired
    MediaTypeExtensionService mediaTypeExtensionService;

    @Autowired
    SaveMediaStrategyContext saveMediaStrategyContext;

    @Autowired
    UtilityService utilityService;

    @Autowired
    ExploreMediaElasticService exploreMediaElasticService;

    @Autowired
    MediaWorkFlowActionStrategyContext mediaWorkFlowActionStrategyContext;


    @Value("${enableAutoApprove}")
    private Boolean autoApprove;


    /**
     * This method adds Media to Existing Project
     * @param projectId
     * @param subjectId
     * @param multipartFiles
     * @param isMediaImage360
     * @return
     */
    @Override
    public List<Media> addMediaToExistingProject(String projectId, Long subjectId, MultipartFile[] multipartFiles,Boolean isMediaImage360) {



            ExploreProject exploreProject = exploreProjectService.getExploreProjectById(projectId);

            //TODO: Check for Subject as well

            List<Media> medias = new ArrayList<>();
        for (MultipartFile multipartFile: multipartFiles) {


            String extension = getFileExtension(multipartFile.getOriginalFilename());
            /*File file = new File(multipartFile.getOriginalFilename());

                multipartFile.transferTo(file);
                String extension = getFileExtension(file);
                multipartFile.getBytes();*/

            String checksum = checkChecksum(multipartFile,projectId);

            MediaTypeExtension mediaTypeExtension =  mediaTypeExtensionService.getMediaTypeExtensionByExtension(extension);

            MediaType mediaType = mediaTypeExtension.getMediaType();

            if(isMediaImage360){
                mediaType = MediaType.IMAGE360;
            }

            Media media = new Media();
            media.setChecksum(checksum);
            media.setProjectId(projectId);
            media.setMediaType(mediaType);
            media.setSubjectId(subjectId);
            media.setMediaTypeExtension(mediaTypeExtension.getExtension());
            media.preSave(subjectId);
            media.setKeywords(exploreProject.getMyKeywords());
            medias.add(saveMediaStrategyContext.saveMedia(mediaType,media,multipartFile));


        }

        if(autoApprove){
            autoApproveMedias(medias);
        }

        return medias;




    }

    /**
     * This method adds InfoSpots to Media
     * @param projectId
     * @param subjectId
     * @return
     */
    @Override
    public Media addInfoSpotToMedia(String projectId, String mediaId, Long subjectId, Set<InfoSpot> infoSpots) {

        Media media = findById(mediaId);
        if(!media.getProjectId().equals(projectId)){
            throw new ProjectNotFoundException();
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

        return mediaRepository.save(media);
    }

    /**
     * This method is used to remove Infosport from Media
     *
     * @param projectId
     * @param mediaId
     * @param subjectId
     * @param infospotId
     * @return
     */
    @Override
    public Media removeInfoSpotFromMedia(String projectId, String mediaId, Long subjectId, String infospotId) {

        Media media = findById(mediaId);
        if(!media.getProjectId().equals(projectId)){
            throw new ProjectNotFoundException();
        }
        Set<InfoSpot> infoSpotsExisting= media.getInfoSpots();
        InfoSpot infoSpotToRemove = null;
        for (InfoSpot infoSpot: infoSpotsExisting) {
            if(infoSpot.getId().equalsIgnoreCase(infospotId)){
                infoSpotToRemove = infoSpot;
                break;
            }
        }
        if(infoSpotToRemove != null) {
            infoSpotsExisting.remove(infoSpotToRemove);
        }
        media.setInfoSpots(infoSpotsExisting);
        media.preUpdate(subjectId);

        return mediaRepository.save(media);
    }

    /**
     * This method saves media
     * @param media
     * @return
     */
    @Override
    @Transactional
    public Media save(Media media) {
        media.fillExtendedKeywords();
        media = mediaRepository.save(media);
       return mediaRepository.save(media);
    }


    /**
     * This method saves all projects media from UI
     * @param medias
     * @param projectId
     * @param subjectId
     * @return
     */
    @Override
    @Transactional
    public List<Media> updateMedias(List<Media> medias, String projectId,Long subjectId) {
        List<Media> mediasNew = new ArrayList<>();
        for (Media media: medias) {
            mediasNew.add(updateSingle(media,projectId,subjectId));
        }
        return mediasNew;
    }

    /**
     * This method updates single project
     * @param media
     * @param projectId
     * @param subjectId
     * @return
     */
    @Override
    @Transactional
    public Media updateSingle(Media media, String projectId,Long subjectId) {
        Media mediaExisting = findById(media.getId());
        if(!mediaExisting.getProjectId().equals(projectId)){
            //SOMETHING iS FISHY
            throw new MediasNotForThisProjectException();
        }

        String[] arr  = BeanUtils.findFields(Media.class, NotToUseDuringMerge.class);
        BeanUtils.copyProperties(media,mediaExisting,arr);
        mediaExisting.preUpdate(subjectId);
        mediaExisting.fillExtendedKeywords();

        exploreMediaElasticService.toggleMediaElastic(mediaExisting);

        return mediaRepository.save(mediaExisting);
    }


    /**
     * This method retrieves media by Id
     * @param id
     * @return
     */
    @Override
    public Media findById(String id) {
        Optional<Media> mediaOptional = mediaRepository.findById(id);
        if(mediaOptional.isPresent()){
            return mediaOptional.get();
        }

        throw new MediaNotFoundException();
    }

    /**
     * This  method returns medias by Ids
     * @param ids
     * @return
     */
    @Override
    public List<Media> findByIds(Set<String> ids) {
        return new ArrayList<>(BeanUtils.makeCollection(mediaRepository.findAllById(ids)));
    }

    /**
     * This method returns all medias for the project
     * @param projectId
     * @return
     */
    @Override
    public List<Media> findByProjectId(String projectId) {

        return mediaRepository.findByProjectId(projectId);
    }

    /**
     * This method returns all medias for the project
     *
     * @param projectId
     * @return
     */
    @Override
    public List<Media> findByProjectIdPubliclyVisible(String projectId) {
        return mediaRepository.findByProjectIdAndStatusAndActiveTrue(projectId,STATUS.APPROVED);
    }

    /**
     * This method returns all medias for a user
     * @param subjectId
     * @return
     */
    @Override
    public List<Media> findBySubjectId(String subjectId) {
        return mediaRepository.findBySubjectId(subjectId);
    }

    /**
     * This toggles media active status
     * @param projectId
     * @param mediaId
     * @param subjectId
     * @return
     */
    @Override
    public Media toggleMedia(String projectId, String mediaId, Long subjectId) {
        Media media = findById(mediaId);
        if(!media.getProjectId().equals(projectId)){
            throw new ProjectNotFoundException();
        }
        media.setActive(!media.getActive());
        media.preUpdate(subjectId);

        media =  mediaRepository.save(media);
        exploreMediaElasticService.toggleMediaElastic(media);

        return media;
    }

    /**
     * This service method is used to add external Media to existing project
     *
     * @param projectId
     * @param subjectId
     * @param externalMediaDtos
     * @return
     */
    @Override
    public List<Media> addExternalMediaToExistingProject(String projectId, Long subjectId, List<ExternalMediaDto> externalMediaDtos) {
        Set<String> urls= externalMediaDtos.stream().map(ExternalMediaDto::getExternalUrl).collect(Collectors.toSet());
        List<Media> mediasExisting = mediaRepository.findByProjectIdAndExternalUrlIn(projectId,urls);
        Map<String,String> existing = new HashMap<>();
        if(mediasExisting != null && mediasExisting.size() > 0 ){
            for (Media media : mediasExisting) {
                existing.put(media.getExternalUrl(),media.getId());
            }
        }
        List<Media> medias = new ArrayList<>();
        for (ExternalMediaDto externalMediaDto: externalMediaDtos) {
            if(existing.get(externalMediaDto.getExternalUrl()) == null){
                Media media = externalMediaDto.BringMediaFromMe();
                media.setProjectId(projectId);
                media.setSubjectId(subjectId);
                media.preSave(subjectId);
                media.setKeywords(exploreProjectService.getProjectKeywords(projectId));
                medias.add(saveMediaStrategyContext.saveMedia(media.getMediaType(),media,null));
            }
        }
        return medias;
    }

    /**
     * This private method gets Extension from file
     * @param file
     * @return
     */
    private static String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".")+1).toUpperCase();
        else return "";
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


    /**
     * This private method is used to check if image already exists in database
     * @param multipartFile
     * @param projectId
     * @return
     */
    private String checkChecksum(MultipartFile multipartFile, String projectId){
        String checksum = utilityService.getImageHash(multipartFile);
        Media media = mediaRepository.findFirstByChecksumAndProjectId(checksum,projectId);

        if(media != null) {
            throw new DuplicateMediaFoundException();
        }
        return checksum;

    }

    private void autoApproveMedias(List<Media> medias){
        MediaApprovalDto mediaApprovalDto = new MediaApprovalDto();
        mediaApprovalDto.setWorkflowDecision(WorkflowDecision.APPROVED);
        mediaApprovalDto.setMediaIds(medias.stream().map(Media::getId).collect(Collectors.toSet()));
        mediaWorkFlowActionStrategyContext.doAction(Arrays.asList(mediaApprovalDto),1L);
    }
}
