package com.example.myapplication55;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication55.Adapters.TherapistFriendsHolder;
import com.example.myapplication55.model.TherapistFriendsInfos;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Therapist_ChatPage_Activity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    FirebaseRecyclerOptions<TherapistFriendsInfos> options;
    FirebaseRecyclerAdapter<TherapistFriendsInfos, TherapistFriendsHolder> adapter;
    DatabaseReference DataRef;
    final String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therapist_chatpage);

        mRecyclerView=findViewById(R.id.therapistfriendlist);
        DataRef= FirebaseDatabase.getInstance().getReference().child("friends").child(uid);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setHasFixedSize(true);

        LoadData();
    }
    private void LoadData() {
        options=new FirebaseRecyclerOptions.Builder<TherapistFriendsInfos>().setQuery(DataRef, TherapistFriendsInfos.class).build();
        adapter=new FirebaseRecyclerAdapter<TherapistFriendsInfos, TherapistFriendsHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull TherapistFriendsHolder holder, int position, @NonNull TherapistFriendsInfos model) {
                holder.textname.setText(model.getPatientname());
                holder.v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(Therapist_ChatPage_Activity.this, Therapist_Message_Activity.class );
                        intent.putExtra("patientname", model.getPatientname());
                        intent.putExtra("patientuid", getRef(position).getKey());
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public TherapistFriendsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.therapist_friendlist_item, parent, false);
                return new TherapistFriendsHolder(v);
            }
        };
        adapter.startListening();
        mRecyclerView.setAdapter(adapter);
    }
}
