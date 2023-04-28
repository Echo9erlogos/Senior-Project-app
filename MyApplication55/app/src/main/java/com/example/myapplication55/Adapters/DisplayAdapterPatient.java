package com.example.myapplication55.Adapters;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication55.R;

public class DisplayAdapterPatient extends RecyclerView.ViewHolder {
    public TextView textname;
    public TextView textstate;
    public Button cancel;
    public View v;
    public Button status;
    public DisplayAdapterPatient(@NonNull View itemView){
        super(itemView);
        textname=itemView.findViewById(R.id.therapist_name);
        textstate=itemView.findViewById(R.id.state);
        cancel=itemView.findViewById(R.id.cancel);
        status=itemView.findViewById(R.id.status);
        v=itemView;
    }
}
