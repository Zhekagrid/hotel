package com.hrydziushka.finalproject.util.validator;

import java.util.Map;

public interface UserValidator {
    boolean validateSignUpForm(Map<String,String[]> signUpInformation);
    boolean validatePassword(String password);

    boolean validateEmail(String email);

    boolean validateLogin(String login);

    boolean validatePhoneNumber(String phoneNumber);
}
