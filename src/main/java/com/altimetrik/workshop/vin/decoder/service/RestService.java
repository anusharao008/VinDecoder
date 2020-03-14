package com.altimetrik.workshop.vin.decoder.service;

import com.altimetrik.workshop.vin.decoder.components.VinDetails;
import com.altimetrik.workshop.vin.decoder.error.VinException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class RestService {

    @Value("${vin.json.url}")
    private String vinJsonUrl;

    private final RestTemplate restTemplate;

    public RestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public VinDetails getVinDetails(String vin) throws VinException {
        String url = vinJsonUrl+vin;
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("format", "json");
        ResponseEntity<VinDetails> response = this.restTemplate.getForEntity(builder.toUriString(), VinDetails.class);

        if(response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }
        throw getDefaultException();
    }

    private VinException getDefaultException() {
        return new VinException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to get VIN details, please try again later");
    }
}
