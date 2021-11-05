package com.overclock.overclock.service;

import com.overclock.overclock.model.Assembly;
import com.overclock.overclock.util.RequestAssembly;

import java.math.BigInteger;
import java.util.List;

public interface AssemblyService {
    Assembly getAssemblyById(BigInteger id);
    Assembly getAssembliesByIdWithSomeComments(BigInteger id, int limit);
    List<Assembly> getAssembliesByAuthor(String author);
    List<Assembly> search(String searchParameter);
    List<Assembly> getAll();
    boolean save(RequestAssembly assembly);
    boolean delete(BigInteger id);
    boolean updateScore(BigInteger id);
    boolean validate(RequestAssembly assembly);
}
