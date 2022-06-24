package com.hrydziushka.finalproject.controller.command;

import com.hrydziushka.finalproject.controller.command.impl.*;
import com.hrydziushka.finalproject.controller.command.impl.admin.SaveUserChangesCommand;
import com.hrydziushka.finalproject.controller.command.impl.go.*;

public enum CommandType {
    GO_TO_SIGN_IN_PAGE(new GoToSignInPageCommand()),
    GO_TO_SIGN_UP_PAGE(new GoToSignUpPageCommand()),
    GO_TO_HOME_PAGE(new GoToHomePageCommand()),
    GO_TO_APARTMENTS_PAGE(new GoToApartmentsPageCommand()),
    GO_TO_BOOK_APARTMENT_PAGE(new GoToBookApartmentPageCommand()),

    SAVE_USER_CHANGES(new SaveUserChangesCommand()),


    SIGN_UP(new SignUpCommand()),
    SIGN_IN(new SignInCommand()),
    SIGN_OUT(new SignOutCommand()),

    BOOK_APARTMENT(new BookApartmentCommand()),


    CHANGE_LOCALE(new ChangeLocaleCommand()),
    DEFAULT(new DefaultCommand());
    Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public static Command defineCommand(String commandStr) {
        CommandType currentCommandType;

        try {
            if (commandStr != null) {
                currentCommandType = CommandType.valueOf(commandStr.toUpperCase());
            } else {
                currentCommandType = DEFAULT;
            }
        } catch (IllegalArgumentException e) {
            currentCommandType = DEFAULT;
        }

        return currentCommandType.command;
    }
}
