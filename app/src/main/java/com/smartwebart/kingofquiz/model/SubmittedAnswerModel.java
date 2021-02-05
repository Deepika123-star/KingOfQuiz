package com.smartwebart.kingofquiz.model;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SubmittedAnswerModel implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("exams_id")
    @Expose
    private String examsId;
    @SerializedName("subjects_id")
    @Expose
    private String subjectsId;
    @SerializedName("topics_id")
    @Expose
    private String topicsId;
    @SerializedName("questions_id")
    @Expose
    private String questionsId;
    @SerializedName("answers_id")
    @Expose
    private String answersId;
    @SerializedName("user_id")
    @Expose
    private String userId;

    public String getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(String timeTaken) {
        this.timeTaken = timeTaken;
    }

    @SerializedName("time_taken")
    @Expose
    private String timeTaken;

  /*  @Ignore
    @SerializedName("question")
    @Expose
    private QuestionModel question;
*/
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExamsId() {
        return examsId;
    }

    public void setExamsId(String examsId) {
        this.examsId = examsId;
    }

    public String getSubjectsId() {
        return subjectsId;
    }

    public void setSubjectsId(String subjectsId) {
        this.subjectsId = subjectsId;
    }

    public String getTopicsId() {
        return topicsId;
    }

    public void setTopicsId(String topicsId) {
        this.topicsId = topicsId;
    }

    public String getQuestionsId() {
        return questionsId;
    }

    public void setQuestionsId(String questionsId) {
        this.questionsId = questionsId;
    }

    public String getAnswersId() {
        return answersId;
    }

    public void setAnswersId(String answersId) {
        this.answersId = answersId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

  /*  public QuestionModel getQuestion() {
        return question;
    }

    public void setQuestion(QuestionModel question) {
        this.question = question;
    }
*/
}
