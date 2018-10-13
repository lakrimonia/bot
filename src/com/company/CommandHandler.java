package com.company;

import com.company.command.*;

import java.util.HashMap;


public class CommandHandler {
    private HashMap<String, ICommand> systemCommands;
    private HashMap<String, ICommand> initialCommands;
    private HashMap<String, ICommand> quizCommands;
    private HashMap<State, HashMap<String, ICommand>> stateAllowedCommands;


    public CommandHandler(Conversation conversation) {
        stateAllowedCommands = new HashMap<>();
        systemCommands = getCommands(new ICommand[]{
                new ConversationExit(conversation),
                new ShowHelp(conversation)
        });
        initialCommands = getCommands(new ICommand[]{
                new StartQuiz(conversation)
        }, State.INITIAL);
        quizCommands = getCommands(new ICommand[]{
                new QuizExit(conversation)
        }, State.QUIZ);
        ShowHelp helpCommand = (ShowHelp)systemCommands.get("бот, покажи список команд");
        helpCommand.createHelpText(systemCommands, stateAllowedCommands);
    }

    private HashMap<String, ICommand> getCommands(ICommand[] commands) {
        HashMap<String, ICommand> result = new HashMap<>();
        for (ICommand command : commands) {
            result.put(command.getUserRequest(), command);
        }
        return result;
    }

    private HashMap<String, ICommand> getCommands(ICommand[] commands, State state) {
        HashMap<String, ICommand> result = getCommands(commands);
        stateAllowedCommands.put(state, result);
        return result;
    }


    public String tryHandleAsCommand(String message, State state) {

        if (systemCommands.containsKey(message)) {
            return systemCommands.get(message).execute();
        }
        HashMap<String, ICommand> allowedCommands = stateAllowedCommands.get(state);
        if (allowedCommands.containsKey(message))
            return allowedCommands.get(message).execute();
        return null;
    }
}
