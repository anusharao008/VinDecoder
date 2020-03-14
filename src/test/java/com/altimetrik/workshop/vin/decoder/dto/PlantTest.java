package com.altimetrik.workshop.vin.decoder.dto;

import com.altimetrik.workshop.vin.decoder.AbstractTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;

class PlantTest extends AbstractTest {
    @Test
    public void givenNullsIgnoredOnClass_whenWritingObjectWithNullField_thenIgnored()
            throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Plant dtoObject = new Plant();
        dtoObject.setPlantCompanyName("Company Name");

        String dtoAsString = mapper.writeValueAsString(dtoObject);

        assertThat(dtoAsString, containsString("plantCompanyName"));
        assertThat(dtoAsString, not(containsString("plantCity")));
    }
}