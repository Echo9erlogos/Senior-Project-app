package com.example.myapplication55;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
    public void signOut(View view) {
        // [START auth_sign_out]
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(HomeActivity.this, MainActivity.class));
        // [END auth_sign_out]
    }
}
