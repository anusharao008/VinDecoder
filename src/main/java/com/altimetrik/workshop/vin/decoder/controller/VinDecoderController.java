package com.altimetrik.workshop.vin.decoder.controller;

import com.altimetrik.workshop.vin.decoder.service.VinDecoderService;
import com.altimetrik.workshop.vin.decoder.dto.VinDto;
import com.altimetrik.workshop.vin.decoder.error.VinException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/vin")
class VinDecoderController {

    private VinDecoderService vinDecoderService;

    public VinDecoderController(VinDecoderService vinDecoderService) {
        this.vinDecoderService = vinDecoderService;
    }

    @GetMapping("/{vin}")
    public VinDto getVinDetails(@PathVariable String vin) {
        try {
           return vinDecoderService.getVinDto(vin);
        }
        catch (VinException ex) {
            throw new ResponseStatusException(
                    ex.getHttpStatus(), ex.toString());
        }
    }
}
