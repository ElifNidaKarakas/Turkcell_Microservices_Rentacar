package com.turkcell.rentalservice.controllers;

import com.turkcell.rentalservice.business.abstracts.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/rentals")
@RestController
@RequiredArgsConstructor
public class RentalController {

    private final RentalService rentalService;

    @GetMapping("car-status")
    public String getCarStatus(@RequestParam String carId,
                               @RequestParam String name) {
        return rentalService.getCarStatus(carId);

    }
}
