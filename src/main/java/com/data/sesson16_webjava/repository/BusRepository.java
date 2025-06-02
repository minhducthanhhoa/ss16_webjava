package com.data.sesson16_webjava.repository;

import com.data.sesson16_webjava.model.Bus;
import com.data.sesson16_webjava.utils.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BusRepository {
    public List<Bus> findAll() {
        List<Bus> list = new ArrayList<>();
        String sql = "SELECT * FROM bus";
        try (Connection conn = ConnectionDB.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Bus b = new Bus();
                b.setId(rs.getInt("id"));
                b.setLicensePlate(rs.getString("licensePlate"));
                b.setBusType(rs.getString("busType"));
                b.setRowSeat(rs.getInt("rowSeat"));
                b.setColSeat(rs.getInt("colSeat"));
                b.setTotalSeat(rs.getInt("totalSeat"));
                b.setImage(rs.getString("image"));
                list.add(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void save(Bus bus) {
        String sql = "INSERT INTO bus(licensePlate, busType, rowSeat, colSeat, totalSeat, image) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionDB.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, bus.getLicensePlate());
            ps.setString(2, bus.getBusType());
            ps.setInt(3, bus.getRowSeat());
            ps.setInt(4, bus.getColSeat());
            ps.setInt(5, bus.getRowSeat() * bus.getColSeat());
            ps.setString(6, bus.getImage());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                bus.setId(rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM bus WHERE id=?";
        try (Connection conn = ConnectionDB.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Bus findById(int id) {
        String sql = "SELECT * FROM bus WHERE id=?";
        try (Connection conn = ConnectionDB.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Bus b = new Bus();
                b.setId(rs.getInt("id"));
                b.setLicensePlate(rs.getString("licensePlate"));
                b.setBusType(rs.getString("busType"));
                b.setRowSeat(rs.getInt("rowSeat"));
                b.setColSeat(rs.getInt("colSeat"));
                b.setTotalSeat(rs.getInt("totalSeat"));
                b.setImage(rs.getString("image"));
                return b;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Bus bus) {
        String sql = "UPDATE bus SET licensePlate=?, busType=?, rowSeat=?, colSeat=?, totalSeat=?, image=? WHERE id=?";
        try (Connection conn = ConnectionDB.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, bus.getLicensePlate());
            ps.setString(2, bus.getBusType());
            ps.setInt(3, bus.getRowSeat());
            ps.setInt(4, bus.getColSeat());
            ps.setInt(5, bus.getRowSeat() * bus.getColSeat());
            ps.setString(6, bus.getImage());
            ps.setInt(7, bus.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
