package com.overclock.overclock.service.impl;

import com.overclock.overclock.dao.AssemblyDAO;
import com.overclock.overclock.model.Assembly;
import com.overclock.overclock.model.Comment;
import com.overclock.overclock.security.UserDetailsImpl;
import com.overclock.overclock.service.*;
import com.overclock.overclock.util.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@Scope("singleton")
public class AssemblyServiceImpl implements AssemblyService {
    @Autowired
    private AssemblyDAO assemblyDAO;
    @Autowired
    private ValidationService validationService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;

    private static final Logger LOGGER = Logger.getLogger(AssemblyServiceImpl.class.getName());

    @Override
    public Assembly getAssemblyById(BigInteger id) {
        return assemblyDAO.getAssemblyById(id);
    }

    @Override
    public Assembly getAssemblyByIdWithSomeComments(BigInteger id, BigInteger limit) {
        if (id == null) {
            LOGGER.log(Level.WARNING, "id of assembly is null");
            return null;
        }
        List<Comment> commentList = commentService.getLimitedListOfCommentsByAssemblyId(id, limit);
        Assembly assembly = assemblyDAO.getAssemblyById(id);
        if (assembly == null) {
            LOGGER.log(Level.WARNING, "There are no assembly with such id");
            return null;
        } else {
            return new Assembly.Builder(id, assembly.getName())
                    .setMotherboard(assembly.getMotherboard())
                    .setComments(commentList)
                    .setCpu(assembly.getCpu())
                    .setGpu(assembly.getGpu())
                    .setRam(assembly.getRam())
                    .setAuthor(assembly.getAuthor())
                    .setOverclock(assembly.getOverclock())
                    .setScore(assembly.getScore())
                    .build();
        }
    }

    @Override
    public List<Assembly> getAssembliesByAuthorId(BigInteger id) {
        if (id == null) {
            LOGGER.log(Level.WARNING, "Author id is null");
            return null;
        }
        return assemblyDAO.getAssembliesByAuthorId(id);
    }

    @Override
    public List<Assembly> getAssembliesByAuthorName(String author) {
        if (author == null) {
            LOGGER.log(Level.WARNING, "Name of autor is null");
            return null;
        }
        return assemblyDAO.getAssembliesByAuthorName(author);
    }

    @Override
    public List<Assembly> search(String searchParameter) {
        if (searchParameter == null) {
            LOGGER.log(Level.WARNING, "Invalid search parameter");
            return null;
        }
        List<Assembly> assemblies = assemblyDAO.getAllAssemblies();
        String searchParameterLC = searchParameter.toLowerCase();
        assemblies.removeIf(assembly -> {
            String username = userService.getWithMainInformation(assembly.getAuthor()).getUserName().toLowerCase();
            String assemblyName = assembly.getName().toLowerCase();
            return !username.contains(searchParameterLC) && !assemblyName.contains(searchParameterLC);
        });
        return assemblies;
    }

    @Override
    public List<Assembly> getAllAssemblies() {
        return assemblyDAO.getAllAssemblies();
    }

    @Override
    public boolean save(Assembly assembly) {
        checkAssemblyValidation(assembly);
        return assemblyDAO.save(assembly);
    }

    @Override
    public boolean delete(BigInteger id) {
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (Objects.equals(user.getId(), getAssemblyById(id).getAuthor())) {
            return assemblyDAO.delete(id);
        }
        LOGGER.log(Level.WARNING, "Non-user assembly");
        return false;
    }

    @Override
    public boolean updateScore(BigInteger id, BigInteger newScore) {
        return assemblyDAO.updateScore(id, newScore);
    }

    @Override
    public void checkAssemblyValidation(Assembly assembly) {
        if (assembly == null) {
            LOGGER.log(Level.WARNING, "Assembly is null");
            throw new ValidationException("Assembly is null");
        }
        validationService.isValidMotherboard(assembly.getMotherboard());
        validationService.isValidCPU(assembly.getCpu());
        validationService.isValidGPU(assembly.getGpu());
        validationService.checkCompatibility(assembly);
    }
}