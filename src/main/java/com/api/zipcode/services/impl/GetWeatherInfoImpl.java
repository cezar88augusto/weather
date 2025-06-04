package com.api.zipcode.services.impl;

import com.api.zipcode.configurations.EnvironmentConstants;
import com.api.zipcode.exceptions.GetWeatherInfoException;
import com.api.zipcode.services.GetWeatherInfoService;
import com.api.zipcode.services.response.WeatherInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import static com.api.zipcode.constants.AppConstants.ErrorsConstants.ERROR_GET_WEATHER_INFO;
import static com.api.zipcode.constants.AppConstants.QueriesConstants.*;

@Service
@RequiredArgsConstructor
public class GetWeatherInfoImpl implements GetWeatherInfoService {

    private final RestTemplate restTemplate;
    private final EnvironmentConstants environment;

    @Override
    public WeatherInfoResponse getWeatherResponse(String latitude, String longitude) {
        try {
            var URI = UriComponentsBuilder.fromUriString(environment.getOpenMeteoUrl())
                    .queryParam(LATITUDE, latitude)
                    .queryParam(LONGITUDE, longitude)
                    .queryParam(CURRENT, "temperature_2m")
                    .queryParam(DAILY, "temperature_2m_max,temperature_2m_min")
                    .queryParam(TIMEZONE, "GMT")
                    .build()
                    .toUri();

            return restTemplate.getForObject(URI, WeatherInfoResponse.class);
        } catch (Exception exception) {
            throw new GetWeatherInfoException(ERROR_GET_WEATHER_INFO + exception.getMessage());
        }
    }
}