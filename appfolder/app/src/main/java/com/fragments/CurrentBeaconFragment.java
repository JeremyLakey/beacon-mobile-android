package com.fragments;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.app_folder.R;
import com.models.Beacon;
import com.models.DataCache;

import java.util.Timer;
import java.util.TimerTask;

public class CurrentBeaconFragment extends Fragment {

    private Beacon currentBeacon;
    private CountDownTimer timer;
    public TextView textView;
    private CurrentBeaconFragment thisFrag;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        thisFrag = this;
        currentBeacon = DataCache.getInstance().currentBeacon;
        Button playButton = (Button) getActivity().findViewById(R.id.create_beacon_button);
        playButton.setVisibility(View.INVISIBLE);
        //Initialize view
        View view = inflater.inflate(R.layout.fragment_select_beacon, container, false);
        this.textView = view.findViewById(R.id.beacon_count_down);
        TextView titleText = view.findViewById(R.id.beacon_title);
        titleText.setText(currentBeacon.title);
        TextView descText = view.findViewById(R.id.beacon_desc);
        titleText.setText(currentBeacon.description);
        TextView nameText = view.findViewById(R.id.beacon_user_name);
        nameText.setText(currentBeacon.owner.userName);
        ImageView imageView = view.findViewById(R.id.current_beacon_image);
        if (imageView != null && currentBeacon.owner != null)
            imageView.setImageResource(currentBeacon.owner.image);


        if(currentBeacon.stillTime())
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
                    clockString += Integer.toString(hours);
                }
                else {
                    clockString += "0:";
                }
                if (minutes > 0) {
                    String add = Integer.toString(minutes) + ":";
                    if (add.length() == 2) {
                        add = "0" + add;
                    }
                    clockString += add;
                }
                else {
                    clockString += "00:";
                }
                if (seconds > 0) {
                    String add = Integer.toString(seconds);
                    if (add.length() == 1) {
                        add = "0" + add;
                    }
                    clockString += add;
                }
                else {
                    clockString += "00";
                }
                textView.setText(clockString);
            }

            public void onFinish() {
                textView.setText("0:00:00");
                if(thisFrag != null)
                    getActivity().getSupportFragmentManager().beginTransaction().remove(thisFrag).commit();
            }
        }.start();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {


    }

    @Override
    public void onDetach() {
        Button playButton = (Button) getActivity().findViewById(R.id.create_beacon_button);
        playButton.setVisibility(View.VISIBLE);
        super.onDetach();
        thisFrag = null;
        if (timer != null)
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
