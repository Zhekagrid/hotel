package com.hrydziushka.finalproject.model.entity;

import java.math.BigDecimal;

public class User extends AbstractEntity {
    private String login;
    private String email;
    private String phoneNumber;
    private BigDecimal balance;
    private UserRole userRole;
    private UserStatus userStatus;

    public static Builder newBuilder() {
        return new User().new Builder();
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("login='").append(login).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", phoneNumber='").append(phoneNumber).append('\'');
        sb.append(", balance=").append(balance);
        sb.append(", userRole=").append(userRole);
        sb.append(", userStatus=").append(userStatus);
        sb.append('}');
        return sb.toString();
    }

    public class Builder {

        private Builder() {

        }

        public Builder setId(Long id) {
            User.this.setId(id);
            return this;
        }

        public Builder setLogin(String login) {
            User.this.login = login;
            return this;
        }

        public Builder setEmail(String email) {
            User.this.email = email;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            User.this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder setBalance(BigDecimal balance) {
            User.this.balance = balance;
            return this;
        }

        public Builder setUserRole(UserRole userRole) {
            User.this.userRole = userRole;
            return this;
        }

        public Builder setUserStatus(UserStatus userStatus) {
            User.this.userStatus = userStatus;
            return this;
        }


        public User build() {
            return User.this;
        }

    }


    public String getLogin() {
        return login;
    }


    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }
}
