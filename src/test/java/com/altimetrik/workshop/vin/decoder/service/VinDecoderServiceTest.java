package com.altimetrik.workshop.vin.decoder.service;

import com.altimetrik.workshop.vin.decoder.AbstractTest;
import com.altimetrik.workshop.vin.decoder.components.VinDetails;
import com.altimetrik.workshop.vin.decoder.components.VinResult;
import com.altimetrik.workshop.vin.decoder.dto.VinDto;
import com.altimetrik.workshop.vin.decoder.error.VinException;
import com.altimetrik.workshop.vin.decoder.mapper.VinMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import java.util.Arrays;

import static com.altimetrik.workshop.vin.decoder.constants.Constants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class VinDecoderServiceTest extends AbstractTest {
    @InjectMocks
    VinDecoderService vinDecoderService;

    @Mock
    RestService restService;
    @Spy
    VinMapper vinMapper;

    @Test
    void getVinDto_validation() {
        String vin = "";
        VinException thrown = Assertions.assertThrows(VinException.class, () -> {
            vinDecoderService.getVinDto(vin);
        });

        assertTrue(thrown.getHttpStatus().is4xxClientError());
    }

    @Test
    void getVinDto() {
        String vin = "vin";
        VinResult vinResultMake = new VinResult();
        vinResultMake.setValue("make");
        vinResultMake.setVariable(MAKE);

        VinResult vinResultModel = new VinResult();
        vinResultModel.setValue("model");
        vinResultModel.setVariable(MODEL);

        VinResult vinResultPlantCountry = new VinResult();
        vinResultPlantCountry.setValue("USA");
        vinResultPlantCountry.setVariable(PLANT_COUNTRY);

        VinDetails vinDetails = new VinDetails();
        vinDetails.setResults(Arrays.asList(vinResultMake, vinResultModel, vinResultPlantCountry));

        when(restService.getVinDetails(vin)).thenReturn(vinDetails);

        VinDto vinDto = vinDecoderService.getVinDto(vin);

        assertEquals(vinResultMake.getValue(), vinDto.getModel().getMake());
        assertEquals(vinResultPlantCountry.getValue(), vinDto.getPlant().getPlantCountry());
        assertNull(vinDto.getManufacturer());
    }
}