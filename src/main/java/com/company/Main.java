package com.company;

import java.util.logging.Level;
import java.util.logging.Logger;

import ru.thematdev.bot.TelegramBot;

import java.io.IOException;
import java.util.logging.FileHandler;




public class Main {
	
	private static Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args)  {
    	try {
    		FileHandler fh = new FileHandler("log//LogApp");
    		logger.addHandler(fh);
    	} catch (IOException e) {
    		logger.log(Level.SEVERE, "/n Exeption:", e);
    	}
        Bot bot = new Bot();
        Conversation conversation = new Conversation(bot.topicContent, bot.questionAnswer);
        String chatId = "000";
        Agent agent = new Agent();
        while (conversation.continueConversation) {
        	try {
        		String message = agent.getUserRequest();
        		String answer = bot.handleMessage(message, chatId);
        		agent.sendBotAnswer(answer);
        	} catch (Exception e) {
        		logger.log(Level.SEVERE, "/n Exeption:", e);
        		throw e;
        	}
        	
        }
    }
}
