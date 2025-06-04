package com.api.zipcode.models.dto;

import lombok.Builder;

@Builder
public record DailyTemperatureDTO (
        String date,
        Double maxTemperature,
        Double minTemperature
) {
}