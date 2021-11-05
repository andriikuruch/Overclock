package com.overclock.overclock.model;

import javax.validation.constraints.*;
import java.math.BigInteger;
import java.util.Date;

abstract class AbstractUser {

    @Positive
    protected BigInteger id;

    @NotNull
    @NotEmpty
    @Size(min = 4, max = 23)
    protected String name;

    @NotNull
    @NotEmpty
    protected String password;

    @NotNull
    @NotEmpty
    @Email
    protected String email;

    @NotNull
    protected Date registrationDate;

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
}
