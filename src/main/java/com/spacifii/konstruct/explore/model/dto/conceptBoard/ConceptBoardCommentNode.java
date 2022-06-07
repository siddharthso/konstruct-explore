package com.spacifii.konstruct.explore.model.dto.conceptBoard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder(alphabetic = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConceptBoardCommentNode {

    ConceptBoardComment conceptBoardComment;

    List<ConceptBoardCommentNode> childrenNodes;

    public ConceptBoardComment getConceptBoardComment() {
        return conceptBoardComment;
    }

    public void setConceptBoardComment(ConceptBoardComment conceptBoardComment) {
        this.conceptBoardComment = conceptBoardComment;
    }

    public List<ConceptBoardCommentNode> getChildrenNodes() {
        return childrenNodes;
    }

    public void setChildrenNodes(List<ConceptBoardCommentNode> childrenNodes) {
        this.childrenNodes = childrenNodes;
    }
}
