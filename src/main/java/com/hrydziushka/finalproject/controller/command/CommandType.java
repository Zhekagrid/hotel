package com.hrydziushka.finalproject.controller.command;

import com.hrydziushka.finalproject.controller.command.impl.*;
import com.hrydziushka.finalproject.controller.command.impl.go.GoToSignInPageCommand;
import com.hrydziushka.finalproject.controller.command.impl.go.GoToSignUpCommand;

public enum CommandType {
    GO_TO_SIGN_IN_PAGE(new GoToSignInPageCommand()),
    GO_TO_SIGN_UP_PAGE(new GoToSignUpCommand()),


    SIGN_UP(new SignUpCommand()),
    SIGN_IN(new SignInCommand()),
    SIGN_OUT(new SignOutCommand()),
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
