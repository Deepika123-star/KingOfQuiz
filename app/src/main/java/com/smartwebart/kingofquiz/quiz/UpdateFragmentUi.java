package com.smartwebart.kingofquiz.quiz;

import com.smartwebart.kingofquiz.model.QuestionModel;

public interface UpdateFragmentUi {
    void onDataUpdate(QuestionModel questionModel, int counter);
    void onUpdatreAdapter(QuestionModel questionModel);
    void onScoreReviewFragment(String selected_language);
}
