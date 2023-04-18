package com.example.myapplication55;


import com.example.myapplication55.Adapters.PatientFriendsHolder;
import com.example.myapplication55.model.PatientFriendsInfos;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Patient_ChatPage_Activity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    FirebaseRecyclerOptions<PatientFriendsInfos> options;
    FirebaseRecyclerAdapter<PatientFriendsInfos, PatientFriendsHolder> adapter;
    DatabaseReference DataRef;
    final String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_chatpage);

        mRecyclerView=findViewById(R.id.patientfriendlist);
        DataRef= FirebaseDatabase.getInstance().getReference().child("chats").child(uid);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setHasFixedSize(true);

        LoadData();
    }

    private void LoadData() {
        options=new FirebaseRecyclerOptions.Builder<PatientFriendsInfos>().setQuery(DataRef, PatientFriendsInfos.class).build();
        adapter=new FirebaseRecyclerAdapter<PatientFriendsInfos, PatientFriendsHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull PatientFriendsHolder holder, int position, @NonNull PatientFriendsInfos model) {
                holder.textname.setText(model.getTherapistname());
                holder.v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(Patient_ChatPage_Activity.this, Patient_Message_Activity.class );
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public PatientFriendsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.patient_friendlist_item, parent, false);
                return new PatientFriendsHolder(v);
            }
        };
        adapter.startListening();
        mRecyclerView.setAdapter(adapter);
    }
}
