package com.api.zipcode.service;

import com.api.zipcode.controller.response.ZipCodeResponse;

public interface GetZipCodeInfoService {
    ZipCodeResponse getZipCodeResponse(String zipCode);
}