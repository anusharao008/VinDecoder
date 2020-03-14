package com.altimetrik.workshop.vin.decoder.service;

import com.altimetrik.workshop.vin.decoder.components.VinDetails;
import com.altimetrik.workshop.vin.decoder.dto.VinDto;
import com.altimetrik.workshop.vin.decoder.error.VinException;
import com.altimetrik.workshop.vin.decoder.mapper.VinMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class VinDecoderService {
    private static final Logger LOGGER = LogManager.getLogger();
    private RestService restService;
    private VinMapper vinMapper;

    public VinDecoderService(RestService restService,
                             VinMapper vinMapper) {
        this.restService = restService;
        this.vinMapper = vinMapper;
    }

    public VinDto getVinDto(String vin) {
        LOGGER.debug("vin {}", vin);
        validate(vin);
        VinDetails vinDetails = restService.getVinDetails(vin);
        return vinMapper.toVinDto(vin, vinDetails);
    }

    private void validate(String vin) {
        if(StringUtils.isEmpty(vin)) {
            throw new VinException(HttpStatus.BAD_REQUEST, "Bad VIN");
        }
    }
}
