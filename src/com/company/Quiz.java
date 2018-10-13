package com.company;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Stack;

public class Quiz {
    private int score;
    private int tries;
    private Stack<Pair<String, String>> questions;
    private HashMap<String, String> topicContent;
    public boolean isOver;
    private Conversation conversation;

    public Quiz(HashMap<String, String> questionAnswer, HashMap<String, String> topicContent,
                Conversation conversation) {
        this.conversation = conversation;
        isOver = false;
        score = 0;
        tries = 3;
        this.topicContent = topicContent;
        questions = new Stack<>();
        for (String question : questionAnswer.keySet()) {
            questions.push(new Pair<>(question, questionAnswer.get(question)));
        }
    }

    public String handle(String message) {
        StringBuilder answerBuilder = new StringBuilder();
        String rightAnswer = questions.peek().getValue();
        String answer = conversation.tryHandle(message);
        if (answer != null) return answer;

        answerBuilder.append(message.equals(rightAnswer)
                ? countAsRightAnswer(message)
                : countAsWrongAnswer());
        return answerBuilder.toString();

    }

    private String countAsRightAnswer(String message) {
        StringBuilder result = new StringBuilder();
        score += 100;
        result.append(String.format(topicContent.get("ВЕРНЫЙ ОТВЕТ"), score));
        questions.pop();
        if (questions.isEmpty()) {
            result.append(topicContent.get("ПОБЕДА"));
            isOver = true;
        } else
            result.append(getQuestion());
        return result.toString();
    }

    private String countAsWrongAnswer() {
        StringBuilder result = new StringBuilder();
        result.append(topicContent.get("НЕВЕРНЫЙ ОТВЕТ"));
        tries--;
        if (tries > 0) {
            result.append(String.format(topicContent.get("КОЛИЧЕСТВО ПОПЫТОК"), tries));
            result.append(getQuestion());
        } else {
            result.append(topicContent.get("ПРОИГРЫШ"));
            isOver = true;
        }
        return result.toString();
    }

    public String getQuestion() {
        return questions.peek().getKey();
    }

    public int getScore() {
        return score;
    }

    public int getTries() {
        return tries;
    }
}
