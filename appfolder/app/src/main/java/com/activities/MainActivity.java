package com.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

import com.example.app_folder.R;

public class MainActivity extends AppCompatActivity implements LocationListener {

    protected LocationManager locationManager;
    protected LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 0, this);
        }
        catch (Exception err) {
            System.out.println("Error but we cool\n");
        }
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onLocationChanged(Location location) {
        TextView txtLat = (TextView) findViewById(R.id.test);
        txtLat.setText("Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude());
        //System.out.println("Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude());
        //System.out.println("Bearing: " + location.getBearing() + "\n");
    }
}