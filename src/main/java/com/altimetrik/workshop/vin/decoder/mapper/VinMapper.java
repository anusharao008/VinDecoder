package com.altimetrik.workshop.vin.decoder.mapper;

import com.altimetrik.workshop.vin.decoder.constants.Constants;
import com.altimetrik.workshop.vin.decoder.components.VinDetails;
import com.altimetrik.workshop.vin.decoder.components.VinResult;
import com.altimetrik.workshop.vin.decoder.dto.Manufacturer;
import com.altimetrik.workshop.vin.decoder.dto.Model;
import com.altimetrik.workshop.vin.decoder.dto.Plant;
import com.altimetrik.workshop.vin.decoder.dto.VinDto;
import com.altimetrik.workshop.vin.decoder.error.VinException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class VinMapper {
    private static final Logger LOGGER = LogManager.getLogger();

    public VinDto toVinDto(String vin, VinDetails vinDetails) {
        this.validateVinDetails(vin, vinDetails);
        VinDto vinDto = new VinDto();
        Plant plant = new Plant();
        Model model = new Model();
        Manufacturer manufacturer = new Manufacturer();
        for (VinResult vinResult : vinDetails.getResults()) {
            if (StringUtils.isEmpty(vinResult.getValue())) {
                continue;
            }
            switch (vinResult.getVariable()) {
                case Constants.MAKE:
                    model.setMake(vinResult.getValue());
                    break;
                case Constants.MODEL:
                    model.setModel(vinResult.getValue());
                    break;
                case Constants.MODEL_YEAR:
                    model.setModelYear(vinResult.getValue());
                    break;
                case Constants.PLANT_COMPANY_NAME:
                    plant.setPlantCompanyName(vinResult.getValue());
                    break;
                case Constants.PLANT_CITY:
                    plant.setPlantCity(vinResult.getValue());
                    break;
                case Constants.PLANT_STATE:
                    plant.setPlantState(vinResult.getValue());
                    break;
                case Constants.PLANT_COUNTRY:
                    plant.setPlantCountry(vinResult.getValue());
                    break;
                case Constants.MANUFACTURER_ID:
                    manufacturer.setManufacturerId(vinResult.getValue());
                    break;
                case Constants.MANUFACTURER_NAME:
                    manufacturer.setManufacturerName(vinResult.getValue());
                    break;
                default:
                    break;
            }
        }

        if (model.toString() != null) vinDto.setModel(model);
        if (manufacturer.toString() != null) vinDto.setManufacturer(manufacturer);
        if (plant.toString() != null) vinDto.setPlant(plant);
        vinDto.setVin(vin);
        return vinDto;
    }

    private void validateVinDetails(String vin, VinDetails vinDetails) {
        if (vinDetails.getResults().stream()
                .anyMatch(vinResult -> Constants.ERROR_CODE.equals(vinResult.getVariable()) && !StringUtils.isEmpty(vinResult.getValue()))) {
            if (vinDetails.getResults().stream()
                    .anyMatch(vinResult -> Constants.MAKE.equals(vinResult.getVariable()) && StringUtils.isEmpty(vinResult.getValue()))) {

                LOGGER.error("Error decoding VIN {}", vin);
                throw this.toVinException(vinDetails);
            }
        }
    }

    private VinException toVinException(VinDetails vinDetails) {
        VinException vinException = new VinException();
        vinException.setHttpStatus(HttpStatus.BAD_REQUEST);
        for (VinResult vinResult : vinDetails.getResults()) {
            if (StringUtils.isEmpty(vinResult.getVariable())) {
                continue;
            }
            switch (vinResult.getVariable()) {
                case Constants.ERROR_CODE:
                    vinException.setErrorCode(vinResult.getValue());
                    break;
                case Constants.ERROR_TEXT:
                    vinException.setErrorText(vinResult.getValue());
                    break;
                default:
                    break;
            }
        }
        return vinException;
    }
}
