package com.altimetrik.workshop.vin.decoder.mapper;

import com.altimetrik.workshop.vin.decoder.AbstractTest;
import com.altimetrik.workshop.vin.decoder.components.VinDetails;
import com.altimetrik.workshop.vin.decoder.components.VinResult;
import com.altimetrik.workshop.vin.decoder.dto.VinDto;
import com.altimetrik.workshop.vin.decoder.error.VinException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.Arrays;

import static com.altimetrik.workshop.vin.decoder.constants.Constants.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.*;

class VinMapperTest extends AbstractTest {
    @InjectMocks
    VinMapper vinMapper;

    @Test
    void toVinDto_noResults() {
        VinDetails vinDetails = new VinDetails();
        assertNotNull(vinMapper.toVinDto("vin", vinDetails));
    }

    @Test
    void toVinDto() throws JsonProcessingException {
        VinResult vinResultMake = new VinResult();
        vinResultMake.setValue("make");
        vinResultMake.setVariable("Make");

        VinResult vinResultModel = new VinResult();
        vinResultModel.setValue("model");
        vinResultModel.setVariable("Model");

        VinDetails vinDetails = new VinDetails();
        vinDetails.setResults(Arrays.asList(vinResultMake, vinResultModel));

        VinDto vinDto = vinMapper.toVinDto("vin", vinDetails);

        ObjectMapper mapper = new ObjectMapper();

        String dtoAsString = mapper.writeValueAsString(vinDto);

        assertThat(dtoAsString, containsString("model"));
        assertThat(dtoAsString, not(containsString("plant")));
        assertThat(dtoAsString, not(containsString("manufacturer")));
    }

    @Test
    void toVinDto_exception() throws JsonProcessingException {
        VinResult vinResultMake = new VinResult();
        vinResultMake.setValue(null);
        vinResultMake.setVariable(MAKE);

        VinResult vinResultModel = new VinResult();
        vinResultModel.setValue("model");
        vinResultModel.setVariable(MODEL);

        VinResult vinResultErrorCode = new VinResult();
        vinResultErrorCode.setValue("error codes 1, 2,3");
        vinResultErrorCode.setVariable(ERROR_CODE);

        VinDetails vinDetails = new VinDetails();
        vinDetails.setResults(Arrays.asList(vinResultMake, vinResultModel, vinResultErrorCode));

        Assertions.assertThrows(VinException.class, () -> {
            vinMapper.toVinDto("vin", vinDetails);
        });
    }
}