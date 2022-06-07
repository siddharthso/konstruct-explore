package com.spacifii.konstruct.explore.entities.showcases;

import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

public class Coordinate {

    @Field(value = "X")
    private BigDecimal x;

    @Field(value = "Y")
    private BigDecimal y;

    @Field(value = "Z")
    private BigDecimal z;

    @Field(value = "HEIGHT")
    private BigDecimal height;

    @Field(value = "WIDTH")
    private BigDecimal width;

    public BigDecimal getZ() {
        return z;
    }

    public void setZ(BigDecimal z) {
        this.z = z;
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

    public BigDecimal getX() {
        return x;
    }

    public void setX(BigDecimal x) {
        this.x = x;
    }

    public BigDecimal getY() {
        return y;
    }

    public void setY(BigDecimal y) {
        this.y = y;
    }
}
