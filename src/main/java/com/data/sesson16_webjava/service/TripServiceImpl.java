package com.data.sesson16_webjava.service;

import com.data.sesson16_webjava.model.Trip;
import com.data.sesson16_webjava.repository.TripRepository;

import java.util.List;

public class TripServiceImpl implements TripService {
    private final TripRepository tripRepository = new TripRepository();

    @Override
    public List<Trip> searchTrips(String departure, String destination, int page) {
        if (departure == null) departure = "";
        if (destination == null) destination = "";
        return tripRepository.findTrips(departure.trim(), destination.trim(), page);
    }

    @Override
    public int countTrips(String departure, String destination) {
        if (departure == null) departure = "";
        if (destination == null) destination = "";
        return tripRepository.countTrips(departure.trim(), destination.trim());
    }
}
