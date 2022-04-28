package com.hrydziushka.finalproject.controller.command;

import com.hrydziushka.finalproject.controller.command.impl.*;

import java.util.Locale;

public enum CommandType {
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    SIGNUP(new SignUpCommand()),
    CHANGE_LOCALE(new ChangeLocaleCommand()),
    DEFAULT(new DefaultCommand());
    Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public static Command defineCommand(String commandStr) {
        CommandType currentCommandType;
        try {

            currentCommandType = CommandType.valueOf(commandStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            currentCommandType = DEFAULT;
        }

        return currentCommandType.command;
    }
}
