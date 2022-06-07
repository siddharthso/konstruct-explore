package com.spacifii.konstruct.explore.model.dto.recentlyspacified;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.LinkedHashSet;

/**
 * This is DTO class used by customer to fill the Testimonial
 */
@JsonPropertyOrder(alphabetic = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TestimonialDto {

    private String id;

    private String quoteText;

    private String descriptionText;

    private LinkedHashSet<String> peopleToBeThanked;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuoteText() {
        return quoteText;
    }

    public void setQuoteText(String quoteText) {
        this.quoteText = quoteText;
    }

    public String getDescriptionText() {
        return descriptionText;
    }

    public void setDescriptionText(String descriptionText) {
        this.descriptionText = descriptionText;
    }

    public LinkedHashSet<String> getPeopleToBeThanked() {
        return peopleToBeThanked;
    }

    public void setPeopleToBeThanked(LinkedHashSet<String> peopleToBeThanked) {
        this.peopleToBeThanked = peopleToBeThanked;
    }
}
