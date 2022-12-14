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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;

public class PatientSignup extends FirebaseAuthMethods {
    String email, password, name,gender,repass;
    EditText emailInput, passwordInput, nameInput, genderInput, repassInput;
    TextView errorText;

    //start auth
    private FirebaseAuth myAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_signup);

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
                            PatientModel patientModel=new PatientModel();
                            patientModel.pname=name;
                            patientModel.gender=gender;
                            patientModel.email=email;
                            patientModel.uid=uid;
                            patientModel.age="-";
                            patientModel.city="-";
                            patientModel.state="-";
                            patientModel.street="-";
                            patientModel.phone="-";
                            patientModel.condition="-";
                            setupProfile(name);
                            //sign in success
                            Log.d(TAG,"createUserWithEmail:success");
                            FirebaseUser user = myAuth.getCurrentUser();
                            //updateUI(user);

                            //this is where we update to next screen with user info and profile
                            Intent intent = null;
                            intent = new Intent(PatientSignup.this, Patient_HomePage_Activity.class);
                            startActivity(intent);
                            FirebaseDatabase.getInstance().getReference().child("patient").child(uid).setValue(patientModel);
                        }
                        else{
                            //sign in fails
                            Log.w(TAG,"createUserWithEmail:failure",task.getException());
                            Toast.makeText(PatientSignup.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                            //won't update since this failed and user will need to retry
                        }
                    }
                });
    }

    //function retrieves inputs from user
    public void getInput(View view)throws IOException{
        emailInput = findViewById(R.id.email);
        passwordInput = findViewById(R.id.password);
        nameInput = findViewById(R.id.fullname);
        genderInput = findViewById(R.id.gender);
        repassInput = findViewById(R.id.repassword);
        errorText = findViewById(R.id.bannerDescription);

        //convert amd save to local vars
        email = emailInput.getText().toString();
        password = passwordInput.getText().toString();
        repass = repassInput.getText().toString();
        name = nameInput.getText().toString();
        gender = genderInput.getText().toString();

        //check if password is valid/matches
        if(!password.equals(repass)){
            errorText.setText("Password does not match");
        }else {
            createAccount(email, password);
        }
    }

}