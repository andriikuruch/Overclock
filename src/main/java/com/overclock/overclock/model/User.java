package com.overclock.overclock.model;

import com.overclock.overclock.model.enums.Role;

import javax.validation.constraints.*;
import java.math.BigInteger;
import java.util.Date;

public class User {
    @Positive
    private BigInteger id;

    @NotNull
    @NotEmpty
    @Size(min = 4, max = 23)
    private String name;

    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    @NotEmpty
    @Email
    private String email;

    @NotNull
    private Date registrationDate;

    @NotNull
    private Role role;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getUserName() {
        return name;
    }

    public void setUserName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

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

        public Builder setRole(Role role) {
            user.role = role;
            return this;
        }

        public User build() {
            return user;
        }
    }
}