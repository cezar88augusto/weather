package com.api.zipcode.model;

import com.api.zipcode.controller.dto.WeatherInfoDTO;
import com.api.zipcode.controller.response.WeatherInfoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WeatherMapper {

    WeatherMapper INSTANCE = Mappers.getMapper(WeatherMapper.class);

    @Mapping(target = "currentTemperature", source = "weatherInfoResponse.current.temperature_2m")
    WeatherInfoDTO toWeatherInfoDTO(WeatherInfoResponse weatherInfoResponse);
}