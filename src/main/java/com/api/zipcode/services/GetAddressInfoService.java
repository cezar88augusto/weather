package com.api.zipcode.services;

import com.api.zipcode.services.response.AddressResponse;

public interface GetAddressInfoService {

    AddressResponse getAddressResponse(String zipCode);
}