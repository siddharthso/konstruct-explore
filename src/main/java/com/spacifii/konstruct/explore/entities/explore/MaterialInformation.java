package com.spacifii.konstruct.explore.entities.explore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Set;

/**
 * This class is used to adding material information to infospot
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
public class MaterialInformation {

    @Field("BRAND")
    private String brand;

    @Field("MODEL")
    private String model;

    @Field("NAME")
    private String name;

    @Field("DESCRIPTION")
    private String description;

    @Field("AVAILABLE_COLORS")
    private Set<ColorNameHexCode> availableColors;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<ColorNameHexCode> getAvailableColors() {
        return availableColors;
    }

    public void setAvailableColors(Set<ColorNameHexCode> availableColors) {
        this.availableColors = availableColors;
    }
}
