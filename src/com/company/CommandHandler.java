package com.company;

import com.company.command.*;

import java.util.HashMap;


public class CommandHandler {
    private HashMap<String, ICommand> systemCommands;
    private HashMap<State, HashMap<String, ICommand>> stateAllowedCommands;

    public CommandHandler(Conversation conversation) {
        ICommand[] commands = new ICommand[]{
                new ConversationExit(conversation),
                new QuizExit(conversation),
                new ShowHelp(conversation),
                new StartQuiz(conversation)
        };
        systemCommands = new HashMap<>();
        stateAllowedCommands = new HashMap<>();
        sortCommands(commands);
        ShowHelp helpCommand = (ShowHelp) systemCommands.get("бот, покажи список команд");
        helpCommand.createHelpText(systemCommands, stateAllowedCommands);
    }

    private void sortCommands(ICommand[] commands) {
        for (ICommand command : commands) {
            State state = command.getState();
            if (state == null)
                systemCommands.put(command.getUserRequest(), command);
            else {
                if (stateAllowedCommands.containsKey(state))
                    stateAllowedCommands.get(state).put(command.getUserRequest(), command);
                else {
                    stateAllowedCommands.put(state, new HashMap<>());
                    stateAllowedCommands.get(state).put(command.getUserRequest(), command);
                }
            }
        }
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
