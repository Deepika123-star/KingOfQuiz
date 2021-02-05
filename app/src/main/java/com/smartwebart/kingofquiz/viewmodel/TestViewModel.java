package com.smartwebart.kingofquiz.viewmodel;

import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.smartwebart.kingofquiz.MainActivity;
import com.smartwebart.kingofquiz.R;
import com.smartwebart.kingofquiz.level.LevelActivity;
import com.smartwebart.kingofquiz.model.AnswerModel;
import com.smartwebart.kingofquiz.model.QuestionModel;
import com.smartwebart.kingofquiz.model.SubmitTestModel;
import com.smartwebart.kingofquiz.model.SubmittedAnswerModel;
import com.smartwebart.kingofquiz.quiz.OTPModel;
import com.smartwebart.kingofquiz.quiz.QuestionAnswer_Activity;
import com.smartwebart.kingofquiz.retrofit.UtilMethods;
import com.smartwebart.kingofquiz.retrofit.mCallBackResponse;
import com.smartwebart.kingofquiz.room.AnswerEntity;
import com.smartwebart.kingofquiz.room.DatabaseClient;
import com.smartwebart.kingofquiz.room.QueryDao;
import com.smartwebart.kingofquiz.room.QuestionsEntity;
import com.smartwebart.kingofquiz.utils.AppSharedPreferences;
import com.smartwebart.kingofquiz.utils.MyClick;
import com.smartwebart.kingofquiz.utils.UsefullMethods;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class TestViewModel extends AppSharedPreferences {

    private Activity activity;
    private MutableLiveData<List<QuestionModel>> mutableLiveData;
    private MutableLiveData<List<QuestionModel>> mutableLiveDataEntity;
    private MutableLiveData<List<SubmittedAnswerModel>> mutableLiveDataSubmittedEntity;
    private List<OTPModel>otpModels;

    public TestViewModel(Application application) {
        super(application);
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public MutableLiveData<List<QuestionModel>> getQuestion(Activity context) {
//        mutableLiveData = new MutableLiveData<List<QuestionModel>>();

//        getQuestions(context, topic_id,level);
        return mutableLiveDataEntity;
    }

    public MutableLiveData<List<QuestionModel>> getQuestionfromLocalDb(Activity context) {
        mutableLiveDataEntity = new MutableLiveData<List<QuestionModel>>();
//        getQuestionsfromLocal(context);
//        getQuestionfromRoomDatabase(context);
        return mutableLiveDataEntity;
    }


    public void getQuestionfromRoomDatabase(final Activity context) {

        try {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    QueryDao querydao = DatabaseClient.getInstance(context).getAppDatabase().queryDao();
                    List<QuestionModel> questionModel = querydao.getAllQuestionAnswer();
                    if (questionModel != null) {
                        for (int i = 0; i < questionModel.size(); i++) {

                            List<AnswerModel> answerModels = querydao.getAllAnswer(questionModel.get(i).getId());
                            if (answerModels != null) {
                                questionModel.get(i).setAnswers(answerModels);
                            }

                        }
                    }
                    mutableLiveDataEntity.setValue(questionModel);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void getQuestions(Activity resultCallback, String topic_id, String level) {
        UtilMethods.INSTANCE.getQuestionApi(resultCallback, topic_id,level
                , new mCallBackResponse() {
                    @Override
                    public void success(String from, String message) {

                        Type type = new TypeToken<List<QuestionModel>>() {
                        }.getType();
                        List<QuestionModel> list = new Gson().fromJson(message, type);
                        if (list!=null&&list.size()>0)
                        {
                            Log.d("Monita===", "success: "+list);
                            mutableLiveDataEntity.setValue(list);
//                            new Handler(Looper.getMainLooper()).post(new Runnable() {
//                                @Override
//                                public void run() {
//                                   /* QueryDao querydao = DatabaseClient.getInstance(resultCallback).getAppDatabase().queryDao();
//                                    querydao.deleteQuestionTb();
//                                    querydao.deleteAnswerTb();*/
//                                    for (int i = 0; i < list.size(); i++) {
//
//                                        QuestionsEntity taskEntity = createQuestion(list.get(i), querydao);
//                                        createAnswer(list.get(i), querydao);
//                                        querydao.insert(taskEntity);
//
//                                    }
//                                    Intent intent=new Intent(getActivity(), QuestionAnswer_Activity.class);
//                                    getActivity().startActivity(intent);
//                                }
//                            });
                        }
                        else {
                            mutableLiveDataEntity.setValue(null);
                            Toast.makeText(resultCallback, "You Have No Question....", Toast.LENGTH_SHORT).show();
                        }

//                        mutableLiveData.setValue(list);
                    }

                    @Override
                    public void fail(String from) {
                        mutableLiveDataEntity.setValue(null);
                        UsefullMethods.showMessage(getActivity(), SweetAlertDialog.ERROR_TYPE, resultCallback.getString(R.string.no_data_found), from, "OK", null);
                    }
                }
        );
    }


    public void submittest(SubmitTestModel question_attempted) {
        UtilMethods.INSTANCE.testSubmitted(getActivity(), question_attempted, new mCallBackResponse() {
                    @Override
                    public void success(String from, String message) {
                        OTPModel otpModel=new Gson().fromJson(message,OTPModel.class);
                      ShowResult(otpModel);


                      /*  UsefullMethods.showMessage(getActivity(), SweetAlertDialog.SUCCESS_TYPE, "Test", "You have successfully submitted the test", "Ok", new MyClick() {

                            @Override
                            public void onClick() {
                                getActivity().finish();
                            }
                        });
                   */

                    }

                    @Override
                    public void fail(String from) {
                        UsefullMethods.showMessage(getActivity(), SweetAlertDialog.ERROR_TYPE, "Test", from, "OK", new MyClick() {
                            @Override
                            public void onClick() {
                                getActivity().finish();
                            }
                        });
                    }
                }
        );
    }

    private void ShowResult(OTPModel rightAns) {

        final Dialog dialog1 = new Dialog(getActivity());
        dialog1.setCancelable(false);
        dialog1.setContentView(R.layout.review_test_layout);
        dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView right=  dialog1.findViewById(R.id.correct_count);
        TextView wrong=  dialog1.findViewById(R.id.incorrect_count);
        TextView total=  dialog1.findViewById(R.id.unattempted_count);
        TextView submit1=  dialog1.findViewById(R.id.submit);
        right.setText(String.valueOf(rightAns.getRightAns().size()));
        wrong.setText(String.valueOf(rightAns.getWrongAns().size()));
       total.setText(String.valueOf(rightAns.getWrongAns().size()+rightAns.getRightAns().size()));
        submit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              dialog1.dismiss();
              activity.finish();
               /*Intent int1= new Intent(activity, LevelActivity.class);
                int1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
               activity.startActivity(int1);*/
            }
        });
        dialog1.show();
    }

    public MutableLiveData<List<SubmittedAnswerModel>> getQuestionReview(Activity context,
                                                                         String topic_id, String exams_id) {
        mutableLiveDataSubmittedEntity = new MutableLiveData<List<SubmittedAnswerModel>>();
       // getQuestionsReview(context, topic_id,exams_id);
        return mutableLiveDataSubmittedEntity;
    }


    private QuestionsEntity createQuestion(QuestionModel task, QueryDao taskDao) {
        QuestionsEntity entity = new QuestionsEntity();
        entity.setId(task.getId());
        entity.setAttempted(false);
        entity.setMarked_review(false);
        entity.setUnattempted(false);
        entity.setNameInHi(task.getNameInHi());
        entity.setNameInEng(task.getNameInEng());
        entity.setExamsId(task.getExamsId());
        entity.setTopicsId(task.getTopicsId());
        entity.setSubjectsId(task.getSubjectsId());
        return entity;
    }

    private QuestionsEntity createSubmittedQuestion(SubmittedAnswerModel task, QueryDao taskDao) {
        QuestionsEntity entity = new QuestionsEntity();
       // entity.setId(task.getQuestion().getId());
        entity.setAttempted(false);
        entity.setMarked_review(false);
        entity.setUnattempted(false);

        entity.setSubmittedAnswerId(task.getAnswersId());
        return entity;
    }


    private void createAnswer(QuestionModel answerModel, QueryDao taskDao) {
        if (answerModel.getAnswers() != null && answerModel.getAnswers().size() > 0) {
            for (int i = 0; i < answerModel.getAnswers().size(); i++) {
                Log.d("Monita Hello==", "" + answerModel.getAnswers().size() + "=======" + answerModel.getAnswers().get(i).getName_in_en() + "Idddd=" + answerModel.getAnswers().get(i).getId());
                AnswerEntity entity = new AnswerEntity();
                entity.setQuestionsId(answerModel.getAnswers().get(i).getQuestions_id());
                entity.setId(answerModel.getAnswers().get(i).getId());
                entity.setAnswer_marked(false);
                entity.setIsRight(answerModel.getAnswers().get(i).getIs_right());
                entity.setNameInEn(answerModel.getAnswers().get(i).getName_in_en());
                entity.setNameInHi(answerModel.getAnswers().get(i).getName_in_hi());
                taskDao.insert(entity);
            }
        }

    }

    public void showSubmitDialog(List<QuestionModel> questions, Activity activity) {
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.test_dialog_layout, null);
//        final RelativeLayout power = view.findViewById(R.id.power);
        final TextView total_count = view.findViewById(R.id.total_count);
        final TextView attempted_count = view.findViewById(R.id.attempted_count);
        final TextView unattempted_count = view.findViewById(R.id.unattempted_count);
        final TextView martattmp_count = view.findViewById(R.id.martattmp_count);
        final TextView marknotattmp_count = view.findViewById(R.id.marknotattmp_count);
        final TextView resume_test = view.findViewById(R.id.resume_test);
        final TextView submit = view.findViewById(R.id.submit);
        int att_count = 0;
        int unatte_count = 0;
        ArrayList<QuestionModel> question_attempted = new ArrayList<>();

        for (int i = 0; i < questions.size(); i++) {
            if (questions.get(i).getAttempted()) {
                question_attempted.add(questions.get(i));
                att_count++;
            } else {
                unatte_count++;
            }
        }
        SubmitTestModel submit_array = new SubmitTestModel();
        submit_array.setQuestion(question_attempted);
        submit_array.setTbname("user_answers");

List<OTPModel>correctAnswer=new ArrayList<>();
       OTPModel otpModel=new OTPModel();
       otpModel.getRightAns();

        total_count.setText(String.valueOf(questions.size()));
      /*  incorrect_count.setText(String.valueOf(otpModel.getWrongAns()));
        correct_count.setText(String.valueOf(otpModel.getRightAns()));*/
       attempted_count.setText(String.valueOf(att_count));
        unattempted_count.setText(String.valueOf(unatte_count));


        final Dialog dialog = new Dialog(getActivity());
        dialog.setCancelable(false);
        dialog.setContentView(view);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

     /*   resume_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });*/

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                submittest(submit_array);
                dialog.dismiss();


            }
        });

        dialog.show();

    }

    public void showTimeDialog(List<QuestionModel> questions, Activity activity) {
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_layout, null);
        final TextView submit = view.findViewById(R.id.submit);
        int att_count = 0;
        int unatte_count = 0;
        ArrayList<QuestionModel> question_attempted = new ArrayList<>();
        for (int i = 0; i < questions.size(); i++) {
            if (questions.get(i).getAttempted()) {

                question_attempted.add(questions.get(i));

                att_count++;
            } else {
                unatte_count++;
            }
        }
        SubmitTestModel submit_array = new SubmitTestModel();
        submit_array.setQuestion(question_attempted);
        submit_array.setTbname("user_answers");

        final Dialog dialog = new Dialog(getActivity());
        dialog.setCancelable(false);
        dialog.setContentView(view);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  submittest(submit_array);
                dialog.dismiss();

            }
        });

        dialog.show();

    }

    public void init() {
        mutableLiveDataEntity = new MutableLiveData<>();
    }
}
