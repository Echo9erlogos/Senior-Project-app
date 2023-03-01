package com.example.myapplication55.Adapters;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication55.R;

public class DisplayAdapterTherapist extends RecyclerView.ViewHolder {
    public TextView textpatientname;
    public TextView textaddress;
    public TextView textdate;
    public TextView texttime;
    public TextView textphone;
    public TextView textemail;
    public TextView textcondition;
    public Button cancel;
    public Button request;

    public View v;
    public DisplayAdapterTherapist(@NonNull View itemView) {
        super(itemView);
        v=itemView;
        textpatientname=itemView.findViewById(R.id.patient_name);
        textaddress=itemView.findViewById(R.id.patient_address);
        textdate=itemView.findViewById(R.id.date);
        texttime=itemView.findViewById(R.id.time);
        textphone=itemView.findViewById(R.id.phone);
        textemail=itemView.findViewById(R.id.email);
        textcondition=itemView.findViewById(R.id.condition);
        cancel=itemView.findViewById(R.id.cancel);
        request=itemView.findViewById(R.id.request);
    }
}
