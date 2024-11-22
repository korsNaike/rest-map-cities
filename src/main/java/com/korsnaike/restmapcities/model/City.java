package com.korsnaike.restmapcities.model;

import java.util.UUID;

public class City {

    private String id;
    private String name;
    private String country;
    private String phoneCode;
    private boolean isCapital;
    private int population;

    public City() {}

    public City(String id, String name, String country, String phoneCode, boolean isCapital, int population) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.phoneCode = phoneCode;
        this.isCapital = isCapital;
        this.population = population;
    }

    public City(String name, String country, String phoneCode, boolean isCapital, int population) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.country = country;
        this.phoneCode = phoneCode;
        this.isCapital = isCapital;
        this.population = population;
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    public boolean isCapital() {
        return isCapital;
    }

    public void setCapital(boolean capital) {
        isCapital = capital;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

}
