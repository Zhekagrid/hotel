package com.hrydziushka.finalproject.model.dao;

public final class ColumnName {
    //users columns
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    public static final String PHONE_NUMBER = "phone_number";
    public static final String BALANCE = "balance";
    public static final String ROLE = "role";
    public static final String STATUS = "status";
    public static final String USER_ID = "user_id";
    public static final String USERS_COUNT="users_count";
    public static final String USER_EXISTS ="user_exists";

    //apartments columns
    public static final String APARTMENT_ID = "apartment_id";
    public static final String PEOPLE_COUNT = "people_count";
    public static final String APARTMENT_CLASS = "class";
    public static final String PRICE_PER_DAY = "price_per_day";
    public static final String APARTMENT_NUMBER = "apartment_number";
    public static final String DESCRIPTION = "description";
    public static final String AVERAGE_RATING = "average_rating";
    public static final String APARTMENTS_COUNT = "apartments_count";
    //images
    public static final String IMAGE = "image";
    public static final String IMAGE_ID = "image_id";


    private ColumnName() {
    }
}
