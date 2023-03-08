package com.example.myapplication55;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Check_Email_Verification extends AppCompatActivity {

private String PhoneNum;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_email_verification);
        PhoneNum = getIntent().getStringExtra("Phone Number");
    }

    public void verifyEmail(View view){
        Intent intent = null;

        intent = new Intent(Check_Email_Verification.this, Multifactor_ActivityPage.class);
        intent.putExtra("Phone Number",PhoneNum);
        startActivity(intent);
    }
}
