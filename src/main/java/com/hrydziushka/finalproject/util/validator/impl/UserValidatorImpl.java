package com.hrydziushka.finalproject.util.validator.impl;

import com.hrydziushka.finalproject.util.validator.UserValidator;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.hrydziushka.finalproject.Message.*;
import static com.hrydziushka.finalproject.controller.RequestParameter.*;
import static com.hrydziushka.finalproject.util.validator.ValidatorRegex.*;


public class UserValidatorImpl implements UserValidator {


    private static UserValidatorImpl instance = new UserValidatorImpl();

    private UserValidatorImpl() {
    }

    public static UserValidatorImpl getInstance() {
        return instance;
    }

    @Override
    public boolean validateSignUpForm(Map<String, String[]> signUpInformation) {
        boolean isFormValid = true;
        String login = signUpInformation.get(LOGIN)[0];
        String password = signUpInformation.get(PASSWORD)[0];
        String repeatPassword = signUpInformation.get(REPEAT_PASSWORD)[0];
        String email = signUpInformation.get(EMAIL)[0];


        if (!validateLogin(login)) {
            String[] loginData = new String[2];
            loginData[0] = login;
            loginData[1] = INCORRECT_LOGIN_MESSAGE;
            signUpInformation.put(LOGIN, loginData);
            isFormValid = false;
        }
        if (!validatePassword(password)) {
            String[] passwordData = new String[2];
            passwordData[0] = password;
            passwordData[1] = INCORRECT_PASSWORD_MESSAGE;
            signUpInformation.put(PASSWORD, passwordData);
            isFormValid = false;
        }
        if (!validateEmail(email)) {
            String[] emailData = new String[2];
            emailData[0] = email;
            emailData[1] = INCORRECT_EMAIL_MESSAGE;
            signUpInformation.put(EMAIL, emailData);
            isFormValid = false;
        }
        if (!password.equals(repeatPassword)) {
            String[] repeatPasswordData = new String[2];
            repeatPasswordData[0] = repeatPassword;
            repeatPasswordData[1] = INCORRECT_REPEAT_PASSWORD_MESSAGE;
            signUpInformation.put(REPEAT_PASSWORD, repeatPasswordData);
            isFormValid = false;
        }

        return isFormValid;

    }

    @Override
    public boolean validatePassword(String password) {
        Pattern pattern = Pattern.compile(PASSWORD_REGEX);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    @Override
    public boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @Override
    public boolean validateLogin(String login) {
        Pattern pattern = Pattern.compile(LOGIN_REGEX);
        Matcher matcher = pattern.matcher(login);
        return matcher.matches();
    }

    @Override
    public boolean validatePhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile(PHONE_NUMBER_REGEX);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
}
