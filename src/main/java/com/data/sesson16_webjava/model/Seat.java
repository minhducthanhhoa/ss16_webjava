package com.data.sesson16_webjava.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Seat {
    private int id;
    private String nameSeat;
    private double price;
    private int busId;
    private boolean status;
}
