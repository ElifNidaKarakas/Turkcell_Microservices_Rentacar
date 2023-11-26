package com.turkcell.customerservice.business.concretes;

import com.turkcell.customerservice.business.abstracts.CustomerService;
import com.turkcell.customerservice.entities.Customer;
import com.turkcell.customerservice.entities.dtos.Customer.CustomerForAddDto;
import com.turkcell.customerservice.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerManager implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public void addCustomer(CustomerForAddDto request) {
        Customer customerFromAutoMapping = modelMapper.map(request, Customer.class);
        customerRepository.save(customerFromAutoMapping);
    }

    @Override
    public Optional<Customer> getById(int id) {
        return customerRepository.findById(id);
    }

    @Override
    public void deleteCustomer(int id) {
        customerRepository.deleteById(id);
    }

    @Override
    public void updateCustomer(int id, Customer customer) {
        Customer customerFromAutoMapping = modelMapper.map(customer, Customer.class);
        customerRepository.save(customerFromAutoMapping);
    }
}
