package com.spacifii.konstruct.explore.service.explore;

import com.spacifii.konstruct.explore.entities.explore.InfoSpot;
import com.spacifii.konstruct.explore.entities.explore.Media;
import com.spacifii.konstruct.explore.model.dto.explore.ExternalMediaDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

/**
 * This is service class for Media
 */
public interface MediaService {

    /**
     * This method adds Media to Existing Project
     * @param projectId
     * @param subjectId
     * @param multipartFiles
     * @param isMediaImage360
     * @return
     */
    List<Media> addMediaToExistingProject(String projectId, Long subjectId, MultipartFile[] multipartFiles,Boolean isMediaImage360);

    /**
     * This method adds InfoSpots to Media
     * @param projectId
     * @param subjectId
     * @return
     */
    Media addInfoSpotToMedia(String projectId, String mediaId , Long subjectId, Set<InfoSpot> infoSpot);

    /**
     * This method is used to remove Infosport from Media
     * @param projectId
     * @param mediaId
     * @param subjectId
     * @param infospotId
     * @return
     */
    Media removeInfoSpotFromMedia(String projectId, String mediaId, Long subjectId, String infospotId);

    /**
     * This method saves media
     * @param media
     * @return
     */
    Media save(Media media);

    /**
     * This method saves all projects media from UI
     * @param medias
     * @param projectId
     * @param subjectId
     * @return
     */
    List<Media> updateMedias(List<Media> medias, String projectId ,Long subjectId);


    /**
     * This method updates single project
     * @param media
     * @param projectId
     * @param subjectId
     * @return
     */
    Media updateSingle(Media media, String projectId,Long subjectId);

    /**
     * This method retrieves media by Id
     * @param id
     * @return
     */
    Media findById(String id);

    /**
     * This  method returns medias by Ids
     * @param ids
     * @return
     */
    List<Media> findByIds(Set<String> ids);


    /**
     * This method returns all medias for the project
     * @param projectId
     * @return
     */
    List<Media> findByProjectId(String projectId);

    /**
     * This method returns all medias for the project
     * @param projectId
     * @return
     */
    List<Media> findByProjectIdPubliclyVisible(String projectId);


    /**
     * This method returns all medias for a user
     * @param subjectId
     * @return
     */
    List<Media> findBySubjectId(String subjectId);

    /**
     * This toggles media active status
     * @param projectId
     * @param mediaId
     * @param subjectId
     * @return
     */
    Media toggleMedia(String projectId, String mediaId, Long subjectId);

    /**
     * This service method is used to add external Media to existing project
     * @param projectId
     * @param subjectId
     * @param externalMediaDtos
     * @return
     */
    List<Media> addExternalMediaToExistingProject(String projectId, Long subjectId, List<ExternalMediaDto> externalMediaDtos);


}
