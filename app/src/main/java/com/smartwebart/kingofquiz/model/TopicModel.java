package com.smartwebart.kingofquiz.model;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TopicModel implements Serializable {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("max_time")
    @Expose
    private String maxTime;
    @SerializedName("image")
    @Expose
    private Object image;
    @SerializedName("subjects_id")
    @Expose
    private String subjectsId;
    @SerializedName("exams_id")
    @Expose
    private String examsId;
    @SerializedName("correct")
    @Expose
    private Integer correct;
    @SerializedName("incorrect")
    @Expose
    private Integer incorrect;
    @SerializedName("accuracy")
    @Expose
    private Float accuracy;
    @SerializedName("total_ques")
    @Expose
    private Integer totalQues;
    @SerializedName("topic_attempted")
    @Expose
    private Boolean topicAttempted;
    @SerializedName("marks")
    @Expose
    private Integer marks;
    @SerializedName("topper_score")
    @Expose
    private Integer topperScore;
    @SerializedName("air")
    @Expose
    private Integer air;

    public String getIspaid() {
        return ispaid;
    }

    public void setIspaid(String ispaid) {
        this.ispaid = ispaid;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    @SerializedName("ispaid")
    @Expose
    private String ispaid;
    @SerializedName("amt")
    @Expose
    private String amt;

    public String getSyllPdf() {
        return syllPdf;
    }

    public void setSyllPdf(String syllPdf) {
        this.syllPdf = syllPdf;
    }

    @SerializedName("ques_pdf")
    @Expose
    private Object quesPdf;
    @SerializedName("syll_pdf")
    @Expose
    private String syllPdf;

 /*   @SerializedName("user_answer")
    @Expose
    private List<UserAnswer> userAnswer = null;*/

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(String maxTime) {
        this.maxTime = maxTime;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

    public String getSubjectsId() {
        return subjectsId;
    }

    public void setSubjectsId(String subjectsId) {
        this.subjectsId = subjectsId;
    }

    public String getExamsId() {
        return examsId;
    }

    public void setExamsId(String examsId) {
        this.examsId = examsId;
    }

    public Integer getCorrect() {
        return correct;
    }

    public void setCorrect(Integer correct) {
        this.correct = correct;
    }

    public Integer getIncorrect() {
        return incorrect;
    }

    public void setIncorrect(Integer incorrect) {
        this.incorrect = incorrect;
    }

    public Float getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Float accuracy) {
        this.accuracy = accuracy;
    }

    public Integer getTotalQues() {
        return totalQues;
    }

    public void setTotalQues(Integer totalQues) {
        this.totalQues = totalQues;
    }

    public Boolean getTopicAttempted() {
        return topicAttempted;
    }

    public void setTopicAttempted(Boolean topicAttempted) {
        this.topicAttempted = topicAttempted;
    }

    public Integer getMarks() {
        return marks;
    }

    public void setMarks(Integer marks) {
        this.marks = marks;
    }

    public Integer getTopperScore() {
        return topperScore;
    }

    public void setTopperScore(Integer topperScore) {
        this.topperScore = topperScore;
    }

    public Integer getAir() {
        return air;
    }

    public void setAir(Integer air) {
        this.air = air;
    }

 /*   public List<UserAnswer> getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(List<UserAnswer> userAnswer) {
        this.userAnswer = userAnswer;
    }*/

}


