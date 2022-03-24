package com.models;

public class LocationData {

    public LocationData(float latitude, float longitude, float direction) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.direction = direction;
    }

    public float longitude;
    public float latitude;
    public float direction;

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getDirection() {
        return direction;
    }

    public void setDirection(float direction) {
        this.direction = direction;
    }
}
