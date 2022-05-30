package com.hrydziushka.finalproject.util.validator;

public final class ValidatorRegex {

    public static final String LOGIN_REGEX = "[A-Za-z\\d_\\-]{5,15}";
    public static final String PASSWORD_REGEX = "(?=.*\\d)(?=.*[_\\-!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[\\dA-Za-z!@#$%^&*_\\-]{7,15}";
    public static final String EMAIL_REGEX = "[\\dA-Za-z._%+-]+@([\\dA-Za-z]){3,10}\\.(com|net|org|by|ru|de)";
    public static final String PHONE_NUMBER_REGEX = "^\\+\\d{3}((-\\d{2,3}-\\d{3}-\\d{2}-\\d{2})|(\\d{9,10}))$";

    private ValidatorRegex() {
    }

}
