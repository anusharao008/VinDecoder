package com.altimetrik.workshop.vin.decoder.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Plant {
    private String plantCity;
    private String plantCountry;
    private String plantCompanyName;
    private String plantState;

    public String getPlantCity() {
        return plantCity;
    }

    public void setPlantCity(String plantCity) {
        this.plantCity = plantCity;
    }

    public String getPlantCountry() {
        return plantCountry;
    }

    public void setPlantCountry(String plantCountry) {
        this.plantCountry = plantCountry;
    }

    public String getPlantCompanyName() {
        return plantCompanyName;
    }

    public void setPlantCompanyName(String plantCompanyName) {
        this.plantCompanyName = plantCompanyName;
    }

    public String getPlantState() {
        return plantState;
    }

    public void setPlantState(String plantState) {
        this.plantState = plantState;
    }

    @Override
    public String toString() {
        if(this.plantCity == null && this.plantCountry == null && this.plantState == null && this.plantCompanyName == null) {
            return null;
        }
        return "Plant City :" + this.plantCity +"\n"
                +"Plant Country :" + this.plantCountry +"\n"
                +"Plant Company Name :" + this.plantCompanyName +"\n"
                +"Plant State :" + this.plantState ;
    }
}
