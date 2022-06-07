package com.spacifii.konstruct.explore.entities.explore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.spacifii.konstruct.explore.annotation.Attribute;
import com.spacifii.konstruct.explore.annotation.NotToUseDuringMerge;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.*;
import java.util.stream.Collectors;

@JsonPropertyOrder(alphabetic = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "EXPLORE_MEDIA")
public class Media {

    @Id
    @Attribute(keyword = "MEDIA_ID", resolvedKeyword = "id", enableFindBy = true)
    @Field(value = "ID")
    @NotToUseDuringMerge
    private String id;

    @Indexed
    @Attribute(keyword = "MEDIA_PROJECTID", resolvedKeyword = "projectId")
    @Field(value = "PROJECT_ID")
    @NotToUseDuringMerge
    private String projectId;

    @Attribute(keyword = "MEDIA_MEDIASOURCETYPE", resolvedKeyword = "mediaSourceType")
    @Field(value = "MEDIA_SOURCE_TYPE")
    @NotToUseDuringMerge
    private MediaSourceType mediaSourceType;

    @Indexed
    @Attribute(keyword = "MEDIA_FILENAME", resolvedKeyword = "filename")
    @Field(value = "FILENAME")
    @NotToUseDuringMerge
    private String filename;

    @Attribute(keyword = "MEDIA_SIZEINBYTES", resolvedKeyword = "sizeInBytes")
    @Field(value = "SIZE_IN_BYTES")
    @NotToUseDuringMerge
    private Long sizeInBytes;

    @Indexed
    @Attribute(keyword = "MEDIA_SUBJECTID", resolvedKeyword = "subjectId")
    @Field(value = "SUBJECT_ID")
    @NotToUseDuringMerge
    private Long subjectId;

    @Attribute(keyword = "MEDIA_COVERIMAGE", resolvedKeyword = "coverImage")
    @Field(value = "COVER_IMAGE")
    private Boolean coverImage;

    @Attribute(keyword = "MEDIA_KEYWORDS", resolvedKeyword = "keywords")
    @Field(value = "KEYWORDS")
    private Set<String> keywords = new TreeSet<>();

    @Attribute(keyword = "MEDIA_COLORS", resolvedKeyword = "colors")
    @Field(value = "COLORS")
    private Set<String> colors = new TreeSet<>();

    @Attribute(keyword = "MEDIA_COLORSANDHEX", resolvedKeyword = "colorsAndHex")
    @Field(value = "COLORS_AND_HEX")
    private Set<String> colorsAndHex=  new TreeSet<>();

    @Attribute(keyword = "MEDIA_KEYWORDS", resolvedKeyword = "extendedKeywords")
    @Field(value = "EXTENDED_KEYWORDS")
    private Set<String> extendedKeywords = new TreeSet<>();

    @Attribute(keyword = "MEDIA_OBJECTLOCALIZATIONCONTAINER", resolvedKeyword = "objectLocalizationContainers")
    @Field(value = "OBJECT_LOCALIZATION_CINTAINERS")
    private List<ObjectLocalizationContainer> objectLocalizationContainers = new ArrayList<>();

    @Attribute(keyword = "MEDIA_TAGS", resolvedKeyword = "tags")
    @Field(value = "TAGS")
    private Set<String> tags = new HashSet<>();

    @Attribute(keyword = "MEDIA_INFOSPOTS", resolvedKeyword = "infoSpots")
    @Field(value = "INFOSPOTS")
    private Set<InfoSpot> infoSpots = new HashSet<>();

    @Attribute(keyword = "MEDIA_NAME", resolvedKeyword = "name")
    @Field(value = "NAME")
    private String name;

    @Attribute(keyword = "MEDIA_DESCRIPTION", resolvedKeyword = "description")
    @Field(value = "DESCRIPTION")
    private String description;

    @Attribute(keyword = "MEDIA_MEDIA_TYPE", resolvedKeyword = "mediaType")
    @Field(value = "MEDIA_TYPE")
    @NotToUseDuringMerge
    private MediaType mediaType;

    @Attribute(keyword = "MEDIA_MEDIATYPEEXTENSION", resolvedKeyword = "mediaTypeExtension")
    @Field(value = "MEDIA_TYPE_EXTENSION")
    @NotToUseDuringMerge
    private String mediaTypeExtension;

    @Attribute(keyword = "MEDIA_EXTERNALURL", resolvedKeyword = "externalUrl")
    @Field(value = "EXTERNAL_URL")
    @NotToUseDuringMerge
    private String externalUrl;

    @Attribute(keyword = "MEDIA_EXTERNALSOURCE", resolvedKeyword = "externalSource")
    @Field(value = "EXTERNALSOURCE")
    @NotToUseDuringMerge
    private ExternalSource externalSource;

    @Attribute(keyword = "MEDIA_URL", resolvedKeyword = "url")
    @Field(value = "URL")
    @NotToUseDuringMerge
    private String url;

    @Attribute(keyword = "MEDIA_ALTURL", resolvedKeyword = "altUrl")
    @Field(value = "ALT_URL")
    @NotToUseDuringMerge
    private String altUrl;

    @Attribute(keyword = "MEDIA_STATUS", resolvedKeyword = "status")
    @Field(value = "STATUS")
    @NotToUseDuringMerge
    private STATUS status = STATUS.APPROVAL_PENDING;

    @Attribute(keyword = "MEDIA_VISIBILITY", resolvedKeyword = "visibility")
    @Field(value = "VISIBILITY")
    @NotToUseDuringMerge
    private VISIBILITY visibility;

    @Indexed
    @Attribute(keyword = "MEDIA_CHECKSUM", resolvedKeyword = "checksum")
    @Field(value = "CHECKSUM")
    @NotToUseDuringMerge
    private String checksum;

    @Attribute(keyword = "MEDIA_ACTIVE", resolvedKeyword = "active")
    @Field(value = "ACTIVE")
    @NotToUseDuringMerge
    private Boolean active = Boolean.TRUE;

    @Attribute(keyword = "MEDIA_SPACEMAP", resolvedKeyword = "spaceMap")
    @Field(value = "SPACE_MAP")
    @NotToUseDuringMerge
    private SpaceMap spaceMap;

    @Attribute(keyword = "MEDIA_CREATIONDATE", resolvedKeyword = "creationDate")
    @Field(value  = "CREATION_DATE")
    @NotToUseDuringMerge
    private Long creationDate;

    @Attribute(keyword = "MEDIA_LASTMODIFIEDDATE", resolvedKeyword = "lastModifiedDate")
    @Field(value  = "LAST_MODIFIED_DATE")
    @NotToUseDuringMerge
    private Long lastModifiedDate;

    @Attribute(keyword = "MEDIA_CREATEDBY", resolvedKeyword = "createdBy")
    @Field(value  = "CREATED_BY")
    @NotToUseDuringMerge
    private Long createdBy;

    @Attribute(keyword = "MEDIA_LASTMODIFIEDBY", resolvedKeyword = "lastModifiedBy")
    @Field(value  = "LAST_MODIFIED_BY")
    @NotToUseDuringMerge
    private Long lastModifiedBy;

    @Version
    @Attribute(keyword = "MEDIA_VERSION", resolvedKeyword = "version")
    @Field(value  = "VERSION")
    @NotToUseDuringMerge
    private Integer version;


    @Attribute(keyword = "MEDIA_MEDIAUSERACTIONSTATISTICS", resolvedKeyword = "mediaUserActionStatistics")
    @Field(value  = "MEDIA_USER_ACTION_STATISTICS")
    @NotToUseDuringMerge
    private MediaUserActionStatistics mediaUserActionStatistics = new MediaUserActionStatistics();


    public void preSave(Long subjectId){
        this.setCreatedBy(subjectId);
        this.setCreationDate(System.currentTimeMillis());
        this.setLastModifiedBy(subjectId);
        this.setLastModifiedDate(this.getCreationDate());
        mergeInfospots();

        // this.setVersion(0);
    }

    public void preUpdate(Long subjectId){
        this.setLastModifiedBy(subjectId);
        this.setLastModifiedDate(System.currentTimeMillis());
        mergeInfospots();


        // this.setVersion(this.getVersion() + 1);
    }



    private void mergeInfospots() {
        if(this.getInfoSpots().size() > 0){
            Set<InfoSpot> infoSpots = new LinkedHashSet<>();
            for (InfoSpot infoSpot: this.getInfoSpots()) {
                if(infoSpot.getId() == null){
                    infoSpot.setId(UUID.randomUUID().toString());
                }
                infoSpots.add(infoSpot);
            }
            this.setInfoSpots(infoSpots);
        }
    }

    public MediaSourceType getMediaSourceType() {
        return mediaSourceType;
    }

    public void setMediaSourceType(MediaSourceType mediaSourceType) {
        this.mediaSourceType = mediaSourceType;
    }

    public SpaceMap getSpaceMap() {
        return spaceMap;
    }

    public void setSpaceMap(SpaceMap spaceMap) {
        this.spaceMap = spaceMap;
    }

    public void fillExtendedKeywords(){
        this.extendedKeywords.addAll(this.getKeywords());
        this.extendedKeywords.addAll(this.getColorsAndHex());
    }

    public List<ObjectLocalizationContainer> getObjectLocalizationContainers() {
        return objectLocalizationContainers;
    }

    public void setObjectLocalizationContainers(List<ObjectLocalizationContainer> objectLocalizationContainers) {
        this.objectLocalizationContainers = objectLocalizationContainers;
    }

    public String getAltUrl() {
        return altUrl;
    }

    public void setAltUrl(String altUrl) {
        this.altUrl = altUrl;
    }

    public ExternalSource getExternalSource() {
        return externalSource;
    }

    public void setExternalSource(ExternalSource externalSource) {
        this.externalSource = externalSource;
    }

    public Boolean getActive() {
        return active;
    }

    public Long getSizeInBytes() {
        return sizeInBytes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSizeInBytes(Long sizeInBytes) {
        this.sizeInBytes = sizeInBytes;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
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

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Boolean getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(Boolean coverImage) {
        this.coverImage = coverImage;
    }

    public Set<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(Set<String> keywords) {
        this.keywords = keywords;
    }

    public Set<InfoSpot> getInfoSpots() {
        return infoSpots;
    }

    public void setInfoSpots(Set<InfoSpot> infoSpots) {
        this.infoSpots = infoSpots;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public STATUS getStatus() {
        return status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

    public VISIBILITY getVisibility() {
        return visibility;
    }

    public void setVisibility(VISIBILITY visibility) {
        this.visibility = visibility;
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

    public MediaUserActionStatistics getMediaUserActionStatistics() {
        return mediaUserActionStatistics;
    }

    public void setMediaUserActionStatistics(MediaUserActionStatistics mediaUserActionStatistics) {
        this.mediaUserActionStatistics = mediaUserActionStatistics;
    }
}
