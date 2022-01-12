package com.overclock.overclock.service.impl;

import com.overclock.overclock.dao.RamDAO;
import com.overclock.overclock.model.RAM;
import com.overclock.overclock.service.RAMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Scope("singleton")
public class RAMServiceImpl implements RAMService {

    private RamDAO ramDAO;

    @Autowired
    public void setRamDAO(RamDAO ramDAO) {
        this.ramDAO = ramDAO;
    }

    @Override
    public RAM getById(BigInteger id) {
        return ramDAO.getRamById(id);
    }

    @Override
    public List<RAM> getAll() {
        return ramDAO.getAllRams();
    }

    @Override
    public boolean save(RAM ram) {
        return ramDAO.save(ram);
    }

    @Override
    public boolean updateById(BigInteger id, RAM newRam) {
        return ramDAO.update(id, newRam);
    }

    @Override
    public boolean delete(BigInteger id) {
        return ramDAO.delete(id);
    }

    @Override
    public List<RAM> getRAMsByName(String name) {
        List<RAM> rams = new ArrayList<>();
        Pattern pattern = Pattern.compile("(" + name + ")+", Pattern.CASE_INSENSITIVE);
        for (RAM ram : getAll()) {
            Matcher matcher = pattern.matcher(ram.getName());
            if (matcher.find()) {
                rams.add(ram);
            }
        }
        return rams;
    }
}