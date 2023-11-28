package com.turkcell.customerservice.business.abstracts;

import com.turkcell.customerservice.entities.Customer;
import com.turkcell.customerservice.entities.dtos.Customer.CustomerForAddDto;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    List<Customer> getAll();

    void addCustomer(CustomerForAddDto request);

    Optional<Customer> getById(int id);

    void deleteCustomer(int id );

    void updateCustomer(int id, Customer customer);

}