package com.overclock.overclock.service.impl;

import com.overclock.overclock.dao.AssemblyDAO;
import com.overclock.overclock.model.Assembly;
import com.overclock.overclock.service.*;
import com.overclock.overclock.util.RequestAssembly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
@Scope("singleton")
public class AssemblyServiceImpl implements AssemblyService {
    @Autowired
    private AssemblyDAO assemblyDAO;
    private ValidationServiceImpl compatibilityService;
    private CommentService commentService;
    private UserService userService;
    private TestService testService;

    @Override
    public Assembly getAssemblyById(BigInteger id) {
        return null;
    }

    @Override
    public Assembly getAssembliesByIdWithSomeComments(BigInteger id, int limit) {
        return null;
    }

    @Override
    public List<Assembly> getAssembliesByAuthor(String author) {
        return null;
    }

    @Override
    public List<Assembly> search(String searchParameter) {
        return null;
    }

    @Override
    public List<Assembly> getAll() {
        return assemblyDAO.getAll();
    }

    @Override
    public boolean save(RequestAssembly assembly) {
        return false;
    }

    @Override
    public boolean delete(BigInteger id) {
        return false;
    }

    @Override
    public boolean updateScore(BigInteger id) {
        return false;
    }

    @Override
    public boolean validate(RequestAssembly assembly) {
        return false;
    }
}
