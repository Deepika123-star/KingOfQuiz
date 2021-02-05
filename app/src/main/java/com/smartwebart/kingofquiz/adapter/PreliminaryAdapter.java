package com.smartwebart.kingofquiz.adapter;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.smartwebart.kingofquiz.R;
import com.smartwebart.kingofquiz.level.LevelActivity;
import com.smartwebart.kingofquiz.model.NewSeriesModel;
import com.smartwebart.kingofquiz.model.SubjectModel;

import java.util.List;

public class PreliminaryAdapter extends RecyclerView.Adapter<PreliminaryAdapter.MyViewHolder> {
    List<NewSeriesModel> content_data;
    Activity requireActivity;

    public PreliminaryAdapter(List<NewSeriesModel> content_data, Activity requireActivity) {
        this.content_data = content_data;
        this.requireActivity = requireActivity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textViewSub1Title.setText(content_data.get(position).getName());
       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(requireActivity, LevelActivity.class);
               intent.putExtra("id",""+content_data.get(position).getId());
               Log.d("dev==", "onClick: "+content_data.get(position).getId());
               requireActivity.startActivity(intent);
           }
       });
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return content_data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView  textViewSub1Title/*,topic_count,description*/;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
           // topic_count = itemView.findViewById(R.id.topic_count);
            textViewSub1Title = itemView.findViewById(R.id.textViewSub1Title);
           // description = itemView.findViewById(R.id.description);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // Toast.makeText(requireActivity, "", Toast.LENGTH_SHORT).show();
                   /* Intent i = new Intent(requireActivity, TopicListingActivity.class);
                    i.putExtra("id",content_data.get(getAdapterPosition()).getId());
                    i.putExtra("exams_id",content_data.get(getAdapterPosition()).getExamsId());
//                    i.putExtra("subjects_id",content_data.get(getAdapterPosition()).getSubjectsId());
                    i.putExtra("exams_type","prelims");
                    requireActivity.startActivity(i);*/
                }
            });
        }
    }
}
