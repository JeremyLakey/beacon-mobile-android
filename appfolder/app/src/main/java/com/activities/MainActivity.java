package com.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.app_folder.R;
import com.fragments.MapFragment;
import com.fragments.NavBarFragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.models.Beacon;
import com.models.DataCache;

import java.util.List;

public class MainActivity extends AppCompatActivity implements LocationListener {

    protected LocationManager locationManager;
    GoogleMap googleMap;
    Marker currentLocationMarker;
    Button createBeaconButton;
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataCache.getInstance();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 0, this);
        }
        catch (Exception err) {
            System.out.println("Error but we cool\n");
        }
        setContentView(R.layout.activity_main);

        //Initialize map fragment
        Fragment fragment = new MapFragment();

        //Initialize Nav Bar Fragment
        Fragment navFragment = new NavBarFragment();


        //Open map fragment
        FragmentManager manager = getSupportFragmentManager();
        manager
                .beginTransaction()
                .replace(R.id.frame_container, fragment)
                .commit();
        manager
                .beginTransaction()
                .replace(R.id.navBar_main_container, navFragment)
                .commit();


        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.bounce);
        //final Button button = findViewById(R.id.btn_test);


        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //Create Beacon Code
        createBeaconButton = findViewById(R.id.create_beacon_button);
        if (DataCache.getInstance().getCurrUserBeacon() != null) {createBeaconButton.setVisibility(View.GONE);}
        createBeaconButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startCreateBeacon();
            }
        });
    }

    @Override
    public void onLocationChanged(Location location) {
        DataCache cache = DataCache.getInstance();
        cache.locationData.setLatitude((float)location.getLatitude());
        cache.locationData.setLongitude((float)location.getLatitude());
        cache.locationData.setDirection(location.getBearing());
        if(this.googleMap == null) {
            if(DataCache.getInstance().locationData.googleMap == null)
                return;
            this.googleMap = DataCache.getInstance().locationData.googleMap;
        }
        if(this.currentLocationMarker != null) {
            currentLocationMarker.remove();
        }
        updateBeacons();
        //MarkerOptions markerOptions = new MarkerOptions();
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        //markerOptions.position(latLng);
        //currentLocationMarker = googleMap.addMarker(markerOptions);

        CameraPosition currentPlace = new CameraPosition.Builder()
                .target(new LatLng(location.getLatitude(), location.getLongitude()))
                .bearing(cache.locationData.direction)
                .zoom(DataCache.getInstance().locationData.zoom)
                .build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(currentPlace));

    }

    public void startCreateBeacon() {
        Intent intent = new Intent(this, CreateBeacon.class);
        startActivity(intent);
    }

    private void updateBeacons() {
        if (googleMap == null)
        {
            googleMap = DataCache.getInstance().getLocationData().googleMap;
            return;
        }
        googleMap.clear();
        List<Beacon> beaconList = DataCache.getInstance().getBeaconList();
        for (Beacon beacon: beaconList) {
            if(!beacon.validateBeacon())
                continue;
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(new LatLng(beacon.latitude, beacon.longitude));
            //markerOptions.title(beacon.title);
            //markerOptions.snippet(beacon.getDescription());
            Marker marker = googleMap.addMarker(markerOptions);
            beacon.setMarkerId(marker.getId());
        }
    }
}