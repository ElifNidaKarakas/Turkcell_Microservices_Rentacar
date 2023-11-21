package com.turkcell.customerservice.controllers;

import com.turkcell.customerservice.business.abstracts.CustomerService;
import com.turkcell.customerservice.entities.dtos.Customer.CustomerForAddDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@RequestMapping("customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerServices;
    @PostMapping("add")
    public String add(@RequestBody @Valid CustomerForAddDto customer) {
        customerServices.addCustomer(customer);

        return (customer.getName() +" " + " İsimli Müşteri eklendi");

        //return new ResponseEntity(product.getProductName() + " adlı ürün eklendi.", HttpStatus.CREATED);

    }
}
