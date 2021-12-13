package com.overclock.overclock.model;

import javax.validation.constraints.NotBlank;
import java.math.BigInteger;

public class ResetPasswordToken {
    private final BigInteger id;
    @NotBlank
    private final String resetPasswordToken;

    public ResetPasswordToken(BigInteger id, String resetPasswordToken) {
        this.id = id;
        this.resetPasswordToken = resetPasswordToken;
    }

    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public BigInteger getId() {
        return id;
    }
}
