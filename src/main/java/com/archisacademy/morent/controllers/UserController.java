package com.archisacademy.morent.controllers;

import com.archisacademy.morent.ApiResponse.ApiResponse;
import com.archisacademy.morent.dtos.auth.LoginRequest;
import com.archisacademy.morent.dtos.auth.LoginResponse;
import com.archisacademy.morent.dtos.auth.RegisterRequest;
import com.archisacademy.morent.dtos.auth.RegisterResponse;
import com.archisacademy.morent.dtos.requests.CreateUserRequest;
import com.archisacademy.morent.services.concretes.AuthServiceImpl;
import com.archisacademy.morent.services.concretes.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;
    private final AuthServiceImpl authService;


    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody CreateUserRequest createUserRequest) {
        ApiResponse save = userService.save(createUserRequest);
        return new ResponseEntity<>(save, HttpStatus.OK);
    }


    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request) {
        RegisterResponse response = authService.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }



}