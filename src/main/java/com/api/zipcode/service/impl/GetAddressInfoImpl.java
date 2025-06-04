package com.api.zipcode.service.impl;

import com.api.zipcode.controller.response.AddressResponse;
import com.api.zipcode.service.GetAddressInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class GetAddressInfoImpl implements GetAddressInfoService {

    private final RestTemplate restTemplate;

    @Override
    public AddressResponse getAddressResponse(String zipCode) {
        String url = UriComponentsBuilder.fromHttpUrl("https://nominatim.openstreetmap.org/search")
                .queryParam("postalcode", zipCode)
                .queryParam("polygon_geojson", "1")
                .queryParam("format", "jsonv2")
                .toUriString();

        AddressResponse[] response = restTemplate.getForObject(url, AddressResponse[].class);

        assert response != null;
        return Arrays.stream(response).findFirst().orElse(null);
    }
}
