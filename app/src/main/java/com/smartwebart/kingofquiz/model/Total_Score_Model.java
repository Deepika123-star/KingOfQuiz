package com.smartwebart.kingofquiz.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Total_Score_Model {

    @SerializedName("topic_data")
    @Expose
    private List<TopicModel> topicData = null;
    @SerializedName("average_mark")
    @Expose
    private Double averageMark;
    @SerializedName("attempted")
    @Expose
    private String attempted;
    @SerializedName("total_topic")
    @Expose
    private Integer totalTopic;
    @SerializedName("average_attemp")
    @Expose
    private Integer averageAttemp;

    public List<TopicModel> getTopicData() {
        return topicData;
    }

    public void setTopicData(List<TopicModel> topicData) {
        this.topicData = topicData;
    }

    public Double getAverageMark() {
        return averageMark;
    }

    public void setAverageMark(Double averageMark) {
        this.averageMark = averageMark;
    }

    public String getAttempted() {
        return attempted;
    }

    public void setAttempted(String attempted) {
        this.attempted = attempted;
    }

    public Integer getTotalTopic() {
        return totalTopic;
    }

    public void setTotalTopic(Integer totalTopic) {
        this.totalTopic = totalTopic;
    }

    public Integer getAverageAttemp() {
        return averageAttemp;
    }

    public void setAverageAttemp(Integer averageAttemp) {
        this.averageAttemp = averageAttemp;
    }

}
