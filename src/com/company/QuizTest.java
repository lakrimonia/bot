package com.company;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class QuizTest {
    private Quiz quiz;
    private HashMap<String, String> topicContent;
    private HashMap<String, String> questionAnswer;

    @org.junit.jupiter.api.BeforeEach
    void setUp() throws IOException {
        Bot bot = new Bot();
        topicContent = bot.topicContent;
        questionAnswer = bot.questionAnswer;
        this.quiz = new Quiz(questionAnswer, topicContent);
    }

    @org.junit.jupiter.api.Test
    void rightAnswerGiven() {
        int initialScore = quiz.getScore();
        int initialTries = quiz.getTries();
        String question = quiz.handle("", false);
        String answer = questionAnswer.get(question);
        String actual = quiz.handle(answer, true).split("\r\n")[0] + "\r\n";
        assertEquals(String.format(topicContent.get("ВЕРНЫЙ ОТВЕТ"), quiz.getScore()), actual);
        assertTrue(quiz.getScore() > initialScore);
        assertTrue(quiz.getTries() == initialTries);
    }

    @org.junit.jupiter.api.Test
    void wrongAnswerGiven() {
        int initialTries = quiz.getTries();
        int initialScore = quiz.getScore();
        String question = quiz.handle("", false);
        String answer = "wrong answer";
        String actual = quiz.handle(answer, true).split("\r\n")[0] + "\r\n";
        assertEquals(topicContent.get("НЕВЕРНЫЙ ОТВЕТ"), actual);
        assertTrue(quiz.getScore() == initialScore);
        assertTrue(quiz.getTries() + 1 == initialTries);
    }

    @org.junit.jupiter.api.Test
    void winAfterAllRightAnswers(){
        int questionsCount = questionAnswer.size();
        for(int i=0;i<questionsCount;i++)
            rightAnswerGiven();
        assertTrue(quiz.isOver);
    }

    @org.junit.jupiter.api.Test
    void failAfterThreeWrongAnswers() {
        int tries = 3;
        for (int i = 0; i < tries; i++)
            wrongAnswerGiven();
        assertTrue(quiz.isOver);
    }
}
