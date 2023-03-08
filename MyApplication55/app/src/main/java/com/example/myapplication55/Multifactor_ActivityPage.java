package com.example.myapplication55;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.MultiFactorSession;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;
import android.view.View;

public class Multifactor_ActivityPage extends FirebaseAuthMethods {
    private String phoneNum;
    private String verifyCode;

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks;


    private TextView phoneNumInput;
    //gets phone number form user and add it to their account

    //get the sms code and verify account
    public void verifyAccount(View view){

    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_account);


        phoneNum =getIntent().getStringExtra("Phone Number");

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        user.getMultiFactor().getSession()
                .addOnCompleteListener(
                        new OnCompleteListener<MultiFactorSession>() {
                            @Override
                            public void onComplete(@NonNull Task<MultiFactorSession> task) {
                                if (task.isSuccessful()) {
                                    MultiFactorSession multiFactorSession = task.getResult();

                                }
                            }
                        });

        callbacks =
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    private PhoneAuthProvider.ForceResendingToken forceResendingToken;
                    private String verificationId;
                    private PhoneAuthCredential credential;

                    @Override
                    public void onVerificationCompleted(PhoneAuthCredential credential) {
                        Log.d(TAG,"----------------OnVerComplete:success--------------");
                        // This callback will be invoked in two situations:
                        // 1) Instant verification. In some cases, the phone number can be
                        //    instantly verified without needing to send or enter a verification
                        //    code. You can disable this feature by calling
                        //    PhoneAuthOptions.builder#requireSmsValidation(true) when building
                        //    the options to pass to PhoneAuthProvider#verifyPhoneNumber().
                        // 2) Auto-retrieval. On some devices, Google Play services can
                        //    automatically detect the incoming verification SMS and perform
                        //    verification without user action.
                        this.credential = credential;
                    }
                    @Override
                    public void onVerificationFailed(FirebaseException e) {
                        // This callback is invoked in response to invalid requests for
                        // verification, like an incorrect phone number.
                        if (e instanceof FirebaseAuthInvalidCredentialsException) {
                            // Invalid request
                            // ...
                        } else if (e instanceof FirebaseTooManyRequestsException) {
                            // The SMS quota for the project has been exceeded
                            // ...
                        }
                        // Show a message and update the UI
                        // ...
                    }
                    @Override
                    public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken token) {
                        // The SMS verification code has been sent to the provided phone number.
                        // We now need to ask the user to enter the code and then construct a
                        // credential by combining the code with a verification ID.
                        // Save the verification ID and resending token for later use.
                        Log.d(TAG,"----------------SMS:success--------------");
                        this.verificationId = verificationId;
                        this.forceResendingToken = token;
                        // ...
                    }
                };

        PhoneAuthOptions phoneAuthOptions =
                PhoneAuthOptions.newBuilder()
                        .setPhoneNumber("+1 " +phoneNum)
                        .setTimeout(30L, TimeUnit.SECONDS)
                        .setActivity(this)
                        .setCallbacks(callbacks)
                        .build();
//.setMultiFactorSession(multiFactorSession)
        PhoneAuthProvider.verifyPhoneNumber(phoneAuthOptions);
        //setContentView(R.layout.activity_verify_account);
    }
}
