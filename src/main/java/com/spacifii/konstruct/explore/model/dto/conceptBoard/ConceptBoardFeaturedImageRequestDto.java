package com.spacifii.konstruct.explore.model.dto.conceptBoard;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(alphabetic = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConceptBoardFeaturedImageRequestDto {
    private String conceptBoardId;
    private String conceptBoardMediaId;


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
}
