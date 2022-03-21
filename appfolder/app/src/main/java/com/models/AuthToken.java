package com.models;

import java.io.Serializable;
import java.sql.Time;

public class AuthToken implements Serializable {

    public String uniqueId;
    public Time timeOut;


    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public Time getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Time timeOut) {
        this.timeOut = timeOut;
    }
}
