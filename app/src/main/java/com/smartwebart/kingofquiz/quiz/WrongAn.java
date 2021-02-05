package com.smartwebart.kingofquiz.quiz;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WrongAn {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name_in_en")
    @Expose
    private String nameInEn;
    @SerializedName("name_in_hi")
    @Expose
    private String nameInHi;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("description_hi")
    @Expose
    private Object descriptionHi;
    @SerializedName("is_right")
    @Expose
    private String isRight;
    @SerializedName("questions_id")
    @Expose
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

    public Object getDescriptionHi() {
        return descriptionHi;
    }

    public void setDescriptionHi(Object descriptionHi) {
        this.descriptionHi = descriptionHi;
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

}
