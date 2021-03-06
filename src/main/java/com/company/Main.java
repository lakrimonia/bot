package com.company;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class Main {

    private static Logger consoleLogger = Logger.getLogger("console");
    private static Agent agent = new Agent();
    private static Reader reader = new Reader();
    private static final String chatId = "000";

    public static void main(String[] args) {
        DOMConfigurator.configure("log.xml");
        Bot bot = new Bot(reader.makeTopicContent(), reader.makeQuestionAnswer());

        while (true) {
            try {
                String message = agent.getUserRequest();
                String answer = bot.handleMessage(message, chatId); 
                agent.sendBotAnswer(answer);
                
            } catch (Exception e) {
                consoleLogger.log(Level.ERROR, "Exception:", e);
            }
        }
    }
}
