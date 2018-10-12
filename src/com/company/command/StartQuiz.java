package com.company.command;

import com.company.Conversation;

public class StartQuiz implements ICommand {
    private String userRequest;
    private StringBuilder botAnswer;
    private Conversation conversation;

    public StartQuiz(Conversation conversation) {
        userRequest = "математика";
        botAnswer = new StringBuilder();
        botAnswer.append("Отличный выбор! Математика -- царица наук!");
        this.conversation = conversation;
    }

    @Override
    public String getUserRequest() {
        return userRequest;
    }

    @Override
    public String getBotAnswer() {
        botAnswer.append(conversation.quiz.getQuestion());
        return botAnswer.toString();
    }

    @Override
    public String execute() {
        conversation.initializeQuiz();
        return getBotAnswer();
    }
}
