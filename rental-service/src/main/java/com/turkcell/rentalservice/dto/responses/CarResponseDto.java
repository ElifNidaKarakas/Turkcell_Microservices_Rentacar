package com.turkcell.rentalservice.dto.responses;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarResponseDto {

    private String id;

    @NotBlank(message = "NameNotNull")
    private String name;

    @NotBlank(message = "ModelNotNull")
    private String model;

    @NotBlank(message = "brandNotNull")
    private String brand;

    @NotBlank(message = "colorNotNull")
    private String color;

    @NotBlank(message = "NotNull")
    private String date;

    @NotBlank(message = "NotNull")
    private double dailyPrice;

    private Boolean carStatus;

}