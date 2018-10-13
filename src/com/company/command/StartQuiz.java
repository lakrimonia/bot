package com.company.command;

import com.company.Conversation;
import com.company.State;

public class StartQuiz implements ICommand {
    private String description;
    private String userRequest;
    private StringBuilder botAnswer;
    private Conversation conversation;
    private State state = State.INITIAL;

    public StartQuiz(Conversation conversation) {
        description = "запускает викторину.";
        userRequest = "математика";
        botAnswer = new StringBuilder();
        botAnswer.append("Отличный выбор! Математика -- царица наук!");
        this.conversation = conversation;
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public String getDescription() {
        return description;
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
