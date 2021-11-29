package com.overclock.overclock.service;

import com.overclock.overclock.model.Overclock;

import java.math.BigInteger;

public interface OverclockService {
    boolean save(Overclock overclock,  BigInteger assemblyId);
    boolean update(BigInteger id, Overclock newOverclock);
    boolean delete(BigInteger id);
    boolean deleteByAssemblyId(BigInteger assemblyId);
    Overclock getOverclockById(BigInteger id);
    Overclock getDefaultOverclockValues(BigInteger assemblyId);
    boolean isValidOverclock(Overclock overclock);

}
