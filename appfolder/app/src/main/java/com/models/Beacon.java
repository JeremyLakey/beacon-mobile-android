package com.models;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

public class Beacon implements Serializable {
    public float longitude;
    public float latitude;
    public String title;
    public String description;
    public Friend owner;
    public Date timeOut;
    public byte[] imageBytes;

    public Beacon(float latitude, float longitude,  String title, String description, Friend owner, Date timeOut) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.title = title;
        this.description = description;
        this.owner = owner;
        this.timeOut = timeOut;
    }

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

    public Date getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Date timeOut) {
        this.timeOut = timeOut;
    }
}
