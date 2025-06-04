package com.api.zipcode.models;

import com.api.zipcode.models.dto.WeatherInfoDTO;
import com.api.zipcode.models.mapper.WeatherMapper;
import com.api.zipcode.services.response.WeatherInfoResponse;
import org.springframework.stereotype.Component;

@Component
public class CreateWeatherInfoResponse {

    public WeatherInfoDTO createWeatherInfoResponse(WeatherInfoResponse weatherInfoResponse) {
        return WeatherMapper.INSTANCE.toWeatherInfoDTO(weatherInfoResponse);
    }
}