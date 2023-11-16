package com.turkcell.rentalservice.business.concretes;

import com.turkcell.rentalservice.business.abstracts.RentalService;
import com.turkcell.rentalservice.entities.Rental;
import com.turkcell.rentalservice.repositories.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RentalManager implements RentalService {

    private final RentalRepository rentalRepository;


    @Override
    public String getCarStatus(String carId) {
        Rental rental = rentalRepository.findByCarId(carId);
        System.out.println("durum:"+rental.getCarStatus());
        return rental.getCarStatus();
    }
}
