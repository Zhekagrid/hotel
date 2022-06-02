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
import static com.hrydziushka.finalproject.controller.SessionAttribute.PREV_PAGE_URI;


@WebFilter(urlPatterns = {"/controller", "/pages/*"},dispatcherTypes = {DispatcherType.FORWARD,DispatcherType.REQUEST})
public class PageFilter implements Filter {
private static final Logger logger=LogManager.getLogger();
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {


        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String commandName = httpServletRequest.getParameter(COMMAND);
        HttpSession session = httpServletRequest.getSession();
        try {

            logger.info("Current command "+commandName);
            if (commandName != null && CommandType.valueOf(commandName.toUpperCase()) != CommandType.CHANGE_LOCALE) {
                String prevPageUri= (String) session.getAttribute(CURRENT_PAGE);
                session.setAttribute(PREV_PAGE_URI,prevPageUri);
                String currentPage =   "/controller?" + httpServletRequest.getQueryString();
                session.setAttribute(CURRENT_PAGE, currentPage);


            }
        } catch (IllegalArgumentException e) {
            //todo default command
//            String prevPageUri= (String) session.getAttribute(CURRENT_PAGE);
//            session.setAttribute(PREV_PAGE_URI,prevPageUri);
//            String currentPage =   "/controller?" + httpServletRequest.getQueryString();
//            session.setAttribute(CURRENT_PAGE, currentPage);

        }
        chain.doFilter(request, response);
    }
}
