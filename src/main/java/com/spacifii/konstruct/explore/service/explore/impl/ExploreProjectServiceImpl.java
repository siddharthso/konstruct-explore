package com.spacifii.konstruct.explore.service.explore.impl;

import com.spacifii.konstruct.explore.annotation.NotToUseDuringMerge;
import com.spacifii.konstruct.explore.entities.explore.ExploreProject;
import com.spacifii.konstruct.explore.entities.explore.Media;
import com.spacifii.konstruct.explore.entities.explore.MediaUserActionStatistics;
import com.spacifii.konstruct.explore.entities.explore.ProjectUserActionStatistics;
import com.spacifii.konstruct.explore.exception.explore.ProjectNotFoundException;
import com.spacifii.konstruct.explore.model.dto.explore.EnvelopedMedia;
import com.spacifii.konstruct.explore.model.dto.explore.EnvelopedProject;
import com.spacifii.konstruct.explore.repository.explore.ExploreProjectRepository;
import com.spacifii.konstruct.explore.service.explore.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * This is service class for ExploreProject
 */
@Service
public class ExploreProjectServiceImpl implements ExploreProjectService {

    @Autowired
    ExploreProjectRepository exploreProjectRepository;

    @Autowired
    MediaService mediaService;

    @Autowired
    MediaUserActionStatisticsService mediaUserActionStatisticsService;

    @Autowired
    ProjectUserActionStatisticsService projectUserActionStatisticsService;

    @Autowired
    ExploreWalkThroughService exploreWalkThroughService;


    /**
     * This method saves ExploreProject
     * @param exploreProject
     * @param subjectId
     * @return
     */
    @Override
    public ExploreProject save(ExploreProject exploreProject, Long subjectId) {
        exploreProject.setSubjectId(subjectId);
        exploreProject.preSave(subjectId);
        return exploreProjectRepository.save(exploreProject);
    }

    /**
     * This method updates existing ExploreProject
     * @param exploreProject
     * @param projectId
     * @param subjectId
     * @return
     */
    @Override
    public ExploreProject updateProject(ExploreProject exploreProject, String projectId, Long subjectId) {
        ExploreProject exploreProjectExisting = getExploreProjectById(projectId);
        String[] arr  = BeanUtils.findFields(ExploreProject.class, NotToUseDuringMerge.class);
        BeanUtils.copyProperties(exploreProject,exploreProjectExisting,arr);
        exploreProjectExisting.preUpdate(subjectId);

        return exploreProjectRepository.save(exploreProjectExisting);
    }

    /**
     * This method gets existing ExploreProject by Id
     * @param projectId
     * @return
     */
    @Override
    public ExploreProject getExploreProjectById(String projectId) {
        Optional<ExploreProject> exploreProjectOptional  = exploreProjectRepository.findById(projectId);

        if(exploreProjectOptional.isPresent()){
            return exploreProjectOptional.get();
        }

        throw new ProjectNotFoundException();
    }

    /**
     * This method returns All ExploreProjects for a subjectId
     * @param subjectId
     * @return
     */
    @Override
    public List<ExploreProject> getAllExploreProjectForSubject(Long subjectId) {
        return exploreProjectRepository.findBySubjectId(subjectId);
    }

    /**
     * This method returns EnvelopedProject by using projectId and subjectId
     * @param projectId
     * @param subjectId
     * @return
     */
    @Override
    public EnvelopedProject getEnvelopedProject(String projectId, Long subjectId) {

        ExploreProject exploreProject  = getExploreProjectById(projectId);
        //TODO: check if user can get this

        EnvelopedProject envelopedProject = new EnvelopedProject();
        envelopedProject.setExploreProject(exploreProject);

        List<EnvelopedMedia> envelopedMedias = null;

        List<Media> medias = mediaService.findByProjectId(projectId);
        envelopedMedias = getEnvelopedMedia(envelopedMedias, medias);
        envelopedProject.setEnvelopedMedia(envelopedMedias);
        envelopedProject.setWalkThroughs(exploreWalkThroughService.findByProjectId(projectId));

        return envelopedProject;
    }


    /**
     * This method returns all EnvelopedProject for subjectId
     * @param subjectId
     * @return
     */
    @Override
    public List<EnvelopedProject> getAllEnvelopedProjectForUser(Long subjectId) {
        List<ExploreProject> exploreProjects =  exploreProjectRepository.findBySubjectId(subjectId);

        List<EnvelopedProject> envelopedProjects = null;
        if(exploreProjects != null && exploreProjects.size() > 0){
            envelopedProjects = new ArrayList<>();
            for (ExploreProject exploreProject : exploreProjects){
                envelopedProjects.add(getEnvelopedProjectByProject(exploreProject,subjectId));
            }
        }

        return envelopedProjects;
    }


    /**
     * This method returns all EnvelopedProject by ExploreProject
     * @param exploreProject
     * @param subjectId
     * @return
     */
    public EnvelopedProject getEnvelopedProjectByProject(ExploreProject exploreProject, Long subjectId) {

       // ExploreProject exploreProject  = getExploreProjectById(projectId);
        //TODO: check if user can get this

        EnvelopedProject envelopedProject = new EnvelopedProject();
        envelopedProject.setExploreProject(exploreProject);

        List<EnvelopedMedia> envelopedMedias = null;

        List<Media> medias = mediaService.findByProjectId(exploreProject.getProjectId());
        envelopedMedias = getEnvelopedMedia(envelopedMedias, medias);
        ProjectUserActionStatistics projectUserActionStatistics = exploreProject.getProjectUserActionStatistics();
        envelopedProject.setEnvelopedMedia(envelopedMedias);
        envelopedProject.setProjectUserActionStatistics(projectUserActionStatistics);
        return envelopedProject;
    }

    /**
     * This method returns all EnvelopedProject by ExploreProject with Publicly Viewable Images
     *
     * @param exploreProject
     * @param subjectId
     * @return
     */
    @Override
    public EnvelopedProject getEnvelopedProjectByProjectForPublic(ExploreProject exploreProject, Long subjectId) {
        EnvelopedProject envelopedProject = new EnvelopedProject();
        envelopedProject.setExploreProject(exploreProject);
        List<EnvelopedMedia> envelopedMedias = null;
        List<Media> medias = mediaService.findByProjectIdPubliclyVisible(exploreProject.getProjectId());
        envelopedMedias = getEnvelopedMedia(envelopedMedias, medias);
        ProjectUserActionStatistics projectUserActionStatistics = envelopedProject.getProjectUserActionStatistics();
        envelopedProject.setEnvelopedMedia(envelopedMedias);
        envelopedProject.setProjectUserActionStatistics(projectUserActionStatistics);
        return envelopedProject;
    }


    /**
     * This method gets EnvelopedMedia from medias
     * @param envelopedMedias
     * @param medias
     * @return
     */
    public List<EnvelopedMedia> getEnvelopedMedia(List<EnvelopedMedia> envelopedMedias, List<Media> medias) {
        if(medias != null){
            envelopedMedias = new ArrayList<>();
            //Set<String> ids = medias.stream().map(Media::getId).collect(Collectors.toSet());

            //Map<String,MediaUserActionStatistics> map =  mediaUserActionStatisticsService.findByMediaIds(ids);

            for(Media media: medias){
                EnvelopedMedia envelopedMedia = new EnvelopedMedia();
                envelopedMedia.setMedia(media);
                envelopedMedia.setMediaUserActionStatistics(media.getMediaUserActionStatistics());
                envelopedMedias.add(envelopedMedia);
            }

        }
        return envelopedMedias;
    }

    /**
     * This method gets all ExploreProjects by Id
     * @param projectIds
     * @return
     */
    @Override
    public Map<String, ExploreProject> getExploreProjectsByIds(Set<String> projectIds) {
        return BeanUtils.makeCollection(exploreProjectRepository.findAllById(projectIds))
                .stream().collect(Collectors.toMap(ExploreProject::getProjectId, Function.identity()));
    }

    /**
     * This method gets all EnvelopedProjects by Id
     *
     * @param projectIds
     * @return
     */
    @Override
    public Map<String, EnvelopedProject> getEnvelopedExploreProjectsByIds(Set<String> projectIds) {
        Map<String,ExploreProject> stringExploreProjectMap = getExploreProjectsByIds(projectIds);
        Set<ExploreProject> projects  = new HashSet<>(stringExploreProjectMap.values());
        Map<String,EnvelopedProject> map = new LinkedHashMap<>();
        for (ExploreProject exploreProject: projects) {
            map.put(exploreProject.getProjectId(),getEnvelopedProjectByProject(exploreProject,0L));
        }
        return map;
    }

    /**
     * This method is used to set FeaturedProjectMedia for ExploreProject
     *
     * @param projectId
     * @param mediaId
     * @param subjectId
     * @return
     */
    @Override
    public Boolean setFeaturedExploreProjectMedia(String projectId, String mediaId, Long subjectId) {
        ExploreProject exploreProject = getExploreProjectById(projectId);
        Media media = mediaService.findById(mediaId);
        exploreProject.setFeaturedProjectMedia(media);
        exploreProject.preUpdate(subjectId);
        exploreProjectRepository.save(exploreProject);
        return true;
    }

    /**
     * This service method  is used to get Project Keywords by Id
     *
     * @param projectId
     * @return
     */
    @Override
    public Set<String> getProjectKeywords(String projectId) {
        ExploreProject exploreProject = getExploreProjectById(projectId);
        Set<String> strings = new HashSet<>();
        strings.addAll(exploreProject.getProjectStyles());
        strings.addAll(exploreProject.getScopeTypes());
        if(exploreProject.getPropertyType() != null) {
            strings.add(exploreProject.getProjectType());
        }
        return strings;
    }

}
