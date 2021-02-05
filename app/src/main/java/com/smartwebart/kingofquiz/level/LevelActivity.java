package com.smartwebart.kingofquiz.level;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.smartwebart.kingofquiz.MainActivity;
import com.smartwebart.kingofquiz.R;
import com.smartwebart.kingofquiz.add.InterstitialAdsActivityActivity;
import com.smartwebart.kingofquiz.model.QuestionModel;
import com.smartwebart.kingofquiz.quiz.QuestionAnswer_Activity;
import com.smartwebart.kingofquiz.retrofit.UtilMethods;
import com.smartwebart.kingofquiz.viewmodel.TestViewModel;

import java.io.Serializable;
import java.util.List;

public class LevelActivity extends AppCompatActivity {
    String id;
    private List<QuestionModel> topicList;
    private TestViewModel mViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
//        Intent intent = getIntent();
         id = getIntent().getStringExtra("id");
//         UtilMethods.subject_id = getIntent().getStringExtra("id");

        mViewModel = ViewModelProviders.of(this).get(TestViewModel.class);
        mViewModel.setActivity(this);
        mViewModel.init();
        getTopicsObserver();
    }

    private void getTopicsObserver() {
        Observer observer = new Observer() {
            @Override
            public void onChanged(@Nullable Object object) {
                topicList = (List<QuestionModel>) object;
               // Log.d("Monita Observer=====", "" + topicList.size());
                if (topicList!=null&&topicList.size()>0){
                    Intent intent=new Intent(LevelActivity.this, QuestionAnswer_Activity.class);
                    intent.putExtra("topicList", (Serializable) topicList);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(LevelActivity.this, "There is no Question..", Toast.LENGTH_SHORT).show();
                }


            }
        };
        mViewModel.getQuestion(LevelActivity.this).observe(LevelActivity.this, observer);
    }

    public void low(View view) {

        Log.d("Level====", "low: "+UtilMethods.subject_id);
        mViewModel.getQuestions(LevelActivity.this,id,"1");

    }

    public void Medium(View view) {
        mViewModel.getQuestions(LevelActivity.this,id,"2");
//        getTopicsObserver("2");
    }

    public void High(View view) {
        mViewModel.getQuestions(LevelActivity.this,id,"3");
//        getTopicsObserver("3");
    }



}