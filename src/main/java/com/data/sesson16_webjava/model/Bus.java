package com.data.sesson16_webjava.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Bus {
    private Integer id;

    @NotBlank(message = "Biển số xe không được để trống")
    private String licensePlate;

    @NotBlank(message = "Loại xe không được để trống")
    private String busType; // NORMAL, VIP, LUXURY

    @Min(value = 1, message = "Số hàng ghế phải lớn hơn 0")
    private int rowSeat;

    @Min(value = 1, message = "Số cột ghế phải lớn hơn 0")
    private int colSeat;

    private int totalSeat;

    @NotBlank(message = "Ảnh không được để trống")
    private String image;
}
