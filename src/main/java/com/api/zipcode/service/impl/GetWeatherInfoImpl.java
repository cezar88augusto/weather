package com.api.zipcode.service.impl;

import com.api.zipcode.service.GetWeatherInfoService;
import com.api.zipcode.service.response.WeatherInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class GetWeatherInfoImpl implements GetWeatherInfoService {

    private final RestTemplate restTemplate;

    @Override
    public WeatherInfoResponse getWeatherResponse(String latitude, String longitude) {
        var url = UriComponentsBuilder.fromHttpUrl("https://api.open-meteo.com/v1/forecast")
                .queryParam("latitude", latitude)
                .queryParam("longitude", longitude)
                .queryParam("current", "temperature_2m")
                .queryParam("daily", "temperature_2m_max,temperature_2m_min")
                .queryParam("timezone", "GMT")
                .toUriString();

        return restTemplate.getForObject(url, WeatherInfoResponse.class);
    }
}
