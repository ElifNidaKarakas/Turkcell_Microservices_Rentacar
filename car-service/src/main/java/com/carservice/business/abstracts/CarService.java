package com.carservice.business.abstracts;

import com.carservice.entities.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {

    List<Car> getAll();

    void deleteCar(String Id);

    Car add(Car car);

    Optional<Car> getById(String Id);

    void updateCar(String id, Car car);

}
