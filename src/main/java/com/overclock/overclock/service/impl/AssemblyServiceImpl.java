package com.overclock.overclock.service.impl;

import com.overclock.overclock.dao.AssemblyDAO;
import com.overclock.overclock.model.Assembly;
import com.overclock.overclock.model.Comment;
import com.overclock.overclock.service.*;
import com.overclock.overclock.util.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
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
            Assembly assemblyWithSomeComments = new Assembly.Builder(id, assembly.getName())
                    .setMotherboard(assembly.getMotherboard())
                    .setComments(commentList)
                    .setCpu(assembly.getCpu())
                    .setGpu(assembly.getGpu())
                    .setRam(assembly.getRam())
                    .setAuthor(assembly.getAuthor())
                    .setOverclock(assembly.getOverclock())
                    .setScore(assembly.getScore())
                    .build();
            return assemblyWithSomeComments;
        }
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
        if (іsValidAssembly(assembly)) {
            return assemblyDAO.save(assembly);
        }
        return false;
    }

    @Override
    public boolean delete(BigInteger id) {
        return assemblyDAO.delete(id);
    }

    @Override
    public boolean updateScore(BigInteger id, BigInteger newScore) {
        return assemblyDAO.updateScore(id, newScore);
    }

    @Override
    public boolean іsValidAssembly(Assembly assembly) {
        if (assembly == null) {
            LOGGER.log(Level.WARNING, "Assembly is null");
            return false;
        } else {
            //boolean isValidMotherboard = validationService.isValidMotherboard(assembly.getMotherboard());
            //boolean isValidCPU = validationService.isValidCPU(assembly.getCpu());
            //boolean isValidGPU = validationService.isValidGPU(assembly.getGpu());
            //boolean checkCompatibility = validationService.checkCompatibility(assembly);
            //boolean isCompatibleMotherboardAndCPU = validationService.isCompatibleMotherboardAndCPU(assembly.getMotherboard(), assembly.getCpu());
            //return isValidMotherboard && isValidCPU && isValidGPU && checkCompatibility && isCompatibleMotherboardAndCPU;
            try {
                validationService.isValidMotherboard(assembly.getMotherboard());
                validationService.isValidCPU(assembly.getCpu());
                validationService.isValidGPU(assembly.getGpu());
                validationService.checkCompatibility(assembly);
                validationService.isCompatibleMotherboardAndCPU(assembly.getMotherboard(), assembly.getCpu());
                return true;
            } catch (ValidationException e) {
                return false;
            }
        }
    }
}