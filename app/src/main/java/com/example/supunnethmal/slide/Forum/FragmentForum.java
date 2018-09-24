package com.example.supunnethmal.slide.Forum;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.supunnethmal.slide.Bot.CameraActivityWithVision;
import com.example.supunnethmal.slide.MainActivity;
import com.example.supunnethmal.slide.R;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FragmentForum extends Fragment {

    ArrayList<dataModel> listData;
    private ListAdapter mAdapter;
    DatabaseReference databaseArtists;
    FloatingActionMenu floatingActionMenu;
    FloatingActionButton search, add;
    ListView listView;

    public FragmentForum() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listData = new ArrayList<>();
        databaseArtists = FirebaseDatabase.getInstance().getReference("Posts");

        listView = getActivity().findViewById(R.id.listView);

        databaseArtists.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                listData.clear();

                for (DataSnapshot artistSnapshot : dataSnapshot.getChildren()) {
                    dataModel artist = artistSnapshot.getValue(dataModel.class);

                    listData.add(artist);
                }

                mAdapter = new ListAdapter(getActivity(), listData);
                listView.setAdapter(mAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listView = listView.findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dataModel d = new dataModel();
                dataModel posts = listData.get(position);
                Intent intent = new Intent(getActivity(), post.class);

                post.tittle = posts.postTittle;
                post.category = posts.postType;
                post.tags = posts.postTags;
                post.base64 = posts.base64;
                post.postid = posts.postid;
                post.correct = posts.correct;
                post.incorrect = posts.incorrect;

                startActivity(intent);

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forum, container, false);

        floatingActionMenu = (FloatingActionMenu) view.findViewById(R.id.floatingActionMenu);
        search = (FloatingActionButton) view.findViewById(R.id.floatingActionSearch);
        add = (FloatingActionButton) view.findViewById(R.id.floatingActionAdd);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getContext(), "Search", Toast.LENGTH_SHORT).show();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), NewPost.class);
                startActivity(intent);
            }
        });


        return view;
    }

    public ArrayList<dataModel> getData() {

        ArrayList<dataModel> list = new ArrayList<>();


        return list;
    }


}
