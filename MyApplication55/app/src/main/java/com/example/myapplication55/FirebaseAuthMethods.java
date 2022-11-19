package com.example.myapplication55;

import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class FirebaseAuthMethods extends AppCompatActivity {
    private FirebaseAuth myAuth;


    private static final String TAG = "__FirebaseAuthMethods__";

    public void signOut() {
        // [START auth_sign_out]
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        // [END auth_sign_out]
    }

    public void deleteUser() {
        // [START delete_user]
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "_________User account deleted.________");
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                    }
                });
        // [END delete_user]
    }

    //add name to user's profile
    public void setupProfile(String name){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        UserProfileChangeRequest pChanges = new UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .build();

        user.updateProfile(pChanges)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Log.d(TAG,"__________userSetupProfile Success__________");

                        }
                    }
                });
        try{
            Thread.sleep(2000);
        }
        catch(Exception e) {

        }
    }
    //end of setup Profile

    //get users name
    public String getNameFBAuth(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            return user.getDisplayName();
        }
        return"Null";
    }
}
