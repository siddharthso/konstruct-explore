package com.spacifii.konstruct.explore.model.dto.explore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoard;
import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardMedia;
import com.spacifii.konstruct.explore.entities.explore.ExternalSource;
import com.spacifii.konstruct.explore.entities.explore.Media;
import com.spacifii.konstruct.explore.entities.explore.MediaType;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
public class ExternalMediaDto {

    private MediaType mediaType;

    private ExternalSource externalSource;

    private String name;

    private String description;

    private String externalUrl;


    public Media BringMediaFromMe(){
        Media media = new Media();
        media.setMediaType(this.mediaType);
        media.setExternalSource(this.externalSource);
        media.setName(this.name);
        media.setDescription(this.description);
        media.setExternalUrl(this.externalUrl);
        media.setUrl(this.externalUrl);
        return media;
    }

    public ConceptBoardMedia BringConceptBoardMediaFromMe(){
        ConceptBoardMedia media = new ConceptBoardMedia();
        media.setMediaType(this.mediaType);
        media.setExternalSource(this.externalSource);
        media.setName(this.name);
        media.setDescription(this.description);
        media.setExternalUrl(this.externalUrl);
        media.setUrl(this.externalUrl);
        return media;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public ExternalSource getExternalSource() {
        return externalSource;
    }

    public void setExternalSource(ExternalSource externalSource) {
        this.externalSource = externalSource;
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

    public String getExternalUrl() {
        return externalUrl;
    }

    public void setExternalUrl(String externalUrl) {
        this.externalUrl = externalUrl;
    }
}
