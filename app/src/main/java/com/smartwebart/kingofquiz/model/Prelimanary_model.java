package com.smartwebart.kingofquiz.model;

import android.graphics.drawable.Drawable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Prelimanary_model {

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    private Drawable image;



    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;

    /*@SerializedName("image")
    @Expose
    private Object image;*/
    @SerializedName("exams_id")
    @Expose
    private String examsId;

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

 /*   public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }*/

    public String getExamsId() {
        return examsId;
    }

    public void setExamsId(String examsId) {
        this.examsId = examsId;
    }

}
