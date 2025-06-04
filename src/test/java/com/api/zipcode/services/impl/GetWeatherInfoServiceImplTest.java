package com.api.zipcode.services.impl;

import com.api.zipcode.configurations.EnvironmentConstants;
import com.api.zipcode.exceptions.GetWeatherInformationException;
import com.api.zipcode.services.response.AddressResponse;
import com.api.zipcode.services.response.WeatherInfoResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.api.zipcode.services.response.WeatherInfoResponse.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetWeatherInfoServiceImplTest {

    private static final Double LATITUDE = 45.123456;
    private static final Double LONGITUDE = 10.654321;
    private static final String TIME = "2025-06-04";
    private static final Double TEMPERATURE = 15.9;
    private static final String URL = "https://nominatim.openstreetmap.org/search";

    @InjectMocks
    private GetWeatherInfoServiceImpl service;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private EnvironmentConstants environment;

    @BeforeEach
    void setUp() {
        when(environment.getOpenMeteoUrl()).thenReturn(URL);
    }

    @Test
    void getWeatherInfoResponse_successWhenWeatherInfoResponse_returnsWeatherInfoResponse() {
        var expectedAddressResponse = mockWeatherInfoResponse();

        when(restTemplate.getForObject(any(), eq(WeatherInfoResponse.class))).thenReturn(expectedAddressResponse);

        var actual = service.getWeatherResponse(LATITUDE.toString(), LONGITUDE.toString());
        assertEquals(expectedAddressResponse, actual);
    }

    @Test
    void getWeatherInfoResponse_failWhenGettingWeatherInfoResponse_throwsGetWeatherInformationException() {
        var exception = mock(HttpClientErrorException.class);

        when(restTemplate.getForObject(any(), eq(WeatherInfoResponse.class))).thenThrow(exception);

        assertThrows(GetWeatherInformationException.class, () -> service.getWeatherResponse(LATITUDE.toString(), LONGITUDE.toString()));
    }

    private WeatherInfoResponse mockWeatherInfoResponse() {
        return new WeatherInfoResponse(LATITUDE, LONGITUDE, mockCurrent(), mockDaily());
    }

    private Current mockCurrent() {
        return new Current(TIME, TEMPERATURE);
    }

    private Daily mockDaily() {
        return new Daily(List.of(), List.of(), List.of());
    }
}