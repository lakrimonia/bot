package com.company.command;

import com.company.Conversation;
import com.company.State;

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
        botAnswer.append(conversation.quiz.handle(userRequest, false));
        return botAnswer.toString();
    }

    @Override
    public String execute() {
        conversation.initializeQuiz();
        conversation.state = State.QUIZ;
        return getBotAnswer();
    }
}
