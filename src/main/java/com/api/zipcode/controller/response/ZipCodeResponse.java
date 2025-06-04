package com.api.zipcode.controller.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ZipCodeResponse(
        String lat,
        String lon
) {
}