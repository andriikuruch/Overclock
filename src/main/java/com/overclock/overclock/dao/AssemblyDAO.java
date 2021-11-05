package com.overclock.overclock.dao;

import com.overclock.overclock.model.Assembly;

import java.math.BigInteger;
import java.util.List;

public interface AssemblyDAO {
    Assembly getById(BigInteger id);
    Assembly getByAuthor(String author);
    List<Assembly> getAll();
    List<Assembly> getAllByAuthor(String author);
    boolean save(Assembly assembly);
    boolean delete(BigInteger id);
}
