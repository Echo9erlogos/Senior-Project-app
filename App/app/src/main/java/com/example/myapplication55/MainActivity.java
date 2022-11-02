package com.example.myapplication55;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    LinearLayout welcomePageButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PrefManager prefManager = new PrefManager(getApplicationContext());
        if(prefManager.isFirstTimeLaunch()){
            prefManager.setFirstTimeLaunch(false);
            startActivity(new Intent(MainActivity.this, welcome.class));
            finish();
        }
        welcomePageButtons = findViewById(R.id.welcome_page_buttons);

        OnClick onClick = new OnClick();

        for (int i = 0; i < welcomePageButtons.getChildCount(); i++) {
            welcomePageButtons.getChildAt(i).setOnClickListener(onClick);
        }
    }


    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getTag().toString()) {
                case "login":
                    intent = new Intent(MainActivity.this, LoginPage.class);
                    break;
                case "start":
                    intent = new Intent(MainActivity.this, SignUpPage.class);
                    break;
                default:
                    break;
            }
            if (intent != null) {
                startActivity(intent);
            }else{
                Toast.makeText(MainActivity.this,"invalid button",Toast.LENGTH_SHORT).show();
            }
        }
    }
}