package com.smartwebart.kingofquiz.retrofit;


import com.smartwebart.kingofquiz.model.NewSeriesModel;
import com.smartwebart.kingofquiz.model.QuestionModel;
import com.smartwebart.kingofquiz.model.SubjectModel;
import com.smartwebart.kingofquiz.model.SubmittedAnswerModel;
import com.smartwebart.kingofquiz.model.Total_Score_Model;
import com.smartwebart.kingofquiz.quiz.OTPModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface EndPointInterface {

    @POST("api/controller/api/common/selectOneByColumn.php")
    @FormUrlEncoded
    Call<List<SubjectModel>> getSubjects(@Field("tbname") String tbname
                                        /* @Field("column") String column,
                                         @Field("id") String id)*/);



    @POST("api/controller/api/common/selectAllByColumn.php")
    @FormUrlEncoded
    Call<List<NewSeriesModel>> getNewSeries(@Field("tbname") String subjects,
                                            @Field("column") String status,
                                            @Field("api_key") String api_key,
                                            @Field("loginid") String loginid,
                                            @Field("colval") String enable);

    @POST("api/controller/api/common/selectAll.php")
    @FormUrlEncoded
    Call<List<NewSeriesModel>> getCourses(@Field("tbname") String tbname);



    @POST("api/controller/api/common/selectMulti.php")
    @FormUrlEncoded
    Call<Total_Score_Model> getTopic(@Field("tbname") String tbname
                                    /* @Field("loginid") String loginid,
                                     @Field("subject_id") String subjects_id,
                                     @Field("exams_id") String exams_id )*/);


    @POST("api/controller/api/common/selectMultiple.php")
    @FormUrlEncoded
    Call<List<QuestionModel>> getQuestions(@Field("topics_id") String topics_id,
                                           @Field("subjects_id") String level);

    @POST("api/controller/api/common/responseAnswer.php")
    @FormUrlEncoded
    Call<OTPModel> saveAnswer(@Field("data") String question_attempted);

    @POST("api/controller/api/common/questionAnswer.php")
    @FormUrlEncoded
    Call<List<SubmittedAnswerModel>> questionAnswerReview(@Field("api_key") String apikey,
                                                          @Field("loginid") String loginid,
                                                          @Field("topic_id") String topics_id,
                                                          @Field("exams_id") String exams_id);



}
