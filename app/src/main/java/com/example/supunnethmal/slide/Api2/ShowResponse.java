package com.example.supunnethmal.slide.Api2;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.supunnethmal.slide.R;

import org.json.JSONObject;

public class ShowResponse extends AppCompatActivity {

    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_response);

        linearLayout = findViewById(R.id.linear_layout);


        Intent intent = getIntent();
        if (intent != null ){
            String message = intent.getStringExtra("EXTRA_TEXT");
           // TextView textView = (TextView) findViewById(R.id.ac2text);
           // textView.setText(message);

            try {

                JSONObject obj = new JSONObject(message);

                Log.d("Response type", obj.getString("type").toString());
                Log.d("Response Data", obj.getString("data").toString());
                String[] separated = obj.getString("data").toString().split(",");

                String type = obj.getString("type").toString();

                Toast.makeText(getApplicationContext(),type,Toast.LENGTH_LONG).show();

                generateView(separated);



            } catch (Throwable t) {
                Log.e("My App", "Could not parse malformed JSON: \"" + message + "\"");
            }
        }
    }

    public void generateView(String[] str){

        LinearLayout textLinearLayout = new LinearLayout(this);
        textLinearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.addView(textLinearLayout);

        for(int i = 0; i<str.length; i++){
            TextView textView = new TextView(this);
            textView.setText(str[i]);
            setTextViewAttributes(textView);
            textLinearLayout.addView(textView);

            if(i!=(str.length)-1){
                ImageView image = new ImageView(this);
                image.setLayoutParams(new android.view.ViewGroup.LayoutParams(60,200));
                image.setMaxHeight(10);
                image.setMaxWidth(10);
                image.setBackgroundResource(R.drawable.arrow_down);
                textLinearLayout.addView(image);
            }
        }
    }

    private void setTextViewAttributes(TextView textView) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        params.setMargins(convertDpToPixel(16),
                convertDpToPixel(16),
                0, 0
        );

        textView.setTextColor(Color.BLACK);
        textView.setLayoutParams(params);
    }

    private int convertDpToPixel(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return Math.round(px);
    }
}
