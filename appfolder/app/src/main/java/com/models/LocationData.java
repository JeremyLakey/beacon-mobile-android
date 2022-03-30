package com.models;

import com.google.android.gms.maps.GoogleMap;

public class LocationData {

    public LocationData(float latitude, float longitude, float direction) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.direction = direction;
    }

    public float longitude;
    public float latitude;
    public float direction;
    public GoogleMap googleMap;

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

    public GoogleMap getGoogleMap() {
        return googleMap;
    }

    public void setGoogleMap(GoogleMap googleMap) {
        this.googleMap = googleMap;
    }
}
