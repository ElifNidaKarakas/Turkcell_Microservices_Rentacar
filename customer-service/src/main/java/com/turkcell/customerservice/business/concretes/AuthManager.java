package com.turkcell.customerservice.business.concretes;

import com.turkcell.customerservice.business.abstracts.AuthService;
import com.turkcell.customerservice.core.jwt.JwtService;
import com.turkcell.customerservice.entities.Customer;
import com.turkcell.customerservice.entities.dtos.auth.AuthenticationResponse;
import com.turkcell.customerservice.entities.dtos.auth.LoginRequest;
import com.turkcell.customerservice.entities.dtos.auth.RegisterRequest;
import com.turkcell.customerservice.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthManager implements AuthService {

    private final JwtService jwtService;//giriş yapmış ve kayıtlı bir kullanıcıya jwt döneceğiz
    //private final AuthenticationManager authenticationManager;
    private final CustomerRepository customerRepository;

    @Override
   public AuthenticationResponse register(RegisterRequest request) {
       //validasyon ve iş kuralları daha sonra eklenecek
       Customer customer = Customer.builder() //rolünü de buradan atayacağız
               .name(request.getName())
               .surname(request.getSurname())
               .role("USER")
               .phone(request.getPhone())
               .email(request.getEmail())
        .build();

       customerRepository.save(customer);
       String token = jwtService.generateToken(customer);
       return new AuthenticationResponse(token);
   }

    @Override
    public AuthenticationResponse login(LoginRequest request) {
        //authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getName(), request.getEmail()));
        Customer customer = customerRepository.findByName(request.getName()).orElseThrow(() -> new RuntimeException("Customer not found"));
        String token = jwtService.generateToken(customer);
        return new AuthenticationResponse(token);
    }
}