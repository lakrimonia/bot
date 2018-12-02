package com.company;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class Main {

    private static Logger rootLogger = Logger.getLogger(Main.class.getName());


    public static void main(String[] args) {
        DOMConfigurator.configure("log.xml");
        Bot bot = new Bot();
        Conversation conversation = new Conversation(bot.topicContent, bot.questionAnswer);
        String chatId = "000";
        Agent agent = new Agent();
        while (conversation.getContinueConversation()) {
            try {
                String message = agent.getUserRequest();
                String answer = bot.handleMessage(message, chatId);
                agent.sendBotAnswer(answer);
            } catch (Exception e) {
                rootLogger.log(Level.ERROR, "Exeption:", e);
                throw e;
            }
        }
    }
}
