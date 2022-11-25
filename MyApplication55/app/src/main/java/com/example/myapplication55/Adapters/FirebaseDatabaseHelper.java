package com.example.myapplication55.Adapters;

import androidx.annotation.NonNull;


import com.example.myapplication55.model.Infos;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private List<Infos> Infos=new ArrayList<>();

    public interface DataStatus{
        void DataIsLoaded(List<Infos>Infos,List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public FirebaseDatabaseHelper() {
        mDatabase=FirebaseDatabase.getInstance();
        mReference=mDatabase.getReference("therapist");
    }
    public void readInfos(final DataStatus dataStatus){
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Infos.clear();
                List<String> keys=new ArrayList<>();
                for(DataSnapshot KeyNode:snapshot.getChildren()){
                    keys.add(KeyNode.getKey());
                    Infos info = KeyNode.getValue(Infos.class);
                    Infos.add(info);
                }
                dataStatus.DataIsLoaded(Infos,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
