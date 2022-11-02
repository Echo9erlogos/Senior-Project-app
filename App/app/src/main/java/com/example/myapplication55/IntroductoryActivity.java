package com.example.myapplication55;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;


public class IntroductoryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introductory);
        Timer timer=new Timer();
        timer.schedule(new Task(), 3000);

    }
    class Task extends TimerTask{
        @Override
        public void run() {
            startActivity(new Intent(IntroductoryActivity.this, welcome.class));
        }
    }

}