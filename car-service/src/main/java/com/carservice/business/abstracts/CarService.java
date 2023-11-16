package com.carservice.business.abstracts;

import com.carservice.dto.requests.CarRequestDto;
import com.carservice.dto.responses.CarResponseDto;
import com.carservice.entities.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {

   /* List<CarForListingDto> getAll();

    List<CarForListingIdDto> getById(Long Id);

    void addCar(CarForAddDto request);

    void updateCar(Long Id, CarForUpdateDto car);

    void deleteCar(Long Id);


     List<CarRequestDto> getById(Long Id);

    CarRequestDto update(Long Id, CarRequestDto car);

    void deleteCar(Long Id);

        CarResponseDto add(CarRequestDto request);
    */


    List<Car> getAll();

    void deleteCar(String Id);

    Car add(Car car);

    Optional<Car> getById(String Id);

    void updateCar(String id, Car car);







}
