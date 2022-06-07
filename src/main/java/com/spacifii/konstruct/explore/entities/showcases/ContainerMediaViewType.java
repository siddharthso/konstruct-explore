package com.spacifii.konstruct.explore.entities.showcases;

import com.spacifii.konstruct.explore.annotation.NotToUseDuringMerge;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Document(collection = "CONTAINER_MEDIA_VIEW_TYPE")
public class ContainerMediaViewType {

    @Id
    @NotToUseDuringMerge
    @Field(value = "ID")
    private String id;

    @Field(value = "NAME")
    private String name;

    @Field(value = "DESCRIPTION")
    private String description;

    @Field(value = "DEGREE1")
    private BigDecimal degree1;

    @Field(value = "DEGREE2")
    private BigDecimal degree2;

    @Field(value = "DEGREE3")
    private BigDecimal degree3;

    @Field(value = "DEGREE4")
    private BigDecimal degree4;

    @NotToUseDuringMerge
    @Field(value = "OFFERING_PRODUCT_VIEW_TYPES")
    private Set<OfferingProductViewType> offeringProductViewTypes = new LinkedHashSet<>();



    public Set<OfferingProductViewType> getOfferingProductViewTypes() {
        return offeringProductViewTypes;
    }

    public void setOfferingProductViewTypes(Set<OfferingProductViewType> offeringProductViewTypes) {
        this.offeringProductViewTypes = offeringProductViewTypes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public BigDecimal getDegree1() {
        return degree1;
    }

    public void setDegree1(BigDecimal degree1) {
        this.degree1 = degree1;
    }

    public BigDecimal getDegree2() {
        return degree2;
    }

    public void setDegree2(BigDecimal degree2) {
        this.degree2 = degree2;
    }

    public BigDecimal getDegree3() {
        return degree3;
    }

    public void setDegree3(BigDecimal degree3) {
        this.degree3 = degree3;
    }

    public BigDecimal getDegree4() {
        return degree4;
    }

    public void setDegree4(BigDecimal degree4) {
        this.degree4 = degree4;
    }
}
