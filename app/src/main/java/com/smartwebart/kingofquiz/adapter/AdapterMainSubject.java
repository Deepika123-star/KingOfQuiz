package com.smartwebart.kingofquiz.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.smartwebart.kingofquiz.R;
import com.smartwebart.kingofquiz.level.LevelActivity;

import java.util.List;

public class AdapterMainSubject extends RecyclerView.Adapter<AdapterMainSubject.MYViewHolder> {
    Context context;

    public AdapterMainSubject(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MYViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MYViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_item_layout,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull MYViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class MYViewHolder extends RecyclerView.ViewHolder {
        public MYViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context, LevelActivity.class);
                    context.startActivity(intent);
                }
            });
        }

    }
}
