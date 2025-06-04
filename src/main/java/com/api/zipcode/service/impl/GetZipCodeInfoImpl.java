package com.api.zipcode.service.impl;

import com.api.zipcode.controller.response.ZipCodeResponse;
import com.api.zipcode.service.GetZipCodeInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class GetZipCodeInfoImpl implements GetZipCodeInfoService {

    private final RestTemplate restTemplate;

    @Override
    public ZipCodeResponse getZipCodeResponse(String zipCode) {
        String url = UriComponentsBuilder.fromHttpUrl("https://nominatim.openstreetmap.org/search")
                .queryParam("postalcode", zipCode)
                .queryParam("polygon_geojson", "1")
                .queryParam("format", "jsonv2")
                .toUriString();

        ZipCodeResponse[] response = restTemplate.getForObject(url, ZipCodeResponse[].class);

        assert response != null;
        return Arrays.stream(response).findFirst().orElse(null);
    }
}
