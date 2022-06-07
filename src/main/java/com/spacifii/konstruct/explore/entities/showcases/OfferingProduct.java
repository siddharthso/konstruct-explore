package com.spacifii.konstruct.explore.entities.showcases;

import com.spacifii.konstruct.explore.annotation.NotToUseDuringMerge;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.util.LinkedHashSet;

@Document(collection = "OFFERING_PRODUCT")
public class OfferingProduct {

    @Id
    @NotToUseDuringMerge
    @Field(value = "PRODUCT_ID")
    private String productId;

    @Field(value = "NAME")
    private String name;

    @Field(value = "DESCRIPTION")
    private String description;

    @Field(value = "FACTOR")
    private BigDecimal factor;

    @NotToUseDuringMerge
    @Field(value = "OFFERING_VIEW_TYPE_WISE_MEDIA")
    private LinkedHashSet<OfferingViewTypeWiseMedia> offeringViewTypeWiseMedia = new LinkedHashSet<>();

    public BigDecimal getFactor() {
        return factor;
    }

    public void setFactor(BigDecimal factor) {
        this.factor = factor;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    public LinkedHashSet<OfferingViewTypeWiseMedia> getOfferingViewTypeWiseMedia() {
        return offeringViewTypeWiseMedia;
    }

    public void setOfferingViewTypeWiseMedia(LinkedHashSet<OfferingViewTypeWiseMedia> offeringViewTypeWiseMedia) {
        this.offeringViewTypeWiseMedia = offeringViewTypeWiseMedia;
    }

}
