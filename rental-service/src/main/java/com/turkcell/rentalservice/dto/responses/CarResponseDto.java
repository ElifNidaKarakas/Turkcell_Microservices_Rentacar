package com.turkcell.rentalservice.dto.responses;

import jakarta.persistence.Entity;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarResponseDto {
    private String id;
    private String name;
    private String model;
    private String brand;
    private String color;
    private String date;
    private double dailyPrice;
    private Boolean carStatus;
}
