package com.example.supunnethmal.slide.Forum;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.supunnethmal.slide.Bot.CameraActivity;
import com.example.supunnethmal.slide.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.http.POST;

public class NewPost extends AppCompatActivity {

    ImageView imageView;
    Button addbtn;
    Button submitbtn;
    EditText title;
    EditText tags;
    Spinner spinnercategory;
    DatabaseReference databasePosts;
    Bitmap bitmap;
    String base64;
    String correct;
    String incorrect;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

        imageView = (ImageView) findViewById(R.id.imageView);
        addbtn = (Button) findViewById(R.id.addbtn);
        submitbtn = (Button) findViewById(R.id.submit);
        title = findViewById(R.id.tittle);
        tags = findViewById(R.id.tags);
        spinnercategory = findViewById(R.id.spinnercategory);

        databasePosts = FirebaseDatabase.getInstance().getReference("Posts");


        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CropImage.activity().start(NewPost.this);
            }
        });


        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addposts();

            }
        });


    }

    private void addposts() {
        String postTitle = title.getText().toString().trim();
        String postTags = tags.getText().toString().trim();
        String category = spinnercategory.getSelectedItem().toString();
        correct = "0";
        incorrect = "0";


        if (!TextUtils.isEmpty(postTitle)) {
            if ((!TextUtils.isEmpty(postTags))) {

                String id = databasePosts.push().getKey();
                dataModel post = new dataModel(id, category, postTitle, postTags, base64, correct, incorrect);

                databasePosts.child(id).setValue(post);

                Toast.makeText(this, "Post Successfully Added", Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(getApplicationContext(), "You should add tags", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(getApplicationContext(), "You should enter a Tittle", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();

                imageView.setImageURI(resultUri);

                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), resultUri);

                    new AsyncTask<Void, Void, String>() {
                        @Override
                        protected String doInBackground(Void... voids) {
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                            byte[] b = baos.toByteArray();

                            String encodeImage = Base64.encodeToString(b, Base64.DEFAULT);

                            return encodeImage;
                        }

                        @Override
                        protected void onPostExecute(String s) {
                            base64 = s;
                            Toast.makeText(getApplicationContext(), "Image Load Successful", Toast.LENGTH_SHORT).show();
                        }
                    }.execute();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
                Toast.makeText(this, "" + error, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
