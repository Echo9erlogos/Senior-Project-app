package com.example.myapplication55;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ReservationInformationEditPage extends AppCompatActivity {
    ConstraintLayout viewPageButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_information_edit_page);
        viewPageButtons = findViewById(R.id.buttonlists);

        OnClick onClick = new OnClick();

        for (int i = 0; i < viewPageButtons.getChildCount(); i++) {
            viewPageButtons.getChildAt(i).setOnClickListener(onClick);
        }
    }

    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()) {
                case R.id.back:
                    intent = new Intent(ReservationInformationEditPage.this, Patient_AppointmentPage_Activity.class);
                    break;
                case R.id.send:
                    Toast.makeText(v.getContext(),"succeeded",Toast.LENGTH_SHORT).show();
                    intent = new Intent(ReservationInformationEditPage.this, Patient_HomePage_Activity.class);
                    break;
                default:
                    break;
            }
            if (intent != null) {
                startActivity(intent);
            } else {
                Toast.makeText(ReservationInformationEditPage.this, "invalid button", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
