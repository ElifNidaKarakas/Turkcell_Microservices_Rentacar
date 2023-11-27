package com.turkcell.rentalservice.business.abstracts;

import com.turkcell.rentalservice.dto.responses.CarResponseDto;
import com.turkcell.rentalservice.dto.responses.CustomerResponseDto;

public interface RentalService {

    String getCarStatus(String carId);

    String getRentACar(CarResponseDto carInfo, CustomerResponseDto customerInfo);
    String getDeliveryACar(CarResponseDto carInfo);
    void deleteCarStatusDescription(String carId);
    void addCarStatusDescription(String carId, String message, Integer customerId);
}
