package com.spacifii.konstruct.explore.integration.rbac.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
public class RbacRegisterDto {

    private String applicationName;
    private Set<RbacAuthorityDto> rbacAuthorities;

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public Set<RbacAuthorityDto> getRbacAuthorities() {
        return rbacAuthorities;
    }

    public void setRbacAuthorities(Set<RbacAuthorityDto> rbacAuthorities) {
        this.rbacAuthorities = rbacAuthorities;
    }
}
