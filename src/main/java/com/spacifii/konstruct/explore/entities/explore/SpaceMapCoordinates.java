package com.spacifii.konstruct.explore.entities.explore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

@JsonPropertyOrder(alphabetic = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpaceMapCoordinates {

    @Field("SPACE_NAME")
    private String spaceName;

    @Field("MEDIA_ID")
    private String mediaId;

    @Field("URL")
    private String url;

    @Field("X_AXIS")
    private BigDecimal xAxis;

    @Field("Y_AXIS")
    private BigDecimal yAxis;

    @Field("Z_AXIS")
    private BigDecimal zAxis;


    public String getSpaceName() {
        return spaceName;
    }

    public void setSpaceName(String spaceName) {
        this.spaceName = spaceName;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public BigDecimal getxAxis() {
        return xAxis;
    }

    public void setxAxis(BigDecimal xAxis) {
        this.xAxis = xAxis;
    }

    public BigDecimal getyAxis() {
        return yAxis;
    }

    public void setyAxis(BigDecimal yAxis) {
        this.yAxis = yAxis;
    }

    public BigDecimal getzAxis() {
        return zAxis;
    }

    public void setzAxis(BigDecimal zAxis) {
        this.zAxis = zAxis;
    }
}
