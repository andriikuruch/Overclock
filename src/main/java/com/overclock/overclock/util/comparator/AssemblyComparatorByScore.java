package com.overclock.overclock.util.comparator;

import com.overclock.overclock.model.Assembly;

import java.util.Comparator;

public class AssemblyComparatorByScore implements Comparator<Assembly> {
    @Override
    public int compare(Assembly assembly1, Assembly assembly2) {
        return Integer.compare(assembly1.getScore().intValue(), assembly2.getScore().intValue());
    }
}
