package com.example.supunnethmal.slide.Result;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.supunnethmal.slide.Forum.NewPost;
import com.example.supunnethmal.slide.R;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

public class FragmentOutput extends Fragment {
    public FragmentOutput() {
    }

    FloatingActionMenu floatingActionMenu;
    FloatingActionButton add;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_output, container, false);

        floatingActionMenu = (FloatingActionMenu) view.findViewById(R.id.floatingActionMenu);

        add = (FloatingActionButton) view.findViewById(R.id.floatingActionAdd);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), NewPost.class);
                startActivity(intent);
            }
        });


        return view;
    }
}
