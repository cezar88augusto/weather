package com.api.zipcode.services.impl;

import com.api.zipcode.configurations.EnvironmentConstants;
import com.api.zipcode.exceptions.GetWeatherInformationException;
import com.api.zipcode.services.GetAddressInfoService;
import com.api.zipcode.services.response.AddressResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.Optional;

import static com.api.zipcode.constants.AppConstants.ErrorsConstants.ERROR_GET_ADDRESS;
import static com.api.zipcode.constants.AppConstants.ErrorsConstants.ERROR_GET_ADDRESS_NOT_FOUND;
import static com.api.zipcode.constants.AppConstants.QueriesConstants.*;

@Service
@RequiredArgsConstructor
public class GetAddressInfoServiceImpl implements GetAddressInfoService {

    private final RestTemplate restTemplate;
    private final EnvironmentConstants environment;

    @Override
    @Cacheable(value = "addressCache", key = "#zipCode")
    public AddressResponse getAddressResponse(String zipCode) {
        try {
            var URI = UriComponentsBuilder.fromUriString(environment.getOpenStreetUrl())
                    .queryParam(POSTAL_CODE, zipCode)
                    .queryParam(POLYGON_GEOJSON, "1")
                    .queryParam(FORMAT, "jsonv2")
                    .build()
                    .toUri();

            var response = restTemplate.getForObject(URI, AddressResponse[].class);

            return Optional.ofNullable(response)
                    .flatMap(addressResponses -> Arrays.stream(addressResponses).findFirst())
                    .orElseThrow(() -> new GetWeatherInformationException(ERROR_GET_ADDRESS_NOT_FOUND + zipCode));

        } catch (HttpClientErrorException exception) {
            throw new GetWeatherInformationException(ERROR_GET_ADDRESS + exception.getMessage());
        }
    }
}