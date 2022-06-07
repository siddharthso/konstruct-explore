package com.spacifii.konstruct.explore.entities.explore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Objects;

/**
 * This entity class is used to Embed (currently into infoSpot)
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
public class ColorNameHexCode {

    @Field("HEX_CODE")
    private String hexCode;

    @Field("COLOR_NAME")
    private String colorName;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ColorNameHexCode that = (ColorNameHexCode) o;
        return Objects.equals(hexCode, that.hexCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hexCode);
    }

    public String getHexCode() {
        return hexCode;
    }

    public void setHexCode(String hexCode) {
        this.hexCode = hexCode;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }
}
