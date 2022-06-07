package com.spacifii.konstruct.explore.service.showcase;

import com.spacifii.konstruct.explore.entities.showcases.Material;
import com.spacifii.konstruct.explore.model.dto.explore.FilterRequestDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

/**
 * This service class manges Material
 */
public interface MaterialService {

    /**
     * This service method is used to save or Update Material
     * @param material
     * @return
     */
    Material saveOrUpdate(Material material);

    /**
     * This service method is used to find all Materials
     * @return
     */
    List<Material> findAll();

    /**
     * This service method is used to find Material by Id
     * @param id
     * @return
     */
    Material findById(String id);

    /**
     * This service method is used to find Paginated Materials via FilterRequestDto
     * @param filterRequestDto
     * @return
     */
    Page<Material> getMaterialFilterred(FilterRequestDto filterRequestDto);

    /**
     * This service method is used to get Materials by Ids
     * @param ids
     * @return
     */
    Set<Material> getMaterialsByIds(Set<String> ids);
}
