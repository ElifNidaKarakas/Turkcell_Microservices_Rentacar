package com.turkcell.customerservice.business.concretes;

import com.turkcell.customerservice.business.abstracts.CustomerService;
import com.turkcell.customerservice.entities.Customer;
import com.turkcell.customerservice.entities.dtos.Customer.CustomerForAddDto;
import com.turkcell.customerservice.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerManager implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Override
    public void addCustomer(CustomerForAddDto request) {
        Customer customerFromAutoMapping = modelMapper.map(request, Customer.class);
        customerRepository.save(customerFromAutoMapping);
    }

    @Override
    public Optional<Customer> getById(int id) {
        return customerRepository.findById(id);
    }
}
