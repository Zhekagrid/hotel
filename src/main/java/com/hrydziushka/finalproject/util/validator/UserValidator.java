package com.hrydziushka.finalproject.util.validator;

public interface UserValidator {
    boolean validatePassword(String password);

    boolean validateEmail(String email);

    boolean validateLogin(String login);

    boolean validatePhoneNumber(String phoneNumber);
}
