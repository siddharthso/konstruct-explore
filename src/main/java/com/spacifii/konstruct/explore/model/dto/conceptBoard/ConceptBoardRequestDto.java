package com.spacifii.konstruct.explore.model.dto.conceptBoard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardVisibility;

@JsonPropertyOrder(alphabetic = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConceptBoardRequestDto {

    private String id;

    private String folderName;

    private String description;

    private ConceptBoardVisibility conceptBoardVisibility;

    private Boolean active = Boolean.TRUE;

    private String parentConceptBoardId;

    private Boolean allowComments= Boolean.TRUE;
    private Boolean allowQuestions= Boolean.TRUE;

    public Boolean getAllowComments() {
        return allowComments;
    }

    public void setAllowComments(Boolean allowComments) {
        this.allowComments = allowComments;
    }

    public Boolean getAllowQuestions() {
        return allowQuestions;
    }

    public void setAllowQuestions(Boolean allowQuestions) {
        this.allowQuestions = allowQuestions;
    }

    public String getParentConceptBoardId() {
        return parentConceptBoardId;
    }

    public void setParentConceptBoardId(String parentConceptBoardId) {
        this.parentConceptBoardId = parentConceptBoardId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public ConceptBoardVisibility getConceptBoardVisibility() {
        return conceptBoardVisibility;
    }

    public void setConceptBoardVisibility(ConceptBoardVisibility conceptBoardVisibility) {
        this.conceptBoardVisibility = conceptBoardVisibility;
    }
}
