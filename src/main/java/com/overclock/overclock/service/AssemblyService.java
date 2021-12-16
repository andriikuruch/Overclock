package com.overclock.overclock.service;

import com.overclock.overclock.model.Assembly;

import java.math.BigInteger;
import java.util.List;

public interface AssemblyService {
    Assembly getAssemblyById(BigInteger id);
    Assembly getAssemblyByIdWithSomeComments(BigInteger id, BigInteger limit);
    List<Assembly> getAssembliesByAuthorId(BigInteger id);
    List<Assembly> getAssembliesByAuthorName(String author);
    List<Assembly> search(String searchParameter);
    List<Assembly> getAllAssemblies();
    boolean save(Assembly assembly);
    boolean delete(BigInteger id);
    boolean updateScore(BigInteger id, BigInteger newScore);
    void checkAssemblyValidation(Assembly assembly);
}

