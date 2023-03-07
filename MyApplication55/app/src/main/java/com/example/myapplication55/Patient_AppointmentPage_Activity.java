package com.example.myapplication55;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication55.Adapters.MyViewHolder;
import com.example.myapplication55.model.Infos;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Patient_AppointmentPage_Activity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    FirebaseRecyclerOptions<Infos> options;
    FirebaseRecyclerAdapter<Infos, MyViewHolder>adapter;
    DatabaseReference DataRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_appointmentpage);

        mRecyclerView=findViewById(R.id.therapistlist);
        DataRef= FirebaseDatabase.getInstance().getReference().child("therapist");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setHasFixedSize(true);

        LoadData();



    }

    private void LoadData() {
        options=new FirebaseRecyclerOptions.Builder<Infos>().setQuery(DataRef, Infos.class).build();
        adapter=new FirebaseRecyclerAdapter<Infos, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull Infos model) {
                holder.textname.setText(model.getName());
                holder.textscore.setText(model.getScore());
                holder.textcity.setText(model.getCity());
                holder.v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(Patient_AppointmentPage_Activity.this, Patient_View_TherapistInfo_Activity.class );
                        intent.putExtra("therapistKey", getRef(position).getKey());
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
                return new MyViewHolder(v);
            }
        };
        adapter.startListening();
        mRecyclerView.setAdapter(adapter);
    }
}
