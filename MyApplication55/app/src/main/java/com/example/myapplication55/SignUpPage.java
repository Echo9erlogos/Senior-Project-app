package com.example.myapplication55;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.*;

public class SignUpPage extends AppCompatActivity {
    //user information
    String username, password, name;
    String phoneNum, dateOfBirth;
    //User Location Data
    String address, address2, city, zipcode;

    EditText usernameIn, passwordIn, nameIN, phoneNumIn, dobIn, addressIn, address2In, cityIn, zipIn;
    LinearLayout signupButtons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        signupButtons = findViewById(R.id.signup_buttons);
        OnClick onClick = new OnClick();
        for (int i = 0; i < signupButtons.getChildCount(); i++) {
            signupButtons.getChildAt(i).setOnClickListener(onClick);
        }

    }
    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getTag().toString()) {
                case "start":
                    intent = new Intent(SignUpPage.this, MainActivity.class);
                    break;
                default:
                    break;
            }
            if (intent != null) {
                startActivity(intent);
            }else{
                Toast.makeText(SignUpPage.this,"invalid button",Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void getImput(View v) throws IOException{
        nameIN =findViewById(R.id.fullNameInput);
        usernameIn = findViewById(R.id.EmailInput);
        passwordIn =findViewById(R.id.PasswordInput);

        name = nameIN.getText().toString();
        username = usernameIn.getText().toString();
        password = passwordIn.getText().toString();

        //write info into txt file as test
        try{
            PrintStream write = new PrintStream(new File("userInfo.txt"));
            write.println(name);
            write.println(username);
            write.println(password);
        }catch(IOException e){}
    }


}