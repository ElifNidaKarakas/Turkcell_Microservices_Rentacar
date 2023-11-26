package com.carservice.business.abstracts;

import com.carservice.entities.Car;
import com.carservice.entities.CarImages;

import java.util.List;
import java.util.Optional;

public interface CarService {

    List<Car> getAll();

    void deleteCar(String Id);

    Car add(Car car);

    CarImages addCarImages(List<CarImages> carImages);

    Optional<Car> getById(String Id);

    void updateCar(String id, Car car);

}
