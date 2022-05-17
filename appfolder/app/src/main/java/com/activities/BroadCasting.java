package com.activities;

import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_folder.R;

import java.util.Timer;
import java.util.TimerTask;

public class BroadCasting extends AppCompatActivity {

    Timer timer;
    ImageView broadCastView;
    AnimatedVectorDrawable animation;



    @Override
    protected void onStop() {
        super.onStop();
        timer.cancel();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_broad_casting);
        broadCastView = findViewById(R.id.broad_casting_animation);
        broadCastView.setBackgroundResource(R.drawable.broad_cast_pulse);
        animation = (AnimatedVectorDrawable) broadCastView.getBackground();
        animation.start();


        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
                                      @Override
                                      public void run() {
                                          // Magic here
                                          broadCastView.setBackgroundResource(R.drawable.broad_cast_pulse);
                                          animation = (AnimatedVectorDrawable) broadCastView.getBackground();
                                          animation.start();
                                      }
                                  },
                0, 2200);
    }

}
