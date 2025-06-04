package com.api.zipcode.controller;

import com.api.zipcode.models.CreateWeatherInfoResponse;
import com.api.zipcode.models.GetMaxAndMinTemperature;
import com.api.zipcode.models.dto.WeatherInfoDTO;
import com.api.zipcode.services.GetAddressInfoService;
import com.api.zipcode.services.GetWeatherInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("weather")
@RequiredArgsConstructor
public class WeatherController {

    private final GetAddressInfoService getAddressInfoService;
    private final GetWeatherInfoService getWeatherInfoService;
    private final CreateWeatherInfoResponse mapper;
    private final GetMaxAndMinTemperature getMaxAndMinTemperature;

    @GetMapping
    public ResponseEntity<WeatherInfoDTO> getWeatherInformation(@RequestParam(value = "zipCode") String zipCode) {

        var addressResponse = getAddressInfoService.getAddressResponse(zipCode);
        var weatherInfoResponse = getWeatherInfoService.getWeatherResponse(addressResponse.lat(), addressResponse.lon());
        var weatherInfoDTO = mapper.createWeatherInfoResponse(weatherInfoResponse);
        var weatherInfoDTOWithMaxAndMinTemperatures = getMaxAndMinTemperature.getMaxAndMinTemperature(weatherInfoDTO);

        return ResponseEntity.ok(weatherInfoDTOWithMaxAndMinTemperatures);
    }
}