package com.example.supunnethmal.slide.Api2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.supunnethmal.slide.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainApi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_api);

        Button submit = findViewById(R.id.buttonSignUp);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.8.100:5000")
                .build();

        final Api api = retrofit.create(Api.class);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText email = findViewById(R.id.editTextEmail);
                String name = email.getText().toString();

                JSONObject json = new JSONObject();
                try {
                    json.put("name",name);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                String s = json.toString();

                //String json = "{\"name\":\"+name+\"}";

                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),s);

                api.lang(requestBody).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {

                            openActivity2(response.body().string().toString());

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });

            }
        });
    }

    public void openActivity2(String content){
        Intent intent = new Intent(this, ShowResponse.class);
        intent.putExtra("EXTRA_TEXT", content);
        startActivity(intent);
    }
}
