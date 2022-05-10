package com.hrydziushka.finalproject.controller.command.impl;

import com.hrydziushka.finalproject.controller.command.Command;
import com.hrydziushka.finalproject.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;

public class SignOutCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        return null;
    }
}
