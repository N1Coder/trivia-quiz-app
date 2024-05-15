package com.main.triviaquizapp.controller;

import com.main.triviaquizapp.model.Option;
import com.main.triviaquizapp.model.Question;
import com.main.triviaquizapp.model.Quiz;
import com.main.triviaquizapp.utils.jdm.QuestionJDM;
import com.main.triviaquizapp.utils.music.Music;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.*;

public class QuizController {
    private Quiz quizQuestions;
    private Random random;
    private Question currentQuestion;
    private Set<Question> displayedQuestions;
    private List<Option> displayedOptions;
    private String numberOfQuestion = "01";
    private int elapsedSecond = 0;
    private int timeMinutes;
    private int timeSeconds;

    Music sound = new Music();


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


    public void correctAnswer(ActionEvent actionEvent, Option selectedOption) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        if (isCorrectOption(selectedOption)) {
            System.out.println("Jawaban benar!");
            sound.playMusic("correct.wav",1f);

            displayedOptions.remove(selectedOption);
            try {
                shuffleQuestions();
                numberOfQuestion = increaseAndPad(numberOfQuestion);
                initialize();
            } catch (Exception e) {
                System.out.println("No more questions to display");
            }
        } else {
            System.out.println("Salah");
            sound.playMusic("wrong.wav",1f);
        }
    }

    public static String increaseAndPad(String numberOfQuestion) {
        int increasedNumber = Integer.parseInt(numberOfQuestion) + 1;
        return String.format("%02d", increasedNumber);
    }


    public void showQuiz(){
        this.question.setText(getQuestionText());
        List<Option> options = getOptions();

        displayedOptions = new ArrayList<>(options);
        Collections.shuffle(displayedOptions);

        for (int i = 0; i < options.size(); i++) {
            Option option = displayedOptions.get(i);
            Button button = getButtonByIndex(i);
            button.setText(option.getAnswer());
            button.setOnMouseEntered(event -> {
                try {
                    sound.playMusic("hoverButtonSound.wav",1f);
                } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
                    e.printStackTrace();
                }
            });
            button.setOnAction(event -> {
                try {
                    correctAnswer(event, option);
                } catch (UnsupportedAudioFileException e) {
                    throw new RuntimeException(e);
                } catch (LineUnavailableException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    Timer quizTimer = new Timer();
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            elapsedSecond++;
            timeSeconds = (elapsedSecond/1) % 60;
            timeMinutes = (elapsedSecond/60) % 60;
            second.setText(String.format("%02d", timeSeconds));
            minutes.setText(String.format("%02d", timeMinutes));
        }
    };

    public void startTimer(){
        quizTimer.scheduleAtFixedRate(task,1000,1000);
    }

    @FXML
    Text question,number,second,minutes;
    @FXML
    Button btnOption1, btnOption2, btnOption3, btnOption4;

    @FXML
    private void initialize() throws Exception {

        showQuiz();
        number.setText(numberOfQuestion);
        startTimer();


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