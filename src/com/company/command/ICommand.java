package com.company.command;

public interface ICommand {
    String getDescription();

    String getUserRequest();

    String getBotAnswer();

    String execute();
}
