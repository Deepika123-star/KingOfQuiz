package com.smartwebart.kingofquiz.quiz;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class OTPModel implements Serializable {


    @SerializedName("right_ans")
    @Expose
    private List<Object> rightAns = null;
    @SerializedName("wrong_ans")
    @Expose
    private List<WrongAn> wrongAns = null;

    public List<Object> getRightAns() {
        return rightAns;
    }

    public void setRightAns(List<Object> rightAns) {
        this.rightAns = rightAns;
    }

    public List<WrongAn> getWrongAns() {
        return wrongAns;
    }

    public void setWrongAns(List<WrongAn> wrongAns) {
        this.wrongAns = wrongAns;
    }

}
