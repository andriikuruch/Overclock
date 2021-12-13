package com.overclock.overclock.controller.request;

import javax.validation.constraints.NotBlank;

public class ResetPasswordRequest {
    @NotBlank
    private String resetPasswordToken;

    @NotBlank
    private String newPassword;

    public ResetPasswordRequest(String resetPasswordToken, String newPassword) {
        this.resetPasswordToken = resetPasswordToken;
        this.newPassword = newPassword;
    }

    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
