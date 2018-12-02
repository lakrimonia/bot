package com.company.command;

import com.company.Conversation;
import com.company.State;

public class QuizExit implements ICommand {
    private String description;
    private String userRequest;
    private String botAnswer;
    private Conversation conversation;
    private State state = State.QUIZ;

    public QuizExit(Conversation conversation) {
        description = "выход из игры.";
        userRequest = "бот, выход";
        botAnswer = String.format("Игра окончена. Твой счёт: %d\r\n", conversation.getQuiz().getScore());
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
        return botAnswer;
    }

    @Override
    public String execute() {
        conversation.getQuiz().stop();
        return getBotAnswer();
    }
}
