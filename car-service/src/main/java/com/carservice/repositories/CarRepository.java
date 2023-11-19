package com.carservice.repositories;

import com.carservice.entities.Car;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface CarRepository extends MongoRepository<Car, String> {
    @Query("{'inventoryCode': ?0 }")
    Car findByInventoryCodeQuery(String invCode);

}
