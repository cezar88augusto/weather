package com.api.zipcode.exceptions;

public class GetWeatherInfoException extends RuntimeException {

    public GetWeatherInfoException(String message) {
        super(message);
    }
}