package com.turkcell.rentalservice.controllers;

import com.turkcell.rentalservice.business.abstracts.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/rentals")
@RestController
@RequiredArgsConstructor
public class RentalController {

    private final RentalService rentalService;

    @GetMapping("car-status")
    public ResponseEntity<String> getCarStatus(@RequestParam String carId) {
        return new ResponseEntity<>(rentalService.getCarStatus(carId),
                rentalService.getCarStatus(carId) != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @PostMapping("set-car-status")
    public void setCarStatus(@RequestParam String carId,
                             @RequestParam String message,
                             @RequestParam Integer customerId) {
        rentalService.addCarStatusDescription(carId, message, customerId);
    }

    @GetMapping("rent-a-car")
    public String rentACar(@RequestParam String carId,
                           @RequestParam int customerId) {
        return rentalService.getRentACar(carId, customerId);
    }

    @GetMapping("delivery-a-car")
    public String deliveryACar(@RequestParam String carId) {
        return rentalService.getDeliveryACar(carId);
    }

    @DeleteMapping("/{carId}")
    public void deleteCarStatus(@PathVariable String carId) {
        rentalService.deleteCarStatusDescription(carId);
    }

}