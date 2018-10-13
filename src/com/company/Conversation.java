package com.company;

import java.util.HashMap;
import java.util.function.Supplier;

public class Conversation {
    private State state;
    public Quiz quiz;
    private CommandHandler commandHandler;
    private HashMap<String, Supplier<String>> commands;
    private HashMap<String, String> topicContent; // Basic background information
    private HashMap<String, String> questionAnswer;
    public boolean continueConversation;

    public Conversation(HashMap<String, String> topicContent, HashMap<String, String> questionAnswer) {
        quiz = new Quiz(questionAnswer, topicContent, this);
        commandHandler = new CommandHandler(this);
        state = State.INITIAL;
        commands = new HashMap<>();
        commands.put("бот, пока", this::sayBye);
        commands.put("бот, покажи список команд", this::showHelp);
        this.topicContent = topicContent;
        this.questionAnswer = questionAnswer;
        continueConversation = true;
    }

    public void start() {
        Agent agent = new Agent();
        agent.print(topicContent.get("ПРИВЕТСТВИЕ"));
        agent.print(topicContent.get("СПРАВКА"));
        agent.print(topicContent.get("ПРЕДЛОЖЕНИЕ ПОИГРАТЬ"));

        while (continueConversation) {
            String message = agent.getMessage();
            agent.print(handle(message));
        }
    }

    public String handle(String message) {
        message = message.toLowerCase();
        String answer = null;
        
        switch (state) {
        	case INITIAL:
        		answer = commandHandler.tryHandleAsCommand(message, state);
        		break;
        	case QUIZ:
        		answer = quiz.handle(message);
        		if (quiz.isOver) {
        			state = State.INITIAL;
        		}
        }
        
        if (answer == null) {
        	return topicContent.get("НЕКОРРЕКТНАЯ КОМАНДА");
        }
        return answer;
    }

    private String sayBye() {
        continueConversation = false;
        if (quiz != null)
            quiz.isOver = true;
        return topicContent.get("ПРОЩАНИЕ");
    }

    public String showHelp() {
        return topicContent.get("СПРАВКА");
    }

    public State getState() {
    	return state;
    }

    public void initializeQuiz(){
        state = State.QUIZ;        	
    }
    
    public String tryHandle(String message) {
    	return commandHandler.tryHandleAsCommand(message, state);
    }
}
