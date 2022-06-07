package com.spacifii.konstruct.explore.model.dto.explore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder(alphabetic = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MediaCommentNode {

    MediaComment mediaComment;

    List<MediaCommentNode> childrenNodes;

    public MediaComment getMediaComment() {
        return mediaComment;
    }

    public void setMediaComment(MediaComment mediaComment) {
        this.mediaComment = mediaComment;
    }

    public List<MediaCommentNode> getChildrenNodes() {
        return childrenNodes;
    }

    public void setChildrenNodes(List<MediaCommentNode> childrenNodes) {
        this.childrenNodes = childrenNodes;
    }
}
