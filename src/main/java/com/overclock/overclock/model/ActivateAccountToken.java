package com.overclock.overclock.model;

import javax.validation.constraints.NotBlank;
import java.math.BigInteger;

public class ActivateAccountToken {
    private final BigInteger id;

    @NotBlank
    private final String activateAccountToken;

    public ActivateAccountToken(BigInteger id, String activateAccountToken) {
        this.id = id;
        this.activateAccountToken = activateAccountToken;
    }

    public BigInteger getId() {
        return id;
    }

    public String getActivateAccountToken() {
        return activateAccountToken;
    }
}
