package com.api.zipcode.model;

import com.api.zipcode.controller.dto.DailyTemperatureDTO;
import com.api.zipcode.controller.dto.MaxMinTemperature;
import com.api.zipcode.controller.dto.WeatherInfoDTO;
import org.springframework.stereotype.Component;

@Component
public class GetMaxAndMinTemperature {

    public WeatherInfoDTO getMaxAndMinTemperature(WeatherInfoDTO weatherInfoDTO) {
        if (weatherInfoDTO.getDailiesTemperatures().isEmpty()) {
            return weatherInfoDTO;
        }

        var max = getFirstTemperature(weatherInfoDTO);
        var min = getFirstTemperature(weatherInfoDTO);

        for (DailyTemperatureDTO dto : weatherInfoDTO.getDailiesTemperatures()) {
            if (dto.maxTemperature() > max.maxTemperature()) {
                max = dto;
            }
            if (dto.minTemperature() < min.minTemperature()) {
                min = dto;
            }
        }

        weatherInfoDTO.setMaxTemperature(createTemperature(max));
        weatherInfoDTO.setMinTemperature(createTemperature(min));

        return weatherInfoDTO;
    }

    private DailyTemperatureDTO getFirstTemperature(WeatherInfoDTO weatherInfoDTO) {
        return weatherInfoDTO.getDailiesTemperatures().stream().findFirst().orElse(null);
    }

    private MaxMinTemperature createTemperature(DailyTemperatureDTO max) {
        return MaxMinTemperature.builder()
                .date(max.date())
                .temperature(max.maxTemperature())
                .build();
    }
}
