package com.carservice.controllers;

import com.carservice.business.abstracts.CarService;
import com.carservice.dto.requests.CarInfoDto;
import com.carservice.dto.requests.CarRequestDto;
import com.carservice.entities.Car;
import com.carservice.repositories.CarRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/cars")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;
    private final WebClient.Builder webClientBuilder;
    @GetMapping
    public List<Car> getAllCars() {
        List<Car> carInDb = carService.getAll();
        return carInDb;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable String id){
        carService.deleteCar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PostMapping("/AddCar")
    public ResponseEntity<Car> addCar(@RequestBody Car car){
        Car addCar = carService.add(car);
        return ResponseEntity.status(HttpStatus.OK).body(addCar);
    }
    @GetMapping("/{id}")
        public Optional<Car> getByCarId(@PathVariable String id) {
        return carService.getById(id);
    }

  /*  @PutMapping("updateCar/{id}")
    public ResponseEntity updateCar(@PathVariable("id") String id, @RequestBody @Valid Car car) {
        carService.updateCar(id, car);

        return new ResponseEntity(" Araba güncellendi", HttpStatus.OK);
    }/*

   */
  @PutMapping("/cars/{id}")
  public void updateCar(@PathVariable String id, @RequestBody Car car) {
      carService.updateCar(id, car);
  }

    @PostMapping
    public String submitOrder(@RequestBody CarInfoDto request)
    {
        // Web istekleri default async
        // sync
         return webClientBuilder.build()
                .get()
                .uri("http://rental-service/api/v1/rentals/car-status",
                        (uriBuilder) -> uriBuilder
                                .queryParam("carId",request.getId())
                                .queryParam("name",request.getName())
                                .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();
        // senkron
       // return new ResponseEntity<>(carStatus , carStatus ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

}
