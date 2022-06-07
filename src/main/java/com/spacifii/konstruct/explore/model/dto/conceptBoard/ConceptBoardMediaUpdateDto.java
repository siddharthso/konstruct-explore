package com.spacifii.konstruct.explore.model.dto.conceptBoard;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(alphabetic = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConceptBoardMediaUpdateDto {

    private String conceptBoardId;
    private String conceptBoardMediaId;
    private String name;
    private String description;
    private Boolean active = Boolean.TRUE;

    public String getConceptBoardId() {
        return conceptBoardId;
    }

    public void setConceptBoardId(String conceptBoardId) {
        this.conceptBoardId = conceptBoardId;
    }

    public String getConceptBoardMediaId() {
        return conceptBoardMediaId;
    }

    public void setConceptBoardMediaId(String conceptBoardMediaId) {
        this.conceptBoardMediaId = conceptBoardMediaId;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
