package com.api.zipcode.services.impl;

import com.api.zipcode.configurations.EnvironmentConstants;
import com.api.zipcode.exceptions.GetWeatherInformationException;
import com.api.zipcode.services.response.AddressResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetAddressInfoServiceImplTest {

    private static final String LATITUDE = "45.123456";
    private static final String LONGITUDE = "10.654321";
    private static final String ZIP_CODE = "123456789";
    private static final String URL = "https://api.open-meteo.com/v1/forecast";

    @InjectMocks
    private GetAddressInfoServiceImpl service;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private EnvironmentConstants environment;

    @BeforeEach
    void setUp() {
        when(environment.getOpenStreetUrl()).thenReturn(URL);
    }

    @Test
    void getAddressResponse_successWhenGettingAddressInfo_returnsAddressResponse() {
        var expectedAddressResponse = mockAddressResponse();
        var responseArray = mockAddressResponses(expectedAddressResponse);

        when(restTemplate.getForObject(any(), eq(AddressResponse[].class))).thenReturn(responseArray);

        var actual = service.getAddressResponse(ZIP_CODE);
        assertEquals(expectedAddressResponse, actual);
    }

    @Test
    void getAddressResponse_failWhenAddressListIsEmpty_throwsGetWeatherInformationException() {
        when(restTemplate.getForObject(any(), eq(AddressResponse[].class))).thenReturn(new AddressResponse[0]);

        assertThrows(GetWeatherInformationException.class, () -> service.getAddressResponse(ZIP_CODE));
    }

    @Test
    void getAddressResponse_failWhenGettingAddressInfo_throwsGetWeatherInformationException() {
        var exception = mock(HttpClientErrorException.class);

        when(restTemplate.getForObject(any(), eq(AddressResponse[].class))).thenThrow(exception);

        assertThrows(GetWeatherInformationException.class, () -> service.getAddressResponse(ZIP_CODE));
    }

    private AddressResponse[] mockAddressResponses(AddressResponse expectedAddressResponse) {
        return new AddressResponse[]{expectedAddressResponse};
    }

    private AddressResponse mockAddressResponse() {
        return new AddressResponse(LATITUDE, LONGITUDE);
    }
}