package com.main.triviaquizapp.utils.jdm;

import com.google.gson.Gson;
import com.main.triviaquizapp.model.Option;
import com.main.triviaquizapp.model.Question;
import com.main.triviaquizapp.model.Quiz;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;

public class QuestionJDM {

    public Quiz getQuizData() throws FileNotFoundException {
        File file = new File("quiz.json");
        FileReader fileReader = new FileReader(file);

        Gson gson = new Gson();
        Quiz quiz = gson.fromJson(fileReader, Quiz.class);

        return quiz;
    }

}
