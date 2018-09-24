package com.example.supunnethmal.slide.Forum;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.supunnethmal.slide.R;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class post extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton radioButton;
    Button btn;
    Bitmap bitmap;
    ImageView imageView;
    TextView txtComment;

    public static String tittle;
    public static String category;
    public static String tags;
    public static String base64;
    public static String postid;
    public static String correct;
    public static String incorrect;
    int myNum;

    DatabaseReference databasePosts;

    ArrayList<dataModel> listData;
    private ListAdapter mAdapter;
    DatabaseReference databaseArtists;
    FloatingActionMenu floatingActionMenu;
    FloatingActionButton search, add;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        listData = new ArrayList<>();
        databaseArtists = FirebaseDatabase.getInstance().getReference("Posts");

        listView = findViewById(R.id.listView);

        databaseArtists.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                listData.clear();

                for (DataSnapshot artistSnapshot : dataSnapshot.getChildren()) {
                    dataModel artist = artistSnapshot.getValue(dataModel.class);

                    listData.add(artist);
                }

                mAdapter = new ListAdapter(getApplicationContext(), listData);
                listView.setAdapter(mAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        TextView txttitle = (TextView) findViewById(R.id.tittle);
        txttitle.setText(tittle);

        TextView txtcategory = (TextView) findViewById(R.id.category);
        txtcategory.setText(category);

        TextView txttags = (TextView) findViewById(R.id.tags);
        txttags.setText(tags);

        imageView = findViewById(R.id.imageView);
        base64decode();


        radioGroup = findViewById(R.id.radioGroup);
        btn = findViewById(R.id.submit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkButton();
                addposts();

            }
        });
    }

    public ArrayList<dataModel> getData() {

        ArrayList<dataModel> list = new ArrayList<>();


        return list;
    }


    private void addposts() {

        databasePosts = FirebaseDatabase.getInstance().getReference("Posts/" + postid + "/comment");

        txtComment = findViewById(R.id.txtViewComment);
        String comment = txtComment.getText().toString().trim();

        if (!TextUtils.isEmpty(comment)) {

            String id = databasePosts.push().getKey();
            databasePosts.child(id).setValue(comment);

            Toast.makeText(this, "Comment Successfully Added", Toast.LENGTH_LONG).show();
        } else {
            // Toast.makeText(this, "Answer Rated", Toast.LENGTH_LONG).show();
        }

    }

    public void checkButton() {

        if (radioGroup.getCheckedRadioButtonId() == -1) {

            Toast.makeText(getApplicationContext(), "Rate or add a comment", Toast.LENGTH_SHORT).show();

        } else {

            radioButton = findViewById(R.id.Correct);

            if(radioButton.isChecked()){

                databasePosts = FirebaseDatabase.getInstance().getReference("Posts/" + postid);
                databasePosts.child("correct").setValue(Integer.toString(Integer.parseInt(correct.toString())+1));

            }else {

                databasePosts = FirebaseDatabase.getInstance().getReference("Posts/" + postid);
                databasePosts.child("incorrect").setValue(Integer.toString(Integer.parseInt(incorrect.toString())+1));
            }

        }
    }

    public void base64decode() {
        byte[] decodeString = Base64.decode(base64, Base64.DEFAULT);
        Bitmap decoded = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
        imageView.setImageBitmap(decoded);
    }
}

