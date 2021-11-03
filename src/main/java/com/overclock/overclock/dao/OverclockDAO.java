package com.overclock.overclock.dao;

import com.overclock.overclock.model.Overclock;

public interface OverclockDAO {
    Overclock getOverclockById(int id);
    boolean save(Overclock overclock);
    boolean update(int id, Overclock newOverclock);
}
