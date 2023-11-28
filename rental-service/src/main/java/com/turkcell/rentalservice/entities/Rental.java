package com.turkcell.rentalservice.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rentals")
public class Rental {

    @Id
    @Column(name = "car_id")
    private String carId;

    @Column(name = "customer_id")
    private int customerId;

    @Column(name = "car_status")
    private String carStatus;
}