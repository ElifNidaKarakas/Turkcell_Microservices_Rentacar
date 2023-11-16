package com.turkcell.rentalservice.repositories;

import com.turkcell.rentalservice.entities.Rental;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental , String> {

    Rental findByCarId(String carId);

}
