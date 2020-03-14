package com.altimetrik.workshop.vin.decoder.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Manufacturer {
    private String manufacturerName;
    private String manufacturerId;

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(String manufacturerId) {
        this.manufacturerId = manufacturerId;
    }


    @Override
    public String toString() {
        if(this.manufacturerName == null && this.manufacturerId == null) {
            return null;
        }
        return "Manufacturer Name :" + this.manufacturerName +"\n"
                +"Manufacturer Id :" + this.manufacturerId ;
    }
}
