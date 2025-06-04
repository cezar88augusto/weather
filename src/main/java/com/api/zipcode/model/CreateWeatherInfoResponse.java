package com.api.zipcode.model;

import com.api.zipcode.model.dto.WeatherInfoDTO;
import com.api.zipcode.model.mapper.WeatherMapper;
import com.api.zipcode.service.response.WeatherInfoResponse;
import org.springframework.stereotype.Component;

@Component
public class CreateWeatherInfoResponse {

    public WeatherInfoDTO createWeatherInfoResponse(WeatherInfoResponse weatherInfoResponse) {
        return WeatherMapper.INSTANCE.toWeatherInfoDTO(weatherInfoResponse);
    }
}