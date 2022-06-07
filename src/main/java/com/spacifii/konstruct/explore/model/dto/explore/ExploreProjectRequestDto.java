package com.spacifii.konstruct.explore.model.dto.explore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.math.BigDecimal;
import java.util.Set;

/**
 * This is DTO Class for ExploreProject
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
public class ExploreProjectRequestDto {

    private String projectName;

    private String projectType;

    private String propertyType;

    private String projectAddress;

    private String developerOrProjectName;

    private Integer projectYear;

    private BigDecimal projectBudgetActual;

    private String projectBudgetRange;

    private BigDecimal projectBudgetStartRange;

    private BigDecimal projectBudgetEndRange;

    private Set<String> projectStyles;

    private Set<String> scopeTypes;

    private Set<String> spaces;

    private BigDecimal duration;

    private String durationType;

    private String projectDescription;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getProjectAddress() {
        return projectAddress;
    }

    public void setProjectAddress(String projectAddress) {
        this.projectAddress = projectAddress;
    }

    public String getDeveloperOrProjectName() {
        return developerOrProjectName;
    }

    public void setDeveloperOrProjectName(String developerOrProjectName) {
        this.developerOrProjectName = developerOrProjectName;
    }

    public Integer getProjectYear() {
        return projectYear;
    }

    public void setProjectYear(Integer projectYear) {
        this.projectYear = projectYear;
    }

    public BigDecimal getProjectBudgetActual() {
        return projectBudgetActual;
    }

    public void setProjectBudgetActual(BigDecimal projectBudgetActual) {
        this.projectBudgetActual = projectBudgetActual;
    }

    public String getProjectBudgetRange() {
        return projectBudgetRange;
    }

    public void setProjectBudgetRange(String projectBudgetRange) {
        this.projectBudgetRange = projectBudgetRange;
    }

    public BigDecimal getProjectBudgetStartRange() {
        return projectBudgetStartRange;
    }

    public void setProjectBudgetStartRange(BigDecimal projectBudgetStartRange) {
        this.projectBudgetStartRange = projectBudgetStartRange;
    }

    public BigDecimal getProjectBudgetEndRange() {
        return projectBudgetEndRange;
    }

    public void setProjectBudgetEndRange(BigDecimal projectBudgetEndRange) {
        this.projectBudgetEndRange = projectBudgetEndRange;
    }

    public Set<String> getSpaces() {
        return spaces;
    }

    public void setSpaces(Set<String> spaces) {
        this.spaces = spaces;
    }

    public Set<String> getProjectStyles() {
        return projectStyles;
    }

    public void setProjectStyles(Set<String> projectStyles) {
        this.projectStyles = projectStyles;
    }

    public Set<String> getScopeTypes() {
        return scopeTypes;
    }

    public void setScopeTypes(Set<String> scopeTypes) {
        this.scopeTypes = scopeTypes;
    }

    public BigDecimal getDuration() {
        return duration;
    }

    public void setDuration(BigDecimal duration) {
        this.duration = duration;
    }

    public String getDurationType() {
        return durationType;
    }

    public void setDurationType(String durationType) {
        this.durationType = durationType;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }
}
