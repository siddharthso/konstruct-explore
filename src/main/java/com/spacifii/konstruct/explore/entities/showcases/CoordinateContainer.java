package com.spacifii.konstruct.explore.entities.showcases;

import org.springframework.data.mongodb.core.mapping.Field;

import java.util.LinkedHashSet;


public class CoordinateContainer {

    @Field("ID")
    private String id;

    @Field("CONTAINER_MEDIA_COORDINGATE_TYPE")
    private ContainerMediaCoordinateType containerMediaCoordinateType;

    @Field("COORDINATES")
    private LinkedHashSet<Coordinate> coordinates = new LinkedHashSet<>();



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ContainerMediaCoordinateType getContainerMediaCoordinateType() {
        return containerMediaCoordinateType;
    }

    public void setContainerMediaCoordinateType(ContainerMediaCoordinateType containerMediaCoordinateType) {
        this.containerMediaCoordinateType = containerMediaCoordinateType;
    }

    public LinkedHashSet<Coordinate> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(LinkedHashSet<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }

}
