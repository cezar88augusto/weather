package com.api.zipcode.constants;

public class AppConstants {

    public static class ErrorsConstants {
        public static final String ERROR_GET_ADDRESS = "Error retrieving address information from ZIP code: ";
        public static final String ERROR_GET_WEATHER_INFO = "Error retrieving weather information: ";
    }

    public static class QueriesConstants {
        public static final String LATITUDE = "latitude";
        public static final String LONGITUDE = "longitude ";
        public static final String CURRENT = "current";
        public static final String DAILY = "daily";
        public static final String TIMEZONE = "timezone ";
        public static final String POSTAL_CODE = "postalcode";
        public static final String POLYGON_GEOJSON = "polygon_geojson";
        public static final String FORMAT = "format";
    }
}