package com.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
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
import com.fragments.RadarFragment;

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

        //Initialize fragment
        Fragment fragment = new MapFragment();
        ImageView asd = (ImageView)findViewById(R.id.circle);

        //Open fragment
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_container, fragment)
                .commit();


        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.bounce);
        final Button button = findViewById(R.id.btn_test);
        final ImageView img = findViewById(R.id.img_2);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // start the animation
                img.startAnimation(animation);
            }
        });
    }

    @Override
    public void onLocationChanged(Location location) {
        TextView txtLat = (TextView) findViewById(R.id.test);
        txtLat.setText("Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude());
        //System.out.println("Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude());
        //System.out.println("Bearing: " + location.getBearing() + "\n");
    }
}