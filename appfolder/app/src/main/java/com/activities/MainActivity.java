package com.activities;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.models.Beacon;
import com.models.DataCache;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

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
    // TODO: move this to the map Fragment. Because design principles
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
            Marker marker = googleMap.addMarker(beaconToMarkerOptions(beacon).icon(BitmapDescriptorFactory.fromBitmap(
                    createCustomMarker(this, beacon.owner.getImageUrl(),"Manish"))));
            beacon.setMarkerId(marker.getId());
        }
    }

    private MarkerOptions beaconToMarkerOptions(Beacon beacon) {
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(new LatLng(beacon.latitude, beacon.longitude));
        markerOptions.title(beacon.title);
        markerOptions.snippet(beacon.getDescription());
        return markerOptions;
    }

    public static Bitmap createCustomMarker(Context context, @DrawableRes int resource, String _name) {

        View marker = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_marker_layout, null);

        CircleImageView markerImage = (CircleImageView) marker.findViewById(R.id.user_dp);
        markerImage.setImageResource(resource);
        TextView txt_name = (TextView)marker.findViewById(R.id.name);
        txt_name.setText(_name);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        marker.setLayoutParams(new ViewGroup.LayoutParams(52, ViewGroup.LayoutParams.WRAP_CONTENT));
        marker.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
        marker.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        marker.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(marker.getMeasuredWidth(), marker.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        marker.draw(canvas);

        return bitmap;
    }
}