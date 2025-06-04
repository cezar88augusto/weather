package com.api.zipcode.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class WeatherInfoDTO {

    private double currentTemperature;
    private MaxMinTemperature maxTemperature;
    private MaxMinTemperature minTemperature;
    private List<DailyTemperatureDTO> dailiesTemperatures;
}