package com.spacifii.konstruct.explore.service.explore;

import com.spacifii.konstruct.explore.entities.explore.ExploreProject;
import com.spacifii.konstruct.explore.entities.explore.Media;
import com.spacifii.konstruct.explore.model.dto.explore.EnvelopedMedia;
import com.spacifii.konstruct.explore.model.dto.explore.EnvelopedProject;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This is service class for ExploreProject
 */
public interface ExploreProjectService {

    /**
     * This method saves ExploreProject
     * @param exploreProject
     * @param subjectId
     * @return
     */
    ExploreProject save(ExploreProject exploreProject, Long subjectId);

    /**
     * This method updates existing ExploreProject
     * @param exploreProject
     * @param projectId
     * @param subjectId
     * @return
     */
    ExploreProject updateProject(ExploreProject exploreProject, String projectId, Long subjectId);

    /**
     * This method gets existing ExploreProject by Id
     * @param projectId
     * @return
     */
    ExploreProject getExploreProjectById(String projectId);


    /**
     * This method returns All ExploreProjects for a subjectId
     * @param subjectId
     * @return
     */
    List<ExploreProject> getAllExploreProjectForSubject(Long subjectId);

    /**
     * This method returns EnvelopedProject by using projectId and subjectId
     * @param projectId
     * @param subjectId
     * @return
     */
    EnvelopedProject getEnvelopedProject(String projectId, Long subjectId);

    /**
     * This method returns all EnvelopedProject by ExploreProject
     * @param exploreProject
     * @param subjectId
     * @return
     */
     EnvelopedProject getEnvelopedProjectByProject(ExploreProject exploreProject, Long subjectId);

    /**
     * This method returns all EnvelopedProject by ExploreProject with Publicly Viewable Images
     * @param exploreProject
     * @param subjectId
     * @return
     */
    EnvelopedProject getEnvelopedProjectByProjectForPublic(ExploreProject exploreProject, Long subjectId);


    /**
     * This method returns all EnvelopedProject for subjectId
     * @param subjectId
     * @return
     */
    List<EnvelopedProject> getAllEnvelopedProjectForUser(Long subjectId);


    /**
     * This method gets EnvelopedMedia from medias
     * @param envelopedMedias
     * @param medias
     * @return
     */
    List<EnvelopedMedia> getEnvelopedMedia(List<EnvelopedMedia> envelopedMedias, List<Media> medias);

    /**
     * This method gets all ExploreProjects by Id
     * @param projectIds
     * @return
     */
    Map<String,ExploreProject> getExploreProjectsByIds(Set<String> projectIds);

    /**
     * This method gets all EnvelopedProjects by Id
     * @param projects
     * @return
     */
    Map<String,EnvelopedProject> getEnvelopedExploreProjectsByIds(Set<String> projects);

    /**
     * This method is used to set FeaturedProjectMedia for ExploreProject
     * @param projectId
     * @param mediaId
     * @param subjectId
     * @return
     */
    Boolean setFeaturedExploreProjectMedia(String projectId, String mediaId, Long subjectId);


    /**
     * This service method  is used to get Project Keywords by Id
     * @param projectId
     * @return
     */
    Set<String> getProjectKeywords(String projectId);

}
