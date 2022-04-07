package com.fragments;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.app_folder.R;
import com.models.Beacon;
import com.models.DataCache;

import java.util.Timer;
import java.util.TimerTask;

public class CurrentBeaconFragment extends Fragment {

    private Beacon currentBeacon;
    private CountDownTimer timer;
    public TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Initialize view
        View view = inflater.inflate(R.layout.fragment_select_beacon, container, false);
        this.textView = view.findViewById(R.id.beacon_count_down);
        updateText("This Updates");

        currentBeacon = DataCache.getInstance().currentBeacon;

        new CountDownTimer((int)currentBeacon.getCountDownMilliseconds(), 100) {

            public void onTick(long millisUntilFinished) {
                String clockString = "";
                if (currentBeacon == null)
                {
                    textView.setText("Nully");
                    return;
                }
                int minutes = currentBeacon.countDownMinutes();
                int seconds = currentBeacon.countDownSeconds();
                int hours = currentBeacon.countDownHours();
                if (hours > 0) {
                    clockString = Integer.toString(hours) + ":" + Integer.toString(minutes);
                }
                else {
                    clockString = "0:" + Integer.toString(minutes) + ":" + Integer.toString(seconds);
                }
                textView.setText(clockString);
            }

            public void onFinish() {
                textView.setText("0:00:00");
            }
        }.start();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {


    }

    @Override
    public void onDetach() {
        super.onDetach();
        timer.cancel();
    }

    public void updateText(String text) {
        if (textView == null || text == null || text.equals(textView.getText()))
            return;

        textView.setText(text);
    }



    protected class UpdateCountDown extends TimerTask {

        @Override
        public void run() {
            updateTime();
        }

        private void updateTime() {
            if (currentBeacon == null) {
                updateText("Almost");
                return;
            }
            if(currentBeacon.stillTime()) {
                // useless
            }
            else {
                updateText("0:00:00");
            }
        }
    }
}
