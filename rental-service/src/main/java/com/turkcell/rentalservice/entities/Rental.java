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

    @Column(name = "car_status")
    private String carStatus;
}
