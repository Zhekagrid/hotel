package com.hrydziushka.finalproject.controller.command.impl;

import com.hrydziushka.finalproject.controller.command.Command;
import com.hrydziushka.finalproject.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Random;

public class DefaultCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        return null;
    }
}
