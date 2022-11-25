package com.example.myapplication55;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;

public class LoginPage extends AppCompatActivity {
    private CheckBox therapist;
    private CheckBox patient;
    private static final String TAG = "_EmailPassword_";

    private FirebaseAuth myAuth;

    String email,password;
    EditText emailInput,passInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        therapist=findViewById(R.id.therapist);
        patient=findViewById(R.id.patient);
        //init auth
        myAuth = FirebaseAuth.getInstance();
    }
    @Override
    public void onStart(){
        super.onStart();
        //check if user is already signed in
        FirebaseUser currUser = myAuth.getCurrentUser();
        if(currUser != null){
            Log.d(TAG, "__________________________________________signINWithEmail:success__________________________________________");
            //reload(); here just load to homepage with user data might move this to an earlier screen as having the app check here is not useful tbh
        }
    }

    private void signIn(String email,String password){
        myAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this,
                        new OnCompleteListener<AuthResult>(){
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task){
                                if(task.isSuccessful()==true){
                                    //sign in success
                                    Log.d(TAG, "__________________________________________signINWithEmail:success__________________________________________");
                                    FirebaseUser user = myAuth.getCurrentUser();
                                    //update UI to go to home page
                                    if(therapist.isChecked()&&patient.isChecked()){
                                        Toast.makeText(LoginPage.this,"please choose one",Toast.LENGTH_SHORT).show();
                                    }
                                    if(therapist.isChecked()==false&&patient.isChecked()==false){
                                        Toast.makeText(LoginPage.this,"please choose one!",Toast.LENGTH_SHORT).show();
                                    }
                                    if(therapist.isChecked()&&patient.isChecked()==false){
                                        startActivity(new Intent(LoginPage.this, Therapist_HomePage_Activity.class));
                                    }
                                    if(patient.isChecked()&&therapist.isChecked()==false){
                                        startActivity(new Intent(LoginPage.this, Patient_HomePage_Activity.class));
                                    }


                            }if(task.isSuccessful()==false){
                                //sign in fails
                                Log.w(TAG, "__________________________________________signInWithEmail:failure__________________________________________", task.getException());
                                Toast.makeText(LoginPage.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                //dont update ui user auth failed they should try again
                            }
                        }
    });
    }

    //get inputs method
    public void getSignInInput(View view) throws IOException{
        emailInput = findViewById(R.id.email);
        passInput = findViewById(R.id.password);

        email = emailInput.getText().toString();
        password = passInput.getText().toString();

        signIn(email,password);
    }
}