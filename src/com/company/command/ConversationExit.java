package com.company.command;

import com.company.Conversation;

public class ConversationExit implements ICommand {
    private String userRequest;
    private String botAnswer;
    private Conversation conversation;

    public ConversationExit(Conversation conversation) {
        userRequest = "бот, пока";
        botAnswer = "Пока! Жду нашей встречи!";
        this.conversation = conversation;
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
        conversation.continueConversation = false;
        if (conversation.quiz != null)
            conversation.quiz.isOver = true;
        return getBotAnswer();
    }
}
