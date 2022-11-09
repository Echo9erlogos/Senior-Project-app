package com.example.myapplication55;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Button;
import android.widget.Toast;

public class Signupchoice extends AppCompatActivity {
    ConstraintLayout SignUpChoicePageButtons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupchoice);

        SignUpChoicePageButtons = findViewById(R.id.sign_up_choice_page_buttons);

        Signupchoice.OnClick onClick = new Signupchoice.OnClick();

        for (int i = 0; i < SignUpChoicePageButtons.getChildCount(); i++) {
            SignUpChoicePageButtons.getChildAt(i).setOnClickListener(onClick);
        }
        }

        class OnClick implements View.OnClickListener {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                switch (v.getTag().toString()) {
                   case "patient":
                        intent = new Intent(Signupchoice.this, SignUpPage.class);
                        break;
                    case "therapist":
                        intent = new Intent(Signupchoice.this, TherapistSignup.class);
                        break;
                    default:
                        break;
                }
                if (intent != null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(Signupchoice.this, "invalid button", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
