package com.company.command;

import com.company.Conversation;
import com.company.State;

public class ConversationExit implements ICommand {
    private String description;
    private String userRequest;
    private String botAnswer;
    private Conversation conversation;
    private State state = null;

    public ConversationExit(Conversation conversation) {
        description = "завершение диалога.";
        userRequest = "бот, пока";
        botAnswer = "Пока! Жду нашей встречи!\r\n";
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
        conversation.stop();
        return getBotAnswer();
    }
}
