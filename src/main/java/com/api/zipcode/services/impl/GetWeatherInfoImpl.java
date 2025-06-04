package com.api.zipcode.services.impl;

import com.api.zipcode.configurations.EnvironmentConstants;
import com.api.zipcode.services.GetWeatherInfoService;
import com.api.zipcode.services.response.WeatherInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class GetWeatherInfoImpl implements GetWeatherInfoService {

    private final RestTemplate restTemplate;
    private final EnvironmentConstants environment;

    @Override
    public WeatherInfoResponse getWeatherResponse(String latitude, String longitude) {
        var URI = UriComponentsBuilder.fromUriString(environment.getOpenMeteoUrl())
                .queryParam("latitude", latitude)
                .queryParam("longitude", longitude)
                .queryParam("current", "temperature_2m")
                .queryParam("daily", "temperature_2m_max,temperature_2m_min")
                .queryParam("timezone", "GMT")
                .build()
                .toUri();

        return restTemplate.getForObject(URI, WeatherInfoResponse.class);
    }
}