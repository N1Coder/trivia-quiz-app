package com.main.triviaquizapp.model;

import com.main.triviaquizapp.entity.Trivia;

import java.util.List;

public class Quiz extends Trivia {
    private List<Question> questions;

    public Quiz(){
        this.questions = questions;
    }

    public List<Question> getQuestions() {
        return questions;
    }
}
