package com.smartwebart.kingofquiz.model;
import androidx.room.Ignore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


public class QuestionModel implements Serializable {
    private boolean attempted;
    private boolean unattempted;
    private boolean marked_review;
    public String getTime_taken() {
        return time_taken;
    }
    public void setTime_taken(String time_taken) {
        this.time_taken = time_taken;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public String getNatureOfQues() {
        return natureOfQues;
    }

    public void setNatureOfQues(String natureOfQues) {
        this.natureOfQues = natureOfQues;
    }

    @SerializedName("difficulty_level")
    @Expose
    private String difficultyLevel;

    @SerializedName("nature_of_ques")
    @Expose
    private String natureOfQues;

    @Ignore
    private String time_taken;

    public String getSubmittedAnswerId() {
        return submittedAnswerId;
    }

    public void setSubmittedAnswerId(String submittedAnswerId) {
        this.submittedAnswerId = submittedAnswerId;
    }

    private String submittedAnswerId;

    public String getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(String answer_id) {
        this.answer_id = answer_id;
    }

    private String answer_id;

    public boolean getAttempted() {
        return attempted;
    }

    public void setAttempted(boolean attempted) {
        this.attempted = attempted;
    }

    public boolean getUnattempted() {
        return unattempted;
    }

    public void setUnattempted(boolean unattempted) {
        this.unattempted = unattempted;
    }

    public boolean getMarked_review() {
        return marked_review;
    }

    public void setMarked_review(boolean marked_review) {
        this.marked_review = marked_review;
    }


    private String id;



    @SerializedName("name_in_eng")
    @Expose
    private String nameInEng;



    @SerializedName("name_in_hi")
    @Expose
    private String nameInHi;

    @Ignore
    @SerializedName("mcqs_id")
    @Expose
    private String mcqsId;



    @SerializedName("exams_id")
    @Expose
    private String examsId;


    @SerializedName("topics_id")
    @Expose
    private String topicsId;


    @SerializedName("subjects_id")
    @Expose
    private String subjectsId;

    public String getTbname() {
        return tbname;
    }

    public void setTbname(String tbname) {
        this.tbname = tbname;
    }

    public String getApi_id() {
        return api_id;
    }

    public void setApi_id(String api_id) {
        this.api_id = api_id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Ignore
    private String tbname;
    @Ignore
    private String api_id;
    @Ignore
    private String userid;

    @Ignore
    @SerializedName(value = "answers", alternate = "answerdata"
    )
    private List<AnswerModel> answers;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameInEng() {
        return nameInEng;
    }

    public void setNameInEng(String nameInEng) {
        this.nameInEng = nameInEng;
    }

    public String getNameInHi() {
        return nameInHi;
    }

    public void setNameInHi(String nameInHi) {
        this.nameInHi = nameInHi;
    }

    public String getMcqsId() {
        return mcqsId;
    }

    public void setMcqsId(String mcqsId) {
        this.mcqsId = mcqsId;
    }

    public String getExamsId() {
        return examsId;
    }

    public void setExamsId(String examsId) {
        this.examsId = examsId;
    }

    public String getTopicsId() {
        return topicsId;
    }

    public void setTopicsId(String topicsId) {
        this.topicsId = topicsId;
    }

    public String getSubjectsId() {
        return subjectsId;
    }

    public void setSubjectsId(String subjectsId) {
        this.subjectsId = subjectsId;
    }

    public List<AnswerModel> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerModel> answers) {
        this.answers = answers;
    }

}
