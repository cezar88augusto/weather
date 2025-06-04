package com.api.zipcode.controller.response;

import lombok.Data;

import java.util.List;

@Data
public class WeatherInfoResponse {

    private double latitude;
    private double longitude;
    private Current current;
    private Daily daily;

    @Data
    public static class Current {
        private String time;
        private double temperature_2m;
    }

    @Data
    public static class Daily {
        private List<String> time;
        private List<Double> temperature_2m_max;
        private List<Double> temperature_2m_min;
    }
}