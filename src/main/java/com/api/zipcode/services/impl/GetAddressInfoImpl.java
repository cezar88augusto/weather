package com.api.zipcode.services.impl;

import com.api.zipcode.configurations.EnvironmentConstants;
import com.api.zipcode.services.GetAddressInfoService;
import com.api.zipcode.services.response.AddressResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class GetAddressInfoImpl implements GetAddressInfoService {

    private final RestTemplate restTemplate;
    private final EnvironmentConstants environment;

    @Override
    public AddressResponse getAddressResponse(String zipCode) {
        var URI = UriComponentsBuilder.fromUriString(environment.getOpenStreetUrl())
                .queryParam("postalcode", zipCode)
                .queryParam("polygon_geojson", "1")
                .queryParam("format", "jsonv2")
                .build()
                .toUri();

        var response = restTemplate.getForObject(URI, AddressResponse[].class);

        assert response != null;
        return Arrays.stream(response).findFirst().orElse(null);
    }
}