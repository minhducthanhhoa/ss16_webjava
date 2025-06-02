package com.data.sesson16_webjava.repository;

import com.data.sesson16_webjava.model.Trip;
import com.data.sesson16_webjava.utils.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TripRepository {
    private static final int PAGE_SIZE = 5;

    public List<Trip> findTrips(String departure, String destination, int page) {
        List<Trip> trips = new ArrayList<>();
        String sql = "SELECT * FROM trip WHERE departure LIKE ? AND destination LIKE ? LIMIT ? OFFSET ?";
        try (Connection conn = ConnectionDB.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + departure + "%");
            stmt.setString(2, "%" + destination + "%");
            stmt.setInt(3, PAGE_SIZE);
            stmt.setInt(4, (page - 1) * PAGE_SIZE);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                trips.add(new Trip(
                        rs.getInt("id"),
                        rs.getString("departure"),
                        rs.getString("destination"),
                        rs.getString("time"),
                        rs.getDouble("price")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trips;
    }

    public int countTrips(String departure, String destination) {
        String sql = "SELECT COUNT(*) FROM trip WHERE departure LIKE ? AND destination LIKE ?";
        try (Connection conn = ConnectionDB.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + departure + "%");
            stmt.setString(2, "%" + destination + "%");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
