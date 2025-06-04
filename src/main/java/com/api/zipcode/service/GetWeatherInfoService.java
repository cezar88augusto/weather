package com.api.zipcode.service;

import com.api.zipcode.controller.response.WeatherInfoResponse;

public interface GetWeatherInfoService {

    WeatherInfoResponse getWeatherResponse(String latitude, String longitude);
}