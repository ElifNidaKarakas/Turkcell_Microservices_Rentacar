package com.turkcell.customerservice.controllers;


import com.turkcell.customerservice.business.abstracts.AuthService;
import com.turkcell.customerservice.entities.dtos.auth.AuthenticationResponse;
import com.turkcell.customerservice.entities.dtos.auth.LoginRequest;
import com.turkcell.customerservice.entities.dtos.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("login")
    public AuthenticationResponse login(@RequestBody LoginRequest request){
        return authService.login(request);
    }

    @PostMapping("register")
    public AuthenticationResponse register(@RequestBody RegisterRequest request){
        return  authService.register(request);
    }
}