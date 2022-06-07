package com.spacifii.konstruct.explore.entities.showcases;

import com.spacifii.konstruct.explore.annotation.NotToUseDuringMerge;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.util.Objects;

@Document(collection = "OFFERING_PRODUCT_VIEW_TYPE")
public class OfferingProductViewType {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OfferingProductViewType that = (OfferingProductViewType) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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
