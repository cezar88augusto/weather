package com.api.zipcode.controller;

import com.api.zipcode.controller.dto.WatherInfoDTO;
import com.api.zipcode.service.GetZipCodeInfoService;
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
public class ZipCodeController {

    private final GetZipCodeInfoService zipCodeInfoService;

    @GetMapping
    @Operation(summary = "Pesquisar", description = "Realiza pesquisa de dados a partir do CEP.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucesso.")
    })
    public ResponseEntity<WatherInfoDTO> pesquisar(
            @RequestParam(value = "zipCode", required = true) String zipCode) {

        var zipCodeResponse = zipCodeInfoService.getZipCodeResponse(zipCode);

        return ResponseEntity.ok(null);
    }
}
