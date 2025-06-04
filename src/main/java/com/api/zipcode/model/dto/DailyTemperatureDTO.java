package com.api.zipcode.model.dto;

import lombok.Builder;

@Builder
public record DailyTemperatureDTO (
        String date,
        Double maxTemperature,
        Double minTemperature
) {
}