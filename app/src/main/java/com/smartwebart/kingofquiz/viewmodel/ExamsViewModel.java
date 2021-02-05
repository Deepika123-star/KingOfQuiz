package com.smartwebart.kingofquiz.viewmodel;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.smartwebart.kingofquiz.R;
import com.smartwebart.kingofquiz.model.SubjectModel;
import com.smartwebart.kingofquiz.model.TopicModel;
import com.smartwebart.kingofquiz.model.Total_Score_Model;
import com.smartwebart.kingofquiz.retrofit.UtilMethods;
import com.smartwebart.kingofquiz.retrofit.mCallBackResponse;
import com.smartwebart.kingofquiz.utils.AppSharedPreferences;
import com.smartwebart.kingofquiz.utils.UsefullMethods;


import java.lang.reflect.Type;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ExamsViewModel extends AppSharedPreferences {

    private MutableLiveData<List<SubjectModel>> mutableLiveData;
    private MutableLiveData<List<TopicModel>> subject_mutableLiveData;
    private TopicDataResultCallback resultCallback;

  /*  public TestTopicViewModel(TopicDataResultCallback resultCallback) {

    }*/

    private Activity activity;

    public ExamsViewModel(Application application) {
        super(application);


    }
    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
    public MutableLiveData<List<SubjectModel>> getSubjects(Activity context){
        mutableLiveData = new MutableLiveData<List<SubjectModel>>();
        //callSubjects(context);
        return mutableLiveData;
    }


    public MutableLiveData<List<TopicModel>> getTopicList(Activity context,
                                                          String exam_id, String subjects_id){
        subject_mutableLiveData = new MutableLiveData<List<TopicModel>>();
        callTopic(context);
        return subject_mutableLiveData;
    }

    /*public void setMutableLiveData(List<TopicModel> mutableLiveData) {
        this.mutableLiveData.setValue(mutableLiveData);
    }*/


    public void callTopic(Activity resultCallback) {
        UtilMethods.INSTANCE.getTopicApi(resultCallback, new mCallBackResponse() {
                    @Override
                    public void success(String from, String message) {
//                        Type type = new TypeToken<List<TopicModel>>(){}.getType();
//                        List<TopicModel> list = new Gson().fromJson(message, type);
                        Total_Score_Model data = new Gson().fromJson(message, Total_Score_Model.class);
                        List<TopicModel> list = data.getTopicData();
                        subject_mutableLiveData.setValue(list);
                    }

                    @Override
                    public void fail(String from) {
                        UsefullMethods.showMessage(getActivity(), SweetAlertDialog.ERROR_TYPE, resultCallback.getString(R.string.no_data_found), from, "OK", null);
                    }
                }
        );
    }


/*

    public void callSubjects(Activity resultCallback) {
        UtilMethods.INSTANCE.getSubjectCallApi(resultCallback
                , new mCallBackResponse() {
                    @Override
                    public void success(String from, String message) {

                        Type type = new TypeToken<List<SubjectModel>>(){}.getType();
                        List<SubjectModel> list = new Gson().fromJson(message, type);
                        mutableLiveData.setValue(list);
                    }

                    @Override
                    public void fail(String from) {
                        UsefullMethods.showMessage(getActivity(), SweetAlertDialog.ERROR_TYPE, resultCallback.getString(R.string.no_data_found), from, "OK", null);
                    }
                }
        );
    }
*/


}