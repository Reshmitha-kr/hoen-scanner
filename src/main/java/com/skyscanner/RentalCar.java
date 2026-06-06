package com.skyscanner;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RentalCar {
    private String city;
    private String title;

    public RentalCar() {}

    @JsonProperty
    public String getCity() { return city; }

    @JsonProperty
    public String getTitle() { return title; }

    public void setCity(String city) { this.city = city; }
    public void setTitle(String title) { this.title = title; }
}