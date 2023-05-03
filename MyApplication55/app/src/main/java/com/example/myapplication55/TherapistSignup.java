package com.example.myapplication55;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.myapplication55.model.PatientModel;
import com.example.myapplication55.model.TherapistModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;

public class TherapistSignup extends FirebaseAuthMethods {
    String email, password, name,gender,repass, phoneNum;
    EditText emailInput, passwordInput, nameInput, genderInput, repassInput, phoneNumInput;
    TextView errorText;
    private FirebaseAuth myAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therapist_signup);
        myAuth = FirebaseAuth.getInstance();
    }
    @Override
    public void onStart(){
        super.onStart();

        FirebaseUser currentUser = myAuth.getCurrentUser();
        if(currentUser != null){
            //reload();
        }
    }

    private void createAccount(String email, String password){
        myAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(
                this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            final String uid=FirebaseAuth.getInstance().getCurrentUser().getUid();
                            TherapistModel therapistModel=new TherapistModel();
                            therapistModel.uid=uid;
                            therapistModel.name=name;
                            therapistModel.e_mail=email;
                            therapistModel.city="-";
                            therapistModel.state="-";
                            therapistModel.street="-";
                            therapistModel.phone="-";
                            therapistModel.beingtime="-";
                            therapistModel.number="0";
                            therapistModel.score="-1";
                            therapistModel.feedback="";
                            therapistModel.description="-";
                            therapistModel.workinghours1="0:00";
                            therapistModel.workinghours2="24:00";
                            //sign in success
                            Log.d(TAG,"createUserWithEmail:success");
                            FirebaseUser user = myAuth.getCurrentUser();
                            setupProfile(name);
                            //updateUI(user);

                            sendEmailVerification();

                            //this is where we update to next screen with user info and profile
                            Intent intent = null;
                            intent = new Intent(TherapistSignup.this, Therapist_HomePage_Activity.class);
                            intent.putExtra("Phone Number",phoneNum);
                            intent.putExtra("SignUpChoice","therapist");
                            startActivity(intent);
                            FirebaseDatabase.getInstance().getReference().child("therapist").child(uid).setValue(therapistModel);
                        }
                        else{
                            //sign in fails
                            Log.w(TAG,"createUserWithEmail:failure",task.getException());
                            Toast.makeText(TherapistSignup.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                            //won't update since this failed and user will need to retry
                        }
                    }
                });
    }

    //function retrieves inputs from user
    public void getInput(View view)throws IOException {
        emailInput = findViewById(R.id.email);
        passwordInput = findViewById(R.id.password);
        nameInput = findViewById(R.id.fullname);
        genderInput = findViewById(R.id.gender);
        repassInput = findViewById(R.id.repassword);
        errorText = findViewById(R.id.bannerDescription);
        phoneNumInput =findViewById(R.id.PhoneNumber);

        //convert amd save to local vars
        email = emailInput.getText().toString();
        password = passwordInput.getText().toString();
        repass = repassInput.getText().toString();
        name = nameInput.getText().toString();
        gender = genderInput.getText().toString();
        phoneNum = phoneNumInput.getText().toString();

        //check if password is valid/matches
        if(!password.equals(repass)){
            errorText.setText("Password does not match");
        }else {
            createAccount(email, password);
        }
    }
}