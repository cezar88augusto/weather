package com.api.zipcode.service;

import com.api.zipcode.service.response.AddressResponse;

public interface GetAddressInfoService {

    AddressResponse getAddressResponse(String zipCode);
}