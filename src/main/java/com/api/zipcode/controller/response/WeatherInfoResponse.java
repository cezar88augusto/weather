package com.api.zipcode.controller.response;

import java.util.List;

public record WeatherInfoResponse(
        double latitude,
        double longitude,
        Current current,
        Daily daily
) {
    public record Current(
            String time,
            double temperature_2m
    ) {
    }

    public record Daily(
            List<String> time,
            List<Double> temperature_2m_max,
            List<Double> temperature_2m_min
    ) {
    }
}