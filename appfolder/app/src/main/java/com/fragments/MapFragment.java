package com.fragments;

import android.animation.ValueAnimator;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.example.app_folder.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.models.Beacon;
import com.models.DataCache;

import java.nio.channels.DatagramChannel;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapFragment extends Fragment {

    protected LocationManager locationManager;

    ImageView radarPulse;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        //Initialize view
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        //Initialize map fragment
        SupportMapFragment supportMapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.google_map);

        radarPulse = view.findViewById(R.id.radarPulse);




        //Async map
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                //When map is loaded
                DataCache cache = DataCache.getInstance();
                cache.locationData.setGoogleMap(googleMap);
                List<Beacon> beaconList = cache.getBeaconList();
                for (Beacon beacon: beaconList) {
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(new LatLng(beacon.latitude, beacon.longitude));
                    markerOptions.title(beacon.title);
                    markerOptions.snippet(beacon.getDescription());
                    googleMap.addMarker(markerOptions);
                }

                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(@NonNull LatLng latLng) {
                        //when clicked on map
                        //Initialize marker options
                        MarkerOptions markerOptions = new MarkerOptions();
                        //Set position of marker
                        markerOptions.position(latLng);
                        //Set title of marker
                        markerOptions.title(latLng.latitude + " : " + latLng.longitude);


                        //Add marker on Map
                    }
                });
            }
        });

        rotate(radarPulse, getActivity().getApplicationContext());

        //Return View
        return view;
    }



    private void rotate(ImageView view, Context context) {

        RotateAnimation rotate = new RotateAnimation(0, 360,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 1.04f);
        rotate.setRepeatCount(Animation.INFINITE);

        rotate.setInterpolator(new LinearInterpolator());



        rotate.setDuration(5000);

        view.startAnimation(rotate);
    }
}