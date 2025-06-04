package com.api.zipcode.models;

import com.api.zipcode.models.dto.DailyTemperatureDTO;
import com.api.zipcode.models.dto.MaxMinTemperatureDTO;
import com.api.zipcode.models.dto.WeatherInfoDTO;
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

        weatherInfoDTO.setMaxTemperature(createMaxTemperature(max));
        weatherInfoDTO.setMinTemperature(createMinTemperature(min));

        return weatherInfoDTO;
    }

    private DailyTemperatureDTO getFirstTemperature(WeatherInfoDTO weatherInfoDTO) {
        return weatherInfoDTO.getDailiesTemperatures().stream()
                .findFirst()
                .orElse(null);
    }

    private MaxMinTemperatureDTO createMaxTemperature(DailyTemperatureDTO dto) {
        return MaxMinTemperatureDTO.builder()
                .date(dto.date())
                .temperature(dto.maxTemperature())
                .build();
    }

    private MaxMinTemperatureDTO createMinTemperature(DailyTemperatureDTO dto) {
        return MaxMinTemperatureDTO.builder()
                .date(dto.date())
                .temperature(dto.minTemperature())
                .build();
    }
}