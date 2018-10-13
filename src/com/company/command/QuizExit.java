package com.company.command;

import com.company.Conversation;

public class QuizExit implements ICommand {
    private String description;
    private String userRequest;
    private String botAnswer;
    private Conversation conversation;

    public QuizExit(Conversation conversation) {
        description = "выход из игры.";
        userRequest = "выход";
        botAnswer = String.format("Игра окончена. Твой счёт: %d", conversation.quiz.getScore());
        this.conversation = conversation;
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
        conversation.quiz.isOver = true;
        return getBotAnswer();
    }
}
