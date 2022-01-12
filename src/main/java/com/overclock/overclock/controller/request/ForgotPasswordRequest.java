package com.overclock.overclock.controller.request;

import javax.validation.constraints.Email;

public class ForgotPasswordRequest {
    @Email
    private String email;

    protected String frontUrl;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFrontUrl() {
        return frontUrl;
    }

    public void setFrontUrl(String frontUrl) {
        this.frontUrl = frontUrl;
    }
}
