package com.spacifii.konstruct.explore.service.explore.impl;

import com.spacifii.konstruct.explore.entities.explore.*;
import com.spacifii.konstruct.explore.model.dto.explore.*;
import com.spacifii.konstruct.explore.model.dto.query.SearchQuery;
import com.spacifii.konstruct.explore.service.explore.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;


/**
 * This service class is mainly used for filters
 */
@Service
public class AdhocServiceImpl implements AdhocService {

    @Autowired
    QueryUtilService queryUtilService;


    @Autowired
    ExploreProjectService exploreProjectService;

    @Autowired
    MediaUserActionStatisticsService mediaUserActionStatisticsService;

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    MediaService mediaService;

    @Autowired
    ExploreMediaElasticService exploreMediaElasticService;

    @Autowired
    SaveMediaUserActionStrategyContext saveMediaUserActionStrategyContext;

    @Autowired
    SaveProjectUserActionStrategyContext saveProjectUserActionStrategyContext;

    @Autowired
    CloudVisionService cloudVisionService;



    /**
     * This service method returns SearchQuery results for Media
     * @param searchQueries
     * @return
     */
    @Override
    public List<List<Map<String, Object>>> findMediaAdhoc(List<SearchQuery> searchQueries) {
        return queryUtilService.findAdhoc(searchQueries,new Media());
    }

    /**
     * This service method returns SearchQuery result for Project
     * @param searchQueries
     * @return
     */
    @Override
    public List<List<Map<String, Object>>> findProjectAdhoc(List<SearchQuery> searchQueries) {
        return queryUtilService.findAdhoc(searchQueries,new ExploreProject());
    }

    /**
     * This service method returns pagination results of Media for FilterRequest
     * @param filterRequestDto
     * @return
     */
    @Override
    public Page<Media> findMediaWithFilters(FilterRequestDto filterRequestDto) {

        Set<String> defaults =new HashSet<>();
        defaults.add("lastModifiedDate");
        return queryUtilService.getResultList(filterRequestDto,defaults,new Media());
    }

    /**
     * This service method returns pagination results of ExploreProject for FilterRequest
     * @param filterRequestDto
     * @return
     */
    @Override
    public Page<ExploreProject> findProjectWithFilters(FilterRequestDto filterRequestDto) {
        Set<String> defaults =new HashSet<>();
        defaults.add("lastModifiedDate");
        return queryUtilService.getResultList(filterRequestDto,defaults,new ExploreProject());
    }

    /**
     * This service method returns pagination results of EnvelopedMedia for FilterRequest
     * @param filterRequestDto
     * @return
     */
    @Override
    public Page<EnvelopedMedia> findEnvelopedMediaWithFilters(FilterRequestDto filterRequestDto) {
        Page<Media> mediaPage = findMediaWithFilters(filterRequestDto);
        List<Media> medias = mediaPage.getContent();
        if(medias != null && medias.size() > 0){

            List<EnvelopedMedia> envelopedMedia = exploreProjectService.getEnvelopedMedia(new ArrayList<EnvelopedMedia>(),medias);

            PageImpl<EnvelopedMedia> envelopedMediaPage = new PageImpl<>(envelopedMedia, mediaPage.getPageable(), mediaPage.getTotalElements());

            return envelopedMediaPage;
        }

        return null;
    }

    /**
     * This service method returns pagination results of EnvelopedProject for FilterRequest
     * @param filterRequestDto
     * @return
     */
    @Override
    public Page<EnvelopedProject> findEnvelopedProjectWithFilters(FilterRequestDto filterRequestDto) {
        Page<ExploreProject> exploreProjectPage = findProjectWithFilters(filterRequestDto);
        List<ExploreProject> exploreProjects = exploreProjectPage.getContent();
        if(exploreProjects != null && exploreProjects.size() > 0){
            List<EnvelopedProject> envelopedProjects = new ArrayList<>();
            for (ExploreProject exploreProject: exploreProjects) {
                envelopedProjects.add(exploreProjectService.getEnvelopedProjectByProject(exploreProject,0L));
            }

          PageImpl<EnvelopedProject> envelopedProjectPage = new PageImpl<>(envelopedProjects,exploreProjectPage.getPageable(),exploreProjectPage.getTotalElements());
        return envelopedProjectPage;
        }
        return null;
    }

    /**
     * This service method returns pagination results of EnvelopedProject for FilterRequest for public
     *
     * @param filterRequestDto
     * @return
     */
    @Override
    public Page<EnvelopedProject> findEnvelopedProjectWithFiltersForPublic(FilterRequestDto filterRequestDto) {
        Page<ExploreProject> exploreProjectPage = findProjectWithFilters(filterRequestDto);
        List<ExploreProject> exploreProjects = exploreProjectPage.getContent();
        if(exploreProjects != null && exploreProjects.size() > 0){
            List<EnvelopedProject> envelopedProjects = new ArrayList<>();
            for (ExploreProject exploreProject: exploreProjects) {
                envelopedProjects.add(exploreProjectService.getEnvelopedProjectByProjectForPublic(exploreProject,0L));
            }

            PageImpl<EnvelopedProject> envelopedProjectPage = new PageImpl<>(envelopedProjects,exploreProjectPage.getPageable(),exploreProjectPage.getTotalElements());
            return envelopedProjectPage;
        }
        return null;
    }


    /**
     * This controller method gets paginated results of EnvelopedProject for FilterRequest for a user
     * @param filterRequestDto
     * @param subjectId
     * @return
     */
    @Override
    public Page<EnvelopedProject> findMyEnvelopedProjectWithFilters(FilterRequestDto filterRequestDto, Long subjectId) {

        SearchQuery searchQuery = filterRequestDto.getSearchQuery() == null ? new SearchQuery(): filterRequestDto.getSearchQuery();
        Map<String, List<Object>> where = searchQuery.getWhere() == null ? new LinkedHashMap<>(): searchQuery.getWhere();
        where.put("subjectId",Arrays.asList(subjectId));
        searchQuery.setWhere(where);
        filterRequestDto.setSearchQuery(searchQuery);
        return findEnvelopedProjectWithFilters(filterRequestDto);
    }

    /**
     * This service method gets paginated results of EnvelopedProject for FilterRequest for a profileId
     *
     * @param filterRequestDto
     * @param profileId
     * @return
     */
    @Override
    public Page<EnvelopedProject> findEnvelopedProjectWithFiltersByProfileId(FilterRequestDto filterRequestDto, String profileId) {
        Long subjectId = userProfileService.getSubjectIdForProfileId(profileId);
        SearchQuery searchQuery = filterRequestDto.getSearchQuery() == null ? new SearchQuery(): filterRequestDto.getSearchQuery();
        Map<String, List<Object>> where = searchQuery.getWhere() == null ? new LinkedHashMap<>(): searchQuery.getWhere();
        where.put("subjectId",Arrays.asList(subjectId));
        searchQuery.setWhere(where);
        filterRequestDto.setSearchQuery(searchQuery);
        return findEnvelopedProjectWithFiltersForPublic(filterRequestDto);
    }

    /**
     * This service method gets paginated results of EnvelopedB2CMedia for FilterRequest
     * @param filterRequestDto
     * @return
     */
    @Override
    public Page<EnvelopedB2CMedia> findEnvelopedB2CMediaWithFilters(FilterRequestDto filterRequestDto) {

        SearchQuery searchQuery = filterRequestDto.getSearchQuery();
        if(searchQuery == null){
            searchQuery = new SearchQuery();
        }
        Map<String, List<Object>> where = searchQuery.getWhere();
        if(where == null){
            where = new HashMap<>();
        }
        where.put("active",Arrays.asList(true));
        where.put("status",Arrays.asList(STATUS.APPROVED));
        searchQuery.setWhere(where);
        filterRequestDto.setSearchQuery(searchQuery);

        Page<Media> mediaPage = findMediaWithFilters(filterRequestDto);

        /*if(mediaPage != null && mediaPage.hasContent()){
            Set<String> projectIds = new HashSet<>();
            Set<Long> subjectIds = new HashSet<>();
            Set<String> mediaIds = new HashSet<>();
            List<EnvelopedB2CMedia> envelopedB2CMedias = new ArrayList<>();
            for (Media media: mediaPage.getContent()){
                projectIds.add(media.getProjectId());
                subjectIds.add(media.getSubjectId());
                mediaIds.add(media.getId());
            }
            Map<String, ConceptBoardMediaUserActionStatistics> statisticsMap = mediaUserActionStatisticsService.findByMediaIds(mediaIds);
            Map<String,ExploreProject> projectMap = exploreProjectService.getExploreProjectsByIds(projectIds);
            Map<Long, UserProfileMiniDto> profileMiniDtoMap = userProfileService.getUserProfileForSubjectIds(subjectIds);
            for (Media media: mediaPage.getContent()){
                envelopedB2CMedias.add(new EnvelopedB2CMedia(media,
                        statisticsMap.get(media.getId()) == null?new ConceptBoardMediaUserActionStatistics():statisticsMap.get(media.getId()),
                        projectMap.get(media.getProjectId()),
                        profileMiniDtoMap.get(media.getSubjectId())));
            }

            return new PageImpl<>(envelopedB2CMedias,mediaPage.getPageable(),mediaPage.getTotalElements());

        }

        return null;*/

        return getEnvelpedB2CMediaFromMediaPage(mediaPage);
    }

    /**
     * This service method gets EnvelopedB2CMedia for mediaId
     * @param mediaId
     * @param subjectId
     * @return
     */
    @Override
    public EnvelopedB2CMedia findEnvelopedB2CMediaForMedia(String mediaId, Long subjectId) {
        Media media = mediaService.findById(mediaId);

        //Map<String, MediaUserActionStatistics> statisticsMap = mediaUserActionStatisticsService.findByMediaIds(new HashSet<>(Arrays.asList(mediaId)));
        Map<String,ExploreProject> projectMap = exploreProjectService.getExploreProjectsByIds(new HashSet<>(Arrays.asList(media.getProjectId())));
        Map<Long, UserProfileMiniDto> profileMiniDtoMap = userProfileService.getUserProfileForSubjectIds(new HashSet<>(Arrays.asList(media.getSubjectId())));
        List<Media> projectImages = mediaService.findByProjectIdPubliclyVisible(media.getProjectId());
        List<Media> relatedImages = exploreMediaElasticService.getRelatedMedias(media);

        MediaUserActionDTO mediaUserActionDTO = new MediaUserActionDTO();
        mediaUserActionDTO.setMediaId(mediaId);
        mediaUserActionDTO.setMediaUserActionType(MediaUserActionType.VIEW);

        saveMediaUserActionStrategyContext.save(mediaUserActionDTO,null);

        return new EnvelopedB2CMedia(media,
                media.getMediaUserActionStatistics() == null?new MediaUserActionStatistics():media.getMediaUserActionStatistics(),
                projectMap.get(media.getProjectId()),
                profileMiniDtoMap.get(media.getSubjectId()),projectImages,relatedImages);
    }

    /**
     * This service method gets EnvelopedProject for projectId
     *
     * @param projectId
     * @param subjectId
     * @return
     */
    @Override
    public EnvelopedProject findEnvelopedProjectForProject(String projectId,Long subjectId) {
        ExploreProject exploreProject = exploreProjectService.getExploreProjectById(projectId);
        EnvelopedProject envelopedProject = exploreProjectService.getEnvelopedProjectByProject(exploreProject,0L);
        MediaUserActionDTO mediaUserActionDTO = new MediaUserActionDTO();
        mediaUserActionDTO.setProjectId(projectId);
        mediaUserActionDTO.setMediaUserActionType(MediaUserActionType.VIEW);
        saveProjectUserActionStrategyContext.save(mediaUserActionDTO,subjectId);
        return envelopedProject;
    }

    /**
     * This service method gets paginated results of EnvelopedB2CMedia for FilterRequest global
     * @param filterRequestDto
     * @return
     */
    @Override
    public Page<EnvelopedB2CMedia> findEnvelopedB2CMediaWithFiltersGlobal(FilterRequestDto filterRequestDto) {
        Page<Media> mediaPage = exploreMediaElasticService.getMediaSearch(filterRequestDto.getSearchQuery().getGlobalSearch(),
                PageRequest.of(filterRequestDto.getPage(),filterRequestDto.getSize()));
        return getEnvelpedB2CMediaFromMediaPage(mediaPage);
    }

    /**
     * This service method is used to find EnvelopedB2CMedia with Image Uploading
     *
     * @param multipartFile
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<EnvelopedB2CMedia> findEnvelopedB2CMediaWithImage(MultipartFile multipartFile, Integer page, Integer size) {
        /*List<CloudVisionResponse> stringFloatMap = cloudVisionService.detectLablesAndImageProperties(multipartFile);

        String searchText = "";

        if(stringFloatMap != null && stringFloatMap.size() > 0) {
            for (CloudVisionResponse cloudVisionResponse : stringFloatMap) {

                if (cloudVisionResponse.getCloudVisionType().equals(CloudVisionType.LABEL_DETECTION)) {
                    searchText = searchText +" " + (cloudVisionResponse.getLabel().toUpperCase());
                } else {
                    CloudVisionResponse cloudVisionResponse1 = ImagePropertiesCalculator.
                            updateCloudVisionImgeResponseAttributes(cloudVisionResponse);
                    searchText = searchText +" " + (cloudVisionResponse1.getClosestColorName());
                    searchText = searchText +" " + (cloudVisionResponse1.getParentColorName());
                    searchText = searchText +" " + (cloudVisionResponse1.getPrimaryColorHex());
                    searchText = searchText +" " +(cloudVisionResponse1.getPrimaryColorHexHash());
                    searchText = searchText +" " +(cloudVisionResponse1.getHexCode());
                    searchText = searchText +" " +(cloudVisionResponse1.getHashHexCode());

                }
            }
        }*/


        Map<String, Float> stringFloatMap = cloudVisionService.detectLables(multipartFile);

        String searchText = "";

        for(Map.Entry<String,Float> entry: stringFloatMap.entrySet()){

                searchText = searchText +" " + entry.getKey();

        }

        System.out.println("SEARCH STRING => " + searchText );
        if(searchText.isEmpty()) {
            return null;
        }
        FilterRequestDto filterRequestDto = new FilterRequestDto();
        SearchQuery searchQuery = new SearchQuery();
        searchQuery.setGlobalSearch(searchText);
        filterRequestDto.setPage(page);
        filterRequestDto.setSize(size);
        filterRequestDto.setSearchQuery(searchQuery);
        return findEnvelopedB2CMediaWithFiltersGlobal(filterRequestDto);
    }


    /**
     * This method gets EnvelopedB2CMedia from Page of Media
     * @param mediaPage
     * @return
     */
    private Page<EnvelopedB2CMedia> getEnvelpedB2CMediaFromMediaPage(Page<Media> mediaPage){
        if(mediaPage != null && mediaPage.hasContent()){
            Set<String> projectIds = new HashSet<>();
            Set<Long> subjectIds = new HashSet<>();
            Set<String> mediaIds = new HashSet<>();
            List<EnvelopedB2CMedia> envelopedB2CMedias = new ArrayList<>();
            for (Media media: mediaPage.getContent()){
                projectIds.add(media.getProjectId());
                subjectIds.add(media.getSubjectId());
                mediaIds.add(media.getId());
            }
            //Map<String, MediaUserActionStatistics> statisticsMap = mediaUserActionStatisticsService.findByMediaIds(mediaIds);
            Map<String,ExploreProject> projectMap = exploreProjectService.getExploreProjectsByIds(projectIds);
            Map<Long, UserProfileMiniDto> profileMiniDtoMap = userProfileService.getUserProfileForSubjectIds(subjectIds);
            for (Media media: mediaPage.getContent()){
                envelopedB2CMedias.add(new EnvelopedB2CMedia(media,
                        media.getMediaUserActionStatistics() == null?new MediaUserActionStatistics():media.getMediaUserActionStatistics(),
                        projectMap.get(media.getProjectId()),
                        profileMiniDtoMap.get(media.getSubjectId())));
            }

            return new PageImpl<>(envelopedB2CMedias,mediaPage.getPageable(),mediaPage.getTotalElements());

        }
        return null;
    }

}
