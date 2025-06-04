package com.api.zipcode.services.impl;

import com.api.zipcode.configurations.EnvironmentConstants;
import com.api.zipcode.exceptions.GetAddressInfoException;
import com.api.zipcode.services.GetAddressInfoService;
import com.api.zipcode.services.response.AddressResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.Optional;

import static com.api.zipcode.constants.AppConstants.ErrorsConstants.ERROR_GET_ADDRESS;
import static com.api.zipcode.constants.AppConstants.QueriesConstants.*;

@Service
@RequiredArgsConstructor
public class GetAddressInfoImpl implements GetAddressInfoService {

    private final RestTemplate restTemplate;
    private final EnvironmentConstants environment;

    @Override
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
                    .orElse(null);

        } catch (Exception exception) {
            throw new GetAddressInfoException(ERROR_GET_ADDRESS + exception.getMessage());
        }
    }
}