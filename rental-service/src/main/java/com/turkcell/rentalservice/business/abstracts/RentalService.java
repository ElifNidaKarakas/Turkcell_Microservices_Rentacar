package com.turkcell.rentalservice.business.abstracts;

import com.turkcell.rentalservice.dto.responses.CarResponseDto;
import com.turkcell.rentalservice.dto.responses.CustomerResponseDto;

public interface RentalService {

    String getCarStatus(String carId);

    CarResponseDto getCarInfo(String carId);

    CustomerResponseDto getCustomerInfo(int customerId);

    String getRentACar(String carId, int customerId);

    String getDeliveryACar(String carId);

    void deleteCarStatusDescription(String carId);

    void addCarStatusDescription(String carId, String message, Integer customerId);
}