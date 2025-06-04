package com.api.zipcode.controller.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AddressResponse(
        String lat,
        String lon
) {
}