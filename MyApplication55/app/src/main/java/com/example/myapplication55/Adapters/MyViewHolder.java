package com.example.myapplication55.Adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication55.R;


public class MyViewHolder extends RecyclerView.ViewHolder {

    public TextView textname;
    public TextView textcity;
    public TextView textscore;
    public View v;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        textname=itemView.findViewById(R.id.name);
        textcity=itemView.findViewById(R.id.city);
        textscore=itemView.findViewById(R.id.score);
        v=itemView;
    }
}
