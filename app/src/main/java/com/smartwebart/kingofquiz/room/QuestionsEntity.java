package com.smartwebart.kingofquiz.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "questionsEntity")
public class QuestionsEntity {

    public Boolean getAttempted() {
        return attempted;
    }

    public void setAttempted(Boolean attempted) {
        this.attempted = attempted;
    }

    public Boolean getUnattempted() {
        return unattempted;
    }

    public void setUnattempted(Boolean unattempted) {
        this.unattempted = unattempted;
    }

    public Boolean getMarked_review() {
        return marked_review;
    }

    public void setMarked_review(Boolean marked_review) {
        this.marked_review = marked_review;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameInHi() {
        return nameInHi;
    }

    public void setNameInHi(String nameInHi) {
        this.nameInHi = nameInHi;
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

    public String getNameInEng() {
        return nameInEng;
    }

    public void setNameInEng(String nameInEng) {
        this.nameInEng = nameInEng;
    }
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    private String id;


   @ColumnInfo(name = "nameInHi")
    private String nameInHi;

   @ColumnInfo(name = "examsId")
    private String examsId;

   @ColumnInfo(name = "topicsId")
    private String topicsId;

   @ColumnInfo(name = "subjectsId")
    private String subjectsId;


    @ColumnInfo(name = "nameInEng")
    private String nameInEng;

    @ColumnInfo(name = "natureOfQues")
    private String natureOfQues;

    @ColumnInfo(name = "difficultyLevel")
    private String difficultyLevel;

    public String getNatureOfQues() {
        return natureOfQues;
    }

    public void setNatureOfQues(String natureOfQues) {
        this.natureOfQues = natureOfQues;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

   /* public String getTime_taken() {
        return time_taken;
    }

    public void setTime_taken(String time_taken) {
        this.time_taken = time_taken;
    }

    @ColumnInfo(name = "time_taken")
    private String time_taken;*/


    public String getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(String answer_id) {
        this.answer_id = answer_id;
    }

    @ColumnInfo(name = "answer_id")
    private String answer_id;


    @ColumnInfo(name = "attempted")
    private Boolean attempted;

    @ColumnInfo(name = "unattempted")
    private Boolean unattempted;

    @ColumnInfo(name = "marked_review")
    private Boolean marked_review;


    public String getSubmittedAnswerId() {
        return submittedAnswerId;
    }

    public void setSubmittedAnswerId(String submittedAnswerId) {
        this.submittedAnswerId = submittedAnswerId;
    }

    @ColumnInfo(name = "submittedAnswerId")
    private String submittedAnswerId;




}