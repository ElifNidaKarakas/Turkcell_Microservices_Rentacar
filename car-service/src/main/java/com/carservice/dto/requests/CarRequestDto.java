package com.carservice.dto.requests;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarRequestDto {
    private String name;
    private String model;
    private String brand;
    private String color;
    private String date;
    private double dailyPrice;
    private String image;
}
