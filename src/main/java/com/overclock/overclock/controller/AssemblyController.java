package com.overclock.overclock.controller;

import com.overclock.overclock.model.Assembly;
import com.overclock.overclock.security.UserDetailsImpl;
import com.overclock.overclock.service.AssemblyService;
import com.overclock.overclock.util.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping(value = "/api/assembly")
public class AssemblyController {
    private AssemblyService assemblyService;

    @Autowired
    public void setAssemblyService(AssemblyService assemblyService) {
        this.assemblyService = assemblyService;
    }

    @PostMapping
    public String buildAssembly(@Valid @RequestBody Assembly assembly) {
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        assembly.setAuthor(user.getId());
        try {
            assemblyService.save(assembly);
        } catch (ValidationException exception) {
            System.out.println(exception.getMessage());
            return exception.getMessage();
        }
        return "Сборка успешно создана!";
    }

    @DeleteMapping("/{assembly_id}")
    public ResponseEntity<?> deleteAssembly(@PathVariable("assembly_id") BigInteger id) {
        assemblyService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{assembly_id}")
    public Assembly showAssembly(@PathVariable("assembly_id") BigInteger id) {
        return assemblyService.getAssemblyById(id);
    }

    @GetMapping("/search/{search_parameter}")
    public List<Assembly> search(@PathVariable("search_parameter") String search) {
        return assemblyService.search(search);
    }

    @GetMapping("/all")
    public List<Assembly> getAll() {
        return assemblyService.getAllAssemblies();
    }

    @GetMapping("/user_assemblies/{user_id}")
    public List<Assembly> getAllAssembliesByUser(@PathVariable("user_id") BigInteger userId) {
        return assemblyService.getAssembliesByAuthorId(userId);
    }

    @GetMapping("/my_assemblies")
    public List<Assembly> getMyAssemblies() {
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return assemblyService.getAssembliesByAuthorId(user.getId());
    }

    @PostMapping("/{assembly_id}/score")
    public ResponseEntity<?> updateScore(@PathVariable("assembly_id") BigInteger assemblyId, @RequestBody BigDecimal score) {
        assemblyService.updateScore(assemblyId, score);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
