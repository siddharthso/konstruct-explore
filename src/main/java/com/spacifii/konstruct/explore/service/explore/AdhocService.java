package com.spacifii.konstruct.explore.service.explore;

import com.spacifii.konstruct.explore.entities.explore.ExploreProject;
import com.spacifii.konstruct.explore.entities.explore.Media;
import com.spacifii.konstruct.explore.model.dto.explore.EnvelopedB2CMedia;
import com.spacifii.konstruct.explore.model.dto.explore.EnvelopedMedia;
import com.spacifii.konstruct.explore.model.dto.explore.EnvelopedProject;
import com.spacifii.konstruct.explore.model.dto.explore.FilterRequestDto;
import com.spacifii.konstruct.explore.model.dto.query.SearchQuery;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * This service class is mainly used for filters
 */
public interface AdhocService {

    /**
     * This service method returns SearchQuery results for Media
     * @param searchQueries
     * @return
     */
    List<List<Map<String, Object>>> findMediaAdhoc(List<SearchQuery> searchQueries);

    /**
     * This service method returns SearchQuery result for Project
     * @param searchQueries
     * @return
     */
    List<List<Map<String, Object>>> findProjectAdhoc(List<SearchQuery> searchQueries);

    /**
     * This service method returns pagination results of Media for FilterRequest
     * @param filterRequestDto
     * @return
     */
    Page<Media> findMediaWithFilters(FilterRequestDto filterRequestDto);

    /**
     * This service method returns pagination results of ExploreProject for FilterRequest
     * @param filterRequestDto
     * @return
     */
    Page<ExploreProject> findProjectWithFilters(FilterRequestDto filterRequestDto);

    /**
     * This service method returns pagination results of EnvelopedMedia for FilterRequest
     * @param filterRequestDto
     * @return
     */
    Page<EnvelopedMedia> findEnvelopedMediaWithFilters(FilterRequestDto filterRequestDto);

    /**
     * This service method returns pagination results of EnvelopedProject for FilterRequest
     * @param filterRequestDto
     * @return
     */
    Page<EnvelopedProject> findEnvelopedProjectWithFilters(FilterRequestDto filterRequestDto);

    /**
     * This service method returns pagination results of EnvelopedProject for FilterRequest for public
     * @param filterRequestDto
     * @return
     */
    Page<EnvelopedProject> findEnvelopedProjectWithFiltersForPublic(FilterRequestDto filterRequestDto);

    /**
     * This service method gets paginated results of EnvelopedProject for FilterRequest for a user
     * @param filterRequestDto
     * @param subjectId
     * @return
     */
    Page<EnvelopedProject> findMyEnvelopedProjectWithFilters(FilterRequestDto filterRequestDto, Long subjectId);


    /**
     * This service method gets paginated results of EnvelopedProject for FilterRequest for a profileId
     * @param filterRequestDto
     * @param profileId
     * @return
     */
    Page<EnvelopedProject> findEnvelopedProjectWithFiltersByProfileId(FilterRequestDto filterRequestDto, String profileId);


    /**
     * This service method gets paginated results of EnvelopedB2CMedia for FilterRequest
     * @param filterRequestDto
     * @return
     */
    Page<EnvelopedB2CMedia> findEnvelopedB2CMediaWithFilters(FilterRequestDto filterRequestDto);

    /**
     * This service method gets EnvelopedB2CMedia for mediaId
     * @param mediaId
     * @param subjectId
     * @return
     */
    EnvelopedB2CMedia findEnvelopedB2CMediaForMedia(String mediaId,Long subjectId);


    /**
     * This service method gets EnvelopedProject for projectId
     * @param projectId
     * @param subjectId
     * @return
     */
    EnvelopedProject findEnvelopedProjectForProject(String projectId, Long subjectId);


    /**
     * This service method gets paginated results of EnvelopedB2CMedia for FilterRequest global
     * @param filterRequestDto
     * @return
     */
    Page<EnvelopedB2CMedia> findEnvelopedB2CMediaWithFiltersGlobal(FilterRequestDto filterRequestDto);


    /**
     * This service method is used to find EnvelopedB2CMedia with Image Uploading
     * @param multipartFile
     * @param page
     * @param size
     * @return
     */
    Page<EnvelopedB2CMedia> findEnvelopedB2CMediaWithImage(MultipartFile multipartFile, Integer page, Integer size);
}
