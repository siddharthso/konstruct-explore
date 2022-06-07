package com.spacifii.konstruct.explore.service.conceptBoard.impl;

import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoard;
import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardMedia;
import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardVisibility;
import com.spacifii.konstruct.explore.exception.conceptBoard.ConceptBoardNotFoundException;
import com.spacifii.konstruct.explore.exception.conceptBoard.DuplicateConceptBoardFoundException;
import com.spacifii.konstruct.explore.model.dto.conceptBoard.ConceptBoardAllowedListRequestDto;
import com.spacifii.konstruct.explore.model.dto.conceptBoard.ConceptBoardEnveloped;
import com.spacifii.konstruct.explore.model.dto.conceptBoard.ConceptBoardFeaturedImageRequestDto;
import com.spacifii.konstruct.explore.model.dto.conceptBoard.ConceptBoardRequestDto;
import com.spacifii.konstruct.explore.model.dto.explore.ExternalMediaDto;
import com.spacifii.konstruct.explore.model.dto.explore.FilterRequestDto;
import com.spacifii.konstruct.explore.model.dto.explore.UserProfileMiniDto;
import com.spacifii.konstruct.explore.model.dto.query.SearchQuery;
import com.spacifii.konstruct.explore.repository.conceptBoard.ConceptBoardMediaRepository;
import com.spacifii.konstruct.explore.repository.conceptBoard.ConceptBoardRepository;
import com.spacifii.konstruct.explore.service.conceptBoard.ConceptBoardMediaService;
import com.spacifii.konstruct.explore.service.conceptBoard.ConceptBoardService;
import com.spacifii.konstruct.explore.service.conceptBoard.ConceptBoardShareService;
import com.spacifii.konstruct.explore.service.conceptBoard.ConceptBoardWalkThroughService;
import com.spacifii.konstruct.explore.service.explore.QueryUtilService;
import com.spacifii.konstruct.explore.service.explore.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This is service class for ConceptBoard
 */
@Service
public class ConceptBoardServiceImpl implements ConceptBoardService {

    @Autowired
    ConceptBoardShareService conceptBoardShareService;

   @Autowired
   private ConceptBoardRepository conceptBoardRepository;

   @Autowired
   private ConceptBoardMediaService conceptBoardMediaService;

   @Autowired
    QueryUtilService queryUtilService;

   @Autowired
    UserProfileService userProfileService;

   @Autowired
    ConceptBoardMediaRepository conceptBoardMediaRepository;

   @Autowired
   SaveConceptBoardMediaStrategyContext saveConceptBoardMediaStrategyContext;

   @Autowired
    ConceptBoardWalkThroughService conceptBoardWalkThroughService;


    /**
     * This service method is used to save new ConceptBoard
     * @param conceptBoardRequestDto
     * @param subjectId
     * @return
     */
    @Override
    public ConceptBoard saveConceptBoard(ConceptBoardRequestDto conceptBoardRequestDto, Long subjectId) {


        ConceptBoard conceptBoardExising = conceptBoardRepository.
                findFirstBySubjectIdAndFolderName(subjectId,conceptBoardRequestDto.getFolderName().toUpperCase());
        if(conceptBoardExising != null){
            throw new DuplicateConceptBoardFoundException();
        }
        if(conceptBoardRequestDto.getParentConceptBoardId() != null) {
            ConceptBoard conceptBoardParent = findConceptBoardByIdAndSubjectId(conceptBoardRequestDto.getParentConceptBoardId(), subjectId);

        }
        ConceptBoard conceptBoard = new ConceptBoard();
        conceptBoard.setParentConceptBoard(conceptBoardRequestDto.getParentConceptBoardId());
        conceptBoard.setFolderName(conceptBoardRequestDto.getFolderName().toUpperCase());
        conceptBoard.setDescription(conceptBoardRequestDto.getDescription());
        conceptBoard.setAllowComments(conceptBoardRequestDto.getAllowComments());
        conceptBoard.setAllowQuestions(conceptBoardRequestDto.getAllowQuestions());
        conceptBoard.setConceptBoardVisibility(conceptBoardRequestDto.getConceptBoardVisibility());
        conceptBoard.preSave(subjectId);
        return conceptBoardRepository.save(conceptBoard);
    }

    /**
     * This service method is used to change the visibility of conceptBoard
     * @param conceptBoardRequestDto
     * @param subjectId
     * @return
     */
    @Override
    public ConceptBoard updateConceptBoard(ConceptBoardRequestDto conceptBoardRequestDto, Long subjectId) {
        ConceptBoard conceptBoard = findConceptBoardByIdAndSubjectId(conceptBoardRequestDto.getId(),subjectId);
        conceptBoard.setFolderName(conceptBoardRequestDto.getFolderName().toUpperCase());
        conceptBoard.setDescription(conceptBoardRequestDto.getDescription());
        conceptBoard.setConceptBoardVisibility(conceptBoardRequestDto.getConceptBoardVisibility());
        conceptBoard.setAllowComments(conceptBoardRequestDto.getAllowComments());
        conceptBoard.setAllowQuestions(conceptBoardRequestDto.getAllowQuestions());
        conceptBoard.setActive(conceptBoardRequestDto.getActive());
        conceptBoard.preUpdate(subjectId);

        return conceptBoardRepository.save(conceptBoard);
    }

    /**
     * This service method is used to add allowed users email Ids to concept Board folder
     * @param conceptBoardAllowedListRequestDto
     * @param subjectId
     * @return
     */
    @Override
    public ConceptBoard addAllowedUsersToConceptBoardFolder(ConceptBoardAllowedListRequestDto conceptBoardAllowedListRequestDto, Long subjectId) {
        ConceptBoard conceptBoard = findConceptBoardByIdAndSubjectId(conceptBoardAllowedListRequestDto.getId(),subjectId);
        Set<String> strings = conceptBoard.getEmailWithViewRights();
        strings.addAll(conceptBoardAllowedListRequestDto.getAllowedList());
        conceptBoard.setEmailWithViewRights(strings);
        conceptBoard.preUpdate(subjectId);
        return conceptBoardRepository.save(conceptBoard);
    }


    /**
     * This service method is used to removed users email ids which were previously allowd to access user's ConceptBoard
     * @param conceptBoardAllowedListRequestDto
     * @param subjectId
     * @return
     */
    @Override
    public ConceptBoard removedAllowedUsersFromConceptBoardFolder(ConceptBoardAllowedListRequestDto conceptBoardAllowedListRequestDto, Long subjectId) {
        ConceptBoard conceptBoard = findConceptBoardByIdAndSubjectId(conceptBoardAllowedListRequestDto.getId(),subjectId);
        Set<String> strings = conceptBoard.getEmailWithViewRights();
        strings.removeAll(conceptBoardAllowedListRequestDto.getAllowedList());
        conceptBoard.setEmailWithViewRights(strings);
        conceptBoard.preUpdate(subjectId);
        return conceptBoardRepository.save(conceptBoard);
    }

    /**
     * This service method is used to add Medias to ConceptBoard Folder
     * @param multipartFiles
     * @param id
     * @param subjectId
     * @return
     */
    @Override
    public ConceptBoard addConceptBoardMediaToConceptBoard(MultipartFile[] multipartFiles, String id,Boolean isMedia360 ,Long subjectId) {
        ConceptBoard conceptBoard = findConceptBoardById(id);
        Set<ConceptBoardMedia> medias = conceptBoard.getMedias();
        medias.addAll(conceptBoardMediaService.save(multipartFiles,conceptBoard,isMedia360,subjectId));
        conceptBoard.preSave(subjectId);
        return conceptBoardRepository.save(conceptBoard);
    }


    /**
     * This service method is used to add ExploreMedia to ConceptBoard as reference
     * @param mediaId
     * @param description
     * @param id
     * @param subjectId
     * @return
     */
    @Override
    public ConceptBoard addExploreMediaToConceptBoard(String mediaId, String description ,String id, Long subjectId) {
       //TODO: Update MediaUserStatistcs

        ConceptBoard conceptBoard = findConceptBoardById(id);
        Set<ConceptBoardMedia> medias = conceptBoard.getMedias();
        medias.add(conceptBoardMediaService.saveFromExplore(mediaId,conceptBoard,description,subjectId));
        conceptBoard.preUpdate(subjectId);
        conceptBoard.preSave(subjectId);
        return conceptBoardRepository.save(conceptBoard);
    }

    /**
     * This service method is used to get users conceptBoards
     * @param subjectId
     * @return
     */
    @Override
    public List<ConceptBoard> getUserConceptBoards(Long subjectId) {
        return conceptBoardRepository.findBySubjectId(subjectId);
    }


    /**
     * This service method is used to get user's public conceptBoards
     * @param subjectId
     * @return
     */
    @Override
    public List<ConceptBoard> getUserPublicConceptBoards(Long subjectId) {
        return conceptBoardRepository.findBySubjectIdAndConceptBoardVisibility(subjectId, ConceptBoardVisibility.PUBLIC);
    }

    /**
     * This service method is used to get conceptBoard by Id
     * @param id
     * @return
     */
    @Override
    public ConceptBoard findConceptBoardById(String id) {
        Optional<ConceptBoard> conceptBoardOptional = conceptBoardRepository.findById(id);
        if(conceptBoardOptional.isPresent()){
            return conceptBoardOptional.get();
        }
        throw new ConceptBoardNotFoundException();
    }

    /**
     * This service method is used to get conceptBoard By SubjectId and folderName
     * @param subjectId
     * @param folderName
     * @return
     */
    @Override
    public ConceptBoard findConceptBoardBySubectAndFolderName(Long subjectId, String folderName) {
        ConceptBoard conceptBoard = conceptBoardRepository.findFirstBySubjectIdAndFolderName(subjectId,folderName.toUpperCase());
        if(conceptBoard != null){
            return conceptBoard;
        }
        throw new ConceptBoardNotFoundException();
    }


    /**
     * This service method is used to getConceptBoard by Id and SubjectId. Why SubjectId ?, it acts as extra check
     * @param id
     * @param subjectId
     * @return
     */
    @Override
    public ConceptBoard findConceptBoardByIdAndSubjectId(String id, Long subjectId) {
        ConceptBoard conceptBoard = conceptBoardRepository.findFirstByIdAndSubjectId(id,subjectId);
        if(conceptBoard != null){
            return conceptBoard;
        }
        throw new ConceptBoardNotFoundException();
    }


    /**
     * This service method is used to add Or Change FeaturedMedia for ConceptBoard
     * @param conceptBoardFeaturedImageRequestDto
     * @param subjectId
     * @return
     */
    @Override
    public ConceptBoard addChangeFeaturedMedia(ConceptBoardFeaturedImageRequestDto conceptBoardFeaturedImageRequestDto, Long subjectId) {
        ConceptBoard conceptBoard = findConceptBoardByIdAndSubjectId(conceptBoardFeaturedImageRequestDto.getConceptBoardId(),subjectId);
        //ConceptBoardMedia conceptBoardMedia = conceptBoardMediaService.findByMediaIdAndSubjectId(conceptBoardFeaturedImageRequestDto.getConceptBoardMediaId(),subjectId);
       for(ConceptBoardMedia conceptBoardMedia: conceptBoard.getMedias()) {
        if(conceptBoardMedia.getId().equalsIgnoreCase(conceptBoardFeaturedImageRequestDto.getConceptBoardMediaId())) {
            conceptBoard.setFeaturedMedia(conceptBoardMedia);
            break;
        }
       }
        conceptBoard.preUpdate(subjectId);
        return conceptBoardRepository.save(conceptBoard);
    }

    /**
     * This controller method gets paginated results of ConceptBoard for FilterRequest
     * @param filterRequestDto
     * @param subjectId
     * @return
     */
    @Override
    public Page<ConceptBoard> findConceptBoardWithFilters(FilterRequestDto filterRequestDto, Long subjectId) {
        SearchQuery searchQuery = filterRequestDto.getSearchQuery() == null ? new SearchQuery(): filterRequestDto.getSearchQuery();
        Map<String, List<Object>> where = searchQuery.getWhere() == null ? new LinkedHashMap<>(): searchQuery.getWhere();
        where.put("subjectId",Collections.singletonList(subjectId));
        where.put("isParent", Collections.singletonList(Boolean.TRUE));
        searchQuery.setWhere(where);
        filterRequestDto.setSearchQuery(searchQuery);
        return queryUtilService.getResultList(filterRequestDto,new HashSet<>(Arrays.asList("lastModifiedData")),new ConceptBoard());
    }

    /**
     * This service method gets paginated results of ConceptBoard for FilterRequest
     *
     * @param filterRequestDto
     * @param subjectId
     * @return
     */
    @Override
    public Page<ConceptBoard> findConceptBoardWithFiltersInternal(FilterRequestDto filterRequestDto, Long subjectId) {
        return queryUtilService.getResultList(filterRequestDto,new HashSet<>(Arrays.asList("lastModifiedData")),new ConceptBoard());
    }

    /**
     * This service method gets paginated results of Public ConceptBoard for FilterRequest with ProfileId
     *
     * @param filterRequestDto
     * @param profileId
     * @return
     */
    @Override
    public Page<ConceptBoard> findPublicConceptBoardWithFiltersWithProfileId(FilterRequestDto filterRequestDto, String profileId) {
        Long subjectId = userProfileService.getSubjectIdForProfileId(profileId);
        SearchQuery searchQuery = filterRequestDto.getSearchQuery() == null ? new SearchQuery(): filterRequestDto.getSearchQuery();
        Map<String, List<Object>> where = searchQuery.getWhere() == null ? new LinkedHashMap<>(): searchQuery.getWhere();
        where.put("subjectId",Collections.singletonList(subjectId));
        where.put("isParent", Collections.singletonList(Boolean.TRUE));
        where.put("active",Collections.singletonList(Boolean.TRUE));
        where.put("conceptBoardVisibility",Collections.singletonList("PUBLIC"));
        searchQuery.setWhere(where);
        filterRequestDto.setSearchQuery(searchQuery);
        return findConceptBoardWithFilters(filterRequestDto,subjectId);
    }

    /**
     * This service method is used to get ConceptBoardShared with me
     *
     * @param filterRequestDto
     * @param subjectId
     * @return
     */
    @Override
    public Page<ConceptBoard> conceptBoardSharedWithMe(FilterRequestDto filterRequestDto, Long subjectId) {
        conceptBoardShareService.fixMyShare(subjectId);
        Set<String> conceptBoardids = conceptBoardShareService.getAllConcetpBoardIdsSharedToMe(subjectId);
        if(conceptBoardids == null){
            return null;
        }
        List<Object> conceptBoardIdData = new ArrayList<>();
        conceptBoardIdData.addAll(conceptBoardids);
        SearchQuery searchQuery = filterRequestDto.getSearchQuery() == null ? new SearchQuery(): filterRequestDto.getSearchQuery();
        Map<String, List<Object>> where = searchQuery.getWhere() == null ? new LinkedHashMap<>(): searchQuery.getWhere();
        where.put("active",Collections.singletonList(Boolean.TRUE));
        where.put("id", conceptBoardIdData);
        searchQuery.setWhere(where);
        filterRequestDto.setSearchQuery(searchQuery);
        return findConceptBoardWithFiltersInternal(filterRequestDto,subjectId);
    }

    /**
     * This service method is used to get ConceptBoardEnveloped for certain conceptBoardId
     * @param conceptBoardId
     * @param subjectId
     * @return
     */
    @Override
    public ConceptBoardEnveloped getConceptBoardEnvelopedById(String conceptBoardId, Long subjectId) {
        ConceptBoard conceptBoard = findConceptBoardById(conceptBoardId);
        UserProfileMiniDto userProfileMiniDto = null;
        Map<Long,UserProfileMiniDto> map = userProfileService.getUserProfileForSubjectIds(Collections.singleton(conceptBoard.getCreatedBy()));
        if(map != null && map.size() > 0){
            userProfileMiniDto = map.get(conceptBoard.getCreatedBy());
        }

        List<ConceptBoard> conceptBoards = conceptBoardRepository.findByParentConceptBoard(conceptBoardId);

        return new ConceptBoardEnveloped(conceptBoard,conceptBoards,userProfileMiniDto,conceptBoardWalkThroughService.findByConceptBoardId(conceptBoardId));
    }

    /**
     * This service method is used to set ConceptBoard featured Image
     * @param conceptBoardId
     * @param conceptBoardMediaId
     * @param subjectId
     * @return
     */
    @Override
    public ConceptBoard setFeaturedImage(String conceptBoardId,String conceptBoardMediaId, Long subjectId) {
        ConceptBoard conceptBoard = findConceptBoardByIdAndSubjectId(conceptBoardId,subjectId);

        for (ConceptBoardMedia conceptBoardMedia: conceptBoard.getMedias()) {
            if(conceptBoardMedia.getId().equals(conceptBoardMediaId)){
                conceptBoard.setFeaturedMedia(conceptBoardMedia);
                return conceptBoardRepository.save(conceptBoard);

            }
        }
        return conceptBoard;
    }

    /**
     * This service method is used to get names & ids of conceptBoards
     *
     * @param subjectId
     * @return
     */
    @Override
    public Map<String, String> getAllMyParentConceptBoardNames(Long subjectId) {

        FilterRequestDto filterRequestDto = new FilterRequestDto();
        filterRequestDto.setPage(1);
        filterRequestDto.setSize(10000);
        Page<ConceptBoard> conceptBoardPage = findConceptBoardWithFilters(filterRequestDto,subjectId);
        if(conceptBoardPage.hasContent()){
            Map<String,String> idNameMap = new TreeMap<>();
            for (ConceptBoard conceptBoard: conceptBoardPage.getContent()) {
                idNameMap.put(conceptBoard.getId(),conceptBoard.getFolderName());
            }
            return idNameMap;
        }
        return null;
    }

    /**
     * This service method is used to save External Medias to conceptBoard
     *
     * @param conceptBoardId
     * @param subjectId
     * @param externalMediaDtos
     * @return
     */
    @Override
    public List<ConceptBoardMedia> addExternalMediaToExistingConceptBoard(String conceptBoardId, Long subjectId, List<ExternalMediaDto> externalMediaDtos) {
        ConceptBoard conceptBoard = findConceptBoardById(conceptBoardId);
        Set<String> urls= externalMediaDtos.stream().map(ExternalMediaDto::getExternalUrl).collect(Collectors.toSet());
        Set<ConceptBoardMedia> mediasExisting = conceptBoard.getMedias();
        Map<String,String> existing = new HashMap<>();
        if(mediasExisting != null && mediasExisting.size() > 0 ){
            for (ConceptBoardMedia media : mediasExisting) {
                existing.put(media.getExternalUrl(),media.getId());
            }
        }
        List<ConceptBoardMedia> medias = new ArrayList<>();
        for (ExternalMediaDto externalMediaDto: externalMediaDtos) {
            if(existing.get(externalMediaDto.getExternalUrl()) == null){
                ConceptBoardMedia media = externalMediaDto.BringConceptBoardMediaFromMe();
                media.preSave(subjectId);
                medias.add(saveConceptBoardMediaStrategyContext.saveMedia(media.getMediaType(),conceptBoard,media,null));
            }
        }
        mediasExisting.addAll(medias);
        conceptBoard.setMedias(mediasExisting);
        conceptBoard.preUpdate(subjectId);
        conceptBoardRepository.save(conceptBoard);
        return medias;
    }

    /**
     * This service method is used to get Public ConceptBoardShared
     *
     * @param filterRequestDto
     * @return
     */
    @Override
    public Page<ConceptBoard> paginatePublicConceptBoardSearch(FilterRequestDto filterRequestDto) {
        SearchQuery searchQuery = filterRequestDto.getSearchQuery() == null ? new SearchQuery(): filterRequestDto.getSearchQuery();
        Map<String, List<Object>> where = searchQuery.getWhere() == null ? new LinkedHashMap<>(): searchQuery.getWhere();
        where.put("isParent", Collections.singletonList(Boolean.TRUE));
        where.put("active",Collections.singletonList(Boolean.TRUE));
        where.put("conceptBoardVisibility",Collections.singletonList("PUBLIC"));
        searchQuery.setWhere(where);
        filterRequestDto.setSearchQuery(searchQuery);
        return queryUtilService.getResultList(filterRequestDto,new HashSet<>(Arrays.asList("lastModifiedData")),new ConceptBoard());
    }

    /**
     * This service method finds childBoards by ParentConceptBoardId
     *
     * @param parentConceptBoardId
     * @return
     */
    @Override
    public List<ConceptBoard> findChildBoard(String parentConceptBoardId) {
        return conceptBoardRepository.findByParentConceptBoard(parentConceptBoardId);
    }
}
