package com.company.command;

import com.company.Conversation;
import com.company.State;

import java.util.HashMap;

public class ShowHelp implements ICommand {
    private String description;
    private String userRequest;
    private Conversation conversation;
    private State state = null;
    private HashMap<String, ICommand> systemCommands;
    private HashMap<State, HashMap<String, ICommand>> stateAllowedCommands;

    public ShowHelp(Conversation conversation, HashMap<String, ICommand> systemCommands, HashMap<State, HashMap<String, ICommand>> stateAllowedCommands) {
        description = "я выведу список команд, которые могу исполнить";
        userRequest = "бот, покажи список команд";
        this.conversation = conversation;
        this.systemCommands = systemCommands;
        this.stateAllowedCommands = stateAllowedCommands;
    }

    private String getCommandText(ICommand command) {
        return String.format("%s -- %s", command.getUserRequest(), command.getDescription()) + "\r\n";
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getUserRequest() {
        return userRequest;
    }

    @Override
    public String getBotAnswer() {
        StringBuilder result = new StringBuilder();
        result.append("У меня есть команды:\r\n");
        for (ICommand command : systemCommands.values())
            result.append(getCommandText(command));
        for (ICommand command : stateAllowedCommands.get(conversation.getState()).values())
            result.append(getCommandText(command));
        return result.toString();
    }

    @Override
    public String execute() {
        return getBotAnswer();
    }
}
