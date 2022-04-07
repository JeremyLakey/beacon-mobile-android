package com.models;

import java.io.Serializable;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Beacon implements Serializable {
    public float longitude;
    public float latitude;
    public String title;
    public String description;
    public String markerId;
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

    public long getCountDownMilliseconds() {
        Calendar calendar = new GregorianCalendar();
        return this.timeOut.getTime() - calendar.getTime().getTime();
    }

    public Boolean stillTime() {
        if (getCountDownMilliseconds() > 0)
            return true;
        return false;
    }

    public int countDownSeconds() {
        long difference = getCountDownMilliseconds();
        return (int) (difference / 1000) % 60 ;
    }

    public int countDownMinutes() {
        long difference = getCountDownMilliseconds();
        return (int) (difference / (1000 * 60)) % 60 ;
    }

    public int countDownHours() {
        long difference = getCountDownMilliseconds();
        return (int) (difference / (1000 * 3600)) % 24 ;
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

    public String getMarkerId() {
        return markerId;
    }

    public void setMarkerId(String markerId) {
        this.markerId = markerId;
    }
}
