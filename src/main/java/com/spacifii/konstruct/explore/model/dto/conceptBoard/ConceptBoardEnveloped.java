package com.spacifii.konstruct.explore.model.dto.conceptBoard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoard;
import com.spacifii.konstruct.explore.entities.conceptBoard.ConceptBoardWalkThrough;
import com.spacifii.konstruct.explore.model.dto.explore.UserProfileMiniDto;

import java.util.List;

@JsonPropertyOrder(alphabetic = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConceptBoardEnveloped {

    private ConceptBoard conceptBoard;

    private List<ConceptBoard> childBoards;

    private UserProfileMiniDto profileMiniDto;

    private List<ConceptBoardWalkThrough> conceptBoardWalkThroughs;

    public ConceptBoardEnveloped() {
    }

    public ConceptBoardEnveloped(ConceptBoard conceptBoard, List<ConceptBoard> childBoards) {
        this.conceptBoard = conceptBoard;
        this.childBoards = childBoards;
    }

    public ConceptBoardEnveloped(ConceptBoard conceptBoard, List<ConceptBoard> childBoards, UserProfileMiniDto profileMiniDto) {
        this.conceptBoard = conceptBoard;
        this.childBoards = childBoards;
        this.profileMiniDto = profileMiniDto;
    }

    public ConceptBoardEnveloped(ConceptBoard conceptBoard, List<ConceptBoard> childBoards, UserProfileMiniDto profileMiniDto, List<ConceptBoardWalkThrough> conceptBoardWalkThroughs) {
        this.conceptBoard = conceptBoard;
        this.childBoards = childBoards;
        this.profileMiniDto = profileMiniDto;
        this.conceptBoardWalkThroughs = conceptBoardWalkThroughs;
    }

    public List<ConceptBoardWalkThrough> getConceptBoardWalkThroughs() {
        return conceptBoardWalkThroughs;
    }

    public void setConceptBoardWalkThroughs(List<ConceptBoardWalkThrough> conceptBoardWalkThroughs) {
        this.conceptBoardWalkThroughs = conceptBoardWalkThroughs;
    }


    public UserProfileMiniDto getProfileMiniDto() {
        return profileMiniDto;
    }

    public void setProfileMiniDto(UserProfileMiniDto profileMiniDto) {
        this.profileMiniDto = profileMiniDto;
    }

    public ConceptBoard getConceptBoard() {
        return conceptBoard;
    }


    public void setConceptBoard(ConceptBoard conceptBoard) {
        this.conceptBoard = conceptBoard;
    }

    public List<ConceptBoard> getChildBoards() {
        return childBoards;
    }

    public void setChildBoards(List<ConceptBoard> childBoards) {
        this.childBoards = childBoards;
    }
}
