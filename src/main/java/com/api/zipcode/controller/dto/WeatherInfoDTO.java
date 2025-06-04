package com.api.zipcode.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class WeatherInfoDTO {

    private double currentTemperature;
    private MaxMinTemperatureDTO maxTemperature;
    private MaxMinTemperatureDTO minTemperature;
    private List<DailyTemperatureDTO> dailiesTemperatures;
}