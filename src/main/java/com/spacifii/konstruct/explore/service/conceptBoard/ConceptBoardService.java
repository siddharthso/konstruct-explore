package com.spacifii.konstruct.explore.service.conceptBoard;

import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoard;
import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardMedia;
import com.spacifii.konstruct.explore.model.dto.conceptBoard.ConceptBoardAllowedListRequestDto;
import com.spacifii.konstruct.explore.model.dto.conceptBoard.ConceptBoardEnveloped;
import com.spacifii.konstruct.explore.model.dto.conceptBoard.ConceptBoardFeaturedImageRequestDto;
import com.spacifii.konstruct.explore.model.dto.conceptBoard.ConceptBoardRequestDto;
import com.spacifii.konstruct.explore.model.dto.explore.ExternalMediaDto;
import com.spacifii.konstruct.explore.model.dto.explore.FilterRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * This is service class for ConceptBoard
 */
public interface ConceptBoardService {

    /**
     * This service method is used to save new ConceptBoard
     * @param conceptBoardRequestDto
     * @param subjectId
     * @return
     */
    ConceptBoard saveConceptBoard(ConceptBoardRequestDto conceptBoardRequestDto,Long subjectId);

    /**
     * This service method is used to update existing conceptBoard
     * @param conceptBoardRequestDto
     * @param subjectId
     * @return
     */
    ConceptBoard updateConceptBoard(ConceptBoardRequestDto conceptBoardRequestDto, Long subjectId);

    /**
     * This service method is used to add allowed users email Ids to concept Board folder
     * @param conceptBoardAllowedListRequestDto
     * @param subjectId
     * @return
     */
    ConceptBoard addAllowedUsersToConceptBoardFolder(ConceptBoardAllowedListRequestDto conceptBoardAllowedListRequestDto, Long subjectId);

    /**
     * This service method is used to removed users email ids which were previously allowd to access user's ConceptBoard
     * @param conceptBoardAllowedListRequestDto
     * @param subjectId
     * @return
     */
    ConceptBoard removedAllowedUsersFromConceptBoardFolder(ConceptBoardAllowedListRequestDto conceptBoardAllowedListRequestDto, Long subjectId);

    /**
     * This service method is used to add Medias to ConceptBoard Folder
     * @param multipartFiles
     * @param id
     * @param subjectId
     * @return
     */
    ConceptBoard addConceptBoardMediaToConceptBoard(MultipartFile[] multipartFiles,String id,Boolean isMedia360  ,Long subjectId);


    /**
     * This service method is used to add ExploreMedia to ConceptBoard as reference
     * @param mediaId
     * @param description
     * @param id
     * @param subjectId
     * @return
     */
    ConceptBoard addExploreMediaToConceptBoard(String mediaId,String description ,String id, Long subjectId);


    /**
     * This service method is used to get users conceptBoards
     * @param subjectId
     * @return
     */
    List<ConceptBoard> getUserConceptBoards(Long subjectId);

    /**
     * This service method is used to get user's public conceptBoards
     * @param subjectId
     * @return
     */
    List<ConceptBoard> getUserPublicConceptBoards(Long subjectId);


    /**
     * This service method is used to get conceptBoard by Id
     * @param id
     * @return
     */
    ConceptBoard findConceptBoardById(String id);

    /**
     * This service method is used to get conceptBoard By SubjectId and folderName
     * @param subjectId
     * @param folderName
     * @return
     */
    ConceptBoard findConceptBoardBySubectAndFolderName(Long subjectId, String folderName);


    /**
     * This service method is used to getConceptBoard by Id and SubjectId. Why SubjectId ?, it acts as extra check
     * @param id
     * @param subjectId
     * @return
     */
    ConceptBoard findConceptBoardByIdAndSubjectId(String id, Long subjectId);


    /**
     * This service method is used to add Or Change FeaturedMedia for ConceptBoard
     * @param conceptBoardFeaturedImageRequestDto
     * @param subjectId
     * @return
     */
    ConceptBoard addChangeFeaturedMedia(ConceptBoardFeaturedImageRequestDto conceptBoardFeaturedImageRequestDto, Long subjectId);


    /**
     * This service method gets paginated results of ConceptBoard for FilterRequest
     * @param filterRequestDto
     * @param subjectId
     * @return
     */
    Page<ConceptBoard> findConceptBoardWithFilters(FilterRequestDto filterRequestDto, Long subjectId);

    /**
     * This service method gets paginated results of ConceptBoard for FilterRequest
     * @param filterRequestDto
     * @param subjectId
     * @return
     */
    Page<ConceptBoard> findConceptBoardWithFiltersInternal(FilterRequestDto filterRequestDto, Long subjectId);


    /**
     * This service method gets paginated results of Public ConceptBoard for FilterRequest with ProfileId
     * @param filterRequestDto
     * @param profileId
     * @return
     */
    Page<ConceptBoard> findPublicConceptBoardWithFiltersWithProfileId(FilterRequestDto filterRequestDto, String profileId);


    /**
     * This service method is used to get ConceptBoardShared with me
     * @param filterRequestDto
     * @param subjectId
     * @return
     */
    Page<ConceptBoard> conceptBoardSharedWithMe(FilterRequestDto filterRequestDto, Long subjectId);

    /**
     * This service method is used to get ConceptBoardEnveloped for certain conceptBoardId
     * @param conceptBoardId
     * @param subjectId
     * @return
     */
    ConceptBoardEnveloped getConceptBoardEnvelopedById(String conceptBoardId, Long subjectId);


    /**
     * This service method is used to set ConceptBoard featured Image
     * @param conceptBoardId
     * @param conceptBoardMediaId
     * @param subjectId
     * @return
     */
    ConceptBoard setFeaturedImage(String conceptBoardId, String conceptBoardMediaId , Long subjectId);

    /**
     * This service method is used to get names & ids of conceptBoards
     * @param subjectId
     * @return
     */
    Map<String,String> getAllMyParentConceptBoardNames(Long subjectId);

    /**
     * This service method is used to save External Medias to conceptBoard
     * @param conceptBoardId
     * @param subjectId
     * @param externalMediaDtos
     * @return
     */
    List<ConceptBoardMedia> addExternalMediaToExistingConceptBoard(String conceptBoardId, Long subjectId, List<ExternalMediaDto> externalMediaDtos);

    /**
     * This service method is used to get Public ConceptBoardShared
     * @param filterRequestDto
     * @return
     */
    Page<ConceptBoard> paginatePublicConceptBoardSearch(FilterRequestDto filterRequestDto);

    /**
     * This service method finds childBoards by ParentConceptBoardId
     * @param parentConceptBoardId
     * @return
     */
    List<ConceptBoard> findChildBoard(String parentConceptBoardId);
}
