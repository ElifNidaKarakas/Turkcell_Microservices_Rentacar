package com.turkcell.rentalservice.controllers;

import com.google.common.net.HttpHeaders;
import com.turkcell.rentalservice.business.abstracts.RentalService;
import com.turkcell.rentalservice.dto.responses.CarResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequestMapping("/api/v1/rentals")
@RestController
@RequiredArgsConstructor
public class RentalController {

    private final RentalService rentalService;
    private final KafkaTemplate<String,String> kafkaTemplate;
    private final WebClient.Builder webClientBuilder;

    @GetMapping("car-status")
    public String getCarStatus(@RequestParam String carId,
                               @RequestParam String name) {
        return rentalService.getCarStatus(carId);
    }

    @PostMapping("set-car-status")
    public void setCarStatus(@RequestParam String carId,
                             @RequestParam String message){

        rentalService.setCarStatusDescription(carId,message);
    }

    @GetMapping("rent-a-car")
    public String rentACar(@RequestParam String carId,
                           @RequestParam int customerId) {

        Integer customerReminder = webClientBuilder.build()
                .get()
                .uri("http://customer-service/api/v1/customers/getRemainder",
                        (uriBuilder) -> uriBuilder
                                .queryParam("customerId",customerId)
                                .build())
                .retrieve()
                .bodyToMono(Integer.class)
                .block();

        CarResponseDto carInfo = webClientBuilder.build()
                .get()
                .uri("http://car-service/api/v1/cars/getByCarId",
                        (uriBuilder) -> uriBuilder
                                .queryParam("id",carId)
                                .build())
                .retrieve()
                .bodyToMono(CarResponseDto.class)
                .block();

        //kafkaTemplate.send("notificationTopic","Mail üzerinden araç kiralama bilgileri gönderildi.");
        return rentalService.getRentACar(carInfo,customerReminder);
    }

}
