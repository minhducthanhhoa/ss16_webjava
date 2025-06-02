package com.data.sesson16_webjava.service;

import java.util.List;
import com.data.sesson16_webjava.model.Bus;
import com.data.sesson16_webjava.repository.BusRepository;

public class BusServiceImpl implements BusService {
    private final BusRepository busRepository = new BusRepository();

    @Override
    public List<Bus> getAll() {
        return busRepository.findAll();
    }

    @Override
    public void save(Bus bus) {
        busRepository.save(bus);
    }

    @Override
    public void update(Bus bus) {
        busRepository.update(bus);
    }

    @Override
    public void delete(int id) {
        busRepository.delete(id);
    }

    @Override
    public Bus findById(int id) {
        return busRepository.findById(id);
    }
}
