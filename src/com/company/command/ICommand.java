package com.company.command;

public interface ICommand {
    String getUserRequest();

    String getBotAnswer();

    String execute();
}
