package com.valeriotor.iWanderBackend.model.traveldata;

public class City {
    private final String cityID;
    private final double latitude;
    private final double longitude;
    private final String name;

    private City(String cityID, double latitude, double longitude, String name) {
        this.cityID = cityID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
    }
}
