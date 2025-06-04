package com.api.zipcode.models;

import com.api.zipcode.models.dto.DailyTemperatureDTO;
import com.api.zipcode.models.dto.MaxMinTemperatureDTO;
import com.api.zipcode.models.dto.WeatherInfoDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class GetMaxAndMinTemperatureTest {

    private static final String DATE_1 = "2025-06-04";
    private static final String DATE_2 = "2025-06-05";
    private static final Double MAX_TEMP_DATE_1 = 30.0;
    private static final Double MIN_TEMP_DATE_1 = 18.0;
    private static final Double MAX_TEMP_DATE_2 = 31.5;
    private static final Double MIN_TEMP_DATE_2 = 19.2;

    @InjectMocks
    private GetMaxAndMinTemperature getMaxAndMinTemperature;

    @Test
    void getMaxAndMinTemperature_successWhenGettingMaxAndMinTemperature_returnsWeatherInfoDTOWithNoDailiesTemperatures() {
        var weatherInfoDTO = mockWeatherInfoDTO(List.of());
        var expectedWeatherInfoDTO = buildEmptydWeatherInfoDTO();

        var actual = getMaxAndMinTemperature.getMaxAndMinTemperature(weatherInfoDTO);
        assertEquals(expectedWeatherInfoDTO, actual);
    }

    @Test
    void getMaxAndMinTemperature_successWhenGettingMaxAndMinTemperature_returnsWeatherInfoDTO() {
        var weatherInfoDTO = mockWeatherInfoDTO(mockDailiesTemperaturesDTO());
        var expectedWeatherInfoDTO = buildExpectedWeatherInfoDTO(mockDailiesTemperaturesDTO());

        var actual = getMaxAndMinTemperature.getMaxAndMinTemperature(weatherInfoDTO);
        assertEquals(expectedWeatherInfoDTO, actual);
    }

    private WeatherInfoDTO mockWeatherInfoDTO(List<DailyTemperatureDTO> dailiesTemperaturesDTO) {
        return WeatherInfoDTO.builder()
                .dailiesTemperatures(dailiesTemperaturesDTO)
                .build();
    }

    private WeatherInfoDTO buildEmptydWeatherInfoDTO() {
        return WeatherInfoDTO.builder()
                .dailiesTemperatures(List.of())
                .build();
    }

    private WeatherInfoDTO buildExpectedWeatherInfoDTO(List<DailyTemperatureDTO> dailiesTemperaturesDTO) {
        return WeatherInfoDTO.builder()
                .minTemperature(mockMaxMinTemperatureDTO(DATE_1, MIN_TEMP_DATE_1))
                .maxTemperature(mockMaxMinTemperatureDTO(DATE_2, MAX_TEMP_DATE_2))
                .dailiesTemperatures(dailiesTemperaturesDTO)
                .build();
    }

    private MaxMinTemperatureDTO mockMaxMinTemperatureDTO(String date, Double temperature) {
        return MaxMinTemperatureDTO.builder()
                .date(date)
                .temperature(temperature)
                .build();
    }

    private List<DailyTemperatureDTO> mockDailiesTemperaturesDTO() {
        return List.of(
                DailyTemperatureDTO.builder()
                        .date(DATE_1)
                        .minTemperature(MIN_TEMP_DATE_1)
                        .maxTemperature(MAX_TEMP_DATE_1)
                        .build(),
                DailyTemperatureDTO.builder()
                        .date(DATE_2)
                        .minTemperature(MIN_TEMP_DATE_2)
                        .maxTemperature(MAX_TEMP_DATE_2)
                        .build()
        );
    }
}