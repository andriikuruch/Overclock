package com.overclock.overclock.service.impl;

import com.overclock.overclock.model.Assembly;
import com.overclock.overclock.service.AssemblyService;
import com.overclock.overclock.service.RatingService;
import com.overclock.overclock.util.comparator.AssemblyComparatorByScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Scope("singleton")
public class RatingServiceImpl implements RatingService {
    @Autowired
    private AssemblyService assemblyService;
    private static final AssemblyComparatorByScore comparatorByScore = new AssemblyComparatorByScore();

    @Override
    public List<Assembly> calculateTopWithoutOverclock() {
        List<Assembly> assemblies = assemblyService.getAll();
        assemblies.removeIf(assembly -> assembly.getOverclock() != null || assembly.getScore() == null);
        assemblies.sort(comparatorByScore);
        Collections.reverse(assemblies);
        return assemblies;
    }

    @Override
    public List<Assembly> calculateTopWithOverclock() {
        List<Assembly> assemblies = assemblyService.getAll();
        assemblies.removeIf(assembly -> assembly.getOverclock() == null || assembly.getScore() == null);
        assemblies.sort(comparatorByScore);
        Collections.reverse(assemblies);
        return assemblies;
    }
}
