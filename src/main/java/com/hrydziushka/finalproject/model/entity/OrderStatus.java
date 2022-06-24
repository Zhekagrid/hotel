package com.hrydziushka.finalproject.model.entity;

public enum OrderStatus {
    DECLINED,
    CONFIRMED,
    PENDING;


    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
