package com.overclock.overclock.controller.request;

import org.springframework.lang.Nullable;

import javax.validation.constraints.*;


public class RequestUser {
    @Size(min = 4, max = 23)
    protected String username;

    protected String password;

    @Email
    @Nullable
    protected String email;

    public RequestUser(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getName() {
        return username;
    }

    public void setName(String username) {
        this.username = username;
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
}
