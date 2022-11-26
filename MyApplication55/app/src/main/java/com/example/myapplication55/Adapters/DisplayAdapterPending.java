package com.example.myapplication55.Adapters;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication55.R;

public class DisplayAdapterPending extends RecyclerView.ViewHolder {
    public TextView textpatientname;
    public TextView textaddress;
    public TextView textdate;
    public TextView texttime;
    public TextView textphone;
    public TextView textemail;
    public TextView textcondition;
    public Button confirm;
    public Button reject;
    public View v;
    public DisplayAdapterPending(@NonNull View itemView) {
        super(itemView);
        v=itemView;
        textpatientname=itemView.findViewById(R.id.patient_name);
        textaddress=itemView.findViewById(R.id.patient_address);
        textdate=itemView.findViewById(R.id.date);
        texttime=itemView.findViewById(R.id.time);
        textphone=itemView.findViewById(R.id.phone);
        textemail=itemView.findViewById(R.id.email);
        textcondition=itemView.findViewById(R.id.condition);
        confirm=itemView.findViewById(R.id.confirm);
        reject=itemView.findViewById(R.id.reject);
    }
}
