package com.smartwebart.kingofquiz.room;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "answerEntity"/*, foreignKeys = @ForeignKey(entity = QuestionsEntity.class,
        parentColumns = "id",
        childColumns = "questions_id",
        onDelete = ForeignKey.CASCADE)*/)
public class AnswerEntity {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    private String id;

    @ColumnInfo(name = "name_in_en")

    private String nameInEn;

    @ColumnInfo(name = "name_in_hi")
    private String nameInHi;


    @ColumnInfo(name = "description")
    private String description;


    @ColumnInfo(name = "is_right")
    private String isRight;


    @ColumnInfo(name = "questions_id")
    private String questionsId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameInEn() {
        return nameInEn;
    }

    public void setNameInEn(String nameInEn) {
        this.nameInEn = nameInEn;
    }

    public String getNameInHi() {
        return nameInHi;
    }

    public void setNameInHi(String nameInHi) {
        this.nameInHi = nameInHi;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsRight() {
        return isRight;
    }

    public void setIsRight(String isRight) {
        this.isRight = isRight;
    }

    public String getQuestionsId() {
        return questionsId;
    }

    public void setQuestionsId(String questionsId) {
        this.questionsId = questionsId;
    }

    public Boolean getAnswer_marked() {
        return answer_marked;
    }

    public void setAnswer_marked(Boolean answer_marked) {
        this.answer_marked = answer_marked;
    }

    @ColumnInfo(name = "answer_marked")
    private Boolean answer_marked;
}
