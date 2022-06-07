package com.spacifii.konstruct.explore.model.dto.conceptBoard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder(alphabetic = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConceptBoardMediaCommentNode {

    ConceptBoardMediaComment mediaComment;

    List<ConceptBoardMediaCommentNode> childrenNodes;

    public ConceptBoardMediaComment getMediaComment() {
        return mediaComment;
    }

    public void setMediaComment(ConceptBoardMediaComment mediaComment) {
        this.mediaComment = mediaComment;
    }

    public List<ConceptBoardMediaCommentNode> getChildrenNodes() {
        return childrenNodes;
    }

    public void setChildrenNodes(List<ConceptBoardMediaCommentNode> childrenNodes) {
        this.childrenNodes = childrenNodes;
    }
}
