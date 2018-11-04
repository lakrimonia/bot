package com.company;

public class Main {

    public static void main(String[] args) {
        Bot bot = new Bot();
        Conversation conversation = new Conversation(bot.topicContent, bot.questionAnswer);
        String chatId = "000";
        Agent agent = new Agent();
        while (conversation.continueConversation) {
            String message = agent.getUserRequest();
            String answer = bot.handleMessage(message, chatId);
            agent.sendBotAnswer(answer);
        }
    }
}
