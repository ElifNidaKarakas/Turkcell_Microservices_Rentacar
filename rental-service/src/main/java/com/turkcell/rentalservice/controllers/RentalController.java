package com.turkcell.rentalservice.controllers;

import com.turkcell.rentalservice.business.abstracts.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/rentals")
@RestController
@RequiredArgsConstructor
public class RentalController {

    private final RentalService rentalService;
    private final KafkaTemplate<String,String> kafkaTemplate;

    @GetMapping("car-status")
    public String getCarStatus(@RequestParam String carId,
                               @RequestParam String name) {
        return rentalService.getCarStatus(carId);
    }

    @GetMapping("rent-a-car")
    public String rentACar(@RequestParam String carId) {
        kafkaTemplate.send("notificationTopic","Mail üzerinden araç kiralama bilgileri gönderildi.");
        return rentalService.getRentACar(carId);
    }

}
