package com.api.zipcode.service;

import com.api.zipcode.service.response.WeatherInfoResponse;

public interface GetWeatherInfoService {

    WeatherInfoResponse getWeatherResponse(String latitude, String longitude);
}