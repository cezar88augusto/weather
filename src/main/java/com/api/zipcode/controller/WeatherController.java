package com.api.zipcode.controller;

import com.api.zipcode.controller.dto.WatherInfoDTO;
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

    @GetMapping
    @Operation(description = "Realiza pesquisa de dados climáticos a partir do CEP.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucesso.")
    })
    public ResponseEntity<WatherInfoDTO> getWeatherInfo(
            @RequestParam(value = "zipCode") String zipCode) {

        var addressResponse = getAddressInfoService.getAddressResponse(zipCode);
        var weatherInfo = getWeatherInfoService.getWeatherResponse(addressResponse.lat(), addressResponse.lon());

        return ResponseEntity.ok(WatherInfoDTO.builder()
                .currentTemperature(weatherInfo.current().temperature_2m())
                .build());
    }
}
