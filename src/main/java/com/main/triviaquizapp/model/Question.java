package com.main.triviaquizapp.model;

import java.util.List;

public class    Question {
    private Integer questionId;
    private String questionText;
    private List<Option>options;

    public Question(Integer questionId, String questionText, List<Option> options){
        this.questionId = questionId;
        this.questionText = questionText;
        this.options = options;

    }


    public int getQuestionId() {
        return questionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<Option> getOptions() {
        return options;
    }
}