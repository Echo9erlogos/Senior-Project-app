package com.example.myapplication55;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication55.Adapters.MessageAdapter;
import com.example.myapplication55.model.MessageModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Therapist_Message_Activity extends AppCompatActivity {
    public static final int MSG_TYPE_LEFT=0;
    public static final int MSG_TYPE_RIGHT=1;
    ImageView profile_image;
    TextView username;
    ImageButton btn_send;
    EditText text_send;
    RecyclerView mRecyclerView;
    FirebaseRecyclerOptions<MessageModel> options;
    FirebaseRecyclerAdapter<MessageModel, MessageAdapter> adapter;
    DatabaseReference DataRef;
    final String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therapist_message);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        username=findViewById(R.id.username);
        profile_image=findViewById(R.id.profile_image);
        btn_send=findViewById(R.id.btn_send);
        text_send=findViewById(R.id.text_send);

        String patientname=getIntent().getStringExtra("patientname");
        String patientuid=getIntent().getStringExtra("patientuid");

        mRecyclerView=findViewById(R.id.message_recycler);
        DataRef= FirebaseDatabase.getInstance().getReference().child("chats").child(uid).child(patientuid);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setHasFixedSize(true);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg=text_send.getText().toString();
                if(!msg.equals("")){
                    sendMessage(uid, patientuid, msg);
                }else{
                    Toast.makeText(Therapist_Message_Activity.this, "You can't send empty message", Toast.LENGTH_SHORT).show();
                }
                text_send.setText("");
            }
        });

        username.setText(patientname);
        LoadData();
    }
    private void sendMessage(String sender, String receiver, String message){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("chats");
        HashMap<String, Object> hashMap=new HashMap<>();
        hashMap.put("sender", sender);
        hashMap.put("receiver", receiver);
        hashMap.put("message",message);
        databaseReference.child(sender).child(receiver).push().setValue(hashMap);
        databaseReference.child(receiver).child(sender).push().setValue(hashMap);
    }

    private void LoadData() {
        options=new FirebaseRecyclerOptions.Builder<MessageModel>().setQuery(DataRef, MessageModel.class).build();
        adapter=new FirebaseRecyclerAdapter<MessageModel, MessageAdapter>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MessageAdapter holder, int position, @NonNull MessageModel model) {
                holder.show_message.setText(model.getMessage());
            }

            public int getItemViewType(int position) {
                if(getItem(position).getSender().equals(uid)){
                    return MSG_TYPE_RIGHT;
                }else{
                    return MSG_TYPE_LEFT;
                }
            }

            @NonNull
            @Override
            public MessageAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
                if(viewType==MSG_TYPE_RIGHT){
                    View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_right, parent, false);
                    return new MessageAdapter(v);
                }else{
                    View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_left, parent, false);
                    return new MessageAdapter(v);
                }
            }
        };
        adapter.startListening();
        mRecyclerView.setAdapter(adapter);
    }
}
