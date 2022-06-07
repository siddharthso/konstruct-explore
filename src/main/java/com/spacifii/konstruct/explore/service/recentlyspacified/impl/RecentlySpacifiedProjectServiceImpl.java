package com.spacifii.konstruct.explore.service.recentlyspacified.impl;


import com.spacifii.konstruct.explore.entities.explore.ExploreProject;
import com.spacifii.konstruct.explore.entities.recentlyspacified.*;
import com.spacifii.konstruct.explore.exception.recentlyspacified.RecentlySpacifiedProjectNotFound;
import com.spacifii.konstruct.explore.model.dto.explore.EnvelopedProject;
import com.spacifii.konstruct.explore.model.dto.explore.FilterRequestDto;
import com.spacifii.konstruct.explore.model.dto.explore.UserProfileMiniDto;
import com.spacifii.konstruct.explore.model.dto.query.SearchQuery;
import com.spacifii.konstruct.explore.model.dto.recentlyspacified.*;
import com.spacifii.konstruct.explore.repository.explore.ExploreProjectRepository;
import com.spacifii.konstruct.explore.repository.recentlyspacified.RecentlySpacifiedProjectRepository;
import com.spacifii.konstruct.explore.service.explore.AdhocService;
import com.spacifii.konstruct.explore.service.explore.ExploreProjectService;
import com.spacifii.konstruct.explore.service.explore.QueryUtilService;
import com.spacifii.konstruct.explore.service.explore.UserProfileService;
import com.spacifii.konstruct.explore.service.recentlyspacified.CustomerTestimonialService;
import com.spacifii.konstruct.explore.service.recentlyspacified.RecentlySpacifiedCustomerReviewService;
import com.spacifii.konstruct.explore.service.recentlyspacified.RecentlySpacifiedProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This service class manges RecentlySpacifiedProject
 */
@Service
public class RecentlySpacifiedProjectServiceImpl implements RecentlySpacifiedProjectService {

    @Autowired
    RecentlySpacifiedProjectRepository recentlySpacifiedProjectRepository;

    @Autowired
    ExploreProjectService exploreProjectService;

    @Autowired
    ExploreProjectRepository projectRepository;

    @Autowired
    QueryUtilService queryUtilService;


    @Autowired
    RecentlySpacifiedCustomerReviewService recentlySpacifiedCustomerReviewService;

    @Autowired
    CustomerTestimonialService customerTestimonialService;

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    AdhocService adhocService;



    /**
     * This service method is used to Save recently Spacified Project from Explore Project
     *
     * @param projectId
     * @param subjectId
     * @return
     */
    @Override
    public EnvelopedRecentlySpacifiedProjectDto saveFromExploreProject(String projectId, Long subjectId) {
        ExploreProject exploreProject = exploreProjectService.getExploreProjectById(projectId);
        exploreProject.setAddedToRecentlySpacifiedAndActive(true);

        exploreProject.preUpdate(subjectId);
        projectRepository.save(exploreProject);

        RecentlySpacifiedProject recentlySpacifiedProject = new RecentlySpacifiedProject();
        recentlySpacifiedProject.setProjectId(projectId);
        recentlySpacifiedProject.mergeProjectInMe(exploreProject);
        recentlySpacifiedProject = recentlySpacifiedProjectRepository.save(recentlySpacifiedProject);

        return getEnvelopedRecentlySpacifiedDtoByRecentlySpacifiedProject(recentlySpacifiedProject);
    }

    /**
     * This service method is used to Update recently Spacified Project from Explore Project
     *
     * @param projectId
     * @param subjectId
     * @return
     */
    @Override
    public EnvelopedRecentlySpacifiedProjectDto updateFromExploreProject(String projectId, Long subjectId) {
        ExploreProject exploreProject = exploreProjectService.getExploreProjectById(projectId);
        exploreProject.setAddedToRecentlySpacifiedAndActive(true);
        exploreProject.preUpdate(subjectId);
        projectRepository.save(exploreProject);
        RecentlySpacifiedProject recentlySpacifiedProject = findById(projectId);
        recentlySpacifiedProject.mergeProjectInMe(exploreProject);
        recentlySpacifiedProject = recentlySpacifiedProjectRepository.save(recentlySpacifiedProject);

        return getEnvelopedRecentlySpacifiedDtoByRecentlySpacifiedProject(recentlySpacifiedProject);
    }

    /**
     * This service method is used to get pages of EnvelopedRecentlySpacifiedProjectDto via FilterRequestDto
     *
     * @param filterRequestDto
     * @return
     */
    @Override
    public Page<EnvelopedRecentlySpacifiedProjectDto> getRecentlySpacifiedProjectFiltered(FilterRequestDto filterRequestDto) {
        return getPageEnvelopedRecentlySpacifiedProjectDtoByRecentlySpacifiedProjectPage(getRecentlySpacifiedProjectFilteredInternal(filterRequestDto));
    }

    /**
     * This service method is used to get Active Recently Spacified Projects
     *
     * @param filterRequestDto
     * @return
     */
    @Override
    public Page<EnvelopedRecentlySpacifiedProjectDto> getActiveRecentlySpacifiedProjetFiltered(FilterRequestDto filterRequestDto) {
        return getPageEnvelopedRecentlySpacifiedProjectDtoByRecentlySpacifiedProjectPage(getRecentlySpacifiedProjectFilteredInternal(filterRequestDto));
    }

    /**
     * This service method is used to get Recentlu Spacified project via FilterRequest Dto
     *
     * @param filterRequestDto
     * @return
     */
    @Override
    public Page<RecentlySpacifiedProject> getRecentlySpacifiedProjectFilteredInternal(FilterRequestDto filterRequestDto) {
        return queryUtilService.getResultList(filterRequestDto,new HashSet<>(Arrays.asList("lastModifiedDate")),new RecentlySpacifiedProject());
    }

    /**
     * This service method is usd to find Recently Spacified Project By Id
     *
     * @param projectId
     * @return
     */
    @Override
    public RecentlySpacifiedProject findById(String projectId) {
        Optional<RecentlySpacifiedProject> recentlySpacifiedProjectOptional = recentlySpacifiedProjectRepository.findById(projectId);
        if(recentlySpacifiedProjectOptional.isPresent()){
            return recentlySpacifiedProjectOptional.get();
        }
        throw new RecentlySpacifiedProjectNotFound();
    }

    /**
     * This service method is used to find EnvelopedRecentlySpacifiedDtoById
     *
     * @param projectId
     * @return
     */
    @Override
    public EnvelopedRecentlySpacifiedProjectDto getEnvelopedRecentlySpacifiedDtoById(String projectId) {
        return getEnvelopedRecentlySpacifiedDtoByRecentlySpacifiedProject(findById(projectId));
    }

    /**
     * This service method is used for get EnvelopedRecentlySpacifiedProjectDto by RecentlySpacifiedProject
     *
     * @param recentlySpacifiedProject
     * @return
     */
    @Override
    public EnvelopedRecentlySpacifiedProjectDto getEnvelopedRecentlySpacifiedDtoByRecentlySpacifiedProject(RecentlySpacifiedProject recentlySpacifiedProject) {
        EnvelopedRecentlySpacifiedProjectDto envelopedRecentlySpacifiedProjectDto = new EnvelopedRecentlySpacifiedProjectDto();
        envelopedRecentlySpacifiedProjectDto.setRecentlySpacifiedProject(recentlySpacifiedProject);
        return envelopedRecentlySpacifiedProjectDto;
    }

    /**
     * This service method is ued to get Pages of EnvelopedRecentlySpacifiedProjectDto by Pages of RecentlySpacifiedProject
     *
     * @param recentlySpacifiedProjectPage
     * @return
     */
    @Override
    public Page<EnvelopedRecentlySpacifiedProjectDto> getPageEnvelopedRecentlySpacifiedProjectDtoByRecentlySpacifiedProjectPage(Page<RecentlySpacifiedProject> recentlySpacifiedProjectPage) {
        if(!recentlySpacifiedProjectPage.hasContent()){
            return null;
        }
        Set<String> projectIds = recentlySpacifiedProjectPage.getContent().stream().map(RecentlySpacifiedProject::getProjectId).collect(Collectors.toSet());
        Map<String,EnvelopedProject> envelopedProjectMap = exploreProjectService.getEnvelopedExploreProjectsByIds(projectIds);
        //List<RecentlySpacifiedProject> recentlySpacifiedProjects = recentlySpacifiedProjectPage.getContent();
        List<EnvelopedRecentlySpacifiedProjectDto> envelopedRecentlySpacifiedProjectDtos = new ArrayList<>();
        for (RecentlySpacifiedProject recentlySpacifiedProject: recentlySpacifiedProjectPage.getContent()) {
            EnvelopedRecentlySpacifiedProjectDto envelopedRecentlySpacifiedProjectDto = new EnvelopedRecentlySpacifiedProjectDto();
            envelopedRecentlySpacifiedProjectDto.setRecentlySpacifiedProject(recentlySpacifiedProject);
            envelopedRecentlySpacifiedProjectDto.setEnvelopedExploreProject(envelopedProjectMap.get(recentlySpacifiedProject.getProjectId()));
            envelopedRecentlySpacifiedProjectDtos.add(envelopedRecentlySpacifiedProjectDto);
        }

        return new PageImpl<>(envelopedRecentlySpacifiedProjectDtos,recentlySpacifiedProjectPage.getPageable(),recentlySpacifiedProjectPage.getTotalElements());
    }

    /**
     * This service method is used to get Recently Spacified Projects for Public access
     *
     * @param filterRequestDto
     * @return
     */
    @Override
    public Page<EnvelopedRecentlySpacifiedProjectDto> getPageEnvelopedRecentlySpacifiedProjectPublic(FilterRequestDto filterRequestDto) {
        SearchQuery searchQuery = filterRequestDto.getSearchQuery() == null ? new SearchQuery(): filterRequestDto.getSearchQuery();
        Map<String, List<Object>> where = searchQuery.getWhere() == null ? new LinkedHashMap<>(): searchQuery.getWhere();
        where.put("recentlySpacifiedStatus",Arrays.asList(RecentlySpacifiedStatus.PUBLISHED));
        where.put("recentlySpacifiedVisibility",Arrays.asList(RecentlySpacifiedVisibility.PUBLIC));
        searchQuery.setWhere(where);
        filterRequestDto.setSearchQuery(searchQuery);
        return getPageEnvelopedRecentlySpacifiedProjectDtoByRecentlySpacifiedProjectPage(getRecentlySpacifiedProjectFilteredInternal(filterRequestDto));
    }

    /**
     * This service method is used to find EnvelopedRecentlySpacifiedProjectDtoExtended by ProjectId
     *
     * @param projectId
     * @return
     */
    @Override
    public EnvelopedRecentlySpacifiedProjectDtoExtended findEnvelopedRecentlySpacifiedProjectDtoExtendedByProjectId(String projectId) {

        RecentlySpacifiedProject recentlySpacifiedProject = findById(projectId);

        EnvelopedProject envelopedProject = exploreProjectService.getEnvelopedProject(projectId,0L);

        List<CustomerTestimonial> customerTestimonials = customerTestimonialService.findByProjectId(projectId);

        List<RecentlySpacifiedCustomerReview> reviews =  recentlySpacifiedCustomerReviewService.findByProjectId(projectId);

        //Added This to Reduce Network Calls
        Set<Long> subjectIds = new HashSet<>();
        subjectIds.add(envelopedProject.getExploreProject().getCreatedBy());
        if(customerTestimonials != null){
            for (CustomerTestimonial customerTestimonial: customerTestimonials) {
                subjectIds.add(customerTestimonial.getCustomerSubjectId());
                subjectIds.add(customerTestimonial.getInitiatedBySubjectId());
            }

        }
        if(reviews != null){
            for (RecentlySpacifiedCustomerReview recentlySpacifiedCustomerReview: reviews) {
                subjectIds.add(recentlySpacifiedCustomerReview.getRevieweeSubjectId());
                subjectIds.add(recentlySpacifiedCustomerReview.getReviewerSubjectId());
            }
        }

        Map<Long, UserProfileMiniDto> map = userProfileService.getUserProfileForSubjectIds(subjectIds);

        List<EnvelopedCustomerTestimonial> envelopedCustomerTestimonials = new ArrayList<>();
        List<EnvelopedCustomerReview> envelopedCustomerReviews = new ArrayList<>();
        if(customerTestimonials != null) {
            for (CustomerTestimonial customerTestimonial : customerTestimonials) {
                envelopedCustomerTestimonials.add(new EnvelopedCustomerTestimonial(map.get(customerTestimonial.getInitiatedBySubjectId()),map.get(customerTestimonial.getCustomerSubjectId()),customerTestimonial));
            }
        }
        if(reviews != null) {
            for (RecentlySpacifiedCustomerReview recentlySpacifiedCustomerReview : reviews) {
                envelopedCustomerReviews.add(new EnvelopedCustomerReview(map.get(recentlySpacifiedCustomerReview.getReviewerSubjectId()),map.get(recentlySpacifiedCustomerReview.getRevieweeSubjectId()),recentlySpacifiedCustomerReview));
            }
        }
        return new EnvelopedRecentlySpacifiedProjectDtoExtended(recentlySpacifiedProject,envelopedProject,envelopedCustomerTestimonials,envelopedCustomerReviews,map.get(envelopedProject.getExploreProject().getCreatedBy()));
    }

    /**
     * This service method is used to get EnvelopedRecentlySpacifiedProjectDtoExtended via RouteUrl
     *
     * @param routeUrl
     * @return
     */
    @Override
    public EnvelopedRecentlySpacifiedProjectDtoExtended findEnvelopedRecentlySpacifiedProjectDtoExtendedViaRouteUrl(String routeUrl) {
        RecentlySpacifiedProject recentlySpacifiedProject = recentlySpacifiedProjectRepository.findFirstByRouteUrl(routeUrl);
        if(recentlySpacifiedProject == null){
            throw new RecentlySpacifiedProjectNotFound();
        }
        return findEnvelopedRecentlySpacifiedProjectDtoExtendedByProjectId(recentlySpacifiedProject.getProjectId());
    }

    /**
     * This service method is used to update BeforeAfterMedias for RecentlySpacifiedProject
     *
     * @param beforeAfterMedias
     * @param projectId
     * @param subjectId
     * @return
     */
    @Override
    public EnvelopedRecentlySpacifiedProjectDto updateBeforeAfterMedias(LinkedHashSet<BeforeAfterMedia> beforeAfterMedias, String projectId, Long subjectId) {
        RecentlySpacifiedProject recentlySpacifiedProject = findById(projectId);
        recentlySpacifiedProject.setBeforeAfterMedias(beforeAfterMedias);
        recentlySpacifiedProject.preUpdate(subjectId);
        return getEnvelopedRecentlySpacifiedDtoByRecentlySpacifiedProject(recentlySpacifiedProjectRepository.save(recentlySpacifiedProject));
    }

    /**
     * This service method is used to update RecentlySpacifiedMedia for RecentlySpacifiedProject
     *
     * @param recentlySpacifiedMedia
     * @param projectId
     * @param subjectId
     * @return
     */
    @Override
    public EnvelopedRecentlySpacifiedProjectDto updateRecentlySpacifiedMedia(LinkedHashSet<RecentlySpacifiedMedia> recentlySpacifiedMedia, String projectId, Long subjectId) {
        RecentlySpacifiedProject recentlySpacifiedProject = findById(projectId);
        recentlySpacifiedProject.setRecentlySpacifiedMedias(recentlySpacifiedMedia);
        recentlySpacifiedProject.preUpdate(subjectId);
        return getEnvelopedRecentlySpacifiedDtoByRecentlySpacifiedProject(recentlySpacifiedProjectRepository.save(recentlySpacifiedProject));
    }

    /**
     * This service method is used to update RecentlySpacified Featured Properties
     *
     * @param recentlySpacifiedRequestFeaturedDto
     * @param projectId
     * @param subjectId
     * @return
     */
    @Override
    public EnvelopedRecentlySpacifiedProjectDto updateRecentlySpacifiedFeaturedProperties(RecentlySpacifiedRequestFeaturedDto recentlySpacifiedRequestFeaturedDto, String projectId, Long subjectId) {
        RecentlySpacifiedProject recentlySpacifiedProject = findById(projectId);
        recentlySpacifiedProject.mergeInMe(recentlySpacifiedRequestFeaturedDto);
        recentlySpacifiedProject.preUpdate(subjectId);
        return getEnvelopedRecentlySpacifiedDtoByRecentlySpacifiedProject(recentlySpacifiedProjectRepository.save(recentlySpacifiedProject));

    }

    /**
     * This service method is used to update RecentlySpacified Main Section
     *
     * @param recentlySpacifiedRequestMainSectionDto
     * @param projectId
     * @param subjectId
     * @return
     */
    @Override
    public EnvelopedRecentlySpacifiedProjectDto updateRecentlySpacifiedMainSection(RecentlySpacifiedRequestMainSectionDto recentlySpacifiedRequestMainSectionDto, String projectId, Long subjectId) {
        RecentlySpacifiedProject recentlySpacifiedProject = findById(projectId);
        recentlySpacifiedProject.mergeInMe(recentlySpacifiedRequestMainSectionDto);
        recentlySpacifiedProject.preUpdate(subjectId);
        return getEnvelopedRecentlySpacifiedDtoByRecentlySpacifiedProject(recentlySpacifiedProjectRepository.save(recentlySpacifiedProject));

    }

    /**
     * This service method is used to update Recently Spacified Permissions
     *
     * @param recentlySpacifiedRequestPermissionDto
     * @param projectId
     * @param subjectId
     * @return
     */
    @Override
    public EnvelopedRecentlySpacifiedProjectDto updateRecentlySpacifiedPermission(RecentlySpacifiedRequestPermissionDto recentlySpacifiedRequestPermissionDto, String projectId, Long subjectId) {
        RecentlySpacifiedProject recentlySpacifiedProject = findById(projectId);
        recentlySpacifiedProject.mergeInMe(recentlySpacifiedRequestPermissionDto);
        recentlySpacifiedProject.preUpdate(subjectId);
        return getEnvelopedRecentlySpacifiedDtoByRecentlySpacifiedProject(recentlySpacifiedProjectRepository.save(recentlySpacifiedProject));

    }

    /**
     * This service method is used to update Keywords Tags and Categories
     *
     * @param recentlySpacifiedRequestKeywordTagsCagtegoriesDto
     * @param projectId
     * @param subjectId
     * @return
     */
    @Override
    public EnvelopedRecentlySpacifiedProjectDto updateRecentlySpacifiedKeywordsTagsCategories(RecentlySpacifiedRequestKeywordTagsCagtegoriesDto recentlySpacifiedRequestKeywordTagsCagtegoriesDto, String projectId, Long subjectId) {
        RecentlySpacifiedProject recentlySpacifiedProject = findById(projectId);
        recentlySpacifiedProject.mergeInMe(recentlySpacifiedRequestKeywordTagsCagtegoriesDto);
        recentlySpacifiedProject.preUpdate(subjectId);
        return getEnvelopedRecentlySpacifiedDtoByRecentlySpacifiedProject(recentlySpacifiedProjectRepository.save(recentlySpacifiedProject));

    }
}
