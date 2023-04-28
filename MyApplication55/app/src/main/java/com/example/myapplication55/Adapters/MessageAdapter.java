package com.example.myapplication55.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication55.R;
import com.example.myapplication55.model.MessageModel;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class MessageAdapter extends RecyclerView.ViewHolder{
    public TextView show_message;
    public ImageView profile_image;

    public MessageAdapter(@NonNull View itemView){
        super(itemView);
        show_message=itemView.findViewById(R.id.show_message);
        profile_image=itemView.findViewById(R.id.profile_image);
    }


}
