package com.turkcell.customerservice.entities.dtos.Customer;


import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerForAddDto {
    @Size(min = 3)
    private String name;

    @NotBlank(message = "Boş bırakılamaz")
    private String surname;

    @Size(min=3 ,max = 11 ,message = "10 karakterden fazla girilemez")
    private long phone;

    @NotBlank(message = "Boş bırakılamaz")
    private String address;

    @NotBlank(message = "Boş bırakılamaz")
    private String email;

    @NotBlank(message = "Boş bırakılamaz")
    private int remainder;

}
