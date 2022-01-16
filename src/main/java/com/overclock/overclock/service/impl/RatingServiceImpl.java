package com.overclock.overclock.service.impl;

import com.overclock.overclock.model.Assembly;
import com.overclock.overclock.service.AssemblyService;
import com.overclock.overclock.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Scope("singleton")
public class RatingServiceImpl implements RatingService {
    @Autowired
    private AssemblyService assemblyService;
    private List<Assembly> assemblies;

    @Override
    public List<Assembly> calculateTopWithoutOverclock() {
        assemblies = getAssemblies();
        assemblies.removeIf(assembly -> assembly.getOverclock() != null || assembly.getScore() == null || assembly.getScore().intValue() == 0);
        return assemblies;
    }

    @Override
    public List<Assembly> calculateTopWithOverclock() {
        assemblies = getAssemblies();
        assemblies.removeIf(assembly -> assembly.getOverclock() == null || assembly.getScore() == null || assembly.getScore().intValue() == 0);
        return assemblies;
    }

    private List<Assembly> getAssemblies() {
        return assemblyService.getSortedByScoreAssemblies();
    }
}
