package com.spacifii.konstruct.explore.entities.explore;

import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

public class Vertices {

    @Field("X_AXIS")
    BigDecimal xAxis;

    @Field("Y_AXIS")
    BigDecimal yAxis;

    public Vertices() {
    }

    public Vertices(BigDecimal xAxis, BigDecimal yAxis) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
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

    @Override
    public String toString() {
        return "Vertices{" +
                "xAxis=" + xAxis +
                ", yAxis=" + yAxis +
                '}';
    }
}
