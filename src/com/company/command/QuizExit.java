package com.company.command;

import com.company.Quiz;

public class QuizExit implements ICommand {
    private String userRequest;
    private String botAnswer;
    private Quiz quiz;

    public QuizExit(Quiz quiz) {
        userRequest = "выход";
        botAnswer = String.format("Игра окончена. Твой счёт: %d", quiz.getScore());
        this.quiz = quiz;
    }

    @Override
    public String getUserRequest() {
        return userRequest;
    }

    @Override
    public String getBotAnswer() {
        return botAnswer;
    }

    @Override
    public String execute() {
        quiz.isOver = true;
        return getBotAnswer();
    }
}
