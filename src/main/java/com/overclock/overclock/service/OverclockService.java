package com.overclock.overclock.service;

import com.overclock.overclock.model.Overclock;

public interface OverclockService {
    Overclock getOverclockById(int id);
    boolean save(Overclock overclock);
    boolean update(int id, Overclock newOverclock);
    Overclock getDefaultValues(int overclockId, int assemblyId);
    boolean validate(Overclock overclock);
}
