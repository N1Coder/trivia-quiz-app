package com.main.triviaquizapp.model;

public class Option {
    Integer id;
    String answer;
    Boolean isCorrect;

    public Option(Integer id, String answer, Boolean isCorrect) {
        this.id = id;
        this.answer = answer;
        this.isCorrect = isCorrect;
    }

    public String getAnswer() {
        return answer;
    }

    public Boolean getCorrect() {
        return isCorrect;
    }

    public Integer getId() {
        return id;
    }
}