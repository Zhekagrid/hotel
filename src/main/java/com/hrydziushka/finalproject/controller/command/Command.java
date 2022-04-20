package com.hrydziushka.finalproject.controller.command;

import com.hrydziushka.finalproject.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

public interface Command {
   Router execute(HttpServletRequest request) throws CommandException;
}
