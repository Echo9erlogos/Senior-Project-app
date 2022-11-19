package com.example.myapplication55.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication55.Patient_HomePage_Activity;
import com.example.myapplication55.R;
import com.example.myapplication55.TherapistProfileViewPage;
import com.example.myapplication55.model.Info;

import java.util.List;

public class RecycleAdapter {
    private Context mContext;
    private InfoAdapter mInfoAdapter;
    public void setConfig(RecyclerView recyclerView, Context context, List<Info> infos, List<String> keys) {
        mContext = context;
        mInfoAdapter=new InfoAdapter(infos,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mInfoAdapter);
    }

        class itemView extends RecyclerView.ViewHolder {
            private TextView name;
            private TextView city;
            private TextView score;
            private String key;

            public itemView(ViewGroup parent) {
                super(LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false));
                name = (TextView) itemView.findViewById(R.id.name);
                city = (TextView) itemView.findViewById(R.id.city);
                score = (TextView) itemView.findViewById(R.id.score);
            }

            public void bind(Info infos, String key) {
                name.setText((infos.getName()));
                city.setText(infos.getCity());
                score.setText(infos.getScore());
                this.key = key;
            }
        }
        class InfoAdapter extends RecyclerView.Adapter<itemView> {
            private List<Info> mInfoList;
            private List<String> mKey;

            public InfoAdapter(List<Info> mInfosList, List<String> mKey) {
                this.mInfoList = mInfosList;
                this.mKey = mKey;
            }

            @NonNull
            @Override
            public itemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new itemView(parent);
            }

            @Override
            public void onBindViewHolder(@NonNull itemView holder, int position) {
                holder.bind(mInfoList.get(position), mKey.get(position));
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            Intent intent=new Intent();
                            intent.setClass(mContext, TherapistProfileViewPage.class);
                            mContext.startActivity(intent);
                    }
                });
            }

            @Override
            public int getItemCount() {
                return mInfoList.size();
            }
        }

}
