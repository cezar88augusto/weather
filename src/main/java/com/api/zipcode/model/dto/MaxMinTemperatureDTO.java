package com.api.zipcode.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MaxMinTemperatureDTO {

    private String date;
    private Double temperature;
}