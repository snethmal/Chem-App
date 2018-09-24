package com.example.supunnethmal.slide.Bot;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.supunnethmal.slide.R;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.IOException;

public class CameraActivityWithVision extends AppCompatActivity {

    ImageView imageView;
    Button selectBtn;
    Button Submitbtn;
    Bitmap bitmap;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_vision);

        imageView = (ImageView) findViewById(R.id.imageView);

        textView = (TextView) findViewById(R.id.textView);

        selectBtn = (Button) findViewById(R.id.selectbtn);
        Submitbtn = (Button) findViewById(R.id.convertbtn);
        //ettext = findViewById(R.id.ettext);

        selectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CropImage.activity().start(CameraActivityWithVision.this);



            }
        });

        Submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //getTextFromImage();
                //User user = new User("cnv",textView.getText().toString());
                //sendText(user);
            }
        });


    }
/*
    private void sendText(User user) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.4:5000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        final UserClient client = retrofit.create(UserClient.class);
        Call<User> call = client.analyzeText(user);


        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                Toast.makeText(getApplicationContext(),""+response.body().getMsg_rtrn(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"error"+t.getMessage(),Toast.LENGTH_LONG).show();
                Log.e("error",""+t.getMessage());
            }
        });
    }*/

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
                    getTextFromImage();
                    Submitbtn.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.VISIBLE);


                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
                Toast.makeText(this, "" + error, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void getTextFromImage() {

        TextRecognizer textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();

        if (!textRecognizer.isOperational()) {
            Toast.makeText(getApplicationContext(), "Could not get the Text", Toast.LENGTH_SHORT).show();
        } else {
            Frame frame = new Frame.Builder().setBitmap(bitmap).build();

            SparseArray<TextBlock> items = textRecognizer.detect(frame);

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < items.size(); ++i) {
                TextBlock myItem = items.valueAt(i);
                sb.append(myItem.getValue());

            }

            textView.setText(sb.toString());


        }
    }
}
