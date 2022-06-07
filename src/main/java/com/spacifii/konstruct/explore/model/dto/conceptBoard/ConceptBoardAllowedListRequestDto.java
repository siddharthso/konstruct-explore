package com.spacifii.konstruct.explore.model.dto.conceptBoard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Set;


@JsonPropertyOrder(alphabetic = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConceptBoardAllowedListRequestDto {

    private String id;

    private String folderName;

    private Set<String> allowedList;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFolderName() {
        return folderName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public Set<String> getAllowedList() {
        return allowedList;
    }

    public void setAllowedList(Set<String> allowedList) {
        this.allowedList = allowedList;
    }
}
