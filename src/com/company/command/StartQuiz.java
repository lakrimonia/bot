package com.company.command;

import com.company.Conversation;
import com.company.State;

public class StartQuiz implements ICommand {
    private String description;
    private String userRequest;
    private String botAnswer;
    private Conversation conversation;
    private State state = State.INITIAL;

    public StartQuiz(Conversation conversation) {
        description = "запуск математической викторины.";
        userRequest = "викторина";
        botAnswer = "Отличный выбор! Математика -- царица наук!";
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
        return botAnswer + ("\n" + conversation.quiz.getQuestion());
    }

    @Override
    public String execute() {
        conversation.initializeQuiz();
        return getBotAnswer();
    }
}
