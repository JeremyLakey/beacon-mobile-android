package com.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.app_folder.R;
import com.fragments.TimePickerFragment;
import com.models.Beacon;
import com.models.DataCache;

import android.app.Dialog;


import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CreateBeacon extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    private TextView countdownText;
    private Button countdownButton;
    private Button setTimeButton;
    private Button createBeaconButton;
    private EditText beaconDesc;

    Date endTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_beacon);


        setTimeButton = findViewById(R.id.set_time_button);
        createBeaconButton = findViewById(R.id.set_beacon_button);
        beaconDesc = findViewById(R.id.beacon_desc);
        endTime = Calendar.getInstance().getTime();



        setTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        createBeaconButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createBeacon();
            }
        });
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String time = "";
        if ((hourOfDay % 12) == 0) {
            time += 12;
        } else {
            time += (hourOfDay % 12);
        }
        time += ":";
        if (minute < 10) time += "0";
        time += minute;
        if (hourOfDay > 11 && hourOfDay != 24) {
            time += " PM";
        } else {
            time += " AM";
        }
        setTimeButton.setText(time);
        endTime.setHours(hourOfDay);
        endTime.setMinutes(minute);
    }



    public void createBeacon() {
        DataCache cache = DataCache.getInstance();
        Beacon createBeacon = new Beacon(cache.locationData.latitude,
                cache.locationData.longitude, beaconDesc.getText().toString(), "I want pie",
                cache.friendList.get(0), endTime);
        cache.beaconList.add(createBeacon);
        Intent i=new Intent(this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}