package com.api.zipcode.model.mapper;

import com.api.zipcode.controller.dto.DailyTemperatureDTO;
import com.api.zipcode.controller.dto.WeatherInfoDTO;
import com.api.zipcode.service.response.WeatherInfoResponse;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface WeatherMapper {

    WeatherMapper INSTANCE = Mappers.getMapper(WeatherMapper.class);

    @Mapping(target = "currentTemperature", source = "current.temperature_2m")
    WeatherInfoDTO toWeatherInfoDTO(WeatherInfoResponse weatherInfoResponse);

    @AfterMapping
    default void mapDailyTemperatures(WeatherInfoResponse source, @MappingTarget WeatherInfoDTO target) {
        var dates = source.daily().time();
        var maxTemps = source.daily().temperature_2m_max();
        var minTemps = source.daily().temperature_2m_min();

        List<DailyTemperatureDTO> dailiesTemperatures = new ArrayList<>(dates.size());
        createDailiesTemperatures(dates, dailiesTemperatures, maxTemps, minTemps);

        target.setDailiesTemperatures(dailiesTemperatures);
    }

    private void createDailiesTemperatures(List<String> dates, List<DailyTemperatureDTO> dailiesTemperatures, List<Double> maxTemps, List<Double> minTemps) {
        for (int index = 0; index < dates.size(); index++) {
            dailiesTemperatures.add(createDailyTemperature(dates, maxTemps, minTemps, index));
        }
    }

    private DailyTemperatureDTO createDailyTemperature(List<String> dates, List<Double> maxTemps, List<Double> minTemps, int index) {
        return DailyTemperatureDTO.builder()
                .date(dates.get(index))
                .maxTemperature(maxTemps.get(index))
                .minTemperature(minTemps.get(index))
                .build();
    }
}