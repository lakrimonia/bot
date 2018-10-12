package com.company;

import java.util.HashMap;
import com.company.command.*;


public class CommandHandler {
	private HashMap <String, ICommand> systemCommands;
	private HashMap <String, ICommand> initialCommands;
	private HashMap <String, ICommand> quizCommands;
	
	
	public CommandHandler(Conversation conversation, Quiz quiz) {
		systemCommands = new HashMap<>();
		String userRequest = new ConversationExit(conversation).getUserRequest();
		systemCommands.put(userRequest, new ConversationExit(conversation));
		userRequest = new ShowHelp().getUserRequest();
		systemCommands.put(userRequest, new ShowHelp());
		
		initialCommands = new HashMap<>();
		userRequest = new StartQuiz(conversation).getUserRequest();
		initialCommands.put(userRequest, new StartQuiz(conversation));
		
		quizCommands = new HashMap<>();
		userRequest = new QuizExit(quiz).getUserRequest();
		quizCommands.put(userRequest, new QuizExit(quiz));
		
	}
	
	
	public String tryHandleAsCommand(String message, State state) {
		
		if (systemCommands.containsKey(message)) {
			return systemCommands.get(message).execute();
		}
		HashMap <String, ICommand> allowedCommands = new HashMap <>();
		
		switch (state) {
		
			case INITIAL:
				allowedCommands = initialCommands;
				break;
				
			case QUIZ:
				allowedCommands = quizCommands;
				break;
		}
		
		if (allowedCommands.containsKey(message)) {
			return allowedCommands.get(message).execute();
		}	
		return null;
		
	}

}
