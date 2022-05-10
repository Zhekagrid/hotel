package com.hrydziushka.finalproject.controller.filter;

import com.hrydziushka.finalproject.controller.command.CommandType;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

import static com.hrydziushka.finalproject.controller.RequestParameter.COMMAND;
import static com.hrydziushka.finalproject.controller.SessionAttribute.CURRENT_PAGE;

@WebFilter(urlPatterns = {"/controller","/pages/*"})
public class PageFilter implements Filter {

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {


        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String commandName = httpServletRequest.getParameter(COMMAND);
        HttpSession session = httpServletRequest.getSession();
        if (commandName != null && CommandType.valueOf(commandName.toUpperCase()) != CommandType.CHANGE_LOCALE) {


            //todo
            String currentPage = httpServletRequest.getServletPath()+"?" + httpServletRequest.getQueryString();
            Logger logger = LogManager.getLogger();
            logger.info(((HttpServletRequest) request).getServletPath());
            logger.info(currentPage);
            session.setAttribute(CURRENT_PAGE, currentPage);

        }

        chain.doFilter(request, response);
    }
}
