package com.example.supunnethmal.slide.Bot;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.supunnethmal.slide.Api2.MainApi;
import com.example.supunnethmal.slide.R;
import com.example.supunnethmal.slide.Result.ResultActivity;


public class FragmentBot extends Fragment {
    public FragmentBot() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_bot, container, false);

        Button btnOpen;
        final Button ston;
        final Button ntos;

        ston = (Button) view.findViewById(R.id.btnston);
        ntos = (Button) view.findViewById(R.id.btnntos);

        btnOpen = (Button) view.findViewById(R.id.btnLewis);
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), ResultActivity.class);
                startActivity(intent);
            }
        });

        btnOpen = (Button) view.findViewById(R.id.btnConversion);
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), CameraActivityWithVision.class);
                startActivity(intent);
            }
        });

        btnOpen = (Button) view.findViewById(R.id.btnEssay);
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), CameraActivityWithVision.class);
                startActivity(intent);
            }
        });

        btnOpen = (Button) view.findViewById(R.id.btnIupac);
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ston.setVisibility(view.VISIBLE);
                ntos.setVisibility(view.VISIBLE);
            }
        });

        btnOpen = (Button) view.findViewById(R.id.btnston);
        ston.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CameraActivity.class);
                startActivity(intent);
            }
        });

        btnOpen = (Button) view.findViewById(R.id.btnntos);
        ntos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CameraActivityWithVision.class);
                startActivity(intent);
            }
        });
        return view;


    }
}
