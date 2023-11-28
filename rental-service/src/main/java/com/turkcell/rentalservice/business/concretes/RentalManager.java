package com.turkcell.rentalservice.business.concretes;

import com.turkcell.rentalservice.business.abstracts.RentalService;
import com.turkcell.rentalservice.dto.responses.CarResponseDto;
import com.turkcell.rentalservice.dto.responses.CustomerResponseDto;
import com.turkcell.rentalservice.entities.Rental;
import com.turkcell.rentalservice.repositories.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RentalManager implements RentalService {
    private final RentalRepository rentalRepository;
    private final WebClient.Builder webClientBuilder;
    private final ModelMapper modelMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public String getCarStatus(String carId) {
        if (rentalRepository.findByCarId(carId) == null) {
            return null;
        }
        Rental rental = rentalRepository.findByCarId(carId);
        System.out.println("durum:" + rental.getCarStatus());
        return rental.getCarStatus();
    }

    @Override
    public String getRentACar(String carId, int customerId) {

        CustomerResponseDto customerInfo = getCustomerInfo(customerId);
        CarResponseDto carInfo = getCarInfo(carId);

        if (carInfo.getCarStatus() && customerInfo.getRemainder() > carInfo.getDailyPrice()) {
            addCarStatusDescription(carInfo.getId(), "Araç kirada.", customerInfo.getId());

            customerInfo.setRemainder((int) (customerInfo.getRemainder() - carInfo.getDailyPrice()));
            setCustomerReminder(customerInfo);

            kafkaTemplate.send("notificationTopic", "Mail üzerinden araç kiralama bilgileri gönderildi.");
            return "Araç kiralama işlemi gerçekleştirildi.";
        } else {
            Rental rental = rentalRepository.findByCarId(carInfo.getId());
            kafkaTemplate.send("notificationTopic", rental.getCarStatus());
            return rental.getCarStatus();
        }
    }

    @Override
    public String getDeliveryACar(String carId) {
        CarResponseDto carInfo = getCarInfo(carId);
        Rental rental = rentalRepository.findByCarId(carInfo.getId());

        if (!carInfo.getCarStatus() && rental.getCarStatus().equals("Araç kirada.")) {
            deleteCarStatusDescription(carInfo.getId());
            kafkaTemplate.send("notificationTopic", "Mail üzerinden araç teslim bilgileri gönderildi.");
            return "Araç teslim alındı.";
        } else {
            kafkaTemplate.send("notificationTopic", "Araç teslim alınmaya uygun değil.");
            return "Araç teslim alınmaya uygun değil.";
        }
    }

    @Override
    public void deleteCarStatusDescription(String carId) {
        rentalRepository.deleteById(carId);
        carStatusUpdate(getCarInfo(carId));
    }

    @Override
    public void addCarStatusDescription(String carId, String message, Integer customerId) {
        Rental rental = new Rental();
        rental.setCarId(carId);
        rental.setCarStatus(message);
        rental.setCustomerId(customerId);
        rentalRepository.save(rental);
        carStatusUpdate(getCarInfo(carId));
    }

    public void carStatusUpdate(CarResponseDto carInfo) {

        carInfo.setCarStatus(!carInfo.getCarStatus());
        System.out.println("CarResponseDto [requestObject]: " + carInfo.getCarStatus());

        webClientBuilder.build()
                .put()
                .uri("http://car-service/api/v1/cars/carUpdate",
                        (uriBuilder) -> uriBuilder
                                .queryParam("id", carInfo.getId())
                                .build())
                .body(Mono.just(carInfo), CarResponseDto.class)
                .retrieve()
                .bodyToMono(CarResponseDto.class)
                .block();
    }

    public CarResponseDto getCarInfo(String carId) {
        return webClientBuilder.build()
                .get()
                .uri("http://car-service/api/v1/cars/getByCarId",
                        (uriBuilder) -> uriBuilder
                                .queryParam("id", carId)
                                .build())
                .retrieve()
                .bodyToMono(CarResponseDto.class)
                .block();
    }

    public CustomerResponseDto getCustomerInfo(int customerId) {
        return webClientBuilder.build()
                .get()
                .uri("http://customer-service/api/v1/customers/getByCustomerId",
                        (uriBuilder) -> uriBuilder
                                .queryParam("id", customerId)
                                .build())
                .retrieve()
                .bodyToMono(CustomerResponseDto.class)
                .block();
    }

    public void setCustomerReminder(CustomerResponseDto customerInfo) {
        webClientBuilder.build()
                .put()
                .uri("http://customer-service/api/v1/customers/customerUpdate",
                        (uriBuilder) -> uriBuilder
                                .queryParam("id", customerInfo.getId())
                                .build())
                .body(Mono.just(customerInfo), CustomerResponseDto.class)
                .retrieve()
                .bodyToMono(CustomerResponseDto.class)
                .block();
    }
}