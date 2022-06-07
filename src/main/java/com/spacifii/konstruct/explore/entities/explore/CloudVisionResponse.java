package com.spacifii.konstruct.explore.entities.explore;

import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CloudVisionResponse {

    @Field("CLOUD_VISION_TYPE")
    private CloudVisionType cloudVisionType;

    @Field("LABEL")
    private String label;

    @Field("SCORE")
    private BigDecimal score;

    @Field("RED")
    private BigDecimal red;

    @Field("GREEN")
    private BigDecimal green;

    @Field("BLUE")
    private BigDecimal blue;

    @Field("PIXEL_FRACTION")
    private BigDecimal pixelFraction;

    @Field("PARENT_COLOR_NAME")
    private String parentColorName;

    @Field("CLOSEST_COLOR_NAME")
    private String closestColorName;

    @Field("VERTICES_LIST")
    private List<Vertices> verticesList = new ArrayList<>();

    @Field("HEX_CODE")
    private String hexCode;

    @Field("HASH_HEX_CODE")
    private String hashHexCode;

    @Field("HUE")
    private BigDecimal hue;

    @Field("SATURATION")
    private BigDecimal saturation;

    @Field("LUMINANCE")
    private BigDecimal luminance;

    @Field("PRIMARY_COLOR_HEX")
    private String primaryColorHex;

    @Field("PRIMARY_COLOR_HEX_HASH")
    private String primaryColorHexHash;

    public void mergeSpacifiiColorInMe(SpacifiiColor spacifiiColor){
        this.setRed(spacifiiColor.getRed());
        this.setGreen(spacifiiColor.getGreen());
        this.setBlue(spacifiiColor.getBlue());
        this.setClosestColorName(spacifiiColor.getColorName());
        this.setParentColorName(spacifiiColor.getPrimaryColorName());
        this.setHue(spacifiiColor.getHue());
        this.setSaturation(spacifiiColor.getSaturation());
        this.setLuminance(spacifiiColor.getLuminance());
        this.setPrimaryColorHex(spacifiiColor.getPrimaryColorHex());
        this.setPrimaryColorHexHash(spacifiiColor.getPrimaryColorHashHex());
    }


    public String getPrimaryColorHexHash() {
        return primaryColorHexHash;
    }

    public void setPrimaryColorHexHash(String primaryColorHexHash) {
        this.primaryColorHexHash = primaryColorHexHash;
    }

    public String getPrimaryColorHex() {
        return primaryColorHex;
    }

    public void setPrimaryColorHex(String primaryColorHex) {
        this.primaryColorHex = primaryColorHex;
    }

    public String getHexCode() {
        return hexCode;
    }

    public void setHexCode(String hexCode) {
        this.hexCode = hexCode;
    }

    public String getHashHexCode() {
        return hashHexCode;
    }

    public void setHashHexCode(String hashHexCode) {
        this.hashHexCode = hashHexCode;
    }

    public BigDecimal getPixelFraction() {
        return pixelFraction;
    }

    public void setPixelFraction(BigDecimal pixelFraction) {
        this.pixelFraction = pixelFraction;
    }

    public CloudVisionType getCloudVisionType() {
        return cloudVisionType;
    }

    public void setCloudVisionType(CloudVisionType cloudVisionType) {
        this.cloudVisionType = cloudVisionType;
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

    public BigDecimal getRed() {
        return red;
    }

    public void setRed(BigDecimal red) {
        this.red = red;
    }

    public BigDecimal getGreen() {
        return green;
    }

    public void setGreen(BigDecimal green) {
        this.green = green;
    }

    public BigDecimal getBlue() {
        return blue;
    }

    public void setBlue(BigDecimal blue) {
        this.blue = blue;
    }

    public String getParentColorName() {
        return parentColorName;
    }

    public void setParentColorName(String parentColorName) {
        this.parentColorName = parentColorName;
    }

    public String getClosestColorName() {
        return closestColorName;
    }

    public void setClosestColorName(String closestColorName) {
        this.closestColorName = closestColorName;
    }

    public BigDecimal getHue() {
        return hue;
    }

    public void setHue(BigDecimal hue) {
        this.hue = hue;
    }

    public BigDecimal getSaturation() {
        return saturation;
    }

    public void setSaturation(BigDecimal saturation) {
        this.saturation = saturation;
    }

    public BigDecimal getLuminance() {
        return luminance;
    }

    public void setLuminance(BigDecimal luminance) {
        this.luminance = luminance;
    }

    public List<Vertices> getVerticesList() {
        return verticesList;
    }

    public void setVerticesList(List<Vertices> verticesList) {
        this.verticesList = verticesList;
    }
}
