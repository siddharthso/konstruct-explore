package com.spacifii.konstruct.explore.entities.explore;

import com.spacifii.konstruct.explore.annotation.Attribute;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "MEDIA_TYPE_EXTENSION")
public class MediaTypeExtension {

    @Id
    @Attribute(keyword = "MEDIATYPEEXTENSION_ID", resolvedKeyword = "id", enableFindBy = true)
    @Field(value = "ID")
    private String id;

    @Attribute(keyword = "MEDIATYPEEXTENSION_MEDIATYPE", resolvedKeyword = "mediaType")
    @Field(value = "MEDIA_TYPE")
    private MediaType mediaType;


    @Attribute(keyword = "MEDIATYPEEXTENSION_EXTENSION", resolvedKeyword = "extension")
    @Field(value = "EXTENSION")
    private String extension;


    @Attribute(keyword = "MEDIATYPEEXTENSION_DESCRIPTION", resolvedKeyword = "description")
    @Field(value = "description")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}
