package com.spacifii.konstruct.explore.service.conceptBoard;

import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoard;
import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardMedia;
import com.spacifii.konstruct.explore.entities.explore.InfoSpot;
import com.spacifii.konstruct.explore.model.dto.conceptBoard.ConceptBoardMediaUpdateDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

/**
 * This is service class for ConceptBoardMedia
 */
public interface ConceptBoardMediaService {

    /**
     * This service method is used to add ConceptBoardMedias via MultipartFiles
     * @param multipartFiles
     * @param conceptBoard
     * @param subjectId
     * @return
     */
    List<ConceptBoardMedia> save(MultipartFile[] multipartFiles, ConceptBoard conceptBoard, Boolean isMediaImage360,Long subjectId);

    /**
     * This service method is used to add conceptBoardMedia via Explore
     * @param mediaId
     * @param conceptBoard
     * @param description
     * @param subjectId
     * @return
     */
    ConceptBoardMedia saveFromExplore(String mediaId, ConceptBoard conceptBoard, String description, Long subjectId);


    /**
     * This service method is used to update conceptBoardMedia information
     * @param conceptBoardMediaUpdateDto
     * @param subjectId
     * @return
     */
    ConceptBoardMedia updateConceptBoardMedia(ConceptBoardMediaUpdateDto conceptBoardMediaUpdateDto, Long subjectId);

    /**
     * This service method is used to get ConceptBoardMedia by id
     * @param id
     * @return
     */
    ConceptBoardMedia findById(String id);

    /**
     * This service method is used to get ConceptBoardMedia by id and subjectId
     * @param id
     * @param subjectId
     * @return
     */
    ConceptBoardMedia findByMediaIdAndSubjectId(String id, Long subjectId);

    /**
     * This service method is used to add Kewords to ConceptBoardMedia By Id
     * @param id
     * @param keywords
     * @param subjectId
     * @return
     */
    ConceptBoardMedia addKeywords(String id, Set<String> keywords, Long subjectId);

    /**
     * This service method is used to remove Keywords to ConceptBoardMedia By Id
     * @param id
     * @param keywords
     * @param subjectId
     * @return
     */
    ConceptBoardMedia removeKeywords(String id, Set<String> keywords, Long subjectId);


    /**
     * This method adds InfoSpots to Media
     * @param conceptBoardId
     * @param subjectId
     * @return
     */
    ConceptBoardMedia addInfoSpotToMedia(String conceptBoardId, String conceptBoardMediaId , Long subjectId, Set<InfoSpot> infoSpot);


    /**
     * This method removes InfoSpots to Media
     * @param conceptBoardId
     * @param subjectId
     * @return
     */
    ConceptBoardMedia removeInfoSpotToMedia(String conceptBoardId, String conceptBoardMediaId , Long subjectId, String infoSpotId);

}
