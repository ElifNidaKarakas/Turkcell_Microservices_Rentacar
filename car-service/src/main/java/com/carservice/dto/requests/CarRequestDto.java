package com.carservice.dto.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarRequestDto {
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

}