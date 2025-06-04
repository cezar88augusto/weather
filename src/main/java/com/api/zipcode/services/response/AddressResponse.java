package com.api.zipcode.services.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AddressResponse(
        String lat,
        String lon
) {
}