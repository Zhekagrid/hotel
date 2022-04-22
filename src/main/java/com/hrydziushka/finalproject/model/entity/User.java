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
}
