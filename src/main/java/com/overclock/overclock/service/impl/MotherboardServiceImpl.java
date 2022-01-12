package com.overclock.overclock.service.impl;

import com.overclock.overclock.dao.MotherboardDAO;
import com.overclock.overclock.model.Motherboard;
import com.overclock.overclock.model.enums.Chipset;
import com.overclock.overclock.model.enums.ChipsetManufacturer;
import com.overclock.overclock.model.enums.MotherboardSocket;
import com.overclock.overclock.service.MotherboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Scope("singleton")
public class MotherboardServiceImpl implements MotherboardService {
    @Autowired
    private MotherboardDAO motherboardDAO;

    @Override
    public Motherboard getMotherboardById(BigInteger id) {
        return motherboardDAO.getMotherboardById(id);
    }

    @Override
    public List<Motherboard> getAllMotherboards() {
        return motherboardDAO.getAllMotherboards();
    }

    @Override
    public boolean save(Motherboard motherboard) {
        return motherboardDAO.save(motherboard);
    }

    @Override
    public boolean updateById(BigInteger id, Motherboard newMotherboard) {
        return motherboardDAO.update(id, newMotherboard);
    }

    @Override
    public boolean delete(BigInteger id) {
        return motherboardDAO.delete(id);
    }

    @Override
    public List<String> getMotherboardChipsetManufacturers() {
        List<String> chipsetManufacturers = new ArrayList<>();
        for (ChipsetManufacturer manufacturer : ChipsetManufacturer.values()) {
            chipsetManufacturers.add(manufacturer.name());
        }
        return chipsetManufacturers;
    }

    @Override
    public List<String> getMotherboardSockets(String chipsetManufacturer) {
        List<String> motherboardsSockets = new ArrayList<>();
        if (chipsetManufacturer.equals(ChipsetManufacturer.Intel.toString())) {
            motherboardsSockets.add(MotherboardSocket.Soc1151.toString());
            motherboardsSockets.add(MotherboardSocket.Soc1200.toString());
        } else {
            motherboardsSockets.add(MotherboardSocket.AM4.toString());
        }
        return motherboardsSockets;
    }

    @Override
    public List<String> getMotherboardChipsets(String socket) {
        List<String> motherboardChipsets = new ArrayList<>();
        if (socket.equals(MotherboardSocket.Soc1151.toString())) {
            for (int i = Chipset.H310.toInt(); i < Chipset.H410.toInt(); i++) {
                motherboardChipsets.add(Chipset.fromInt(i).toString());
            }
        } else if (socket.equals(MotherboardSocket.Soc1200.toString())) {
            for (int i = Chipset.H410.toInt(); i < Chipset.A320.toInt(); i++) {
                motherboardChipsets.add(Chipset.fromInt(i).toString());
            }
        } else if (socket.equals(MotherboardSocket.AM4.toString())) {
            for (int i = Chipset.A320.toInt(); i < Chipset.X570.toInt() + 1; i++) {
                motherboardChipsets.add(Chipset.fromInt(i).toString());
            }
        }
        return motherboardChipsets;
    }

    @Override
    public List<Motherboard> getMotherboardsByName(String name) {
        List<Motherboard> motherboards = new ArrayList<>();
        Pattern pattern = Pattern.compile("(" + name + ")+", Pattern.CASE_INSENSITIVE);
        for (Motherboard motherboard : getAllMotherboards()) {
            Matcher matcher = pattern.matcher(motherboard.getName());
            if (matcher.find()) {
                motherboards.add(motherboard);
            }
        }
        return motherboards;
    }
}