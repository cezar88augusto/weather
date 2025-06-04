package com.api.zipcode.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MaxMinTemperature {

    private String date;
    private Double temperature;
}