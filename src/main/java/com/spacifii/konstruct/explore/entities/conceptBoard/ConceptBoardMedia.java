package com.spacifii.konstruct.explore.entities.conceptBoard;

import com.spacifii.konstruct.explore.annotation.Attribute;
import com.spacifii.konstruct.explore.annotation.NotToUseDuringMerge;
import com.spacifii.konstruct.explore.entities.explore.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.*;

@Document(collection = "CONCEPT_BOARD_MEDIA")
public class ConceptBoardMedia {

    @Id
    @Field(value = "ID")
    @Attribute(keyword = "CONCEPTBOARDMEDIA_ID", resolvedKeyword = "id")
    private String id;

    @Field(value = "NAME")
    @Attribute(keyword = "CONCEPTBOARDMEDIA_NAME", resolvedKeyword = "name")
    private String name;

    @Field(value = "DESCRIPTION")
    @Attribute(keyword = "CONCEPTBOARDMEDIA_DESCRIPTION", resolvedKeyword = "description")
    private String description;

    @Field(value = "URL")
    @Attribute(keyword = "CONCEPTBOARDMEDIA_URL", resolvedKeyword = "ur;")
    private String url;

    @Field(value = "ALT_URL")
    @Attribute(keyword = "CONCEPTBOARDMEDIA_ALTURL", resolvedKeyword = "altUrl;")
    private String altUrl;

    @Field(value = "CONCEPT_BOARD_MEDIA_TYPE")
    @Attribute(keyword = "CONCEPTBOARDMEDIA_MEDIATYPE", resolvedKeyword = "conceptBoardMediaType")
    private ConceptBoardMediaType conceptBoardMediaType;

    @Attribute(keyword = "CONCEPTBOARDMEDIA_MEDIA_TYPE", resolvedKeyword = "mediaType")
    @Field(value = "MEDIA_TYPE")
    @NotToUseDuringMerge
    private MediaType mediaType;

    @Attribute(keyword = "CONCEPTBOARDMEDIA_MEDIATYPEEXTENSION", resolvedKeyword = "mediaTypeExtension")
    @Field(value = "MEDIA_TYPE_EXTENSION")
    @NotToUseDuringMerge
    private String mediaTypeExtension;

    @Attribute(keyword = "CONCEPTBOARDMEDIA_EXTERNALURL", resolvedKeyword = "externalUrl")
    @Field(value = "EXTERNAL_URL")
    @NotToUseDuringMerge
    private String externalUrl;

    @Attribute(keyword = "CONCEPTBOARDMEDIA_EXTERNALSOURCE", resolvedKeyword = "externalSource")
    @Field(value = "EXTERNALSOURCE")
    @NotToUseDuringMerge
    private ExternalSource externalSource;

    @Field(value = "KEYWORDS")
    @Attribute(keyword = "CONCEPTBOARDMEDIA_KEYWORDS", resolvedKeyword = "keywords")
    private Set<String> keywords = new TreeSet<>();

    @Field(value = "OBJECT_LOCALIZATION_CONTAINERS")
    @Attribute(keyword = "CONCEPTBOARDMEDIA_OBJECTLOCALIZATIONCONTAINER", resolvedKeyword = "keywords")
    private List<ObjectLocalizationContainer> objectLocalizationContainers = new ArrayList<>();

    @Attribute(keyword = "CONCEPTBOARDMEDIA_COLORS", resolvedKeyword = "colors")
    @Field(value = "COLORS")
    private Set<String> colors = new TreeSet<>();

    @Attribute(keyword = "CONCEPTBOARDMEDIA_COLORSANDHEX", resolvedKeyword = "colorsAndHex")
    @Field(value = "COLORS_AND_HEX")
    private Set<String> colorsAndHex=  new TreeSet<>();

    @Attribute(keyword = "CONCEPTBOARDMEDIA_KEYWORDS", resolvedKeyword = "extendedKeywords")
    @Field(value = "EXTENDED_KEYWORDS")
    private Set<String> extendedKeywords = new TreeSet<>();

    @Attribute(keyword = "CONCEPTBOARDMEDIA_TAGS", resolvedKeyword = "tags")
    @Field(value = "TAGS")
    private Set<String> tags = new HashSet<>();

    @Attribute(keyword = "CONCEPTBOARDMEDIA_INFOSPOTS", resolvedKeyword = "infoSpots")
    @Field(value = "INFOSPOTS")
    private Set<InfoSpot> infoSpots = new HashSet<>();

    @Indexed
    @Attribute(keyword = "CONCEPTBOARDMEDIA_CHECKSUM", resolvedKeyword = "checksum")
    @Field(value = "CHECKSUM")
    @NotToUseDuringMerge
    private String checksum;

    @Attribute(keyword = "CONCEPTBOARDMEDIA_ACTIVE", resolvedKeyword = "active")
    @Field(value = "ACTIVE")
    @NotToUseDuringMerge
    private Boolean active = Boolean.TRUE;

    @Attribute(keyword = "CONCEPTBOARDMEDIA_CREATIONDATE", resolvedKeyword = "creationDate")
    @Field(value  = "CREATION_DATE")
    @NotToUseDuringMerge
    private Long creationDate;

    @Attribute(keyword = "CONCEPTBOARDMEDIA_LASTMODIFIEDDATE", resolvedKeyword = "lastModifiedDate")
    @Field(value  = "LAST_MODIFIED_DATE")
    @NotToUseDuringMerge
    private Long lastModifiedDate;

    @Attribute(keyword = "CONCEPTBOARDMEDIA_CREATEDBY", resolvedKeyword = "createdBy")
    @Field(value  = "CREATED_BY")
    @NotToUseDuringMerge
    private Long createdBy;

    @Attribute(keyword = "CONCEPTBOARDMEDIA_LASTMODIFIEDBY", resolvedKeyword = "lastModifiedBy")
    @Field(value  = "LAST_MODIFIED_BY")
    @NotToUseDuringMerge
    private Long lastModifiedBy;

    @Version
    @Attribute(keyword = "CONCEPTBOARDMEDIA_VERSION", resolvedKeyword = "version")
    @Field(value  = "VERSION")
    @NotToUseDuringMerge
    private Integer version;


    @Attribute(keyword = "CONCEPTBOARDMEDIA_CONCEPTBOARDMEDIAUSERACTIONSTATISTICS", resolvedKeyword = "conceptBoardMediaUserActionStatistics")
    @Field(value  = "CONCEPTBOARD_MEDIA_USER_ACTION_STATISTICS")
    @NotToUseDuringMerge
    private ConceptBoardMediaUserActionStatistics conceptBoardMediaUserActionStatistics = new ConceptBoardMediaUserActionStatistics();

    public void preSave(Long subjectId){
        this.setCreatedBy(subjectId);
        this.setCreationDate(System.currentTimeMillis());
        this.setLastModifiedBy(subjectId);
        this.setLastModifiedDate(this.getCreationDate());

        // this.setVersion(0);
    }

    public void preUpdate(Long subjectId){
        this.setLastModifiedBy(subjectId);
        this.setLastModifiedDate(System.currentTimeMillis());
        // this.setVersion(this.getVersion() + 1);
    }

    public void mergeInMe(Media media){
        this.setChecksum(media.getChecksum());
        this.setConceptBoardMediaType(ConceptBoardMediaType.REFERENCE_FROM_EXPLORE);
        this.setUrl(media.getUrl());
        this.setAltUrl(media.getAltUrl()    );
        this.setDescription(media.getDescription());
        this.setKeywords(media.getKeywords());
        this.setAltUrl(media.getAltUrl());
       this.setColors(media.getColors());
       this.setColorsAndHex(media.getColorsAndHex());
       this.setExtendedKeywords(media.getExtendedKeywords());
       this.setTags(media.getTags());
       this.setExternalSource(media.getExternalSource());
       this.setExternalUrl(media.getUrl());
       this.setMediaType(media.getMediaType());
       this.setMediaTypeExtension(media.getMediaTypeExtension());
       this.setInfoSpots(media.getInfoSpots());
       this.setObjectLocalizationContainers(media.getObjectLocalizationContainers());
    }


    public List<ObjectLocalizationContainer> getObjectLocalizationContainers() {
        return objectLocalizationContainers;
    }

    public void setObjectLocalizationContainers(List<ObjectLocalizationContainer> objectLocalizationContainers) {
        this.objectLocalizationContainers = objectLocalizationContainers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(Set<String> keywords) {
        this.keywords = keywords;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ConceptBoardMediaType getConceptBoardMediaType() {
        return conceptBoardMediaType;
    }

    public void setConceptBoardMediaType(ConceptBoardMediaType conceptBoardMediaType) {
        this.conceptBoardMediaType = conceptBoardMediaType;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Long creationDate) {
        this.creationDate = creationDate;
    }

    public Long getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Long lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(Long lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Set<InfoSpot> getInfoSpots() {
        return infoSpots;
    }

    public void setInfoSpots(Set<InfoSpot> infoSpots) {
        this.infoSpots = infoSpots;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public String getMediaTypeExtension() {
        return mediaTypeExtension;
    }

    public void setMediaTypeExtension(String mediaTypeExtension) {
        this.mediaTypeExtension = mediaTypeExtension;
    }

    public String getExternalUrl() {
        return externalUrl;
    }

    public void setExternalUrl(String externalUrl) {
        this.externalUrl = externalUrl;
    }

    public ExternalSource getExternalSource() {
        return externalSource;
    }

    public void setExternalSource(ExternalSource externalSource) {
        this.externalSource = externalSource;
    }

    public Set<String> getColors() {
        return colors;
    }

    public void setColors(Set<String> colors) {
        this.colors = colors;
    }

    public Set<String> getColorsAndHex() {
        return colorsAndHex;
    }

    public void setColorsAndHex(Set<String> colorsAndHex) {
        this.colorsAndHex = colorsAndHex;
    }

    public Set<String> getExtendedKeywords() {
        return extendedKeywords;
    }

    public void setExtendedKeywords(Set<String> extendedKeywords) {
        this.extendedKeywords = extendedKeywords;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public String getAltUrl() {
        return altUrl;
    }

    public void setAltUrl(String altUrl) {
        this.altUrl = altUrl;
    }

    public ConceptBoardMediaUserActionStatistics getConceptBoardMediaUserActionStatistics() {
        return conceptBoardMediaUserActionStatistics;
    }

    public void setConceptBoardMediaUserActionStatistics(ConceptBoardMediaUserActionStatistics conceptBoardMediaUserActionStatistics) {
        this.conceptBoardMediaUserActionStatistics = conceptBoardMediaUserActionStatistics;
    }
}
