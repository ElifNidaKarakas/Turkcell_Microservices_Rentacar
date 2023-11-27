package com.turkcell.rentalservice.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseDto {
    private int id;
    private String name;
    private String surname;
    private int phone;
    private String address;
    private String email;
    private int remainder;
}
