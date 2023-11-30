package com.turkcell.customerservice.entities.dtos.Customer;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerForAddDto {
    @Size(min = 3)
    @NotBlank(message = "{NameNotNull}")
    private String name;

    @NotBlank(message = "{NameNotNull}")
    private String surname;

    @Min(value = 1,message = "{phoneLength}")
    private long phone;

    @NotBlank(message = "{NotNull}")
    private String address;

    @NotBlank(message = "{NotNull}")
    @Email(
            message = "{invalidEmail}",
            regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    @NotNull(message = "{NotNull}")
    private int remainder;

}