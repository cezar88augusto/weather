package com.api.zipcode.controller;

import com.api.zipcode.controller.dto.WeatherInfoDTO;
import com.api.zipcode.model.CreateWeatherInfoResponse;
import com.api.zipcode.model.GetMaxAndMinTemperature;
import com.api.zipcode.service.GetAddressInfoService;
import com.api.zipcode.service.GetWeatherInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(description = "Get weather data by ZIP code.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success when getting weather information.")
    })
    public ResponseEntity<WeatherInfoDTO> getWeatherInfo(@RequestParam(value = "zipCode") String zipCode) {

        var addressResponse = getAddressInfoService.getAddressResponse(zipCode);
        var weatherInfoResponse = getWeatherInfoService.getWeatherResponse(addressResponse.lat(), addressResponse.lon());
        var weatherInfoDTO = mapper.createWeatherInfoResponse(weatherInfoResponse);
        var weatherInfoDTOWithMaxAndMinTemperatures = getMaxAndMinTemperature.getMaxAndMinTemperature(weatherInfoDTO);

        return ResponseEntity.ok(weatherInfoDTOWithMaxAndMinTemperatures);
    }
}