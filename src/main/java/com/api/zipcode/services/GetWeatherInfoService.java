package com.api.zipcode.services;

import com.api.zipcode.services.response.WeatherInfoResponse;

public interface GetWeatherInfoService {

    WeatherInfoResponse getWeatherResponse(String latitude, String longitude);
}