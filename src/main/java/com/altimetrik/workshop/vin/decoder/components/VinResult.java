package com.altimetrik.workshop.vin.decoder.components;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VinResult {
    @JsonProperty("Value")
    private String value;
    @JsonProperty("ValueId")
    private String valueId;
    @JsonProperty("Variable")
    private String variable;
    @JsonProperty("VariableId")
    private String variableId;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValueId() {
        return valueId;
    }

    public void setValueId(String valueId) {
        this.valueId = valueId;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public String getVariableId() {
        return variableId;
    }

    public void setVariableId(String variableId) {
        this.variableId = variableId;
    }
}
