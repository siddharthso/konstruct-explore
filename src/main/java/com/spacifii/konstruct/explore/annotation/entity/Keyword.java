package com.spacifii.konstruct.explore.annotation.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Author: Ashish Shinde
 * Date: 21-April-2018
 * Email: ashish.shinde@spacifii.com
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
public class Keyword {

    private String keyword;
    private String resolvedKeyword;
    private String	dataType;

}
