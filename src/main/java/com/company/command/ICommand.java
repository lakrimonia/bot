package com.company.command;

import com.company.State;

public interface ICommand {
    State getState();

    String getDescription();

    String getUserRequest();

    String getBotAnswer();

    String execute();
}
