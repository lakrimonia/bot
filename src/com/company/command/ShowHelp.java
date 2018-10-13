package com.company.command;

import com.company.Conversation;
import com.company.State;

import java.util.HashMap;

public class ShowHelp implements ICommand {
    private String description;
    private String userRequest;
    private Conversation conversation;
    private HashMap<State, String> botAnswer;

    public ShowHelp(Conversation conversation) {
        description = "показывает это сообщение.";
        userRequest = "бот, покажи список команд";
        this.conversation = conversation;
    }

    public void createHelpText(HashMap<String, ICommand> systemCommands, HashMap<State, HashMap<String, ICommand>> stateAllowedCommands) {
        botAnswer = new HashMap<>();
        StringBuilder systemCommandsText = new StringBuilder();
        systemCommandsText.append("У меня есть команды:\r\n");
        for (ICommand command : systemCommands.values())
            systemCommandsText.append(getCommandText(command));
        for (State state : stateAllowedCommands.keySet()){
            StringBuilder text = new StringBuilder(systemCommandsText);
            for (ICommand command : stateAllowedCommands.get(state).values())
                text.append(getCommandText(command));
            botAnswer.put(state, text.toString());
        }
    }

    private String getCommandText(ICommand command){
        return String.format("%s -- %s", command.getUserRequest(), command.getDescription()) + "\r\n";
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
        return botAnswer.get(conversation.getState());
    }

    @Override
    public String execute() {
        return getBotAnswer();
    }
}
