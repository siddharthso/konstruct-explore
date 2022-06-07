package com.spacifii.konstruct.explore.entities.explore;


import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * This is entity class for ExploreProjectElastic
 */
@Document(indexName = "explore", type = "exploreProject")
public class ExploreProjectElastic {

    @Id
    private String projectId;

    @Field(type = FieldType.Text, analyzer = "true")
    private String projectName;


    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
