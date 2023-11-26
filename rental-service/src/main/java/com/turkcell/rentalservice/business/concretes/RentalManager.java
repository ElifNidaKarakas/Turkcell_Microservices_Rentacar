package com.turkcell.rentalservice.business.concretes;

import com.google.common.net.HttpHeaders;
import com.turkcell.rentalservice.business.abstracts.RentalService;
import com.turkcell.rentalservice.dto.responses.CarResponseDto;
import com.turkcell.rentalservice.entities.Rental;
import com.turkcell.rentalservice.repositories.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RentalManager implements RentalService {

    private final RentalRepository rentalRepository;
    private final WebClient.Builder webClientBuilder;
    private final ModelMapper modelMapper;

    @Override
    public String getCarStatus(String carId) {
        Rental rental = rentalRepository.findByCarId(carId);
        System.out.println("durum:" + rental.getCarStatus());
        return rental.getCarStatus();
    }

    @Override
    public String getRentACar(CarResponseDto carInfo, Integer customerReminder) {

        if(carInfo.getCarStatus() && customerReminder > carInfo.getDailyPrice()){
            carStatusUpdate(carInfo);
            addCarStatusDescription(carInfo.getId(),"Araç kirada.");
            return "Araç kiralama işlemi gerçekleştirildi."; // ToDo: Kafka düzeldiğinde güncellenecek.
        }
        else{
            Rental rental = rentalRepository.findByCarId(carInfo.getId());
            return rental.getCarStatus();
        }
    }

    @Override
    public void setCarStatusDescription(String carId, String message) { // ToDo: Admin car-status girişi düzenlenecek. Araç kiradan dönme işlemi yazılacak...
       Rental rental =  rentalRepository.findByCarId(carId);
       rental.setCarStatus(message);
       rentalRepository.save(rental);
    }

    @Override
    public void addCarStatusDescription(String carId, String message) {
        Rental rental = new Rental();
        rental.setCarId(carId);
        rental.setCarStatus(message);
        rentalRepository.save(rental);
    }

    public void carStatusUpdate(CarResponseDto carInfo){

        carInfo.setCarStatus(!carInfo.getCarStatus());
        System.out.println("CarResponseDto [requestObject]: " + carInfo.getCarStatus());

        webClientBuilder.build()
                .put()
                .uri("http://car-service/api/v1/cars/carUpdate",
                        (uriBuilder) -> uriBuilder
                                .queryParam("id",carInfo.getId())
                                .build())
                .body(Mono.just(carInfo), CarResponseDto.class)
                .retrieve()
                .bodyToMono(CarResponseDto.class)
                .block();
    }
}
