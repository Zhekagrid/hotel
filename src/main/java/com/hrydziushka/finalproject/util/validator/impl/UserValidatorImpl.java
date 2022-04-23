package com.hrydziushka.finalproject.util.validator.impl;

import com.hrydziushka.finalproject.util.validator.UserValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidatorImpl implements UserValidator {
    //todo regex
     private static final String LOGIN_REGEX = "[\\p{Alpha}\\p{Digit}_-]{5,15}";
    private static final String PASSWORD_REGEX = "(?=.*\\p{Digit})(?=.*[_\\-!@#$%^&*])(?=.*\\p{Lower})(?=.*\\p{Upper})[\\p{Digit}\\p{Alpha}!@#$%^&*_\\-]{7,}";
    private static final String EMAIL_REGEX = "[\\p{Digit}\\p{Alpha}._%+-]+@([\\p{Digit}\\p{Alpha}]){3,10}\\.(com|net|org|by|ru|de)";
    private static final String PHONE_NUMBER_REGEX = "^\\+\\d{3}((-\\d{2,3}-\\d{3}-\\d{2}-\\d{2})|(\\d{9,10}))$";


    private static UserValidatorImpl instance = new UserValidatorImpl();

    private UserValidatorImpl() {
    }

    public static UserValidatorImpl getInstance() {
        return instance;
    }

    @Override
    public boolean validatePassword(String password) {
        Pattern pattern=Pattern.compile(PASSWORD_REGEX);
        Matcher matcher=pattern.matcher(password);
        return matcher.matches();
    }

    @Override
    public boolean validateEmail(String email) {
       Pattern pattern=Pattern.compile(EMAIL_REGEX);
       Matcher matcher=pattern.matcher(email);
       return matcher.matches();
    }

    @Override
    public boolean validateLogin(String login) {
        Pattern pattern=Pattern.compile(LOGIN_REGEX);
        Matcher matcher=pattern.matcher(login);
        return matcher.matches();
    }

    @Override
    public boolean validatePhoneNumber(String phoneNumber) {
        Pattern pattern=Pattern.compile(PHONE_NUMBER_REGEX);
        Matcher matcher=pattern.matcher(phoneNumber);
        return matcher.matches();
    }
}
