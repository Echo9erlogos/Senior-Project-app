package com.example.myapplication55;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class TherapistProfilePage extends AppCompatActivity {
    Button button=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therapist_profile_page);
        button=(Button)findViewById(R.id.edit);
        button.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(TherapistProfilePage.this,EditTherapistPrifilePage.class);
            startActivity(intent);
        });
    }
}