package com.turkcell.customerservice.controllers;

import com.turkcell.customerservice.business.abstracts.CustomerService;
import com.turkcell.customerservice.entities.Customer;
import com.turkcell.customerservice.entities.dtos.Customer.CustomerForAddDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

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

        return (customer.getName() +" " + " İsimli Müşteri eklendi");

        //return new ResponseEntity(product.getProductName() + " adlı ürün eklendi.", HttpStatus.CREATED);
    }

    @GetMapping("getRemainder")
    public Integer getRemainder(@RequestParam int customerId) {
        Optional<Customer> customer = customerServices.getById(customerId);
        return customer.get().getRemainder();
    }

    @GetMapping("getByCustomerId")
    public Optional<Customer> getByCustomerId(@RequestParam int id) {
        return customerServices.getById(id);
    }

    @PutMapping("customerUpdate")
    public void updateCustomer(@RequestParam int id, @RequestBody Customer customer) {
        customerServices.updateCustomer(id, customer);
    }
}
