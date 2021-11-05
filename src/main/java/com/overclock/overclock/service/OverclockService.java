package com.overclock.overclock.service;

import com.overclock.overclock.model.Overclock;

import java.math.BigInteger;

public interface OverclockService {
    Overclock getOverclockById(BigInteger id);
    boolean save(Overclock overclock);
    boolean update(BigInteger id, Overclock newOverclock);
    Overclock getDefaultValues(BigInteger overclockId, BigInteger assemblyId);
    boolean validate(Overclock overclock);
}
