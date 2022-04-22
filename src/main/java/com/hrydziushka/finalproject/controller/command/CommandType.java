package com.hrydziushka.finalproject.controller.command;

import com.hrydziushka.finalproject.controller.command.impl.DefaultCommand;
import com.hrydziushka.finalproject.controller.command.impl.LoginCommand;
import com.hrydziushka.finalproject.controller.command.impl.LogoutCommand;
import com.hrydziushka.finalproject.controller.command.impl.SignUpCommand;

import java.util.Locale;

public enum CommandType {
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    SIGNUP(new SignUpCommand()),
    DEFAULT(new DefaultCommand());
    Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public static Command defineCommand(String commandStr) {
        CommandType currentCommandType;
        try {

            currentCommandType = CommandType.valueOf(commandStr.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException e) {
            currentCommandType = DEFAULT;
        }

        return currentCommandType.command;
    }
}
