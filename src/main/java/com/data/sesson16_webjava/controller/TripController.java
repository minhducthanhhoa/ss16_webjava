package com.data.sesson16_webjava.controller;

import com.data.sesson16_webjava.model.Trip;
import com.data.sesson16_webjava.service.TripService;
import com.data.sesson16_webjava.service.TripServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/home")
public class TripController {
    private final TripService tripService = new TripServiceImpl();

    @GetMapping
    public String viewHome(
            @RequestParam(defaultValue = "") String departure,
            @RequestParam(defaultValue = "") String destination,
            @RequestParam(defaultValue = "1") int page,
            Model model) {

        List<Trip> trips = tripService.searchTrips(departure, destination, page);
        int totalTrips = tripService.countTrips(departure, destination);
        int totalPages = (int) Math.ceil((double) totalTrips / 5);

        model.addAttribute("trips", trips);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("departure", departure);
        model.addAttribute("destination", destination);

        return "home";
    }
}
