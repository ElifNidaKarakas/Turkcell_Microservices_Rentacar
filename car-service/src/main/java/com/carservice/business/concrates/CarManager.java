package com.carservice.business.concrates;

import com.carservice.business.abstracts.CarService;
import com.carservice.entities.Car;
import com.carservice.repositories.CarRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CarManager implements CarService {


    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    //  private final MessageSource messageSource;

    @Override
    public List<Car> getAll() {
        return carRepository.findAll();
    }

    @Override
    public void deleteCar(String id) {
        carRepository.deleteById(id);
    }

    @Override
    public Car add(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Optional<Car> getById(String id) {
        return carRepository.findById(id);
    }

    @Override
    public void updateCar(String id, Car car) {
        Car carFromAutoMapping = modelMapper.map(car, Car.class);
        carRepository.save(carFromAutoMapping);
    }


}
