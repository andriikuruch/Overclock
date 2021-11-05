package com.overclock.overclock.model;

import java.math.BigInteger;
import java.util.Date;

public class User extends AbstractUser {

    public static class Builder {

        private User user;

        public Builder() {
            this.user = new User();
        }

        public Builder setId(BigInteger id) {
            user.id = id;
            return this;
        }

        public Builder setUserName(String name) {
            user.name = name;
            return this;
        }

        public Builder setPassword(String password) {
            user.password = password;
            return this;
        }

        public Builder setEmail(String email) {
            user.email = email;
            return this;
        }

        public Builder setRegistrationDate(Date registrationDate) {
            user.registrationDate = registrationDate;
            return this;
        }

        public User build() {
            return user;
        }
    }
}