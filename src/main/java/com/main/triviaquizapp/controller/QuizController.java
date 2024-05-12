package com.main.triviaquizapp.controller;

import com.main.triviaquizapp.model.Option;
import com.main.triviaquizapp.model.Question;
import com.main.triviaquizapp.model.Quiz;
import com.main.triviaquizapp.utils.jdm.QuestionJDM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.*;

public class QuizController {
    private Quiz quizQuestions;
    private Random random;
    private Question currentQuestion;
    private Set<Question> displayedQuestions;
    private List<Option> displayedOptions;

    public QuizController() throws Exception {
        quizQuestions = new QuestionJDM().getQuizData();
        random = new Random();
        displayedQuestions = new HashSet<>();
        shuffleQuestions();
    }

    private void shuffleQuestions() throws Exception {
        List<Question> shuffledQuestions = new ArrayList<>(quizQuestions.getQuestions());
        Collections.shuffle(shuffledQuestions);
        currentQuestion = shuffledQuestions.stream()
                .filter(q -> !displayedQuestions.contains(q))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No more questions to display"));
        displayedQuestions.add(currentQuestion);
    }

    public String getQuestionText() {
        return currentQuestion.getQuestionText();
    }

    public List<Option> getOptions() {
        return currentQuestion.getOptions();
    }

    private boolean isCorrectOption(Option option) {
        return option.getCorrect() == Boolean.TRUE;
    }

    public void correctAnswer(ActionEvent actionEvent, Option selectedOption) {
        if (isCorrectOption(selectedOption)) {
            System.out.println("Jawaban benar!");
            displayedOptions.remove(selectedOption);
            try {
                shuffleQuestions();
                initialize();
            } catch (Exception e) {
                System.out.println("No more questions to display");
            }
        } else {
            System.out.println("Salah");
        }
    }

    @FXML
    Label question;
    @FXML
    Button btnOption1, btnOption2, btnOption3, btnOption4;

    @FXML
    private void initialize() throws Exception {
        question.setText(getQuestionText());
        List<Option> options = getOptions();

        displayedOptions = new ArrayList<>(options);
        Collections.shuffle(displayedOptions);

        for (int i = 0; i < options.size(); i++) {
            Option option = displayedOptions.get(i);
            Button button = getButtonByIndex(i);
            button.setText(option.getAnswer());
            button.setOnAction(event -> correctAnswer(event, option));
        }
    }

    private Button getButtonByIndex(int index) {
        switch (index) {
            case 0:
                return btnOption1;
            case 1:
                return btnOption2;
            case 2:
                return btnOption3;
            case 3:
                return btnOption4;
            default:
                throw new IllegalArgumentException("Invalid button index: " + index);
        }
    }
}