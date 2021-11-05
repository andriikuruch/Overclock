package com.overclock.overclock.util;

import javax.validation.constraints.*;


public class RequestUser {
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

    public static class Builder {

        private RequestUser requestUser;

        public Builder() {
            this.requestUser = new RequestUser();
        }

        public Builder setUserName(String name) {
            requestUser.name = name;
            return this;
        }

        public Builder setPassword(String password) {
            requestUser.password = password;
            return this;
        }

        public Builder setEmail(String email) {
            requestUser.email = email;
            return this;
        }

        public RequestUser build() {
            return requestUser;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
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
}
