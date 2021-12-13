package com.overclock.overclock.controller.request;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;

public class UserIdRequest {
    @NotNull
    private BigInteger userId;

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }
}
