package com.data.sesson16_webjava.service;

import com.data.sesson16_webjava.model.Bus;
import java.util.List;

public interface BusService {
    List<Bus> getAll();
    void save(Bus bus);
    void update(Bus bus);
    void delete(int id);
    Bus findById(int id);
}
