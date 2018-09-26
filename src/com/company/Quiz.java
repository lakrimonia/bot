package com.company;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Stack;

public class Quiz {
    private int score;
    private int tries;
    private Stack<Pair<String, String>> questions;
    public boolean isOver;

    public Quiz(HashMap<String, String> questionAnswer) {
        isOver = false;
        score = 0;
        tries = 3;
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
        else if (isThereAnswer) {
            if (message.equals(rightAnswer)) {
                score += 100;
                answer.append(String.format("Верно! Ты получаешь 100 очков. Твой счёт: %d\r\n", score));
                questions.pop();
                if (questions.isEmpty()) {
                    answer.append("Ты правильно ответил на все вопросы! :)\r\n");
                    isOver = true;
                } else
                    answer.append(handle(message, false));
            } else {
                answer.append("Неверно!");
                tries--;
                if (tries > 0)
                    answer.append(String.format("У тебя осталось %d попыток. Подумай и ответь ещё раз.\r\n", tries));
                else {
                    answer.append("У тебя больше не осталось попыток.\r\n");
                    isOver = true;
                }
            }
        } else if (!isOver)
            answer.append(question);
        if (isOver)
            answer.append(String.format("Игра окончена. Твой счёт: %d\r\n", score));
        return answer.toString();
    }
}
