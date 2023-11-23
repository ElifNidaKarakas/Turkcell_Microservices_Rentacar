package com.turkcell.rentalservice.business.abstracts;

public interface RentalService {

    String getCarStatus(String carId);

    String getRentACar(String carId);
}
