package com.company.command;

import com.company.State;

public class ConversationExit implements ICommand {
    private String description;
    private String userRequest;
    private String botAnswer;
    private State state = null;

    public ConversationExit() {
        description = "завершение диалога.";
        userRequest = "бот, пока";
        botAnswer = "Пока! Жду нашей встречи!\r\n";
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
        return getBotAnswer();
    }
}
