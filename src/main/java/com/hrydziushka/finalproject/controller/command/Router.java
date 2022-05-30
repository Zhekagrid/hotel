package com.hrydziushka.finalproject.controller.command;

public class Router {
    private String page;
    private RouterType type;

    public Router(String page, RouterType type) {
        this.page = page;
        this.type = type;
    }

    public String getPage() {
        return page;
    }

    public RouterType getType() {
        return type;
    }

    public enum RouterType{
        FORWARD,
        REDIRECT;
    }
}
