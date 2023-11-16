package com.carservice.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(value="cars")
public class Car {
    @Id
    private String id;
    private String name;
    private String model;
    private String brand;
    private String color;
    private String date;
    private double dailyPrice;
    private String image;
}