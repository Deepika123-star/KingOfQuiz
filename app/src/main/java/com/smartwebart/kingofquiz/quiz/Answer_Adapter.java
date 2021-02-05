package com.smartwebart.kingofquiz.quiz;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.smartwebart.kingofquiz.R;
import com.smartwebart.kingofquiz.model.AnswerModel;
import com.smartwebart.kingofquiz.model.QuestionModel;

import java.util.List;

public class Answer_Adapter extends RecyclerView.Adapter<Answer_Adapter.MyViewHolder> {
    private Context context;
    private List<AnswerModel> topicList;
    private String selected_language, answer_id;
    private QuestionModel question;
    private int row_index = -1;
    private char c = 'A';
    private boolean Is_character = false;
    private boolean Is_selected = false;

    public Answer_Adapter(Context context, List<AnswerModel> topicList, QuestionModel question) {
        this.context = context;
        this.topicList = topicList;
        this.selected_language = selected_language;
        this.question = question;
        this.answer_id = question!=null?question.getAnswer_id():null;

//        Log.d("Adapter ======","Constructor");

    }

    public void setTopicList(List<AnswerModel> topicList) {
        this.topicList = topicList;
    }

    public void setQuestion(QuestionModel question) {
        this.question = question;
        this.answer_id = question.getAnswer_id();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    Log.d("Adapter ======","onCreateViewHolder"+viewType);
        if (viewType!= 0) {
           c++;
        }
        topicList.get(viewType).setAlphabets(c);
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.answer_itemview_layout, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
      //.d("Adapter ======","onBindViewHolder");
        Log.d(" Chacter=====", "" + c);
        holder.answer_no.setText("" +topicList.get(position).getAlphabets());

        if(question.getMarked_review())
        {
            ((QuestionAnswer_Activity)context).starred.setChecked(true);
        }
        else
        {
            ((QuestionAnswer_Activity)context).starred.setChecked(false);
        }

        /*if (selected_language.equalsIgnoreCase("eng")) {
            holder.answer.setText(topicList.get(position).getName_in_en());
        } else {
            holder.answer.setText(topicList.get(position).getName_in_hi());
        }*/
        holder.answer.setText(topicList.get(position).getName_in_en());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row_index = position;
                answer_id = topicList.get(position).getId();
                ((QuestionAnswer_Activity) context).answer_id = topicList.get(position).getId();
                notifyDataSetChanged();
            }
        });


        /*if (question.getAnswer_id() == topicList.get(position).getId()) {
//            holder.parent.setBackgroundColor(context.getResources().getColor(R.color.color_blue_alpha));
            ((QuestionAnswer_Activity) context).answer_id = "";
        }*/


//        if(Is_selected)
        {
            if (row_index == position) {
                if (((QuestionAnswer_Activity) context).setTextonskipButton()) {
                    ((QuestionAnswer_Activity) context).skip_save_erase.setText("SAVE");
                } else {
                    ((QuestionAnswer_Activity) context).skip_save_erase.setText("SAVE & NEXT");
                }

//                holder.parent.setBackgroundColor(context.getResources().getColor(R.color.color_blue_alpha));
                holder.parent.setBackground(context.getResources().getDrawable(R.drawable.rectangle_lightblue_stroke));
//                Is_selected=false;
            } else {
                if (question.getAnswer_id()!=null) {
                    if(answer_id.equals(question.getAnswer_id())&&answer_id.equals(topicList.get(position).getId()))
                    {
                        holder.parent.setBackground(context.getResources().getDrawable(R.drawable.rectangle_lightblue_stroke));
                    }
                    else
                    {
//                        holder.parent.setBackgroundColor(context.getResources().getColor(R.color.white));
                        holder.parent.setBackground(context.getResources().getDrawable(R.drawable.rectangle_tranparent_stroke));
                    }

                } else
//                    holder.parent.setBackgroundColor(context.getResources().getColor(R.color.white));
                holder.parent.setBackground(context.getResources().getDrawable(R.drawable.rectangle_tranparent_stroke));
            }

        }


    }

    @Override
    public int getItemCount() {
//        Log.d("Adapter ======","getItemCount");

        return topicList!=null?topicList.size():0;
    }

    public void notifyDataSetChanged(QuestionModel questionModel) {

        this.question = questionModel;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView answer, answer_no;
        RelativeLayout parent;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
//            Log.d("Adapter ======","MyViewHolder");
            answer = itemView.findViewById(R.id.answer);
            parent = itemView.findViewById(R.id.parent);
            answer_no = itemView.findViewById(R.id.answer_no);

        }

    }

//    @Override
//    public int getItemViewType(int position) {
//        Log.d("Adapter ======","getItemViewType");
//        return position;
//    }
}
