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

    public Quiz(HashMap<String, String> questionAnswer, HashMap<String, String> topicContent) {
        isOver = false;
        score = 0;
        tries = 3;
        this.topicContent = topicContent;
        questions = new Stack<>();
        for (String question : questionAnswer.keySet()) {
            questions.push(new Pair<>(question, questionAnswer.get(question)));
        }
    }

    public String handle(String message, boolean isThereAnswer) {
        StringBuilder answer = new StringBuilder();
        String question = questions.peek().getKey();
        String rightAnswer = questions.peek().getValue();
        if (message.equals("выход"))
            isOver = true;
        else if (isThereAnswer)
            answer.append(message.equals(rightAnswer)
                    ? countAsRightAnswer(message)
                    : countAsWrongAnswer());
        else if (!isOver)
            answer.append(question);
        if (isOver)
            answer.append(String.format(topicContent.get("КОНЕЦ ИГРЫ"), score));
        return answer.toString();
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
            result.append(handle(message, false));
        return result.toString();
    }

    private String countAsWrongAnswer() {
        StringBuilder result = new StringBuilder();
        result.append(topicContent.get("НЕВЕРНЫЙ ОТВЕТ"));
        tries--;
        if (tries > 0)
            result.append(String.format(topicContent.get("КОЛИЧЕСТВО ПОПЫТОК"), tries));
        else {
            result.append(topicContent.get("ПРОИГРЫШ"));
            isOver = true;
        }
        return result.toString();
    }
}
