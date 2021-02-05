package com.smartwebart.kingofquiz.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubjectModel {

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

    public String getTotalTopic() {
        return totalTopic;
    }

    public void setTotalTopic(String totalTopic) {
        this.totalTopic = totalTopic;
    }

    @SerializedName("total_topic")
    @Expose
    private String totalTopic;
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

}