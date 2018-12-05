package com.company;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QuizTest {
    private Quiz quiz;
    private HashMap<String, String> topicContent;
    private HashMap<String, String> questionAnswer;
    private Reader reader = new Reader();

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        topicContent = reader.makeTopicContent();
        questionAnswer = reader.makeQuestionAnswer();
        Conversation conversation = new Conversation(topicContent, questionAnswer);
        this.quiz = new Quiz(questionAnswer, topicContent, conversation);
    }

    @org.junit.jupiter.api.Test
    void rightAnswerGiven() {
        int initialScore = quiz.getScore();
        int initialTries = quiz.getTries();
        String question = quiz.getQuestion();
        String answer = questionAnswer.get(question);
        String actual = quiz.handle(answer).split("\r\n")[0] + "\r\n";
        assertEquals(String.format(topicContent.get("ВЕРНЫЙ ОТВЕТ"), quiz.getScore()), actual);
        assertTrue(quiz.getScore() > initialScore);
        assertTrue(quiz.getTries() == initialTries);
    }

    @org.junit.jupiter.api.Test
    void wrongAnswerGiven() {
        int initialTries = quiz.getTries();
        int initialScore = quiz.getScore();
        String answer = "wrong answer";
        String actual = quiz.handle(answer).split("\r\n")[0];
        assertEquals(topicContent.get("НЕВЕРНЫЙ ОТВЕТ").split("\r\n")[0], actual);
        assertTrue(quiz.getScore() == initialScore);
        assertTrue(quiz.getTries() + 1 == initialTries);
    }

    @org.junit.jupiter.api.Test
    void winAfterAllRightAnswers() {
        int questionsCount = questionAnswer.size();
        for (int i = 0; i < questionsCount; i++)
            rightAnswerGiven();
        assertTrue(quiz.getIsOver());
    }

    @org.junit.jupiter.api.Test
    void failAfterThreeWrongAnswers() {
        int tries = 3;
        for (int i = 0; i < tries; i++)
            wrongAnswerGiven();
        assertTrue(quiz.getIsOver());
    }
}
