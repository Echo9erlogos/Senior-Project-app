package com.example.myapplication55;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class EditTherapistPrifilePage extends AppCompatActivity {
    Button button=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_therapist_prifile_page);
        button=(Button)findViewById(R.id.save);
        button.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(EditTherapistPrifilePage.this, TherapistProfilePage.class);
            startActivity(intent);
        });
    }
}