package com.spacifii.konstruct.explore.entities.showcases;

import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Objects;

public class OfferingViewTypeWiseMedia {

    @Field(value = "OFFERING_PRODUCT_VIEW_TYPE")
    private String offeringProductViewType;

    @Field(value = "MEDIA_URL")
    private String mediaUrl;

    @Field(value = "ALT_URL")
    private String altUrl;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OfferingViewTypeWiseMedia that = (OfferingViewTypeWiseMedia) o;
        return Objects.equals(offeringProductViewType, that.offeringProductViewType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(offeringProductViewType);
    }

    public String getOfferingProductViewType() {
        return offeringProductViewType;
    }

    public void setOfferingProductViewType(String offeringProductViewType) {
        this.offeringProductViewType = offeringProductViewType;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public String getAltUrl() {
        return altUrl;
    }

    public void setAltUrl(String altUrl) {
        this.altUrl = altUrl;
    }
}
