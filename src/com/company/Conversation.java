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
        commandHandler = new CommandHandler(this, quiz);
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
        //StringBuilder answer = new StringBuilder();
        message = message.toLowerCase();
        String answer = null;
        
        switch (state) {
        	case INITIAL:
        		answer = commandHandler.tryHandleAsCommand(message, state);
        		break;
    		
        	case QUIZ:
        		answer = quiz.handle(message, true);
        }
        
        if (answer == null) {
        	return topicContent.get("НЕКОРРЕКТНАЯ КОМАНДА");
        }
        return answer;

        /*if (commands.keySet().contains(message)) {
            answer.append(commands.get(message).get());
            if (state == State.QUIZ)
                answer.append(quiz.handle(message, false));
        } else
            switch (state) {
                case INITIAL:
                    switch (message) {
                        case "математика":
                            answer.append(topicContent.get("МАТЕМАТИКА"));
                            state = State.QUIZ;
                            answer.append(quiz.handle(message, false));
                            break;
                        default:
                            answer.append(topicContent.get("НЕКОРРЕКТНАЯ КОМАНДА"));
                            break;
                    }
                    break;
                case QUIZ:
                    answer.append(quiz.handle(message, true));
                    if (quiz.isOver) {
                        state = State.INITIAL;
                        answer.append(topicContent.get("ПОИГРАТЬ ЕЩЁ РАЗ"));
                    }
                    break;
            }
        return answer.toString();*/
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
