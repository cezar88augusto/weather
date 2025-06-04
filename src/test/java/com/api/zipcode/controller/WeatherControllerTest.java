package com.api.zipcode.controller;

import com.api.zipcode.exceptions.GetWeatherInformationException;
import com.api.zipcode.models.CreateWeatherInfoResponse;
import com.api.zipcode.models.GetMaxAndMinTemperature;
import com.api.zipcode.models.dto.WeatherInfoDTO;
import com.api.zipcode.services.GetAddressInfoService;
import com.api.zipcode.services.GetWeatherInfoService;
import com.api.zipcode.services.response.AddressResponse;
import com.api.zipcode.services.response.WeatherInfoResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WeatherControllerTest {

    private static final String ZIP_CODE = "12345678";
    private static final String LAT = "45.000000";
    private static final String LON = "12.000000";
    private static final String ERROR_MESSAGE = "Address not found for this zip code: 12345678";

    @InjectMocks
    private WeatherController controller;

    @Mock
    private GetAddressInfoService getAddressInfoService;

    @Mock
    private GetWeatherInfoService getWeatherInfoService;

    @Mock
    private CreateWeatherInfoResponse mapper;

    @Mock
    private GetMaxAndMinTemperature getMaxAndMinTemperature;

    @Test
    void getWeatherInformation_success_returnsWeatherInfoDTO() {
        var addressResponse = new AddressResponse(LAT, LON);
        var weatherResponse = mock(WeatherInfoResponse.class);
        var weatherDTO = mock(WeatherInfoDTO.class);
        var weatherWithMaxMin = mock(WeatherInfoDTO.class);

        when(getAddressInfoService.getAddressResponse(ZIP_CODE)).thenReturn(addressResponse);
        when(getWeatherInfoService.getWeatherResponse(LAT, LON)).thenReturn(weatherResponse);
        when(mapper.createWeatherInfoResponse(weatherResponse)).thenReturn(weatherDTO);
        when(getMaxAndMinTemperature.getMaxAndMinTemperature(weatherDTO)).thenReturn(weatherWithMaxMin);

        var actual = controller.getWeatherInformation(ZIP_CODE);
        assertAll(
                () -> assertEquals(ResponseEntity.ok(weatherWithMaxMin), actual),
                () -> verify(getAddressInfoService, times(1)).getAddressResponse(ZIP_CODE),
                () -> verify(getWeatherInfoService, times(1)).getWeatherResponse(LAT, LON)
        );
    }

    @Test
    void getWeatherInformation_failWhenGettingWeatherInformation_returnsUnprocessableEntity() {
        when(getAddressInfoService.getAddressResponse(ZIP_CODE))
                .thenThrow(new GetWeatherInformationException(ERROR_MESSAGE));

        var actual = controller.getWeatherInformation(ZIP_CODE);

        assertEquals(ResponseEntity.unprocessableEntity().body(ERROR_MESSAGE), actual);
    }
}