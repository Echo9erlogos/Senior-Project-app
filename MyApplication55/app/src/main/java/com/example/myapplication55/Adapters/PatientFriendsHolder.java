package com.example.myapplication55.Adapters;
import android.view.View;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication55.R;

public class PatientFriendsHolder extends RecyclerView.ViewHolder{
    public TextView textname;
    public View v;
    public ImageView profile_image;

    public PatientFriendsHolder(@NonNull View itemView) {
        super(itemView);
        profile_image=itemView.findViewById(R.id.profile_image);
        textname=itemView.findViewById(R.id.patientfriendname);
        v=itemView;
    }
}
