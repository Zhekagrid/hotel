package com.hrydziushka.finalproject.controller.command.impl;

import com.hrydziushka.finalproject.controller.command.Command;
import com.hrydziushka.finalproject.controller.command.Router;
import com.hrydziushka.finalproject.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

public class SignUpCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        return null;
    }
}
