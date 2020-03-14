package com.altimetrik.workshop.vin.decoder.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Model {
    private String make;
    private String model;
    private String modelYear;

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModelYear() {
        return modelYear;
    }

    public void setModelYear(String modelYear) {
        this.modelYear = modelYear;
    }


    @Override
    public String toString() {
        if(this.make == null && this.model == null && this.modelYear == null) {
            return null;
        }
        return "Make :" + this.make +"\n"
                +"Model :" + this.model +"\n"
                +"Model Year :" + this.modelYear;
    }
}
