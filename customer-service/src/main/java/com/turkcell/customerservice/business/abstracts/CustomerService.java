package com.turkcell.customerservice.business.abstracts;

import com.turkcell.customerservice.entities.dtos.Customer.CustomerForAddDto;

public interface CustomerService {

    void addCustomer(CustomerForAddDto request);
}
