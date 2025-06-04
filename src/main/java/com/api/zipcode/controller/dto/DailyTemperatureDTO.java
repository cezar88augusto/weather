package com.api.zipcode.controller.dto;

import lombok.Builder;

@Builder
public record DailyTemperatureDTO (
        String date,
        String maxTemperature,
        String minTemperature
) {
}