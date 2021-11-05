package com.overclock.overclock.model;

import java.math.BigInteger;
import java.util.Date;

public class Admin extends AbstractUser {

    public static class Builder {

        private Admin admin;

        public Builder() {
            this.admin = new Admin();
        }

        public Builder setId(BigInteger id) {
            admin.id = id;
            return this;
        }

        public Builder setUserName(String name) {
            admin.name = name;
            return this;
        }

        public Builder setPassword(String password) {
            admin.password = password;
            return this;
        }

        public Builder setEmail(String email) {
            admin.email = email;
            return this;
        }

        public Builder setRegistrationDate(Date registrationDate) {
            admin.registrationDate = registrationDate;
            return this;
        }

        public Admin build() {
            return admin;
        }
    }
}