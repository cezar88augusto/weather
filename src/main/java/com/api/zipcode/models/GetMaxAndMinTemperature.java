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

        var maxTemperature = getFirstTemperature(weatherInfoDTO);
        var minTemperature = getFirstTemperature(weatherInfoDTO);

        for (DailyTemperatureDTO dailyTemperatureDTO : weatherInfoDTO.getDailiesTemperatures()) {
            if (dailyTemperatureDTO.maxTemperature() > maxTemperature.maxTemperature()) {
                maxTemperature = dailyTemperatureDTO;
            }
            if (dailyTemperatureDTO.minTemperature() < minTemperature.minTemperature()) {
                minTemperature = dailyTemperatureDTO;
            }
        }

        weatherInfoDTO.setMaxTemperature(createMaxTemperature(maxTemperature));
        weatherInfoDTO.setMinTemperature(createMinTemperature(minTemperature));

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