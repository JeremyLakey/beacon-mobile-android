package com.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.app_folder.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RadarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RadarFragment extends Fragment {

    ImageView circle;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_radar, container, false);
        circle =(ImageView)view.findViewById(R.id.circle);

        Animation rotate = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate);
        Animation bounce = AnimationUtils.loadAnimation(getActivity(), R.anim.bounce);

        circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println("Attempting to bounce");
                circle.startAnimation(bounce);
            }
        });


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {




    }
}