package com.spacifii.konstruct.explore.service.showcase.impl;

import com.spacifii.konstruct.explore.annotation.NotToUseDuringMerge;
import com.spacifii.konstruct.explore.entities.showcases.*;
import com.spacifii.konstruct.explore.exception.showcase.ShowcaseNotFoundException;
import com.spacifii.konstruct.explore.model.dto.explore.FilterRequestDto;
import com.spacifii.konstruct.explore.repository.showcase.ShowcaseRepository;
import com.spacifii.konstruct.explore.service.explore.BeanUtils;
import com.spacifii.konstruct.explore.service.explore.QueryUtilService;
import com.spacifii.konstruct.explore.service.showcase.ContainerMediaService;
import com.spacifii.konstruct.explore.service.showcase.MaterialService;
import com.spacifii.konstruct.explore.service.showcase.OfferingProductService;
import com.spacifii.konstruct.explore.service.showcase.ShowcaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * This service class manges the Showcases
 */
@Service
public class ShowcaseServiceImpl implements ShowcaseService {

    @Autowired
    ShowcaseRepository showcaseRepository;

    @Autowired
    QueryUtilService queryUtilService;

    @Autowired
    ContainerMediaService containerMediaService;

    @Autowired
    MaterialService materialService;


    @Autowired
    OfferingProductService offeringProductService;


    /**
     * This service method saves or updates Showcases
     *
     * @param showcase
     * @param subjectId
     * @return
     */
    @Override
    public Showcase saveOrUpdateShowcase(Showcase showcase, Long subjectId) {
        if(showcase.getId() != null){
            Optional<Showcase> showcaseOptional = showcaseRepository.findById(showcase.getId());
            if(showcaseOptional.isPresent()){
                Showcase showcaseExisting = showcaseOptional.get();
                String[] arr  = BeanUtils.findFields(Showcase.class, NotToUseDuringMerge.class);
                BeanUtils.copyProperties(showcase,showcaseExisting,arr);
                return showcaseRepository.save(showcase);

            }
        }
        return showcaseRepository.save(showcase);
    }

    /**
     * This service method gets Showcase by Id
     *
     * @param showcaseId
     * @return
     */
    @Override
    public Showcase findById(String showcaseId) {
        Optional<Showcase> showcaseOptional = showcaseRepository.findById(showcaseId);
        if(showcaseOptional.isPresent()){
            return showcaseOptional.get();
        }
        throw new ShowcaseNotFoundException();
    }

    /**
     * This service method gets Showcases paginated via FilterRequest Dto
     *
     * @param filterRequestDto
     * @return
     */
    @Override
    public Page<Showcase> getShowcasePaginated(FilterRequestDto filterRequestDto) {
        return queryUtilService.getResultList(filterRequestDto,new HashSet<>(Arrays.asList("name")),new Showcase());
    }

    /**
     * This service method updates container Media for the showcase
     *
     * @param containerMediaid
     * @param showcaseId
     * @param subjectId
     * @return
     */
    @Override
    public Showcase addUpdateContainerMedia(String containerMediaid, String showcaseId, Long subjectId) {
        Showcase showcase  = findById(showcaseId);
        ContainerMedia containerMedia = containerMediaService.findById(containerMediaid);
        showcase.setContainerMedia(containerMedia);
        return showcaseRepository.save(showcase);
    }

    /**
     * This service method adds Group Coordinate of the showcase
     *
     * @param groupName
     * @param coordinate
     * @param showcaseId
     * @param subjectId
     * @return
     */
    @Override
    public Showcase addGroupCoordinate(String groupName, Coordinate coordinate, String showcaseId, Long subjectId) {

        Showcase showcase  = findById(showcaseId);
        Map<String,Coordinate> groupCoordinateMap = showcase.getGroupCoordinateMap();
        groupCoordinateMap.put(groupName,coordinate);
        showcase.setGroupCoordinateMap(groupCoordinateMap);
        return showcaseRepository.save(showcase);
    }

    /**
     * This service method is used to remove Group Coordinates
     *
     * @param groupName
     * @param showcaseId
     * @param subjectId
     * @return
     */
    @Override
    public Showcase removeGroupGroupCoordinate(String groupName, String showcaseId, Long subjectId) {
        Showcase showcase  = findById(showcaseId);
        Map<String,Coordinate> groupCoordinateMap = showcase.getGroupCoordinateMap();
        if(groupCoordinateMap.get(groupName) != null){
            groupCoordinateMap.remove(groupName);
        }
        showcase.setGroupCoordinateMap(groupCoordinateMap);
        return showcaseRepository.save(showcase);
    }

    /**
     * This service method is used to add Group Offering Products of the showcase
     *
     * @param groupName
     * @param offeringIds
     * @param showcaseId
     * @param subjectId
     * @return
     */
    @Override
    public Showcase addGroupOfferingProducts(String groupName, Set<String> offeringIds, String showcaseId, Long subjectId) {
        Showcase showcase  = findById(showcaseId);
        Set<OfferingProduct> offeringProducts = offeringProductService.findByIds(offeringIds);
         Map<String,Set<OfferingProduct>> groupProducts = showcase.getGroupProducts();
         Set<OfferingProduct> offeringProductsExisting = groupProducts.get(groupName);
         if(offeringProductsExisting == null){
             offeringProductsExisting = new HashSet<>();
         }
         offeringProductsExisting.removeAll(offeringProducts);
         offeringProductsExisting.addAll(offeringProducts);
         groupProducts.put(groupName,offeringProductsExisting);
         showcase.setGroupProducts(groupProducts);
        return showcaseRepository.save(showcase);
    }

    /**
     * This service method is used to remove group Offering Products of the Showcasse
     *
     * @param groupName
     * @param offeringIds
     * @param showcaseId
     * @param subjectId
     * @return
     */
    @Override
    public Showcase removeGroupOfferingProducts(String groupName, Set<String> offeringIds, String showcaseId, Long subjectId) {
        Showcase showcase  = findById(showcaseId);
        Set<OfferingProduct> offeringProducts = offeringProductService.findByIds(offeringIds);
        Map<String,Set<OfferingProduct>> groupProducts = showcase.getGroupProducts();
        Set<OfferingProduct> offeringProductsExisting = groupProducts.get(groupName);
        if(offeringProductsExisting == null){
            return showcase;
        }
        offeringProductsExisting.removeAll(offeringProducts);
        if(offeringProductsExisting.size() == 0){
         groupProducts.remove(groupName);
        } else {
            groupProducts.put(groupName, offeringProductsExisting);
        }
        showcase.setGroupProducts(groupProducts);
        return showcaseRepository.save(showcase);
    }

    /**
     * This service method is used to set Group Defaults of the show case
     *
     * @param groupName
     * @param offeringId
     * @param showcaseId
     * @param subjectId
     * @return
     */
    @Override
    public Showcase setGroupDefaultOfferingProduct(String groupName, String offeringId, String showcaseId, Long subjectId) {
        Showcase showcase  = findById(showcaseId);
        OfferingProduct offeringProduct = offeringProductService.findById(offeringId);
        Map<String,OfferingProduct> groupDefaults = showcase.getGroupDefaults();
        groupDefaults.put(groupName,offeringProduct);
        showcase.setGroupDefaults(groupDefaults);
        return showcaseRepository.save(showcase);
    }

    /**
     * This service method is used to remove Offering  Group Defaults Of the showcase
     *
     * @param groupName
     * @param showcaseId
     * @param subjectId
     * @return
     */
    @Override
    public Showcase removeGroupDefaultOfferingProduct(String groupName, String showcaseId, Long subjectId) {
        Showcase showcase  = findById(showcaseId);
        Map<String,OfferingProduct> groupDefaults = showcase.getGroupDefaults();
        if(groupDefaults.get(groupName) == null){
            return showcase;
        }
        groupDefaults.remove(groupName);
        showcase.setGroupDefaults(groupDefaults);
        return showcaseRepository.save(showcase);
    }

    /**
     * This service method is used to add materials to showcase
     *
     * @param materialIds
     * @param showcaseId
     * @param subjectId
     * @return
     */
    @Override
    public Showcase addMaterials(Set<String> materialIds, String showcaseId, Long subjectId) {

        Showcase showcase  = findById(showcaseId);
        Set<Material> materials = materialService.getMaterialsByIds(materialIds);
        Set<Material> materialExisting = showcase.getMaterials();
        materialExisting.removeAll(materials);
        materialExisting.addAll(materials);
        showcase.setMaterials(materialExisting);

        return showcaseRepository.save(showcase);
    }

    /**
     * This service method is used to remove materials of the showcases
     *
     * @param materialIds
     * @param showcaseId
     * @param subjectId
     * @return
     */
    @Override
    public Showcase removeMaterials(Set<String> materialIds, String showcaseId, Long subjectId) {
        Showcase showcase  = findById(showcaseId);
        Set<Material> materials = materialService.getMaterialsByIds(materialIds);
        Set<Material> materialExisting = showcase.getMaterials();
        materialExisting.removeAll(materials);
        showcase.setMaterials(materialExisting);
        return showcaseRepository.save(showcase);
    }
}
