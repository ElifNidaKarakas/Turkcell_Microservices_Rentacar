package com.turkcell.customerservice.business.abstracts;

import com.turkcell.customerservice.entities.Customer;
import com.turkcell.customerservice.entities.dtos.Customer.CustomerForAddDto;

import java.util.Optional;

public interface CustomerService {

    void addCustomer(CustomerForAddDto request);

    Optional<Customer> getById(int Id);
}
