package com.carservice.repositories;

import com.carservice.entities.CarImages;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CarImagesRepository extends MongoRepository<CarImages, Integer> {

}