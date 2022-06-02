package com.hrydziushka.finalproject.controller;

import com.hrydziushka.finalproject.controller.command.Command;
import com.hrydziushka.finalproject.controller.command.CommandType;
import com.hrydziushka.finalproject.controller.command.Router;
import com.hrydziushka.finalproject.exception.CommandException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

import static com.hrydziushka.finalproject.controller.RequestParameter.COMMAND;

@WebServlet(name = "helloServlet", urlPatterns = "/controller")
public class Controller extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void init() {


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String commandName = request.getParameter(COMMAND);
        try {
            Command command = CommandType.defineCommand(commandName);
            logger.info("Current command "+commandName);
            Router router = command.execute(request);
            String page = router.getPage();
            switch (router.getType()) {
                case FORWARD -> request.getRequestDispatcher(page).forward(request, response);
                case REDIRECT -> response.sendRedirect(request.getContextPath() + page);
                default -> request.getRequestDispatcher(PagePath.ERROR_404_PAGE).forward(request, response);
            }
        } catch (CommandException | IOException e) {
            logger.error("An error occurred while executing the command: " + commandName, e);
            throw new ServletException(e);
        }

    }

    @Override
    public void destroy() {

    }
}