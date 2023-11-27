package com.turkcell.rentalservice.controllers;

import com.google.common.net.HttpHeaders;
import com.turkcell.rentalservice.business.abstracts.RentalService;
import com.turkcell.rentalservice.dto.responses.CarResponseDto;
import com.turkcell.rentalservice.dto.responses.CustomerResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequestMapping("/api/v1/rentals")
@RestController
@RequiredArgsConstructor
public class RentalController {

    private final RentalService rentalService;
    //private final KafkaTemplate<String,String> kafkaTemplate;
    private final WebClient.Builder webClientBuilder;

    @GetMapping("car-status")
    public String getCarStatus(@RequestParam String carId,
                               @RequestParam String name) {
        return rentalService.getCarStatus(carId);
    }

    @PostMapping("set-car-status")
    public void setCarStatus(@RequestParam String carId,
                             @RequestParam String message,
                             @RequestParam Integer customerId
    ){

        rentalService.addCarStatusDescription(carId,message,customerId);
    }

    @GetMapping("rent-a-car")
    public String rentACar(@RequestParam String carId,
                           @RequestParam int customerId) {

        CustomerResponseDto customerInfo=webClientBuilder.build()
                .get()
                .uri("http://customer-service/api/v1/customers/getByCustomerId",
                        (uriBuilder) -> uriBuilder
                                .queryParam("customerId",customerId)
                                .build())
                .retrieve()
                .bodyToMono(CustomerResponseDto.class)
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
        return rentalService.getRentACar(carInfo,customerInfo);
    }

    @GetMapping("delivery-a-car")
    public String deliveryACar(@RequestParam String carId) {

        CarResponseDto carInfo = webClientBuilder.build()
                .get()
                .uri("http://car-service/api/v1/cars/getByCarId",
                        (uriBuilder) -> uriBuilder
                                .queryParam("id",carId)
                                .build())
                .retrieve()
                .bodyToMono(CarResponseDto.class)
                .block();


        //kafkaTemplate.send("notificationTopic",rentalService.getDeliveryACar(carInfo));
        return rentalService.getDeliveryACar(carInfo);
    }
    @DeleteMapping("/{carId}")
    public void deleteCarStatus(@PathVariable String carId){
        rentalService.deleteCarStatusDescription(carId);
    }

}
