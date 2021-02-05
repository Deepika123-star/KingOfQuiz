package com.smartwebart.kingofquiz.viewmodel;


import com.smartwebart.kingofquiz.model.SubjectModel;

public interface TopicDataResultCallback {
    boolean isInternetConnected();
    void showLoader();
    void hideLoader();
    void onSuccessTopicDataResponse(SubjectModel response);
    void onErrorTopicDataResponse(String errMsg);
}
