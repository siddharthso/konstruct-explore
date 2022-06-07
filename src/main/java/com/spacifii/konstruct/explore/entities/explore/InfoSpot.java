package com.spacifii.konstruct.explore.entities.explore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.spacifii.konstruct.explore.annotation.Attribute;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * This is Entity class for Infospot
 */
@JsonPropertyOrder(alphabetic = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class InfoSpot {

    @Attribute(keyword = "INFOSPOT_ID", resolvedKeyword = "id")
    @Field(value = "ID")
    private String id;

    @Attribute(keyword = "INFOSPOT_NAME", resolvedKeyword = "name")
    @Field(value = "NAME")
    private String name;

    @Attribute(keyword = "INFOSPOT_TOOLTIP", resolvedKeyword = "toolTip")
    @Field(value = "TOOLTIP")
    private String toolTip;

    @Attribute(keyword = "INFOSPOT_XCOORDINATE", resolvedKeyword = "xCoordinate")
    @Field(value = "X_COORDINATE")
    private BigDecimal xCoordinate;

    @Attribute(keyword = "INFOSPOT_YCOORDINATE", resolvedKeyword = "yCoordinate")
    @Field(value = "Y_COORDINATE")
    private BigDecimal yCoordinate;

    @Attribute(keyword = "INFOSPOT_ZCOORDINATE", resolvedKeyword = "zCoordinate")
    @Field(value = "Z_COORDINATE")
    private BigDecimal zCoordinate;

    @Attribute(keyword = "INFOSPOT_INFOSPOTACTION", resolvedKeyword = "infoSpotAction")
    @Field(value = "INFOSPOT_ACTION")
    private InfoSpotAction infoSpotAction;

    @Attribute(keyword = "INFOSPOT_INFOSPOTACTIONACTIONVALUE", resolvedKeyword = "infoSportActionValue")
    @Field(value = "INFOSPOT_ACTION_VALUE")
    private String infoSpotActionValue;

    @Attribute(keyword = "INFOSPOT_INFOSPOTKEYWORDS", resolvedKeyword = "infoSpotKeywords")
    @Field(value = "INFOSPOT_KEYWORDS")
    private Set<String> infoSpotKeywords;

    @Attribute(keyword = "INFOSPOT_COMMERCIALLYAVAILABLE", resolvedKeyword = "commerciallyAvailable")
    @Field(value = "COMMERCIALLY_AVAILABLE")
    private Boolean commerciallyAvailable;

    @Attribute(keyword = "INFOSPOT_BRAND", resolvedKeyword = "brand")
    @Field(value = "BRAND")
    private String brand;

    @Attribute(keyword = "INFOSPOT_MODEL", resolvedKeyword = "model")
    @Field(value = "MODEL")
    private String model;

    @Attribute(keyword = "INFOSPOT_CATEGORY", resolvedKeyword = "category")
    @Field(value = "CATEGORY")
    private String category;

    @Attribute(keyword = "INFOSPOT_SUBCATEGORY", resolvedKeyword = "subCategory")
    @Field(value = "SUB_CATEGORY")
    private String subCategory;

    @Attribute(keyword = "INFOSPOT_ISSPACE", resolvedKeyword = "isSpace")
    @Field(value = "IS_SPACE")
    private Boolean isSpace;

    @Attribute(keyword = "INFOSPOT_PRIMARYCOLOR", resolvedKeyword = "primaryColor")
    @Field(value = "PRIMARY_COLOR")
    private ColorNameHexCode primaryColor;

    @Attribute(keyword = "INFOSPOT_SPACE", resolvedKeyword = "space")
    @Field(value = "SPACE")
    private String space;

    @Attribute(keyword = "INFOSPOT_SHAPE", resolvedKeyword = "shape")
    @Field(value = "SHAPE")
    private String shape;

    @Attribute(keyword = "INFOSPOT_HEIGHT", resolvedKeyword = "height")
    @Field(value = "HEIGHT")
    private BigDecimal height;

    @Attribute(keyword = "INFOSPOT_WIDTH", resolvedKeyword = "width")
    @Field(value = "WIDTH")
    private BigDecimal width;

    @Attribute(keyword = "INFOSPOT_LENGTH", resolvedKeyword = "length")
    @Field(value = "LENGTH")
    private BigDecimal length;

    @Attribute(keyword = "INFOSPOT_DESCRIPTION", resolvedKeyword = "description")
    @Field(value = "DESCRIPTION")
    private String description;

    @Attribute(keyword = "INFOSPOT_PRICE", resolvedKeyword = "price")
    @Field(value = "PRICE")
    private BigDecimal price;

    @Attribute(keyword = "INFOSPOT_UNIT", resolvedKeyword = "unit")
    @Field(value = "UNIT")
    private String unit;

    @Attribute(keyword = "INFOSPOT_ACTIVE", resolvedKeyword = "active")
    @Field(value = "ACTIVE")
    private Boolean active = Boolean.TRUE;

    @Attribute(keyword = "INFOSPOT_URL", resolvedKeyword = "redirectUrl")
    @Field(value = "REDIRECT_URL")
    private String redirectUrl;

    @Attribute(keyword = "INFOSPOT_VIDEOINFORMATION", resolvedKeyword = "videoInformation")
    @Field("VIDEO_INFORMATION")
    private Set<VideoInformation> videoInformation = new HashSet<>();


    @Attribute(keyword = "INFOSPOT_MATERIALINFORMATION", resolvedKeyword = "materialInformation")
    @Field("MATERIAL_INFORMATION")
    private Set<MaterialInformation> materialInformation = new HashSet<>();


    @Attribute(keyword = "INFOSPOT_STYLES", resolvedKeyword = "styles")
    @Field("STYLES")
    private Set<String> sytles = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfoSpot infoSpot = (InfoSpot) o;
        return Objects.equals(id, infoSpot.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }



    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Set<String> getInfoSpotKeywords() {
        return infoSpotKeywords;
    }

    public void setInfoSpotKeywords(Set<String> infoSpotKeywords) {
        this.infoSpotKeywords = infoSpotKeywords;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(BigDecimal xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public BigDecimal getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(BigDecimal yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public BigDecimal getzCoordinate() {
        return zCoordinate;
    }

    public void setzCoordinate(BigDecimal zCoordinate) {
        this.zCoordinate = zCoordinate;
    }

    public InfoSpotAction getInfoSpotAction() {
        return infoSpotAction;
    }

    public void setInfoSpotAction(InfoSpotAction infoSpotAction) {
        this.infoSpotAction = infoSpotAction;
    }

    public String getInfoSpotActionValue() {
        return infoSpotActionValue;
    }

    public void setInfoSpotActionValue(String infoSpotActionValue) {
        this.infoSpotActionValue = infoSpotActionValue;
    }

    public Boolean getCommerciallyAvailable() {
        return commerciallyAvailable;
    }

    public void setCommerciallyAvailable(Boolean commerciallyAvailable) {
        this.commerciallyAvailable = commerciallyAvailable;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getToolTip() {
        return toolTip;
    }

    public void setToolTip(String toolTip) {
        this.toolTip = toolTip;
    }

    public Boolean getSpace() {
        return isSpace;
    }

    public void setSpace(String space) {
        this.space = space;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public BigDecimal getLength() {
        return length;
    }

    public void setLength(BigDecimal length) {
        this.length = length;
    }

    public void setSpace(Boolean space) {
        isSpace = space;
    }

    public ColorNameHexCode getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(ColorNameHexCode primaryColor) {
        this.primaryColor = primaryColor;
    }

    public Set<VideoInformation> getVideoInformation() {
        return videoInformation;
    }

    public void setVideoInformation(Set<VideoInformation> videoInformation) {
        this.videoInformation = videoInformation;
    }

    public Set<MaterialInformation> getMaterialInformation() {
        return materialInformation;
    }

    public void setMaterialInformation(Set<MaterialInformation> materialInformation) {
        this.materialInformation = materialInformation;
    }

    public Set<String> getSytles() {
        return sytles;
    }

    public void setSytles(Set<String> sytles) {
        this.sytles = sytles;
    }
}
