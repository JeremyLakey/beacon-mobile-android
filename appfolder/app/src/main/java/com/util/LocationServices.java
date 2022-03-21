package com.util;

import android.content.Context;
import android.location.LocationManager;

public class LocationServices {
    static private LocationManager manager;
    static public void updateLocationManager(LocationManager newManager) {
        manager = newManager;
    }
}
