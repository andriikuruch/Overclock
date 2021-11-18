package com.overclock.overclock.dao;

import com.overclock.overclock.model.Overclock;

import java.math.BigInteger;

public interface OverclockDAO {
    Overclock getOverclockById(BigInteger id);
    boolean save(Overclock overclock, BigInteger assemblyId);
    boolean update(BigInteger id, Overclock newOverclock);
    boolean delete(BigInteger id);
}
