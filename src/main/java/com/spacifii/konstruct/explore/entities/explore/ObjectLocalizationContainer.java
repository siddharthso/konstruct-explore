package com.spacifii.konstruct.explore.entities.explore;

import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ObjectLocalizationContainer {

    @Field("LABEL")
    private String label;

    @Field("SCORE")
    private BigDecimal score;

    @Field("VERTICES_LIST")
    private List<Vertices> verticesList = new ArrayList<>();

    public ObjectLocalizationContainer() {
    }

    public ObjectLocalizationContainer(String label, BigDecimal score, List<Vertices> verticesList) {
        this.label = label;
        this.score = score;
        this.verticesList = verticesList;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public List<Vertices> getVerticesList() {
        return verticesList;
    }

    public void setVerticesList(List<Vertices> verticesList) {
        this.verticesList = verticesList;
    }
}
