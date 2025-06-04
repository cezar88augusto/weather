package com.api.zipcode.service;

import com.api.zipcode.controller.response.AddressResponse;

public interface GetAddressInfoService {
    AddressResponse getAddressResponse(String zipCode);
}