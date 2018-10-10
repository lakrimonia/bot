package com.company.command;

public class ShowHelp implements ICommand {
    private String userRequest;
    private String botAnswer;

    public ShowHelp() {
        userRequest = "бот, покажи список команд";
        StringBuilder sbBotAnswer = new StringBuilder();
        sbBotAnswer.append("У меня есть команды:\r\n");
        sbBotAnswer.append("\"Бот, пока!\" -- завершение диалога.\r\n");
        sbBotAnswer.append("\"Бот, покажи список команд\" -- показывает это сообщение.\r\n");
        botAnswer = sbBotAnswer.toString();
    }

    @Override
    public String getUserRequest() {
        return userRequest;
    }

    @Override
    public String getBotAnswer() {
        return botAnswer;
    }

    @Override
    public String execute() {
        return getBotAnswer();
    }
}
