package com.turkcell.customerservice.controllers;

import com.turkcell.customerservice.business.abstracts.CustomerService;
import com.turkcell.customerservice.entities.Customer;
import com.turkcell.customerservice.entities.dtos.Customer.CustomerForAddDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerServices;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerServices.getAll();
    }

    @DeleteMapping
    public void deleteCustomers(int id) {
        customerServices.deleteCustomer(id);
    }

    @PostMapping("add")
    public String add(@RequestBody @Valid CustomerForAddDto customer) {
        customerServices.addCustomer(customer);
        return (customer.getName() + " " + "isimli müşteri eklendi");
    }

    @GetMapping("getRemainder")
    public Integer getRemainder(@RequestParam @Valid int customerId) {
        Optional<Customer> customer = customerServices.getById(customerId);
        return customer.get().getRemainder();
    }

    @PutMapping("customerUpdate")
    public void updateCustomer(@RequestParam int id, @RequestBody @Valid Customer customer) {
        customerServices.updateCustomer(id, customer);
    }

    @GetMapping("getByCustomerId")
    public Optional<Customer> getByCustomerId(@RequestParam int id) {

        return customerServices.getById(id);
    }
}