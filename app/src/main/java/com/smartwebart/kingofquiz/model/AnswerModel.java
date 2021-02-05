package com.smartwebart.kingofquiz.model;

import androidx.room.Ignore;

import java.io.Serializable;

public class AnswerModel implements Serializable {

    public boolean getAnswer_marked() {
        return answer_marked;
    }

    public void setAnswer_marked(boolean answer_marked) {
        this.answer_marked = answer_marked;
    }

    private boolean answer_marked;

    private String id;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName_in_en() {
        return name_in_en;
    }

    public void setName_in_en(String name_in_en) {
        this.name_in_en = name_in_en;
    }

    public String getName_in_hi() {
        return name_in_hi;
    }

    public void setName_in_hi(String name_in_hi) {
        this.name_in_hi = name_in_hi;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIs_right() {
        return is_right;
    }

    public void setIs_right(String is_right) {
        this.is_right = is_right;
    }

    public String getQuestions_id() {
        return questions_id;
    }

    public void setQuestions_id(String questions_id) {
        this.questions_id = questions_id;
    }

    private String name_in_en;


    private String name_in_hi;


    private String description;


    private String is_right;

    private String questions_id;

    public char getAlphabets() {
        return alphabets;
    }

    public void setAlphabets(char alphabets) {
        this.alphabets = alphabets;
    }

    @Ignore
    private char alphabets;

}
