package com.hrydziushka.finalproject.util;


import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncryptor {
    private static PasswordEncryptor instance = new PasswordEncryptor();

    private PasswordEncryptor() {
    }

    public static PasswordEncryptor getInstance() {
        return instance;
    }

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean checkPasswordMatching(String password, String hashPassword) {
        return BCrypt.checkpw(password, hashPassword);
    }
}
