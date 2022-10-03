package com.example.myapplication55;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class IntroductoryActivity extends AppCompatActivity {
    ImageView logo, introback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introductory);

        logo=findViewById(R.id.logo);
        introback=findViewById(R.id.introback);
        logo.animate().translationY(-2600).setDuration(500).setStartDelay(3000);
        introback.animate().translationY(2800).setDuration(500).setStartDelay(3000);
    }

}