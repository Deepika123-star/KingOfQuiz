package com.smartwebart.kingofquiz.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "submittedAnswerEntity")
public class SubmittedAnswerEntity {
    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
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

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    private String id;

    @ColumnInfo(name = "status")
    private String status;


    @ColumnInfo(name = "examsId")
    private String examsId;

    @ColumnInfo(name = "subjectsId")
    private String subjectsId;

    @ColumnInfo(name = "topicsId")
    private String topicsId;


    @ColumnInfo(name = "questionsId")
    private String questionsId;


    @ColumnInfo(name = "answersId")
    private String answersId;

    @ColumnInfo(name = "userId")
    private String userId;

}
