package com.smartwebart.kingofquiz.model;

import java.io.Serializable;
import java.util.List;

public class SubmitTestModel implements Serializable {

    public List<QuestionModel> getQuestion() {
        return question;
    }

    public void setQuestion(List<QuestionModel> question) {
        this.question = question;
    }

    private List<QuestionModel> question;

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

    private String tbname;
    private String api_id;
    private String userid;
}
