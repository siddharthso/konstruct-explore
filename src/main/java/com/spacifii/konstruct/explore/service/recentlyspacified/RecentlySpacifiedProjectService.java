package com.spacifii.konstruct.explore.service.recentlyspacified;


import com.spacifii.konstruct.explore.entities.recentlyspacified.BeforeAfterMedia;
import com.spacifii.konstruct.explore.entities.recentlyspacified.RecentlySpacifiedMedia;
import com.spacifii.konstruct.explore.entities.recentlyspacified.RecentlySpacifiedProject;
import com.spacifii.konstruct.explore.model.dto.explore.FilterRequestDto;
import com.spacifii.konstruct.explore.model.dto.recentlyspacified.*;
import org.springframework.data.domain.Page;

import java.util.LinkedHashSet;

/**
 * This service class manges Recently Spacified Project
 */
public interface RecentlySpacifiedProjectService {


    /**
     * This service method is used to Save recently Spacified Project from Explore Project
     * @param projectId
     * @param subjectId
     * @return
     */
    EnvelopedRecentlySpacifiedProjectDto saveFromExploreProject(String projectId, Long subjectId);

    /**
     * This service method is used to Update recently Spacified Project from Explore Project
     * @param projectId
     * @param subjectId
     * @return
     */
    EnvelopedRecentlySpacifiedProjectDto updateFromExploreProject(String projectId, Long subjectId);

    /**
     * This service method is used to get pages of EnvelopedRecentlySpacifiedProjectDto via FilterRequestDto
     * @param filterRequestDto
     * @return
     */
    Page<EnvelopedRecentlySpacifiedProjectDto> getRecentlySpacifiedProjectFiltered(FilterRequestDto filterRequestDto);

    /**
     * This service method is used to get Active Recently Spacified Projects
     * @param filterRequestDto
     * @return
     */
    Page<EnvelopedRecentlySpacifiedProjectDto> getActiveRecentlySpacifiedProjetFiltered(FilterRequestDto filterRequestDto);


    /**
     * This service method is used to get Recentlu Spacified project via FilterRequest Dto
     * @param filterRequestDto
     * @return
     */
    Page<RecentlySpacifiedProject> getRecentlySpacifiedProjectFilteredInternal(FilterRequestDto filterRequestDto);

    /**
     * This service method is usd to find Recently Spacified Project By Id
     * @param projectId
     * @return
     */
    RecentlySpacifiedProject findById(String projectId);

    /**
     * This service method is used to find EnvelopedRecentlySpacifiedDtoById
     * @param projectId
     * @return
     */
    EnvelopedRecentlySpacifiedProjectDto getEnvelopedRecentlySpacifiedDtoById(String projectId);

    /**
     * This service method is used for get EnvelopedRecentlySpacifiedProjectDto by RecentlySpacifiedProject
     * @param recentlySpacifiedProject
     * @return
     */
    EnvelopedRecentlySpacifiedProjectDto getEnvelopedRecentlySpacifiedDtoByRecentlySpacifiedProject(RecentlySpacifiedProject recentlySpacifiedProject);

    /**
     * This service method is ued to get Pages of EnvelopedRecentlySpacifiedProjectDto by Pages of RecentlySpacifiedProject
     * @param recentlySpacifiedProjectPage
     * @return
     */
    Page<EnvelopedRecentlySpacifiedProjectDto> getPageEnvelopedRecentlySpacifiedProjectDtoByRecentlySpacifiedProjectPage(Page<RecentlySpacifiedProject> recentlySpacifiedProjectPage);

    /**
     * This service method is used to get Recently Spacified Projects for Public access
     * @param filterRequestDto
     * @return
     */
    Page<EnvelopedRecentlySpacifiedProjectDto> getPageEnvelopedRecentlySpacifiedProjectPublic(FilterRequestDto filterRequestDto);

    /**
     * This service method is used to find EnvelopedRecentlySpacifiedProjectDtoExtended by ProjectId
     * @param projectId
     * @return
     */
    EnvelopedRecentlySpacifiedProjectDtoExtended findEnvelopedRecentlySpacifiedProjectDtoExtendedByProjectId(String projectId);


    /**
     * This service method is used to get EnvelopedRecentlySpacifiedProjectDtoExtended via RouteUrl
     * @param routeUrl
     * @return
     */
    EnvelopedRecentlySpacifiedProjectDtoExtended findEnvelopedRecentlySpacifiedProjectDtoExtendedViaRouteUrl(String routeUrl);

    /**
     * This service method is used to update BeforeAfterMedias for RecentlySpacifiedProject
     * @param beforeAfterMedias
     * @param projectId
     * @param subjectId
     * @return
     */
    EnvelopedRecentlySpacifiedProjectDto updateBeforeAfterMedias(LinkedHashSet<BeforeAfterMedia> beforeAfterMedias, String projectId, Long subjectId);

    /**
     * This service method is used to update RecentlySpacifiedMedia for RecentlySpacifiedProject
     * @param recentlySpacifiedMedia
     * @param projectId
     * @param subjectId
     * @return
     */
    EnvelopedRecentlySpacifiedProjectDto updateRecentlySpacifiedMedia(LinkedHashSet<RecentlySpacifiedMedia> recentlySpacifiedMedia, String projectId, Long subjectId);


    /**
     * This service method is used to update RecentlySpacified Featured Properties
     * @param recentlySpacifiedRequestFeaturedDto
     * @param projectId
     * @param subjectId
     * @return
     */
    EnvelopedRecentlySpacifiedProjectDto updateRecentlySpacifiedFeaturedProperties(RecentlySpacifiedRequestFeaturedDto recentlySpacifiedRequestFeaturedDto, String projectId, Long subjectId);


    /**
     * This service method is used to update RecentlySpacified Main Section
     * @param recentlySpacifiedRequestMainSectionDto
     * @param projectId
     * @param subjectId
     * @return
     */
    EnvelopedRecentlySpacifiedProjectDto updateRecentlySpacifiedMainSection(RecentlySpacifiedRequestMainSectionDto recentlySpacifiedRequestMainSectionDto, String projectId, Long subjectId);

    /**
     * This service method is used to update Recently Spacified Permissions
     * @param recentlySpacifiedRequestPermissionDto
     * @param projectId
     * @param subjectId
     * @return
     */
    EnvelopedRecentlySpacifiedProjectDto updateRecentlySpacifiedPermission(RecentlySpacifiedRequestPermissionDto recentlySpacifiedRequestPermissionDto, String projectId, Long subjectId);


    /**
     * This service method is used to update Keywords Tags and Categories
     * @param recentlySpacifiedRequestKeywordTagsCagtegoriesDto
     * @param projectId
     * @param subjectId
     * @return
     */
    EnvelopedRecentlySpacifiedProjectDto updateRecentlySpacifiedKeywordsTagsCategories(RecentlySpacifiedRequestKeywordTagsCagtegoriesDto recentlySpacifiedRequestKeywordTagsCagtegoriesDto, String projectId, Long subjectId);


}
