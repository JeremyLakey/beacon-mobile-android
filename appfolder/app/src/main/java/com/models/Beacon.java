package com.models;

import java.io.Serializable;
import java.sql.Time;

public class Beacon implements Serializable {
    public float longitude;
    public float laditude;
    public String title;
    public String description;
    public Friend owner;
    public Time timeOut;
    public byte[] imageBytes;

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLaditude() {
        return laditude;
    }

    public void setLaditude(float laditude) {
        this.laditude = laditude;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Friend getOwner() {
        return owner;
    }

    public void setOwner(Friend owner) {
        this.owner = owner;
    }

    public Time getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Time timeOut) {
        this.timeOut = timeOut;
    }
}
