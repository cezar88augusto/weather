package com.api.zipcode.model;

import com.api.zipcode.controller.dto.WeatherInfoDTO;
import com.api.zipcode.service.response.WeatherInfoResponse;
import com.api.zipcode.model.mapper.WeatherMapper;
import org.springframework.stereotype.Component;

@Component
public class CreateWeatherInfoResponse {

    public WeatherInfoDTO createWeatherInfoResponse(WeatherInfoResponse weatherInfoResponse) {
        return WeatherMapper.INSTANCE.toWeatherInfoDTO(weatherInfoResponse);
    }
}