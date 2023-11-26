package com.turkcell.rentalservice.business.abstracts;

import com.turkcell.rentalservice.dto.responses.CarResponseDto;

public interface RentalService {

    String getCarStatus(String carId);

    String getRentACar(CarResponseDto carInfo, Integer customerReminder);

}
