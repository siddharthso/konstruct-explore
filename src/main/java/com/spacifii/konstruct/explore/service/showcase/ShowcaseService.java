package com.spacifii.konstruct.explore.service.showcase;

import com.spacifii.konstruct.explore.entities.showcases.Coordinate;
import com.spacifii.konstruct.explore.entities.showcases.Showcase;
import com.spacifii.konstruct.explore.model.dto.explore.FilterRequestDto;
import org.springframework.data.domain.Page;

import java.util.Set;

/**
 * This service class manges the Showcases
 */
public interface ShowcaseService {

    /**
     * This service method saves or updates Showcases
     * @param showcase
     * @param subjectId
     * @return
     */
    Showcase saveOrUpdateShowcase(Showcase showcase, Long subjectId);

    /**
     * This service method gets Showcase by Id
     * @param showcaseId
     * @return
     */
    Showcase findById(String showcaseId);

    /**
     * This service method gets Showcases paginated via FilterRequest Dto
     * @param filterRequestDto
     * @return
     */
    Page<Showcase> getShowcasePaginated(FilterRequestDto filterRequestDto);

    /**
     * This service method updates container Media for the showcase
     * @param containerMediaid
     * @param showcaseId
     * @param subjectId
     * @return
     */
    Showcase addUpdateContainerMedia(String containerMediaid, String showcaseId, Long subjectId);

    /**
     * This service method adds Group Coordinate of the showcase
     * @param groupName
     * @param coordinate
     * @param showcaseId
     * @param subjectId
     * @return
     */
    Showcase addGroupCoordinate(String groupName, Coordinate coordinate, String showcaseId, Long subjectId);

    /**
     * This service method is used to remove Group Coordinates
     * @param groupName
     * @param showcaseId
     * @param subjectId
     * @return
     */
    Showcase removeGroupGroupCoordinate(String groupName, String showcaseId, Long subjectId);

    /**
     * This service method is used to add Group Offering Products of the showcase
     * @param groupName
     * @param offeringIds
     * @param showcaseId
     * @param subjectId
     * @return
     */
    Showcase addGroupOfferingProducts(String groupName, Set<String> offeringIds, String showcaseId, Long subjectId);

    /**
     * This service method is used to remove group Offering Products of the Showcasse
     * @param groupName
     * @param offeringIds
     * @param showcaseId
     * @param subjectId
     * @return
     */
    Showcase removeGroupOfferingProducts(String groupName, Set<String> offeringIds, String showcaseId, Long subjectId);

    /**
     * This service method is used to set Group Defaults of the show case
     * @param groupName
     * @param offeringId
     * @param showcaseId
     * @param subjectId
     * @return
     */
    Showcase setGroupDefaultOfferingProduct(String groupName, String offeringId, String showcaseId, Long subjectId);


    /**
     * This service method is used to remove Offering  Group Defaults Of the showcase
     * @param groupName
     * @param showcaseId
     * @param subjectId
     * @return
     */
    Showcase removeGroupDefaultOfferingProduct(String groupName, String showcaseId, Long subjectId);

    /**
     * This service method is used to add materials to showcase
     * @param materialIds
     * @param showcaseId
     * @param subjectId
     * @return
     */
    Showcase addMaterials(Set<String> materialIds, String showcaseId, Long subjectId);

    /**
     * This service method is used to remove materials of the showcases
     * @param materialIds
     * @param showcaseId
     * @param subjectId
     * @return
     */
    Showcase removeMaterials(Set<String> materialIds, String showcaseId, Long subjectId);

}
