package com.api.zipcode.models;

import com.api.zipcode.models.dto.DailyTemperatureDTO;
import com.api.zipcode.models.dto.MaxMinTemperatureDTO;
import com.api.zipcode.models.dto.WeatherInfoDTO;
import com.api.zipcode.services.response.WeatherInfoResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.api.zipcode.services.response.WeatherInfoResponse.Current;
import static com.api.zipcode.services.response.WeatherInfoResponse.Daily;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CreateWeatherInfoResponseTest {

    private static final double CURRENT_TEMPERATURE = 22.5;

    private static final Double LATITUDE = -19.9245;
    private static final Double LONGITUDE = -43.9352;
    private static final Double MAX_TEMP_DATE_1 = 30.0;
    private static final Double MIN_TEMP_DATE_1 = 18.0;
    private static final Double MAX_TEMP_DATE_2 = 31.5;
    private static final Double MIN_TEMP_DATE_2 = 19.2;
    private static final String DATE_1 = "2025-06-04";
    private static final String DATE_2 = "2025-06-05";
    private static final String CURRENT_TIME = "2025-06-04T10:00";
    private static final List<String> DATES = List.of(DATE_1, DATE_2);
    private static final List<Double> MAX_TEMPS = List.of(MAX_TEMP_DATE_1, MAX_TEMP_DATE_2);
    private static final List<Double> MIN_TEMPS = List.of(MIN_TEMP_DATE_1, MIN_TEMP_DATE_2);

    @InjectMocks
    private CreateWeatherInfoResponse createWeatherInfoResponse;

    @Test
    void createWeatherInfoResponse_successWhenCreatingWeatherInfoResponse_returnsWeatherInfoDTO() {
        var weatherInfoResponse = mockWeatherInfoResponse();
        var expected = createExpectedWeatherInfoDTO();

        var actual = createWeatherInfoResponse.createWeatherInfoResponse(weatherInfoResponse);
        assertEquals(expected, actual);
    }

    private WeatherInfoDTO createExpectedWeatherInfoDTO() {
        return WeatherInfoDTO.builder()
                .currentTemperature(CURRENT_TEMPERATURE)
                .dailiesTemperatures(createDailiesTemperaturesDTO())
                .build();
    }

    private List<DailyTemperatureDTO> createDailiesTemperaturesDTO() {
        return List.of(
                DailyTemperatureDTO.builder()
                        .date(DATE_1)
                        .maxTemperature(MAX_TEMP_DATE_1)
                        .minTemperature(MIN_TEMP_DATE_1)
                        .build(),
                DailyTemperatureDTO.builder()
                        .date(DATE_2)
                        .maxTemperature(MAX_TEMP_DATE_2)
                        .minTemperature(MIN_TEMP_DATE_2)
                        .build()
        );
    }

    private Current mockCurrent() {
        return new Current(CURRENT_TIME, CURRENT_TEMPERATURE);
    }

    private Daily mockDaily() {
        return new Daily(DATES, MAX_TEMPS, MIN_TEMPS);
    }

    private WeatherInfoResponse mockWeatherInfoResponse() {
        return new WeatherInfoResponse(LATITUDE, LONGITUDE, mockCurrent(), mockDaily());
    }
}