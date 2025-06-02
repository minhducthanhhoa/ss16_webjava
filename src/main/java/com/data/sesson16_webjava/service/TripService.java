package com.data.sesson16_webjava.service;

import com.data.sesson16_webjava.model.Trip;

import java.util.List;

public interface TripService {
    List<Trip> searchTrips(String departure, String destination, int page);
    int countTrips(String departure, String destination);
}
