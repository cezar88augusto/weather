package com.api.zipcode.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WeatherInfoDTO {

    private double currentTemperature;
}